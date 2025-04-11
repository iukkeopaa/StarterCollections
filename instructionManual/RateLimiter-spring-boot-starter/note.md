### 使用说明


#### 打包项目 

```shell
mvn clean package
```


#### 在其他项目中使用starter

```java
<dependency>
    <groupId>com.example</groupId>
    <artifactId>rate-limiter-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```


#### 配置限流参数

在使用该 Starter 的项目的 application.properties 或者 application.yml 文件中配置限流参数：


```java
rate-limiter.fixed-window-limit=20
rate-limiter.fixed-window-size=2000
rate-limiter.sliding-window-limit=20
rate-limiter.sliding-window-size=2000
rate-limiter.token-bucket-capacity=200
rate-limiter.token-bucket-rate=20
```

#### 使用

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApp implements CommandLineRunner {

    @Autowired
    private FixedWindowRateLimiter fixedWindowRateLimiter;

    @Autowired
    private SlidingWindowRateLimiter slidingWindowRateLimiter;

    @Autowired
    private TokenBucketRateLimiter tokenBucketRateLimiter;

    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Fixed Window: " + fixedWindowRateLimiter.tryAcquire());
        System.out.println("Sliding Window: " + slidingWindowRateLimiter.tryAcquire());
        System.out.println("Token Bucket: " + tokenBucketRateLimiter.tryAcquire());
    }
}
```