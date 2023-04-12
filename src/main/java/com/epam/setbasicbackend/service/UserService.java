package com.epam.setbasicbackend.service;

import com.epam.setbasicbackend.exception.NoSuchUserException;
import com.epam.setbasicbackend.exception.EmailHadBeenTakenException;
import com.epam.setbasicbackend.model.User;
import java.util.List;

public interface UserService {

    User createUser(User user) throws EmailHadBeenTakenException;

    User findUserById(Long id) throws NoSuchUserException;

    List<User> findAllUsers();

    User editUser(Long id, User user) throws NoSuchUserException;

    void deleteUser(Long id);
}
