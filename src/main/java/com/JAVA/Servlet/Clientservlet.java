package com.JAVA.Servlet;

import java.io.IOException;

import com.JAVA.Beans.ClientBean;
import com.JAVA.DAO.ClientDAO;
import com.JAVA.utils.DAOException;
import com.JAVA.utils.DAOFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Clientservlet")
public class Clientservlet extends HttpServlet {
    private ClientDAO clientDAO;

   
    public void init() throws ServletException {
        this.clientDAO = DAOFactory.getInstance().getClientDao();
    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createClient(request, response);
        }
    }

    private void createClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String contact = request.getParameter("contact");
        String address = request.getParameter("adress");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        ClientBean clientBean = new ClientBean();
        clientBean.setNom(name);
        clientBean.setContact(contact);
        clientBean.setAdress(address);
        clientBean.setEmail(email);
        clientBean.setpassword(password);

        try {
            clientDAO.create(clientBean, email, password);  // Appel à la méthode create de clientDAO
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (DAOException e) {
            throw new ServletException("Erreur lors de la création du client", e);
        }
    }
}

