package com.zabisoft.alarm_app.helper;

import com.zabisoft.alarm_app.entities.User;
import com.zabisoft.alarm_app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthHelper {

    private final UserRepository userRepository;

    public User getCurrentUser() {

        var auth = SecurityContextHolder
                .getContext()
                .getAuthentication();

        System.out.println(auth);
        System.out.println(auth.getName());
        System.out.println(auth.getAuthorities());

        String email = auth.getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

}