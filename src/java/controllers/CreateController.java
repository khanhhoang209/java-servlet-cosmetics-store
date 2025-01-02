/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import user.UserDAO;
import user.UserDTO;
import user.UserError;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author khanhhoang
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private static final String ERROR = "create.jsp";
    private static final String SUCCESS = "login.html";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final Pattern PATTERN  = Pattern.compile(EMAIL_PATTERN);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserError userError = new UserError();
        String url = ERROR;

        try {
            boolean checkValidation = true;
            UserDAO dao = new UserDAO();

            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");

            if (userID.length() < 2 || userID.length() > 10) {
                checkValidation = false;
                userError.setUserIDError("UserID must be in [2,10]!");
            }
            if (fullName.length() < 5 || fullName.length() > 50) {
                checkValidation = false;
                userError.setFullNameError("Full Name must be in [5,50]!");
            }
            if (!isValid(email)) {
                checkValidation = false;
                userError.setEmailError("Invalid email!");
            }
            
            boolean checkDuplicateEmail = dao.checkDuplicateEmail(email);
            if (checkDuplicateEmail) {
                checkValidation = false;
                userError.setEmailError("Duplicate email!");
            }
            
            if (!confirm.equals(password)) {
                checkValidation = false;
                userError.setConfirmError("Confirm must match the password!");
            }

            boolean checkDuplicate = dao.checkDuplicate(userID);
            if (checkDuplicate) {
                checkValidation = false;
                userError.setUserIDError("Duplicate User ID!");
            }

            if (checkValidation) {
                boolean checkInsert = dao.insert(new UserDTO(userID, fullName, roleID, email, address, password));
                if (checkInsert) {
                    url = SUCCESS;
                } else {
                    userError.setError("Unknown error occurred while creating user");
                }
            }
            
            request.setAttribute("USER_ERROR", userError);

        } catch (Exception e) {
            log("Error at CreateController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    
    public static boolean isValid(final String email) {
        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
