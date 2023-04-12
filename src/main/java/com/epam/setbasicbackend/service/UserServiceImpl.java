package com.epam.setbasicbackend.service;

import com.epam.setbasicbackend.exception.NoSuchUserException;
import com.epam.setbasicbackend.exception.EmailHadBeenTakenException;
import com.epam.setbasicbackend.model.User;
import com.epam.setbasicbackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) throws EmailHadBeenTakenException {
        if (findAllUsers().stream().anyMatch(u -> u.getEmail().equals(user.getEmail())))
            throw new EmailHadBeenTakenException("User with email \"" + user.getEmail() + "\" is already exists.");
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) throws NoSuchUserException {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchUserException("No such user have been found by id: " + id));
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User editUser(Long id, User user) throws NoSuchUserException {
        User originalUser = userRepository.findById(id).orElseThrow(() -> new NoSuchUserException("No such user have been found by id: " + id));

        if (!originalUser.getName().equals(user.getName())) {
            originalUser.setName(user.getName());
        }
        if (!originalUser.getRole().equals(user.getRole())) {
            originalUser.setRole(user.getRole());
        }
        if (!originalUser.getEmail().equals(user.getEmail())) {
            originalUser.setEmail(user.getEmail());
        }
        if (!originalUser.getPhone().equals(user.getPhone())) {
            originalUser.setPhone(user.getPhone());
        }
        if (!originalUser.getAddress().equals(user.getAddress())) {
            originalUser.setAddress(user.getAddress());
        }
        if (!originalUser.getLogin().equals(user.getLogin())) {
            originalUser.setLogin(user.getLogin());
        }
        if (!originalUser.getPassword().equals(user.getPassword())) {
            originalUser.setPassword(user.getPassword());
        }

        return userRepository.save(originalUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
