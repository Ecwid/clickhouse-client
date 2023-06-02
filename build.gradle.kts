import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	java
	signing
	kotlin("jvm") version "1.6.10"
	id("io.codearte.nexus-staging") version "0.22.0"
	id("nebula.release") version "15.2.0"
	id("maven-publish")
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(kotlin("stdlib-jdk8"))
	implementation(kotlin("reflect"))

	implementation("com.google.code.gson:gson:2.9.0")

	implementation("org.apache.httpcomponents:httpcore:4.4.15")
	implementation("org.apache.httpcomponents:httpclient:4.5.13")

	compileOnly("io.prometheus:simpleclient:0.16.0")

	testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
	testImplementation("org.mockito:mockito-core:2.15.0")
	testImplementation("org.hamcrest:hamcrest-library:1.3")
	testImplementation("org.slf4j:slf4j-simple:1.7.36")
}

configure<JavaPluginConvention> {
	sourceCompatibility = JavaVersion.VERSION_11
}

java {
	withSourcesJar()
	withJavadocJar()
}

// Kotlin settings
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "11"

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions.jvmTarget = "11"

tasks.withType<Test> {
	enableAssertions = true
	useJUnitPlatform()
	testLogging {
		exceptionFormat = TestExceptionFormat.FULL
		events = setOf(TestLogEvent.PASSED, TestLogEvent.STARTED, TestLogEvent.FAILED, TestLogEvent.SKIPPED)
		showStandardStreams = false
	}
}


val settingsProvider = SettingsProvider()

tasks {
	// All checks were already made by workflow "On pull request" => no checks here
	if (gradle.startParameter.taskNames.contains("final")) {
		named("build").get().apply {
			dependsOn.removeIf { it == "check" }
		}
	}

	// Publish artifacts to Maven Central before pushing new git tag to repo
	named("release").get().apply {
		dependsOn(named("publish").get())
	}
}


tasks.withType<Sign> {
	doFirst {
		settingsProvider.validateGPGSecrets()
	}
}

tasks.withType<PublishToMavenRepository> {
	doFirst {
		settingsProvider.validateOssrhCredentials()
	}
}

tasks.register("printFinalReleaseNode") {
	doLast {
		printFinalReleaseNode(
			groupId = PublicationSettings.GROUP_ID,
			artifactId = PublicationSettings.ARTIFACT_ID,
			sanitizedVersion = project.sanitizeVersion()
		)
	}
}

publishing {
	publications {
		create<MavenPublication>("mavenJava") {
			from(components["java"])
			groupId = PublicationSettings.GROUP_ID
			artifactId = PublicationSettings.ARTIFACT_ID
			version = project.sanitizeVersion()
			versionMapping {
				usage("java-api") {
					fromResolutionOf("runtimeClasspath")
				}
				usage("java-runtime") {
					fromResolutionResult()
				}
			}
			pom {
				name.set(PublicationSettings.POM_NAME)
				description.set(PublicationSettings.POM_DESCRIPTION)
				url.set(PublicationSettings.POM_URL)
				licenses {
					license {
						name.set(PublicationSettings.LICENSE_NAME)
						url.set(PublicationSettings.LICENSE_URL)
					}
				}
				developers {
					developer {
						id.set(PublicationSettings.DEVELOPER_ID)
						name.set(PublicationSettings.DEVELOPER_NAME)
						email.set(PublicationSettings.DEVELOPER_EMAIL)
					}
				}
				scm {
					connection.set(PublicationSettings.SCM_CONNECTION)
					developerConnection.set(PublicationSettings.SCM_CONNECTION)
					url.set(PublicationSettings.SCM_URL)
				}
			}
		}
	}
	repositories {
		maven {
			credentials {
				username = settingsProvider.ossrhUsername
				password = settingsProvider.ossrhPassword
			}
			url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
		}
	}
}

signing {
	useInMemoryPgpKeys(settingsProvider.gpgSigningKey, settingsProvider.gpgSigningPassword)
	sign(publishing.publications["mavenJava"])
}

nexusStaging {
	packageGroup = PublicationSettings.STAGING_PACKAGE_GROUP
	username = settingsProvider.ossrhUsername
	password = settingsProvider.ossrhPassword
}

fun Project.sanitizeVersion(): String {
	return version.toString()
}

fun printFinalReleaseNode(groupId: String, artifactId: String, sanitizedVersion: String) {
	println()
	println("========================================================")
	println()
	println("New RELEASE artifact version were published:")
	println("	groupId: $groupId")
	println("	artifactId: $artifactId")
	println("	version: $sanitizedVersion")
	println()
	println("Discover on Maven Central:")
	println("	https://repo1.maven.org/maven2/${groupId.replace('.', '/')}/$artifactId/")
	println()
	println("Edit or delete artifacts on OSS Nexus Repository Manager:")
	println("	https://oss.sonatype.org/#nexus-search;gav~$groupId~~~~")
	println()
	println("Control staging repositories on OSS Nexus Repository Manager:")
	println("	https://oss.sonatype.org/#stagingRepositories")
	println()
	println("========================================================")
	println()
}

class SettingsProvider {

	val gpgSigningKey: String?
		get() = System.getenv(GPG_SIGNING_KEY_PROPERTY)

	val gpgSigningPassword: String?
		get() = System.getenv(GPG_SIGNING_PASSWORD_PROPERTY)

	val ossrhUsername: String?
		get() = System.getenv(OSSRH_USERNAME_PROPERTY)

	val ossrhPassword: String?
		get() = System.getenv(OSSRH_PASSWORD_PROPERTY)

	val githubHeadRef: String?
		get() = System.getenv(GITHUB_HEAD_REF_PROPERTY)

	fun validateGPGSecrets() = require(
		value = !gpgSigningKey.isNullOrBlank() && !gpgSigningPassword.isNullOrBlank(),
		lazyMessage = { "Both $GPG_SIGNING_KEY_PROPERTY and $GPG_SIGNING_PASSWORD_PROPERTY environment variables must not be empty" }
	)

	fun validateOssrhCredentials() = require(
		value = !ossrhUsername.isNullOrBlank() && !ossrhPassword.isNullOrBlank(),
		lazyMessage = { "Both $OSSRH_USERNAME_PROPERTY and $OSSRH_PASSWORD_PROPERTY environment variables must not be empty" }
	)

	companion object {
		private const val GPG_SIGNING_KEY_PROPERTY = "GPG_SIGNING_KEY"
		private const val GPG_SIGNING_PASSWORD_PROPERTY = "GPG_SIGNING_PASSWORD"
		private const val OSSRH_USERNAME_PROPERTY = "OSSRH_USERNAME"
		private const val OSSRH_PASSWORD_PROPERTY = "OSSRH_PASSWORD"
		private const val GITHUB_HEAD_REF_PROPERTY = "GITHUB_HEAD_REF"
	}
}

object PublicationSettings {

	const val GROUP_ID = "com.ecwid.clickhouse"
	const val ARTIFACT_ID = "clickhouse-client"

	const val POM_NAME = "Ecwid ClickHouse Client"
	const val POM_DESCRIPTION = "Java client for ClickHouse HTTP API (https://clickhouse.yandex)"
	const val POM_URL = "https://github.com/Ecwid/clickhouse-client"

	const val DEVELOPER_ID = "vgv"
	const val DEVELOPER_NAME = "Vasily Vasilkov"
	const val DEVELOPER_EMAIL = "vgv@ecwid.com"

	const val LICENSE_NAME = "The Apache License, Version 2.0"
	const val LICENSE_URL = "https://www.apache.org/licenses/LICENSE-2.0.txt"

	const val SCM_CONNECTION = "scm:git:git@github.com:Ecwid/clickhouse-client.git"
	const val SCM_URL = "https://github.com/Ecwid/clickhouse-client.git"

	const val STAGING_PACKAGE_GROUP = "com.ecwid"

}
