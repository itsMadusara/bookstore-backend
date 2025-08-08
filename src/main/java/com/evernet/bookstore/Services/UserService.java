package com.evernet.bookstore.Services;

import com.evernet.bookstore.dto.LoginRequestDTO;
import com.evernet.bookstore.dto.RegisterRequestDTO;
import com.evernet.bookstore.dto.UserDTO;
import com.evernet.bookstore.model.User;
import com.evernet.bookstore.model.UserPrincipal;
import com.evernet.bookstore.model.enums.Role;
import com.evernet.bookstore.repository.UserRepo;
import com.evernet.bookstore.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO registerUser(RegisterRequestDTO registerRequestDTO){
        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRole(Role.USER);

        User savedUser = userRepo.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(savedUser.getId());
        userDTO.setEmail(savedUser.getEmail());
        userDTO.setUsername(savedUser.getEmail());

        return userDTO;
    }

    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(loginRequestDTO.getEmail());
            return Optional.of(token);
        }

        return Optional.empty();
    }

    public Optional<User> getUserById(Long id){
        return userRepo.findById(id);
    }
}
