package mate.academy.servlet;

import mate.academy.model.User;
import mate.academy.service.UserService;
import mate.academy.service.UserServiceImpl;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(value = "/users")
public class UsersListServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UsersListServlet.class);
    private static final UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("user on users page");
        List<User> users = userService.getAll().orElseGet(Collections::emptyList);
        request.setAttribute("users", users);
        request.getRequestDispatcher("/views/users.jsp").forward(request, response);
    }
}
