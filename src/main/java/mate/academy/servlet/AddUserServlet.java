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

@WebServlet(value = "/addUser")
public class AddUserServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AddUserServlet.class);
    private static final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User userNew = new User(login, email, password);
        userService.save(userNew);
        logger.info("user was added: " + userNew);
        response.sendRedirect("/hello");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("user on add user page");
        request.getRequestDispatcher("/views/addUser.jsp").forward(request, response);
    }
}
