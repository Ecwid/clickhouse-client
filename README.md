# clickhouse-client

Java/Kotlin client for ClickHouse (https://clickhouse.com)

## How to use

There are three different clients, from raw low-level client to simple object mapper.

### Mapped client (transparently converts response rows into your POJO)
```java
HttpTransport httpTransport = new ApacheHttpClientTransport();
ClickHouseMappedClient client = new ClickHouseMappedClient(httpTransport);

try (MappedResponse<User> response = client.select("http://localhost:8123", "SELECT * FROM user", User::convert)) {
    for (User user : response) {
        System.out.println(user);
    }
}
```

Class `User` and `convert` function can be something like this
```java
class User {

    private final String name;
    private final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static User convert(TypedRow row) {
        String name = row.getString("name");
        int age = row.getInt8("age");
        return new User(name, age);
    }
}
```

### Typed client (looks like JDBC ResultSet class)
```java
HttpTransport httpTransport = new ApacheHttpClientTransport();
ClickHouseTypedClient client = new ClickHouseTypedClient(httpTransport);

try (TypedResponse response = client.select("http://localhost:8123", "SELECT * FROM table")) {
    for (TypedRow typedRow : response) {
        int firstValue = typedRow.getInt32(1);
        Date secondValue = typedRow.getDateTime(2);
        List<Integer> thirdValue = typedRow.getInt32Array(3);

        System.out.println(firstValue + ":" + secondValue + ":" + thirdValue);
    }
}
```

### Raw client (very low level, use if you want to control everything)

```java
HttpTransport httpTransport = new ApacheHttpClientTransport();
ClickHouseRawClient client = new ClickHouseRawClient(httpTransport);

try (RawResponse response = client.select("http://localhost:8123", "SELECT * FROM table")) {
    for (RawRow row : response) {
        System.out.println(row);
    }
}
```


## How to add clickhouse-client into your project
### Gradle
```
compile "com.ecwid.clickhouse:clickhouse-client:0.22.0"
```
### Maven
```
<dependency>
  <groupId>com.ecwid.clickhouse</groupId>
  <artifactId>clickhouse-client</artifactId>
  <version>0.22.0</version>
</dependency>
```

## How to enable metrics in clickhouse-client
### Use prometheus metrics

Add prometheus metric dependency to your project

gradle
```
compile "io.prometheus:prometheus-metrics-core:1.2.0"
```

Inject metrics collector on instantiate client
```java
HttpTransport httpTransport = new ApacheHttpClientTransport();
ClickHouseMappedClient client = new ClickHouseMappedClient(httpTransport, DefaultMetrics.INSTANCE.getPROMETHEUS());
```

### Use custom metrics collector
Add implementation of `com.ecwid.clickhouse.metrics.Metrics` interface
```java
import com.ecwid.clickhouse.metrics.Metrics;
import org.jetbrains.annotations.NotNull;

public class DummyMetrics implements Metrics {
    @Override
    public void measureRequest(@NotNull String host, int statusCode) {
        // Measure request to host with received http status code
    }

    @NotNull
    @Override
    public AutoCloseable startRequestTimer(@NotNull String host) {
        // start measuring request
        final long startedAt = System.currentTimeMillis();
        return new AutoCloseable() {
            @Override
            public void close() throws Exception {
                final long completedAt = System.currentTimeMillis();
                // measure request time to host
            }
        };
    }
}
```

And use it on instantiate client:
```java
HttpTransport httpTransport = new ApacheHttpClientTransport();
ClickHouseMappedClient client = new ClickHouseMappedClient(httpTransport, new DummyMetrics());
```
