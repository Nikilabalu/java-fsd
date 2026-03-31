package com.hexaware.careercrafter.service;

import com.hexaware.careercrafter.dto.PageRespDto;
import com.hexaware.careercrafter.dto.RegisterReqDto;
import com.hexaware.careercrafter.dto.UserRespDto;
import com.hexaware.careercrafter.mapper.UserMapper;
import com.hexaware.careercrafter.model.User;
import com.hexaware.careercrafter.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // used internally by other services — returns raw User entity
    public User getById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // used for test cases and controller — returns UserRespDto like trainer's pattern
    public UserRespDto getUserById(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserMapper.mapToRespDto(user);
    }

    public void register(RegisterReqDto registerReqDto) {
        User user = new User();
        user.setName(registerReqDto.name());
        user.setEmail(registerReqDto.email());
        user.setPassword(passwordEncoder.encode(registerReqDto.password()));
        user.setUserRole(registerReqDto.role());
        user.setPhone(registerReqDto.phone());
        user.setLocation(registerReqDto.location());
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid email given"));
    }
    public PageRespDto getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> pageUser = userRepository.findAll(pageable);
        List<UserRespDto> data = pageUser.getContent()
                .stream()
                .map(UserMapper::mapToRespDto)
                .toList();
        return new PageRespDto(data, pageUser.getTotalElements(), pageUser.getTotalPages());
    }
}