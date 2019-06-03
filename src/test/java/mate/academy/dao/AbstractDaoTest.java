package mate.academy.dao;

import mate.academy.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AbstractDaoTest {

    @Mock
    private DatabaseConnector databaseConnector;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private User user;

    @Before
    public void setUp() throws Exception {
        Assert.assertNotNull(databaseConnector);
        Mockito.when(connection.prepareStatement(Matchers.any(String.class))).thenReturn(preparedStatement);
        Mockito.when(databaseConnector.connect()).thenReturn(Optional.of(connection));
        user = new User();
        user.setId(55L);
        user.setLogin("login");
        user.setEmail("email@gmail.com");
        user.setPassword("password");
        Mockito.when(resultSet.next()).thenReturn(true);
        Mockito.when(resultSet.getLong(1)).thenReturn(55L);
        Mockito.when(resultSet.getString(2)).thenReturn(user.getLogin());
        Mockito.when(resultSet.getString(3)).thenReturn(user.getEmail());
        Mockito.when(resultSet.getString(4)).thenReturn(user.getPassword());
    }

    @Test(expected = NullPointerException.class)
    public void saveNullUser() {
        new UserDaoImpl().save(null);
    }

    @Test
    public void saveUser() {
        new UserDaoImpl().save(user);
    }

    @Test
    public void saveAndGet() {
        UserDao userDao = new UserDaoImpl();
        userDao.save(user);
        User userFromDb = userDao.get(55L).orElseGet(User::new);
        Assert.assertEquals(user, userFromDb);
    }

    @Test
    public void saveUpdateGet() {
        UserDao userDao = new UserDaoImpl();
        userDao.save(user);
        user.setPassword("newPassword");
        userDao.update(user);
        User userFromDb = userDao.get(55L).orElseGet(User::new);
        Assert.assertEquals(user, userFromDb);
    }
}