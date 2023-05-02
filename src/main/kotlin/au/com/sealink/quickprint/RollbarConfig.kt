package au.com.sealink.quickprint

import com.rollbar.notifier.Rollbar
import com.rollbar.notifier.config.Config
import org.springframework.beans.factory.annotation.Autowired
import com.rollbar.spring.webmvc.RollbarSpringConfigBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.core.env.Environment

@Configuration
@EnableWebMvc
@ComponentScan(
    "au.com.sealink.quickprint",
    "au.com.sealink.printing.ticketprinter",
    "com.rollbar.spring"
)
class RollbarConfig {
    @Autowired
    private lateinit var env : Environment
    
    @Bean
    fun rollbar(): Rollbar {
        return Rollbar(getRollbarConfigs(env.getProperty("rollbar.accessToken")!!))
    }

    private fun getRollbarConfigs(accessToken: String): Config {
        return RollbarSpringConfigBuilder.withAccessToken(accessToken)
            .environment("development")
            .build()
    }
}
