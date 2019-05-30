package mate.academy.service;

import mate.academy.dao.UserDao;
import mate.academy.dao.UserDaoImpl;
import mate.academy.model.User;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final UserDao userDao = new UserDaoImpl();

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public Optional<User> get(Long id) {
        return userDao.get(id);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public User delete(User user) {
        return userDao.delete(user);
    }

    @Override
    public Optional<List<User>> getAll() {
        return userDao.getAll();
    }
}
