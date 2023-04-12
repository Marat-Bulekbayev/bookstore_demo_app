package com.epam.setbasicbackend.service;

import com.epam.setbasicbackend.exception.NoSuchUserException;
import com.epam.setbasicbackend.exception.EmailHadBeenTakenException;
import com.epam.setbasicbackend.model.User;
import com.epam.setbasicbackend.model.enums.Role;
import com.epam.setbasicbackend.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testCreateUser() throws EmailHadBeenTakenException {
        User user = new User();
        user.setName("Test_Name");
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        User actualUser = userService.createUser(user);
        Assertions.assertEquals(actualUser.getName(), user.getName());
        verify(userRepository).save(user);
    }

    @Test
    void testFindUserById() throws NoSuchUserException {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User actualUser = userService.findUserById(user.getId());
        assertThat(actualUser).isSameAs(user);
        verify(userRepository).findById(user.getId());
    }

    @Test
    void testFindAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        given(userRepository.findAll()).willReturn(userList);
        List<User> actualUserList = userService.findAllUsers();
        Assertions.assertEquals(actualUserList, userList);
        verify(userRepository).findAll();
    }

    @Test
    void testEditUser() throws NoSuchUserException {
        User user = new User("Test_Name",
                Role.CUSTOMER,
                "Test_Email",
                000000.00,
                "Test_Address",
                "Test_Login",
                "Test_Password");
        User updatedUser = new User("Test_Name_Updated",
                Role.MANAGER,
                "Test_Email_Updated",
                000000.01, "Test_Address_Updated",
                "Test_Login_Updated",
                "Test_Password_Updated");
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        userService.editUser(user.getId(), updatedUser);
        verify(userRepository).save(updatedUser);
        verify(userRepository).findById(user.getId());
    }

    @Test
    void testDeleteUser() {
        User user = new User();
        user.setId(1L);
        userService.deleteUser(user.getId());
        verify(userRepository).deleteById(user.getId());
    }
}