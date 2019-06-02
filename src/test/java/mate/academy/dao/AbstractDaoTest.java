package mate.academy.dao;

import mate.academy.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbstractDaoTest {
    UserDao userDao;
    User userToSave;

    @Before
    public void init() {
        userDao = new UserDaoImpl();
        userToSave = new User(44L, "someUser", "someEmail", "somePass");
    }

    @After
    public void clear() {
        userDao.delete(userToSave);
    }

    @Test
    public void save() {
        User user = userDao.save(userToSave);
        User userGet = userDao.get(44L).orElseGet(User::new);
        Assert.assertEquals(user, userGet);
    }

    @Test
    public void get() {
        userDao.save(userToSave);
        User userGet = userDao.get(44L).orElseGet(User::new);
        Assert.assertEquals(userToSave, userGet);
    }

    @Test
    public void update() {
        userDao.save(userToSave);
        userToSave.setEmail("newEmail");
        userDao.update(userToSave);
        User userGet = userDao.get(44L).orElseGet(User::new);
        Assert.assertEquals(userToSave, userGet);
    }

    @Test
    public void delete() {
        userDao.save(userToSave);
        userDao.delete(userToSave);
        Assert.assertFalse(userDao.get(44L).isPresent());
    }

    @Test
    public void getAll() {
        userDao.save(userToSave);
        Assert.assertFalse(userDao.getAll().get().isEmpty());
    }
}