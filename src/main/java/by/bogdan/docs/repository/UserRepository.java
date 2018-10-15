package by.bogdan.docs.repository;

import by.bogdan.docs.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Bogdan Shishkin
 * project: restdocs-examples
 * date/time: 10/15/2018 / 11:47 PM
 * email: bahdan.shyshkin@itechart-group.com
 */
public interface UserRepository {
    User save(User user);
    Optional<User> findByUserId(UUID userId);
    List<User> getAll();
    void remove(UUID userId);
    User update(UUID userId, User user);
    void removeAll();
}
