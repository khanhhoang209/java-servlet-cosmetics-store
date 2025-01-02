/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import cosmetics.Cart;
import cosmetics.Cosmetics;
import cosmetics.ProductDAO;
import user.UserDTO;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author khanhhoang
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "confirmation.jsp";
    private static final String SUCCESS = "confirmation.jsp";
    private static final String LOGIN = "login.html";
    private static final String ERROR_MESSAGE = "Your purchase has some issue!";
    private static final String SUCCESS_MESSAGE = "Thanks for your purchase!";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            ProductDAO product = new ProductDAO();

            Cart cart = (Cart) session.getAttribute("CART");
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            double total = (double) session.getAttribute("TOTAL");
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            int cnt = 0;
            if (cart != null && loginUser != null) {
                String userID = loginUser.getUserID();
                if (address == null) {
                    address = loginUser.getAddress();
                }
                
                int orderID = product.saveToOrder(userID, total, address, phoneNumber);
                boolean checkUpdateQuantity = false;
                boolean checkOrderDetail = false;
                
                for (Cosmetics cosmetics : cart.getCart().values()) {
                    String id = cosmetics.getId();
                    int quantity = cosmetics.getQuantity();

                    int warehouseQuantity = product.getQuantity(id);
                    int newQuantity = warehouseQuantity - quantity;
                    double price = cosmetics.getPrice();
                    checkUpdateQuantity = product.UpdateQuantity(id, newQuantity);

                    if (orderID != -1) {
                        checkOrderDetail = product.saveToOrderDetail(orderID, id, price, quantity);
                    }
                    if (checkUpdateQuantity && checkOrderDetail) {
                        cnt++;
                    }

                }
                if (cnt>0) {
                        session.setAttribute("PHONE_NUMBER", phoneNumber);
                        session.setAttribute("MESSAGE", SUCCESS_MESSAGE);
                        cart.clear();
                        url = SUCCESS;
                    }
            } else {
                url = LOGIN;
                session.setAttribute("MESSAGE", ERROR_MESSAGE);
            }
        } catch (ClassNotFoundException | SQLException | NamingException e) {
            log("Error at CheckoutController: " + e.toString());
        } finally {
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
