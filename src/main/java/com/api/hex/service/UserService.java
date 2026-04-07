package com.api.hex.service;

import com.api.hex.dto.RegisterReqDto;
import com.api.hex.mapper.UserMapper;
import com.api.hex.model.User;
import com.api.hex.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService  implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterReqDto registerReqDto) {
        User user = UserMapper.mapToEntity(registerReqDto);
        user.setPassword(passwordEncoder.encode(registerReqDto.password()));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username given"));
    }
}
