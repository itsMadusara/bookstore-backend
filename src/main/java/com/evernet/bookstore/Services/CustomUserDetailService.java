package com.evernet.bookstore.Services;

import com.evernet.bookstore.model.User;
import com.evernet.bookstore.model.UserPrincipal;
import com.evernet.bookstore.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(email);

        if (user.isEmpty()){
            throw new UsernameNotFoundException("User with this email not found: " + email);
        } else {
            User user1 = user.get();
            return new UserPrincipal(user1);
        }

    }
}
