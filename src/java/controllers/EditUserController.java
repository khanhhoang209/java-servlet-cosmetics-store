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
@WebServlet(name = "EditUserController", urlPatterns = {"/EditUserController"})
public class EditUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "listUser.jsp";
    private static final String SUCCESS = "listUser.jsp";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

    private static final String ERROR_MESSAGE = "Cannot edit user because of some issue!";
    private static final String SUCCESS_MESSAGE = "Edit user successfully!";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            UserDAO userDAO = new UserDAO();
            UserError userError = new UserError();
            boolean checkValidation = true;
            
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String password = "*****";
            String email = request.getParameter("email");
            String address = request.getParameter("address");

            if (fullName.length() < 5 || fullName.length() > 50) {
                checkValidation = false;
                userError.setFullNameError("Full Name must be in [5,50]");
            }
            
            if (!isValid(email)) {
                checkValidation = false;
                userError.setEmailError("Invalid email!");
            }
            
            if (checkValidation) {
                UserDTO user = new UserDTO(userID, fullName, roleID, password, email, address);
                boolean checkEdit = userDAO.EditUser(user);

                if (checkEdit) {
                    request.setAttribute("MESSAGE", SUCCESS_MESSAGE);
                    url = SUCCESS;
                } else {
                    request.setAttribute("MESSAGE", ERROR_MESSAGE);
                }
                if (loginUser.getUserID().equals(userID)) {
                    loginUser.setFullName(fullName);
                    loginUser.setRoleID(roleID);
                    loginUser.setEmail(email);
                    loginUser.setAddress(address);
                    loginUser.setPassword(password);
                    session.setAttribute("LOGIN_USER", loginUser);
                }
            } else {
                request.setAttribute("MESSAGE", ERROR_MESSAGE);
            }
            request.setAttribute("USER_ERROR", userError);

        } catch (Exception e) {
            log("Error at EditUserController: " + e.toString());
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
