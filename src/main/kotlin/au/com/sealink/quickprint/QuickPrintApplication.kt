package au.com.sealink.quickprint

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired

@SpringBootApplication
class QuickPrintApplication : SpringBootServletInitializer() {
    override fun configure(builder: SpringApplicationBuilder): SpringApplicationBuilder {
        return builder.sources(QuickPrintApplication::class.java)
    }
}

fun main(args: Array<String>) {
    runApplication<QuickPrintApplication>(*args)
}
