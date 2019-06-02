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

@WebServlet(value = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(DeleteUserServlet.class);
    private static final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("user on delete page");
        Long id = Long.parseLong(request.getParameter("id"));
        User userToDelete = userService.get(id).orElseGet(User::new);
        userService.delete(userToDelete);
        logger.warn(userToDelete + " was deleted");
        response.sendRedirect("/users");
    }
}
