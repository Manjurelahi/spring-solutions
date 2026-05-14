package springboot.jdk21.jwt.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.jdk21.jwt.entity.User;
import springboot.jdk21.jwt.payload.response.JWTResponse;
import springboot.jdk21.jwt.service.JWTService;
import springboot.jdk21.jwt.util.JWTUtil;

@RestController
@RequestMapping("/api/v1")
public class JWTController {
  private JWTUtil jwtUtil;
  private JWTService jwtService;

  public JWTController(JWTUtil jwtUtil, JWTService jwtService) {
    this.jwtUtil = jwtUtil;
    this.jwtService = jwtService;
  }

  @PostMapping("/login")
  public ResponseEntity<JWTResponse> login(@RequestBody User user,
                                           HttpServletRequest request, HttpServletResponse response) {
    UserDetails userDetails = jwtService.authenticate(user, request, response);
    String jwtToken = jwtUtil.generateToken(user.getUsername());
    ResponseCookie responseCookie = ResponseCookie
      .from("HTTP-ONLY-JWT-TOKEN", jwtToken)
      .httpOnly(true)
      .secure(true)
      .path("/")
      .maxAge(3600*12)
      .sameSite(SameSiteCookies.STRICT.toString())
      .build();

    JWTResponse jwtResponse = JWTResponse.builder()
      .token(jwtToken)
      .username(user.getUsername())
      .role(userDetails.getAuthorities().stream().findFirst().get().getAuthority())
      .tokenType("HTTP-ONLY")
      .build();

    return ResponseEntity
      .ok()
      .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
      .body(jwtResponse);
  }

  @PostMapping("/logout")
  public ResponseEntity<String> logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
    ResponseCookie jwtResponse = jwtService.getCleanJwtCookie();
    logoutHandler.logout(request, response, authentication);
    SecurityContextHolder.clearContext();
    return ResponseEntity
      .ok()
      .header(HttpHeaders.SET_COOKIE, jwtResponse.toString())
      .body("{\"status\": \"logout\"}");
  }

  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  @GetMapping("/settings")
  public ResponseEntity<String> adminSettings() {
    return ResponseEntity
      .ok()
      .body("{\"admin\": \"settings\"}");
  }
}
