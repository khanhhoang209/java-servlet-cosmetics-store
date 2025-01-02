/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cosmetics;

import utils.DBUtils;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khanhhoang
 */
public class ProductDAO {

    private static final String SAVETOORDER = "INSERT INTO [dbo].[tblOrder](userID, date, total, address, phoneNumber) VALUES (?, GETDATE(), ?, ?, ?)";
    private static final String SAVETOORDERDETAIL = "INSERT INTO [dbo].[tblOrderDetail](orderID, productID, price, quantity) VALUES (?, ?, ?, ?)";
    private static final String QUANTITY = "SELECT quantity FROM [dbo].[tblProducts] WHERE productID = ?";
    private static final String UPDATE = "UPDATE [dbo].[tblProducts] SET quantity = ? WHERE productID = ?";
    private static final String GETTOTAL = "SELECT COUNT(productID) FROM [dbo].[tblProducts]";
    private static final String GETTOTALFORSEARCHBYTYPE = "SELECT COUNT(productID) FROM [dbo].[tblProducts] WHERE category LIKE ?";
    private static final String PAGINGPRODUCT = "SELECT productID, name, category, price, quantity, image, describe FROM tblProducts ORDER BY productID OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";
    private static final String SEARCHBYTYPE = "SELECT productID, name, category, price, quantity, image, describe FROM tblProducts  WHERE category LIKE ? ORDER BY productID OFFSET ? ROWS FETCH NEXT 6 ROWS ONLY";
    private static final String NUMBEROFSALE = "SELECT COUNT(orderID) FROM [dbo].[tblOrder]";
    private static final String TOTALPROCEED = "SELECT SUM(total) FROM [dbo].[tblOrder]";
    private static final String SEARCHPRODUCTBYNAME = "SELECT productID, name, category, price, quantity, image, describe FROM tblProducts WHERE name LIKE ?";
    private static final String DELETE = "DELETE FROM tblProducts WHERE productID = ?";
    private static final String DELETEORDERDETAIL = "DELETE FROM tblOrderDetail WHERE productID = ?";
    private static final String EDIT = "UPDATE tblProducts SET name = ?, price = ?, quantity = ? WHERE productID = ?";
    private static final String INSERT = "INSERT [dbo].[tblProducts] ([productID], [name], [category], [price], [quantity], [image], [describe]) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String DUPLICATE = "SELECT name FROM tblProducts WHERE productID=? ";
    private static final String PRODUCTIDINORDERDETAIL = "SELECT price FROM tblOrderDetail WHERE productID=? ";
    
    
    public int saveToOrder(String userID, double total, String address, String phoneNumber) throws SQLException, ClassNotFoundException, NamingException {
        int orderID = -1;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SAVETOORDER, Statement.RETURN_GENERATED_KEYS);
                ptm.setString(1, userID);
                ptm.setDouble(2, total);
                ptm.setString(3, address);
                ptm.setString(4, phoneNumber);
                if (ptm.executeUpdate() > 0) {
                    rs = ptm.getGeneratedKeys();
                    if (rs.next()) {
                        orderID = rs.getInt(1);
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return orderID;
    }

    public boolean saveToOrderDetail(int orderID, String productID, double price, int quantity) throws ClassNotFoundException, SQLException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SAVETOORDERDETAIL);
                ptm.setInt(1, orderID);
                ptm.setString(2, productID);
                ptm.setDouble(3, price);
                ptm.setInt(4, quantity);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return check;
    }

    public int getQuantity(String cosmeticsID) throws SQLException, ClassNotFoundException, NamingException {
        int quantity = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(QUANTITY);
                ptm.setString(1, cosmeticsID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return quantity;
    }

    public boolean UpdateQuantity(String id, int newQuantity) throws ClassNotFoundException, SQLException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setInt(1, newQuantity);
                ptm.setString(2, id);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return check;
    }
    
    public int getTotalProduct() throws ClassNotFoundException, SQLException, NamingException {
        int total = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETTOTAL);
                rs = ptm.executeQuery();
                while (rs.next()) {                    
                    total = rs.getInt(1);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            } 
        }
        return total;
    }
    
    public List<Cosmetics> pagingProduct(int index) throws ClassNotFoundException, SQLException, NamingException {
        List<Cosmetics> listCosmetics = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PAGINGPRODUCT);
                int offset = (index - 1) * 6;
                ptm.setInt(1, offset);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("productID");
                    String name = rs.getString("name");
                    String category = rs.getString("category");
                    double price = Double.parseDouble(rs.getString("price"));
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    String image = rs.getString("image");
                    String describe = rs.getString("describe");

                    listCosmetics.add(new Cosmetics(userID, name, category, quantity, price, image, describe));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listCosmetics;
    } 
    
    public int getTotalForSearchByType(String category) throws ClassNotFoundException, SQLException, NamingException {
        int total = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETTOTALFORSEARCHBYTYPE);
                ptm.setString(1, "%" + category + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {                    
                    total = rs.getInt(1);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            } 
        }
        return total;
    }

    public List<Cosmetics> searchByType(int index, String category) throws ClassNotFoundException, SQLException, NamingException {
        List<Cosmetics> listCosmetics = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCHBYTYPE);
                int offset = (index - 1) * 6;
                ptm.setString(1, "%" + category + "%");
                ptm.setInt(2, offset);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("productID");
                    String name = rs.getString("name");
                    category = rs.getString("category");
                    double price = Double.parseDouble(rs.getString("price"));
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    String image = rs.getString("image");
                    String describe = rs.getString("describe");

                    listCosmetics.add(new Cosmetics(userID, name, category, quantity, price, image, describe));
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listCosmetics;
    }

    public int getNumberOfSale() throws ClassNotFoundException, SQLException, NamingException {
        int total = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(NUMBEROFSALE);
                rs = ptm.executeQuery();
                while (rs.next()) {                    
                    total = rs.getInt(1);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            } 
        }
        return total;
    }

    public double getTotalProceed() throws ClassNotFoundException, SQLException, NamingException {
        int total = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(TOTALPROCEED);
                rs = ptm.executeQuery();
                while (rs.next()) {                    
                    total = rs.getInt(1);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            } 
        }
        return total;
    }

    public List<Cosmetics> searchProductByName(String productName) throws ClassNotFoundException, SQLException, NamingException {
        List<Cosmetics> listCosmetics = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCHPRODUCTBYNAME);
                ptm.setString(1, "%" + productName + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    String category = rs.getString("category");
                    double price = Double.parseDouble(rs.getString("price"));
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    String image = rs.getString("image");
                    String describe = rs.getString("describe");
                    listCosmetics.add(new Cosmetics(productID, name, category, quantity, price, image, describe));
                }
            }
            
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            } 
        }
        return listCosmetics;
    }

    public boolean removeProduct(String productID) throws NamingException, ClassNotFoundException, SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, productID);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean editProduct(String productID, String name, double price, int quantity) throws ClassNotFoundException, SQLException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(EDIT);
                ptm.setString(1, name);
                ptm.setDouble(2, price);
                ptm.setInt(3, quantity);
                ptm.setString(4, productID);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean removeProductOrderDetail(String productID) throws ClassNotFoundException, SQLException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETEORDERDETAIL);
                ptm.setString(1, productID);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean addProduct(Cosmetics cosmetics) throws ClassNotFoundException, SQLException, NamingException {
         boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, cosmetics.getId());
                ptm.setString(2, cosmetics.getName());
                ptm.setString(3, cosmetics.getCategory());
                ptm.setDouble(4, cosmetics.getPrice());
                ptm.setInt(5, cosmetics.getQuantity());
                ptm.setString(6, cosmetics.getImage());
                ptm.setString(7, cosmetics.getDescribe());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDuplicate(String productID) throws ClassNotFoundException, SQLException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DUPLICATE);
                ptm.setString(1, productID);

                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return check;
    }

    public boolean checkProductID(String productID) throws ClassNotFoundException, SQLException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(PRODUCTIDINORDERDETAIL);
                ptm.setString(1, productID);

                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return check;
    }
    

}
