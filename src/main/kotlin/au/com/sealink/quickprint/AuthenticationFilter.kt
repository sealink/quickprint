package au.com.sealink.quickprint

import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.security.access.AccessDeniedException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(private val apiKey: String) : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain)  {
        val apiKey = request.getHeader("api_key")
        if (apiKey != this.apiKey) {
            throw AccessDeniedException("Invalid API Key")
        }
        filterChain.doFilter(request, response)
    }
}