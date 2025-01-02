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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author khanhhoang
 */
@WebServlet(name = "AddUserController", urlPatterns = {"/AddUserController"})
public class AddUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "addUser.jsp";
    private static final String SUCCESS = "addUser.jsp";
    private static final String ERROR_MESSAGE = "Cannot add user because of some issues!";
    private static final String SUCCESS_MESSAGE = "Add user Succsessfully!";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final Pattern PATTERN  = Pattern.compile(EMAIL_PATTERN);
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError();
        try {
            boolean checkValidation = true;
            HttpSession session = request.getSession();
            UserDAO userDAO = new UserDAO();
            
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            
            if (userID.length() < 2 || userID.length() > 10) {
                checkValidation = false;
                userError.setUserIDError("UserID must be in [2,10]");
            }
            if (fullName.length() < 5 || fullName.length() > 50) {
                checkValidation = false;
                userError.setFullNameError("Full Name must be in [5,50]!");
            }
            
            if (roleID.equals("Role ID")) {
                checkValidation = false;
                userError.setRoleIDError("Please choose role ID!");
            }
            
            if (!isValid(email)) {
                checkValidation = false;
                userError.setEmailError("Invalid email!");
            }
            
            boolean checkDuplicateEmail = userDAO.checkDuplicateEmail(email);
            if (checkDuplicateEmail) {
                checkValidation = false;
                userError.setEmailError("Duplicate email!");
            }
            
            
            if (!confirm.equals(password)) {
                checkValidation = false;
                userError.setConfirmError("Confirm must match the password!");
            }

            boolean checkDuplicate = userDAO.checkDuplicate(userID);
            if (checkDuplicate) {
                checkValidation = false;
                userError.setUserIDError("Duplicate User ID!");
            }

            if (checkValidation) {
                boolean checkInsert = userDAO.insert(new UserDTO(userID, fullName, roleID, password, email, address));
                if (checkInsert) {
                    session.setAttribute("ADMIN_COMMAND", "addUser");
                    request.setAttribute("MESSAGE", SUCCESS_MESSAGE);
                    url = SUCCESS;
                } else {
                    request.setAttribute("MESSAGE", ERROR_MESSAGE);
                    userError.setError(ERROR_MESSAGE);
                }
            } else {
                request.setAttribute("MESSAGE", ERROR_MESSAGE);
                userError.setError(ERROR_MESSAGE);
            }
            
            request.setAttribute("USER_ERROR", userError);

            
        } catch (Exception e) {
            log("Error at AddUserController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    public static boolean isValid(final String email) {
        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
