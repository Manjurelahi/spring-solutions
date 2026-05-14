package springboot.jdk21.jwt.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import springboot.jdk21.jwt.repository.UserRepository;
import springboot.jdk21.jwt.service.CustomUserDetailsService;
import springboot.jdk21.jwt.util.JWTUtil;

import java.io.IOException;
import java.util.Arrays;

@Component
public class JWTFilter extends OncePerRequestFilter {
  private JWTUtil jwtUtil;
  private UserRepository userRepository;
  private CustomUserDetailsService customUserDetailsService;

  @Autowired
  public JWTFilter(JWTUtil jwtUtil, UserRepository userRepository, CustomUserDetailsService customUserDetailsService) {
    this.jwtUtil = jwtUtil;
    this.customUserDetailsService = customUserDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    String token = null;
    String authHeader = request.getHeader("Authorization");
    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      token = authHeader.substring(7);
    } else if (request.getCookies() != null) {
      token = Arrays.stream(request.getCookies())
        .filter(cookie -> "HTTP-ONLY-JWT-TOKEN".equals(cookie.getName()))
        .map(Cookie::getValue)
        .findFirst()
        .orElse(null);
    }
    if (token != null && jwtUtil.validateToken(token)) {
      SecurityContext context = SecurityContextHolder.createEmptyContext();
      //SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
      String username = jwtUtil.extractUsername(token);
      UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
      UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      context.setAuthentication(authToken);
      SecurityContextHolder.setContext(context);
      //securityContextRepository.saveContext(context, request, response);
    }
    filterChain.doFilter(request, response);
  }
}
