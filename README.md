# clickhouse-client

Java/Kotlin client for Yandex ClickHouse (https://clickhouse.yandex)

## How to use

There are three different clients, from raw low-level client to simple object mapper.

Typed client (looks like JDBC ResultSet class)
```java
HttpTransport httpTransport = new ApacheHttpClientTransport();
ClickHouseTypedClient typedClient = new ClickHouseTypedClient(httpTransport);

try (TypedResponse response = typedClient.select("http://localhost:8123", "SELECT * FROM table")) {
    for (TypedRow typedRow : response) {
        int firstValue = typedRow.getInt32(1);
        Date secondValue = typedRow.getDateTime(2);
        List<Integer> thirdValue = typedRow.getInt32Array(3);

        System.out.println(firstValue + ":" + secondValue + ":" + thirdValue);
    }
}
```

Raw client (very low level, use if you want to control everything)

```java
HttpTransport httpTransport = new ApacheHttpClientTransport();
ClickHouseRawClient rawClient = new ClickHouseRawClient(httpTransport);

try (RawResponse response = rawClient.select("http://localhost:8123", "SELECT * FROM table")) {
    for (RawRow row : response) {
        System.out.println(row);
    }
}
```


## How to add clickhouse-client into your project
### Gradle
```
compile "com.ecwid.clickhouse:clickhouse-client:0.0.1"
```
### Maven
```
<dependency>
  <groupId>com.ecwid.clickhouse</groupId>
  <artifactId>clickhouse-client</artifactId>
  <version>0.0.1</version>
</dependency>
```
