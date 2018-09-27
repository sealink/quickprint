package au.com.sealink.quickprint

import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationFilter(private val apiKey: String) : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain)  {
        val apiKey = request.getHeader("x-api-key")
        if (apiKey != this.apiKey) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid API Token")
        }
        filterChain.doFilter(request, response)
    }
}