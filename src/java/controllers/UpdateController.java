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
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {

    private static final String ERROR = "user.jsp";
    private static final String SUCCESS = "user.jsp";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final Pattern PATTERN  = Pattern.compile(EMAIL_PATTERN);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            UserError userError = new UserError();
            boolean checkValidation = true;
            
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            if (roleID == null) {
                roleID = "US";
            }
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            UserDAO dao = new UserDAO();
            UserDTO user = null;
            
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
            if (!confirmPassword.equals(password)) {
                checkValidation = false;
                userError.setConfirmError("Confirm must match the password!");
            }
            if (checkValidation) {
                user = new UserDTO(userID, fullName, roleID, email, address, password);
            }
            request.setAttribute("USER_ERROR", userError);
            
            boolean checkupdate = dao.update(user);
            if (loginUser.getUserID().equals(userID)) {
                if (checkupdate) {
                    url = SUCCESS;
                    loginUser.setFullName(fullName);
                    loginUser.setRoleID(roleID);
                    loginUser.setEmail(email);
                    loginUser.setAddress(address);
                    loginUser.setPassword(password);
                }
                session.setAttribute("LOGIN_USER",loginUser);
            } 

        } catch (Exception e) {
            log("ERROR at UpdateController: " + e.toString());
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
