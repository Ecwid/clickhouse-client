# clickhouse-client

Java/Kotlin client for Yandex ClickHouse (https://clickhouse.yandex)

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
compile "com.ecwid.clickhouse:clickhouse-client:0.0.10"
```
### Maven
```
<dependency>
  <groupId>com.ecwid.clickhouse</groupId>
  <artifactId>clickhouse-client</artifactId>
  <version>0.0.10</version>
</dependency>
```
