package com.karol.todo.entity.user;

import com.karol.todo.config.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final HttpServletRequest request;

    public UserEntity registerNewUser(UserEntity user) {
        String passBcrypt = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setRole("USER");
        user.setPassword(passBcrypt);
        return userRepo.save(user);
    }

    public Long getLoggedInUserId() {
        String jwtToken = request.getHeader("Authorization");
        String email = jwtService.extractUserName(jwtToken.substring(7).trim());

        return userRepo.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found")).getId();
    }

    public String verify(UserEntity user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getEmail());
        } else {
            return "fail";
        }
    }

    public Optional<UserEntity> getUserById(Long id) {
        return userRepo.findById(id);
    }
}
