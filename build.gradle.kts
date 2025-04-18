import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
	java
	alias(libs.plugins.kotlin.jvm)
	signing
	`maven-publish`
	alias(libs.plugins.nebula.release)
	alias(libs.plugins.nexus.publish.plugin)
}

repositories {
	mavenCentral()
}

dependencies {
	// Kotlin
	implementation(libs.kotlin.stdlib)
	implementation(libs.kotlin.reflect)

	// Gson
	implementation(libs.gson)

	// HttpClient
	implementation(libs.httpcomponents.httpcore)
	implementation(libs.httpcomponents.httpclient)

	// Prometheus
	compileOnly(libs.prometheus.metrics.core)

	// Test
	testImplementation(libs.junit.jupiter)
	testImplementation(libs.kotlin.test)
	testImplementation(libs.slf4j)
}


// Kotlin settings
kotlin {
	compilerOptions {
		jvmTarget = JvmTarget.JVM_11
		apiVersion = KotlinVersion.KOTLIN_1_6
		languageVersion = KotlinVersion.KOTLIN_1_6
	}
}

java {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
	withJavadocJar()
	withSourcesJar()
}

// Unit tests settings
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

	afterEvaluate {
		// Publish artifacts to Maven Central before pushing new git tag to repo
		named("release").get().apply {
			dependsOn(named("publishToSonatype").get())
		}

		named("closeAndReleaseStagingRepositories").get().apply {
			dependsOn(named("final").get())
		}
	}
}

tasks.withType<Sign> {
	doFirst {
		settingsProvider.validateGPGSecrets()
	}
	dependsOn(tasks.getByName("build"))
}

tasks.withType<PublishToMavenRepository> {
	doFirst {
		settingsProvider.validateSonatypeCredentials()
	}
}

tasks.register("printFinalReleaseNote") {
	doLast {
		printFinalReleaseNote(
			groupId = PublicationSettings.GROUP_ID,
			artifactId = PublicationSettings.ARTIFACT_ID,
			sanitizedVersion = project.sanitizeVersion()
		)
	}
	dependsOn(tasks.getByName("final"))
}

tasks.register("printDevSnapshotReleaseNote") {
	doLast {
		printDevSnapshotReleaseNote(
			groupId = PublicationSettings.GROUP_ID,
			artifactId = PublicationSettings.ARTIFACT_ID,
			sanitizedVersion = project.sanitizeVersion()
		)
	}
	dependsOn(tasks.getByName("devSnapshot"))
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
}

signing {
	useInMemoryPgpKeys(settingsProvider.gpgSigningKey, settingsProvider.gpgSigningPassword)
	sign(publishing.publications["mavenJava"])
}

nexusPublishing {
	repositories {
		sonatype {
			useStaging.set(!project.isSnapshotVersion())
			packageGroup.set(PublicationSettings.GROUP_ID)
			username.set(settingsProvider.sonatypeUsername)
			password.set(settingsProvider.sonatypePassword)
//			nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
//			snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
		}
	}
}

// We want to change SNAPSHOT versions format from:
// 		<major>.<minor>.<patch>-dev.#+<branchname>.<hash> (local branch)
// 		<major>.<minor>.<patch>-dev.#+<hash> (github pull request)
// to:
// 		<major>.<minor>.<patch>-dev+<branchname>-SNAPSHOT
fun Project.sanitizeVersion(): String {
	val version = version.toString()
	return if (project.isSnapshotVersion()) {
		val githubHeadRef = settingsProvider.githubHeadRef
		if (githubHeadRef != null) {
			// GitHub pull request
			// githubHeadRef contains branch name, but branch name can have '/',
			// for example 'dependabot/gradle/com.netflix.nebula.release-20.2.0'
			// Maven Central doesn't allow '/' in artifact version, so we replace it with '-'
			val branchName = githubHeadRef.replace('/', '-')
			version.replace(Regex("-dev\\.\\d+\\+[a-f0-9]+$"), "-dev+$branchName-SNAPSHOT")
		} else {
			// local branch
			version
				.replace(Regex("-dev\\.\\d+\\+"), "-dev+")
				.replace(Regex("\\.[a-f0-9]+$"), "-SNAPSHOT")
		}
	} else {
		version
	}
}

fun Project.isSnapshotVersion() = version.toString().contains("-dev.")

fun printFinalReleaseNote(groupId: String, artifactId: String, sanitizedVersion: String) {
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

fun printDevSnapshotReleaseNote(groupId: String, artifactId: String, sanitizedVersion: String) {
	println()
	println("========================================================")
	println()
	println("New developer SNAPSHOT artifact version were published:")
	println("	groupId: $groupId")
	println("	artifactId: $artifactId")
	println("	version: $sanitizedVersion")
	println()
	println("Discover on Maven Central:")
	println("	https://oss.sonatype.org/content/repositories/snapshots/${groupId.replace('.', '/')}/$artifactId/")
	println()
	println("Edit or delete artifacts on OSS Nexus Repository Manager:")
	println("	https://oss.sonatype.org/#nexus-search;gav~$groupId~~~~")
	println()
	println("========================================================")
	println()
}

class SettingsProvider {

	val gpgSigningKey: String?
		get() = System.getenv(GPG_SIGNING_KEY_PROPERTY)

	val gpgSigningPassword: String?
		get() = System.getenv(GPG_SIGNING_PASSWORD_PROPERTY)

	val sonatypeUsername: String?
		get() = System.getenv(SONATYPE_USERNAME_PROPERTY)

	val sonatypePassword: String?
		get() = System.getenv(SONATYPE_PASSWORD_PROPERTY)

	val githubHeadRef: String?
		get() = System.getenv(GITHUB_HEAD_REF_PROPERTY)

	fun validateGPGSecrets() = require(
		value = !gpgSigningKey.isNullOrBlank() && !gpgSigningPassword.isNullOrBlank(),
		lazyMessage = { "Both $GPG_SIGNING_KEY_PROPERTY and $GPG_SIGNING_PASSWORD_PROPERTY environment variables must not be empty" }
	)

	fun validateSonatypeCredentials() = require(
		value = !sonatypeUsername.isNullOrBlank() && !sonatypePassword.isNullOrBlank(),
		lazyMessage = { "Both $SONATYPE_USERNAME_PROPERTY and $SONATYPE_PASSWORD_PROPERTY environment variables must not be empty" }
	)

	private companion object {
		private const val GPG_SIGNING_KEY_PROPERTY = "GPG_SIGNING_KEY"
		private const val GPG_SIGNING_PASSWORD_PROPERTY = "GPG_SIGNING_PASSWORD"
		private const val SONATYPE_USERNAME_PROPERTY = "SONATYPE_USERNAME"
		private const val SONATYPE_PASSWORD_PROPERTY = "SONATYPE_PASSWORD"
		private const val GITHUB_HEAD_REF_PROPERTY = "GITHUB_HEAD_REF"
	}
}

object PublicationSettings {

	const val GROUP_ID = "com.ecwid.clickhouse"
	const val ARTIFACT_ID = "clickhouse-client"

	const val POM_NAME = "Ecwid ClickHouse Client"
	const val POM_DESCRIPTION = "Java client for ClickHouse HTTP API (https://clickhouse.com)"
	const val POM_URL = "https://github.com/Ecwid/clickhouse-client"

	const val DEVELOPER_ID = "vgv"
	const val DEVELOPER_NAME = "Vasily Vasilkov"
	const val DEVELOPER_EMAIL = "vasily.vasilkov@lightspeedhq.com"

	const val LICENSE_NAME = "The Apache License, Version 2.0"
	const val LICENSE_URL = "https://www.apache.org/licenses/LICENSE-2.0.txt"

	const val SCM_CONNECTION = "scm:git:git@github.com:Ecwid/clickhouse-client.git"
	const val SCM_URL = "https://github.com/Ecwid/clickhouse-client.git"

}
