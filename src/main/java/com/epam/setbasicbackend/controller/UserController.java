package com.epam.setbasicbackend.controller;

import com.epam.setbasicbackend.exception.NoSuchUserException;
import com.epam.setbasicbackend.exception.EmailHadBeenTakenException;
import com.epam.setbasicbackend.model.User;
import com.epam.setbasicbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get a user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User is found by id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404", description = "No such user have been found by id",
                    content = @Content) })
    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@Parameter(description = "User ID") @PathVariable Long id) throws NoSuchUserException {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @Operation(summary = "Get a list of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of users is returned",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) })})
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @Operation(summary = "Create new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New user is created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) })})
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws EmailHadBeenTakenException {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @Operation(summary = "Edit user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User has been edited",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)),
                    }),
            @ApiResponse(responseCode = "404", description = "No such user have been found by id",
                    content = @Content)})
    @PutMapping("{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User user) throws NoSuchUserException {
        return ResponseEntity.ok(userService.editUser(id, user));
    }

    @Operation(summary = "Delete a user by id")
    @ApiResponse(responseCode = "200", description = "User has been deleted", content = @Content)
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User with id " + id + " has been deleted.");
    }
}
