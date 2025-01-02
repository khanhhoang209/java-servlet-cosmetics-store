/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import cosmetics.Cosmetics;
import cosmetics.ProductDAO;
import user.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author khanhhoang
 */
@WebServlet(name = "SearchProductController", urlPatterns = {"/SearchProductController"})
public class SearchProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "listProduct.jsp";
    private static final String SUCCESS = "listProduct.jsp";

    private static final String SEARCH_FOR_USER_SUCCESS = "shopping.jsp";
    private static final String SEARCH_FOR_USER_ERROR = "shopping.jsp";

    private static final String US = "US";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url;
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
        if (loginUser == null || US.equals(loginUser.getRoleID())) {
            url = SEARCH_FOR_USER_ERROR;
        } else {
            url = ERROR;
        }
        try {
            String productName = request.getParameter("productName");
            if (productName == null) {
                productName = "";
            }

            ProductDAO productDAO = new ProductDAO();
            List<Cosmetics> listCosmetics = productDAO.searchProductByName(productName);
            if (listCosmetics.size() > 0) {
                session.setAttribute("LIST_COSMETICS", listCosmetics);
                if (loginUser == null || US.equals(loginUser.getRoleID())) {
                    url = SEARCH_FOR_USER_SUCCESS;
                } else {
                    session.setAttribute("ADMIN_COMMAND", "listProduct");
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at SearchProductController: " + e.toString());
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
