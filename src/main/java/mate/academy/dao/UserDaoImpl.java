package mate.academy.dao;

import mate.academy.model.User;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByMail(String mail) {
        return Optional.empty();
    }
}
