package com.hexaware.service;


import com.hexaware.careercrafter.dto.UserRespDto;
import com.hexaware.careercrafter.enums.UserRole;
import com.hexaware.careercrafter.model.User;
import com.hexaware.careercrafter.repository.UserRepository;
import com.hexaware.careercrafter.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void getByIdTestWhenExists() {

        // Check if userService is not null
        Assertions.assertNotNull(userService);

        // Preparing the data for mock
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john@test.com");
        user.setPassword("test123");
        user.setUserRole(UserRole.JOB_SEEKER);
        user.setPhone("9876543210");
        user.setLocation("Chennai");

        // Actual Mocking: if and when you encounter a call userRepository.findById(1L)
        // must return this above user object instead of going to DB
        // this is a virtual record used only for testing purpose
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Prepare the DTO for above user object
        // So we know that DTO is getting prepared properly in our actual service class too
        UserRespDto dto = new UserRespDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUserRole(),
                user.getPhone(),
                user.getLocation()
        );

        UserRespDto dto1 = new UserRespDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                UserRole.EMPLOYER,    // different role
                user.getPhone(),
                user.getLocation()
        );

        // if getById(1) gives me the user object
        // Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Assertions.assertEquals(dto, userService.getUserById(1L));
        Assertions.assertNotEquals(dto1, userService.getUserById(1L));

        // verifying that my repository call to DB happens only twice for this findById method
        // which is ideal for fast API processing
        Mockito.verify(userRepository, times(2)).findById(1L);
    }
    @Test
    public void getByIdTestWhenNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        Exception e = Assertions.assertThrows(RuntimeException.class, () -> {
            userService.getUserById(99L);
        });
        Assertions.assertEquals("User not found", e.getMessage());
    }
    @Test
    public void getAllUsersTest() {
        User user1 = new User();
        user1.setId(1L);
        user1.setName("John");
        user1.setEmail("john@test.com");
        user1.setUserRole(UserRole.JOB_SEEKER);

        List<User> list = List.of(user1);
        Page<User> pageUser = new PageImpl<>(list);

        Pageable pageable = PageRequest.of(0, 1);
        when(userRepository.findAll(pageable)).thenReturn(pageUser);

        Assertions.assertEquals(1, userService.getAllUsers(0, 1).data().size());
    }
}
