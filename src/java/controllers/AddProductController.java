/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import cosmetics.Cosmetics;
import cosmetics.ProductDAO;
import cosmetics.ProductError;

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
@WebServlet(name = "AddProductController", urlPatterns = {"/AddProductController"})
public class AddProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String ERROR = "addProduct.jsp";
    private static final String SUCCESS = "addProduct.jsp";
    private static final String ERROR_MESSAGE = "Cannot add product because of some issues!";
    private static final String SUCCESS_MESSAGE = "Add Product Succsessfully!";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductError productError = new ProductError();
        try {
            boolean checkValidation = true;
            ProductDAO productDAO = new ProductDAO();
            HttpSession session = request.getSession();
            String productID = request.getParameter("productID");
            String productName = request.getParameter("productName");
            String category = request.getParameter("category");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String image = request.getParameter("image");
            String imagePath = "images/" + category.toLowerCase() + "/" + image;
            String describe = request.getParameter("describe");
            if (describe == null) {
                describe = "";
            }
            
            if (productID.length() < 2 || productID.length() > 10) {
                checkValidation = false;
                productError.setProductIDError("Product ID must be in [3-10]!");
            }
            
            if (productName.length() < 3 || productName.length() > 50) {
                checkValidation = false;
                productError.setNameError("Product Name must be in [3-50]!");
            }
            
            if (category.equals("Category")) {
                checkValidation = false;
                productError.setCategoryError("Please choose category!");
            }
            
            if (price < 0) {
                checkValidation = false;
                productError.setPriceError("Price must be greater than 0!");
            }
            
            if (quantity < 0) {
                checkValidation = false;
                productError.setQuantityError("Quantity must be greater than 0!");
            }
            
            boolean checkDuplicate = productDAO.checkDuplicate(productID);
            if (checkDuplicate) {
                checkValidation = false;
                productError.setProductIDError("Duplicate Product ID!");
            }
            
            if (checkValidation) {
                Cosmetics cosmetics = new Cosmetics(productID, productName, category, quantity, price, imagePath, describe);
                boolean checkAdd = productDAO.addProduct(cosmetics);
                if (checkAdd) {
                    session.setAttribute("ADMIN_COMMAND", "addProduct");
                    request.setAttribute("MESSAGE", SUCCESS_MESSAGE);
                    url = SUCCESS;
                } else {
                    request.setAttribute("MESSAGE", ERROR_MESSAGE);
                    productError.setError(ERROR_MESSAGE);
                } 
            } else {
                request.setAttribute("MESSAGE", ERROR_MESSAGE);
                productError.setError(ERROR_MESSAGE);
            }
            request.setAttribute("PRODUCT_ERROR", productError);
        } catch (Exception e) {
            log("Error at AddProductController: " + e.toString());
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
