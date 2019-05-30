package mate.academy.service;

import mate.academy.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> get(Long id);
    User update(User user);
    User delete(User user);
    Optional<List<User>> getAll();
}
