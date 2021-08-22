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