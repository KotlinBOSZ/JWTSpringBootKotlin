package me.szydelko.jwtspringbootkotlin.security

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import me.szydelko.jwtspringbootkotlin.service.JWTService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JWTAuthenticationFilter(
    private val JWTService: JWTService,
    @Qualifier("userDetailsService") private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        val authHeader = request.getHeader("Authorization")

        if (authHeader == null || authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response)
            return
        }
        val jwt : String = authHeader.substring(7)
        val userEmail =  JWTService.extractUsername(jwt)

        if (userEmail != null && SecurityContextHolder.getContext().authentication == null){

            var userDetails = userDetailsService.loadUserByUsername(userEmail);

            if (JWTService.isTokenValid(jwt,userDetails)){
                val authToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities
                )
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            }
        }
        filterChain.doFilter(request, response)
    }
}