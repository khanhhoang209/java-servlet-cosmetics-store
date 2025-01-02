/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import cosmetics.Cosmetics;
import cosmetics.ProductDAO;

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
@WebServlet(name = "SearchByTypeController", urlPatterns = {"/SearchByTypeController"})
public class SearchByTypeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "shopping.jsp";
    private static final String SUCCESS = "shopping.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String category = request.getParameter("category");
            String pageNumber =  request.getParameter("pageNumber");
            if (pageNumber == null) {
                pageNumber = "1";
            }
            int index = Integer.parseInt(pageNumber);
            
            HttpSession session = request.getSession();
            
            ProductDAO productDAO = new ProductDAO();
            int count = productDAO.getTotalForSearchByType(category);
            int endPage = count / 6;
            if (count % 6 != 0) {
                endPage++;
            }
            
            List<Cosmetics> listCosmetics = productDAO.searchByType(index, category);
            if (listCosmetics.size() > 0) {
                session.setAttribute("LIST_COSMETICS", listCosmetics);
                request.setAttribute("ACTION", "SearchProductByType");
                request.setAttribute("CATEGORY", category);
                request.setAttribute("END_PAGE", endPage);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at ShoppingController: " + e.toString());
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
