/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import user.UserDAO;
import user.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author khanhhoang
 */
@WebServlet(name = "DeleteController", urlPatterns = {"/DeleteController"})
public class DeleteController extends HttpServlet {
    
    private static final String ERROR="listUser.jsp";
    private static final String SUCCESS="listUser.jsp";
    private static final String ERROR_MESSAGE="Cannot to delete online users!";
    private static final String SUCCESS_MESSAGE="Delele user successfully!";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try  {
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String userID = request.getParameter("userID");
            UserDAO dao = new UserDAO();
            int orderID = dao.checkUserID(userID);
            boolean checkRemoveOrderDetail = true;
            boolean checkRemoveOrder = true;
            if (orderID > 0) {
                checkRemoveOrderDetail = dao.deleteOrderDetail(orderID);
                if (checkRemoveOrderDetail) {
                    checkRemoveOrder = dao.deleteUserOrder(userID);
                }
            }
            if (checkRemoveOrder) {
                if(loginUser.getUserID().equals(userID)){
                    request.setAttribute("MESSAGE", ERROR_MESSAGE);
                }else{
                    boolean checkDelete = dao.delete(userID);
                    if(checkDelete){
                        request.setAttribute("MESSAGE", SUCCESS_MESSAGE);
                        url = SUCCESS;
                    }
                }
            }
        }catch(Exception e){
            log("Error a DeleteController: "+ e.toString());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
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
