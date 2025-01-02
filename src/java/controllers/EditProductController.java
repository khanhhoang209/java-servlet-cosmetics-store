/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import cosmetics.ProductDAO;
import cosmetics.ProductError;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author khanhhoang
 */
@WebServlet(name = "EditProductController", urlPatterns = {"/EditProductController"})
public class EditProductController extends HttpServlet {

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
    private static final String ERROR_MESSAGE = "Cannot edit product because of some issue!";
    private static final String SUCCESS_MESSAGE = "Edit product successfully!";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductError productError = new ProductError();
        try {
            boolean checkValidation = true;
            
            String productID = request.getParameter("id");
            String name = request.getParameter("productName");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            
            ProductDAO productDAO = new ProductDAO();
            
            if (name.length() < 3 || name.length() > 50) {
                checkValidation = false;
                productError.setNameError("Product Name must be in [3-50]!");
            }
            
            if (price < 0) {
                checkValidation = false;
                productError.setPriceError("Price must be greater than 0!");
            }
            
            if (quantity < 0) {
                checkValidation = false;
                productError.setQuantityError("Quantity must be greater than 0!");
            }
            
            if (checkValidation) {
                    boolean checkEdit = productDAO.editProduct(productID, name, price, quantity);
                if (checkEdit) {
                    url = SUCCESS;
                    request.setAttribute("MESSAGE", SUCCESS_MESSAGE);
                } else {
                    request.setAttribute("MESSAGE", ERROR_MESSAGE);
                }
            } else {
                request.setAttribute("MESSAGE", ERROR_MESSAGE);
                productError.setError(ERROR_MESSAGE);
            }
            request.setAttribute("PRODUCT_ERROR", productError);
        } catch (Exception e) {
            log("Error at EditProductController: " + e.toString());
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
