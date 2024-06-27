package it.portfolio.hr.humanResource.filters;

import it.portfolio.hr.humanResource.models.UserDetailsImpl;
import it.portfolio.hr.humanResource.services.UserDetailsServiceImpl;
import it.portfolio.hr.humanResource.utilities.TokenUtilities;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private TokenUtilities tokenUtilities;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = parseJwt(request);
            if (jwt != null && tokenUtilities.validateToken(jwt)) {
                String username = tokenUtilities.extractUsername(jwt);

                UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String companyName = tokenUtilities.extractClaim(jwt, "companyName");
                request.setAttribute("companyName", companyName);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")) {
            return (header.substring(7));
        }
        return null;
    }
}
