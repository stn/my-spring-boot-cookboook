# Configuration

## @Value

`@Value`でコンフィグレーションの設定をコードに反映することができる。

Kotlinでは`@Value`の指定が`${foo}`ではなく、`\${foo}`となることに注意。

Configurationの優先順位
https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config

```kotlin
package com.example.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/greeting")
class GreetingController {

    @Value("\${greeting.name: Mirage}")
    val name = ""

    @GetMapping
    fun greeting(): String {
        return template.format(name)
    }

    companion object {
        private const val template = "Hello, %s!"
    }
}
```

application.propertiesあるいはapplication.ymlで値を設定できる。

application.yml
```yaml
greeting.name: Dakota
```
