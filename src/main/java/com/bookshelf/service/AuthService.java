package com.bookshelf.service;

import com.bookshelf.dto.AuthResponse;
import com.bookshelf.dto.LoginRequest;
import com.bookshelf.dto.RegisterRequest;
import com.bookshelf.dto.UserResponse;
import com.bookshelf.entity.Role;
import com.bookshelf.entity.User;
import com.bookshelf.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public UserResponse register(RegisterRequest request){
        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new RuntimeException("Username is already in use:"+request.username());
        }

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);

        User saved = userRepository.save(user);
        return UserResponse.from(saved);
    }

    public AuthResponse login(LoginRequest request){
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("username or password is incorrect"));

        if (!passwordEncoder.matches(request.password(),user.getPassword())){
            throw new RuntimeException("username or password is incorrect");
        }

        String token = jwtService.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
}
