package au.com.sealink.quickprint

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QuickPrintApplication
fun main(args: Array<String>) {
    runApplication<QuickPrintApplication>(*args)
}
