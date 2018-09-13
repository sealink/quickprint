package au.com.sealink.quickprint

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.*

@Configuration
class ApplicationSecurity : WebSecurityConfigurerAdapter() {
    @Autowired
    private lateinit var env : Environment

    override fun configure(http: HttpSecurity) {
        val protectedRoutes = arrayOf("/print-tickets", "/printers")

        http.csrf().disable()
                .cors().and()
                .requestMatchers()
                    .antMatchers(*protectedRoutes)
                    .and()
                .addFilterBefore(AuthenticationFilter(env.getProperty("secrets.apiKey")!!), BasicAuthenticationFilter::class.java)
    }

    @Bean
    fun corsConfigurationSource() : CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = Arrays.asList("*")
        configuration.allowedMethods = Arrays.asList("GET", "POST", "OPTIONS")
        configuration.allowedHeaders = Arrays.asList("*")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}