package springboot.jdk21.jwt.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import springboot.jdk21.jwt.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
  PasswordEncoder passwordEncoder;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  public UserRepository() {
    this.passwordEncoder = passwordEncoder();
  }

  private String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }

  public List<User> getUserList() {
    return List.of(new User("admin.employee@company.com", encodePassword("employee@1Admin"), "ADMIN"),
      new User("user.employee@company.com", encodePassword("employee@1User"), "USER"));
  }

  public Optional<User> findByUsername(String username) {
    return getUserList().stream().filter(user -> user.getUsername().equals(username)).findFirst();
  }
}
