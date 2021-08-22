# hello

IntelliJ の Spring Initializer で作成したプロジェクト。

- Kotlin
- Gradle
- JDK 11

`@SpringBootApplication` でannotateしたクラスのmainが呼ばれる。

```kotlin
package com.example.hello

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloApplication

fun main(args: Array<String>) {
    runApplication<HelloApplication>(*args)
}
```

`@SpringBootApplication`は以下のアノテーションを組み合わせたもの

- `@Configuration`: アプリケーションコンテキストのbean定義のソース
- `@EnableAutoConfiguration`: クラスパスに基づいて、beanを追加する
- `@ComponentScan`: 他のcomponent, configurationやserviceを探す。

実行は、 `gradlew :bootRun`

```
10:24:27: Executing task 'bootRun'...

> Task :compileKotlin
> Task :compileJava NO-SOURCE
> Task :processResources
> Task :classes
> Task :bootRunMainClassName

> Task :bootRun

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.4)

2021-08-22 10:24:37.210  INFO 22543 --- [  restartedMain] com.example.hello.HelloApplicationKt     : Starting HelloApplicationKt using Java 11.0.10 on mbp2014.lan with PID 22543 (/Users/akira/Works/src/github.com/stn/my-spring-boot-cookboook/hello/build/classes/kotlin/main started by akira in /Users/akira/Works/src/github.com/stn/my-spring-boot-cookboook/hello)
2021-08-22 10:24:37.212  INFO 22543 --- [  restartedMain] com.example.hello.HelloApplicationKt     : No active profile set, falling back to default profiles: default
2021-08-22 10:24:37.246  INFO 22543 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2021-08-22 10:24:37.609  INFO 22543 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2021-08-22 10:24:37.624  INFO 22543 --- [  restartedMain] com.example.hello.HelloApplicationKt     : Started HelloApplicationKt in 0.709 seconds (JVM running for 1.057)

BUILD SUCCESSFUL in 10s
4 actionable tasks: 4 executed
10:24:38: Task execution finished 'bootRun'.
```
