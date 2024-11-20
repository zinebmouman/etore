package com.JAVA.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import com.JAVA.Beans.User;
import com.JAVA.DAO.AuthDAO;
import com.JAVA.utils.DAOConfigurationException;
import com.JAVA.utils.DAOFactory;

@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AuthDAO authDAO;

    @Override
    public void init() {
        try {
            authDAO = DAOFactory.getInstance().getAuthDAO();
        } catch (DAOConfigurationException | SQLException e) {
            e.printStackTrace(); 
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vérifier si la requête est une déconnexion
        String action = request.getParameter("action");
        if ("logout".equals(action)) {
            // Invalidation de la session
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            // Rediriger vers la page de connexion après la déconnexion
            response.sendRedirect("/jee_liv/general/Sign_in.jsp");
        } else {
            // Si ce n'est pas une déconnexion, rediriger vers la page de connexion par défaut
            response.sendRedirect("/jee_liv/general/Sign_in.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = authDAO.authenticate(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            Long userId = user.getUserId(); // Récupérer l'ID de l'utilisateur

            // Redirection selon le type d'utilisateur
            switch (user.getType()) {
                case 1:
                    response.sendRedirect("admin/views/index.jsp?user_id=" + userId); // Ajout de user_id à l'URL
                    break;
                case 2:
                    response.sendRedirect("commerce/views/index.jsp?user_id=" + userId); // Ajout de user_id à l'URL
                    break;
                case 3:
                	session.setAttribute("LivreurId", userId);
                    response.sendRedirect("LivreurCommande?LivreurId=" + userId); // Ajout de user_id à l'URL
                    break;
                case 4:
                	session.setAttribute("clientId", userId);
                	response.sendRedirect("listeProduits?clientId=" + userId);
                    break;
                default:
                    response.sendRedirect("login.jsp?error=InvalidType");
                    break;
            }
        } else {
            response.sendRedirect("login.jsp?error=InvalidCredentials");
        }
    }
}
