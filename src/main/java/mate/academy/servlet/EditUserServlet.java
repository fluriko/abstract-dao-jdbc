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

@WebServlet(value = "/editUser")
public class EditUserServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(EditUserServlet.class);
    private static final UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        User userFromDb = userService.get(id).orElseGet(User::new);
        String loginFromForm = request.getParameter("login");
        String emailFromForm = request.getParameter("email");
        String passwordFromForm = request.getParameter("password");
        userFromDb.setLogin(loginFromForm);
        userFromDb.setEmail(emailFromForm);
        if (!passwordFromForm.isEmpty()) {
            userFromDb.setPassword(passwordFromForm);
        }
        userService.update(userFromDb);
        logger.warn("data was changed for user: " + userFromDb);
        response.sendRedirect("/users");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.debug("user on edit user page");
        Long id = Long.parseLong(request.getParameter("id"));
        User userToEdit = userService.get(id).orElseGet(User::new);
        request.setAttribute("userToEdit", userToEdit);
        request.getRequestDispatcher("views/editUser.jsp").forward(request, response);
    }
}
