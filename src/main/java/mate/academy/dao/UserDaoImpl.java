package mate.academy.dao;

import mate.academy.model.User;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User save(User object) {
        if (object.getId() == null) {
            return super.save(object);
        } else {
            return saveWithId(object);
        }
    }

    private User saveWithId(User user) {
        String query = "INSERT INTO USERS(ID, LOGIN, EMAIL, PASSWORD) VALUES(?,?,?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception in saving to h2", e);
        }
        return user;
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
