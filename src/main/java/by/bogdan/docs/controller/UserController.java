package by.bogdan.docs.controller;

import by.bogdan.docs.model.User;
import by.bogdan.docs.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Bogdan Shishkin
 * project: restdocs-examples
 * date/time: 10/15/2018 / 11:46 PM
 * email: bahdan.shyshkin@itechart-group.com
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
	this.userRepository = userRepository;
    }

    @GetMapping("/{userId}")
    public ResponseEntity getById(@PathVariable UUID userId) {
	return userRepository.findByUserId(userId)
		.map(ResponseEntity::ok)
		.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestBody User user) {
	return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/all")
    public ResponseEntity grabUsers() {
	return ResponseEntity.ok(userRepository.getAll());
    }

    @PutMapping("/{userId}")
    public ResponseEntity updateUser(@PathVariable UUID userId,
				     @RequestBody User user) {
	return ResponseEntity.ok(userRepository.update(userId, user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable UUID userId) {
	userRepository.remove(userId);
	return ResponseEntity.ok().build();
    }
}
