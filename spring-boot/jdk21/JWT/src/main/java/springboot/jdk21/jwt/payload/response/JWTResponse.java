package springboot.jdk21.jwt.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTResponse {
  private String username;
  private String role;
  private String token;
  private String tokenType;
}
