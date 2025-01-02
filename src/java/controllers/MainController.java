/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author khanhhoang
 */
public class MainController extends HttpServlet {
    
    private static final String HOME = "Home";
    private static final String WELCOME = "home.jsp";

    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";

    private static final String SEARCH = "SearchUser";
    private static final String SEARCH_CONTROLLER = "SearchController";

    private static final String DELETE = "Delete";
    private static final String DELETE_CONTROLLER = "DeleteController";

    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";

    private static final String UPDATE = "Update";
    private static final String UPDATE_CONTROLLER = "UpdateController";

    private static final String CREATE = "Create";
    private static final String CREATE_CONTROLLER = "CreateController";

    private static final String SHOPPING = "Shopping";
    private static final String SHOPPING_CONTROLLER = "ShoppingController";

    private static final String ADD = "Add";
    private static final String ADD_CONTROLLER = "AddController";

    private static final String VIEW = "View";
    private static final String VIEW_PAGE = "viewCart.jsp";

    private static final String REMOVE = "Remove";
    private static final String REMOVE_CONTROLLER = "RemoveController";

    private static final String EDIT = "Edit";
    private static final String EDIT_CONTROLLER = "EditController";
    
    private static final String CONFIRM = "Confirm";
    private static final String CONFIRM_CONTROLLER = "ConfirmController";

    private static final String CHECKOUT = "Checkout";
    private static final String CHECKOUT_CONTROLLER = "CheckoutController";
    
    private static final String SEARCH_PRODUCT_BY_TYPE = "SearchProductByType";
    private static final String SEARCH_BY_TYPE_CONTROLLER = "SearchByTypeController";
    
    private static final String DASHBOARD = "Dashboard";
    private static final String DASHBOARD_CONTROLLER = "DashBoardController";
    
    private static final String SEARCHPRODUCT = "SearchProduct";
    private static final String SEARCH_PRODUCT_CONTROLLER = "SearchProductController";
    
    private static final String REMOVEPRODUCT = "RemoveProduct";
    private static final String REMOVE_PRODUCT_CONTROLLER = "RemoveProductController";
    
    private static final String EDIT_PRODUCT = "EditProduct";
    private static final String EDIT_PRODUCT_CONTROLLER = "EditProductController";
    
    private static final String EDIT_USER = "EditUser";
    private static final String EDIT_USER_CONTROLLER = "EditUserController";
    
    private static final String ADDPRODUCT = "AddProduct";
    private static final String ADD_PRODUCT_CONTROLLER = "AddProductController";
    
    private static final String LIST_PRODUCT_PAGE = "ListProductPage";
    private static final String LIST_PRODUCT_PAGE_VIEW = "listProduct.jsp";
    
    private static final String LIST_USER_PAGE = "ListUserPage";
    private static final String LIST_USER_PAGE_VIEW = "listUser.jsp";
    
    private static final String ADD_PRODUCT_PAGE = "AddProductPage";
    private static final String ADD_PRODUCT_PAGE_VIEW = "addProduct.jsp";
    
    private static final String ADD_USER_PAGE = "AddUserPage";
    private static final String ADD_USER_PAGE_VIEW = "addUser.jsp";

    private static final String ADD_USER = "AddUser";
    private static final String ADD_USER_CONTROLLER = "AddUserController";
    
    private static final String FORGOT_PASSWORD_PAGE = "ForgotPasswordPage";
    private static final String FORGOT_PASSWORD_PAGE_VIEW = "forgotPassword.jsp";
    
    private static final String FORGOT_PASSWORD = "ForgotPassword";
    private static final String FORGOT_PASSWORD_CONTROLLER = "ForgotPasswordController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = WELCOME;
        try {
            String action = request.getParameter("action");
            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (SEARCH.equals(action)) {
                url = SEARCH_CONTROLLER;
            } else if (HOME.equals(action)) {
                url = WELCOME;
            } else if (DELETE.equals(action)) {
                url = DELETE_CONTROLLER;
            } else if (LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;
            } else if (UPDATE.equals(action)) {
                url = UPDATE_CONTROLLER;
            } else if (CREATE.equals(action)) {
                url = CREATE_CONTROLLER;
            } else if (SHOPPING.equals(action)) {
                url = SHOPPING_CONTROLLER;
            } else if (ADD.equals(action)) {
                url = ADD_CONTROLLER;
            } else if (VIEW.equals(action)) {
                url = VIEW_PAGE;
            } else if (REMOVE.equals(action)) {
                url = REMOVE_CONTROLLER;
            } else if (EDIT.equals(action)) {
                url = EDIT_CONTROLLER;
            } else if (CONFIRM.equals(action)) {
                url = CONFIRM_CONTROLLER;
            } else if (CHECKOUT.equals(action)) {
                url = CHECKOUT_CONTROLLER;
            } else if (SEARCH_PRODUCT_BY_TYPE.equals(action)) {
                url = SEARCH_BY_TYPE_CONTROLLER;
            } else if (DASHBOARD.equals(action)) {
                url = DASHBOARD_CONTROLLER;
            } else if (SEARCHPRODUCT.equals(action)) {
                url = SEARCH_PRODUCT_CONTROLLER;
            } else if (REMOVEPRODUCT.equals(action)) {
                url = REMOVE_PRODUCT_CONTROLLER;
            } else if (EDIT_PRODUCT.equals(action)) {
                url = EDIT_PRODUCT_CONTROLLER;
            } else if (ADDPRODUCT.equals(action)) {
                url = ADD_PRODUCT_CONTROLLER;
            } else if (ADD_PRODUCT_PAGE.equals(action)) {
                url = ADD_PRODUCT_PAGE_VIEW;
            } else if (EDIT_USER.equals(action)) {
                url = EDIT_USER_CONTROLLER;
            } else if (ADD_USER_PAGE.equals(action)) {
                url = ADD_USER_PAGE_VIEW;
            } else if (ADD_USER.equals(action)) {
                url = ADD_USER_CONTROLLER;
            } else if (LIST_PRODUCT_PAGE.equals(action)) {
                url = LIST_PRODUCT_PAGE_VIEW;
            } else if (LIST_USER_PAGE.equals(action)) {
                url = LIST_USER_PAGE_VIEW;
            } else if (FORGOT_PASSWORD_PAGE.equals(action)) {
                url = FORGOT_PASSWORD_PAGE_VIEW;
            } else if (FORGOT_PASSWORD.equals(action)) {
                url = FORGOT_PASSWORD_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
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
