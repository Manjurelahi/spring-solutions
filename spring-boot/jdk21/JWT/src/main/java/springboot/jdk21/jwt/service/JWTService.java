package springboot.jdk21.jwt.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import springboot.jdk21.jwt.entity.User;
import springboot.jdk21.jwt.repository.UserRepository;

@Service
public class JWTService {

  private AuthenticationManager authenticationManager;
  private UserRepository userRepository;
  private CustomUserDetailsService customUserDetailsService;

  @Autowired
  public JWTService(AuthenticationManager authenticationManager, UserRepository userRepository,
                    CustomUserDetailsService customUserDetailsService) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.customUserDetailsService = customUserDetailsService;
  }

  public UserDetails authenticate(User user, HttpServletRequest request, HttpServletResponse response) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    SecurityContext context = SecurityContextHolder.createEmptyContext();
    SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    context.setAuthentication(authToken);
    SecurityContextHolder.setContext(context);
    securityContextRepository.saveContext(context, request, response);
    return userDetails;
  }

  public ResponseCookie getCleanJwtCookie() {
    return ResponseCookie.from("HTTP-ONLY-JWT-TOKEN", "")
      .path("/")
      .httpOnly(true)
      .maxAge(0)
      .build();
  }
}
