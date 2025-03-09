package com.escass.shop.Services;

import com.escass.shop.DTO.CustomUser;
import com.escass.shop.Entities.UserEntity;
import com.escass.shop.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void addUser(String username, String password, String displayName) throws Exception {
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                displayName == null || displayName.isEmpty()) {
            throw new Exception();
        }
        if (username.length() > 20) {
            throw new Exception();
        }
        if (password.length() < 8 || password.length() > 50) {
            throw new Exception();
        }
        if (displayName.length() > 20) {
            throw new Exception();
        }
        Optional<UserEntity> same = userRepository.findByUsername(username);
        if (same.isPresent()) {
            throw new Exception();
        };
        var result = bCryptPasswordEncoder.encode(password);
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(result);
        user.setDisplayName(displayName);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);
        if (userEntity.isPresent()) {
            var user = userEntity.get();
            List<GrantedAuthority> grant = new ArrayList<>();
            grant.add(new SimpleGrantedAuthority("일반유저"));
            return new CustomUser(user.getUsername(), user.getPassword(), grant, user.getDisplayName());
        }
        return null;
    }
}


