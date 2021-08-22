# REST

公式のガイドを参考に。
https://spring.io/guides/gs/rest-service/

## レスポンスになるオブジェクト

レスポンスとなるデータとして`Greeting`を用意する。

```kotlin
class Greeting(val id: Long, val content: String)
```

## RESTコントローラー

コントローラークラスには`@RestController`アノテーションを用いる。

`@RestController`は`@Controller`と`@ResponseBody`を組み合わせたもの。

```kotlin
package com.example.restapi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class GreetingController {

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String): Greeting {
        return Greeting(counter.incrementAndGet(), template.format(name))
    }

    companion object {
        private const val template = "Hello, %s!"
        private val counter = AtomicLong()
    }
}
```

`greeting()`メソッドをGET /greetingにマッピングするために`@GetMapping("/greeting")`をアノテーションする。

パラメーターは`@RequestParam`でアノテーションし、`name=...`を変数`name`にマッピングしている。

返り値には`Greeting`オブジェクトをそのまま渡しているが、
Jackson 2を用いた`MappingJackson2HttpMessageConverter`によって自動的にJSONへと変換される。

### 実行

```shell
gradlew bootRun
```

```
12:50:40: Executing task 'bootRun'...

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

2021-08-22 12:50:43.395  INFO 26305 --- [  restartedMain] c.example.restapi.RestApiApplicationKt   : Starting RestApiApplicationKt using Java 11.0.10 on mbp2014.lan with PID 26305 (/Users/akira/Works/src/github.com/stn/my-spring-boot-cookboook/rest-api/build/classes/kotlin/main started by akira in /Users/akira/Works/src/github.com/stn/my-spring-boot-cookboook/rest-api)
2021-08-22 12:50:43.397  INFO 26305 --- [  restartedMain] c.example.restapi.RestApiApplicationKt   : No active profile set, falling back to default profiles: default
2021-08-22 12:50:43.446  INFO 26305 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2021-08-22 12:50:43.447  INFO 26305 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2021-08-22 12:50:44.216  INFO 26305 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2021-08-22 12:50:44.229  INFO 26305 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2021-08-22 12:50:44.230  INFO 26305 --- [  restartedMain] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.52]
2021-08-22 12:50:44.294  INFO 26305 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2021-08-22 12:50:44.294  INFO 26305 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 847 ms
2021-08-22 12:50:44.552  INFO 26305 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2021-08-22 12:50:44.576  INFO 26305 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2021-08-22 12:50:44.585  INFO 26305 --- [  restartedMain] c.example.restapi.RestApiApplicationKt   : Started RestApiApplicationKt in 1.525 seconds (JVM running for 1.927)
```

### アクセス

```shell
$ http :8080/greeting
HTTP/1.1 200
Connection: keep-alive
Content-Type: application/json
Date: Sun, 22 Aug 2021 03:55:22 GMT
Keep-Alive: timeout=60
Transfer-Encoding: chunked

{
    "content": "Hello, World!",
    "id": 1
}
```

```shell
$ http ":8080/greeting?name=Joe"
HTTP/1.1 200
Connection: keep-alive
Content-Type: application/json
Date: Sun, 22 Aug 2021 03:56:33 GMT
Keep-Alive: timeout=60
Transfer-Encoding: chunked

{
    "content": "Hello, Joe!",
    "id": 2
}
```
