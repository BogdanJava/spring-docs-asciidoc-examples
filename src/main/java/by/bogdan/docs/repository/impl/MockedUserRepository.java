package by.bogdan.docs.repository.impl;

import by.bogdan.docs.model.User;
import by.bogdan.docs.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Bogdan Shishkin
 * project: restdocs-examples
 * date/time: 10/15/2018 / 11:48 PM
 * email: bahdan.shyshkin@itechart-group.com
 */
@Slf4j
@Service
public class MockedUserRepository implements UserRepository {
    private static final List<User> users = new ArrayList<>();

    @Override
    public User save(User user) {
	user.setUserId(UUID.randomUUID());
	if (users.add(user)) {
	    return user;
	} else {
	    throw new RuntimeException("Error while saving user");
	}
    }

    @Override
    public Optional<User> findByUserId(UUID userId) {
	return users.stream().filter(u -> u.getUserId().equals(userId)).findFirst();
    }

    @Override
    public List<User> getAll() {
	return new ArrayList<>(users);
    }

    @Override
    public void remove(UUID userId) {
	users.removeIf(u -> u.getUserId().equals(userId));
    }

    @Override
    public User update(UUID userId, User user) {
	Optional<User> userToBeUpdated = users.stream().filter(u -> u.getUserId().equals(userId)).findFirst();
	userToBeUpdated.ifPresent(u -> this.updateUser(u, user));
	return userToBeUpdated.orElseThrow(() -> new RuntimeException("No user with such id: " + userId));
    }

    @Override
    public void removeAll() {
	users.removeIf(u -> true);
    }

    private void updateUser(User oldUser, User newUser) {
	oldUser.setName(newUser.getName());
	oldUser.setDescription(newUser.getDescription());
	oldUser.setSurname(newUser.getSurname());
    }
}
