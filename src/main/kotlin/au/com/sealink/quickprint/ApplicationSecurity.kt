package au.com.sealink.quickprint

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
class ApplicationSecurity : WebSecurityConfigurerAdapter() {
    @Autowired
    private lateinit var env : Environment

    override fun configure(http: HttpSecurity) {
        val protectedRoutes = arrayOf("/print-tickets", "/printers")

        http.csrf().disable()
                .requestMatchers()
                    .antMatchers(*protectedRoutes)
                    .and()
                .addFilterBefore(AuthenticationFilter(env.getProperty("secrets.apiKey") ?: ""), BasicAuthenticationFilter::class.java)
    }
}