package com.ex.FirstWebStore.Setting;

import com.ex.FirstWebStore.ConnectDB.ConnectDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    Product product = null;
    private ConnectDB connection = null;
    private PreparedStatement preparedStatement;

    public ProductDao() {
        connection = new ConnectDB();
    }


//    public Product getProduct() {
//        return product;
//    }

    private Product getAllProduct() throws SQLException {
        List<ProductCategory> getProductList = new ArrayList<>();
        String SELECT = "SELECT * FROM product_list";
        try (ResultSet resultSet = connection.getConnection().createStatement().executeQuery(SELECT);) {
            while (resultSet.next()) {
                product = new Product(true, product.group_id);
                product.setId(resultSet.getInt(1));
                product.setGroup_id(resultSet.getInt(2));
                product.setName(resultSet.getString(3));
                product.setSlug(resultSet.getString(4));
                product.setPrice(resultSet.getString(5));
                product.setSize(resultSet.getString(6));
                product.setColor(resultSet.getString(7));
                product.setDescription(resultSet.getString(8));
                product.setSku(resultSet.getString(9));
                product.setStock(resultSet.getString(10));
                product.setBrand(resultSet.getString(11));
            }
            return product;
        }
    }

    public boolean checkProduct(String sku) {
        String SELECT = "SELECT * FROM product_list WHERE `sku`=?";
        try {
            preparedStatement = connection.getConnection().prepareStatement(SELECT);
            preparedStatement.setString(1, sku);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }


    public String setProduct(Product product) {
        String message = "";
        String checkSlug ="";
       ProductDao productDao = new ProductDao();
       Product p = new Product();
        try {
                String INSERT = "INSERT INTO `product_list`(`id`, `group_id`, `name`, `slug`, `price`, `size`, `color`, `description`, `sku`, `stock`, `brand`) VALUES (null,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.getConnection().prepareStatement(INSERT);
                preparedStatement.setInt(1,product.getGroup_id());
                preparedStatement.setString(2, product.getName());
                preparedStatement.setString(3, product.getSlug());
                preparedStatement.setString(4, product.getPrice());
                preparedStatement.setString(5, product.getSize());
                preparedStatement.setString(6, product.getColor());
                preparedStatement.setString(7, product.getDescription());
                preparedStatement.setString(8, product.getSku());
                preparedStatement.setString(9, product.getStock());
                preparedStatement.setString(10, product.getBrand());
                preparedStatement.executeUpdate();
                message = "Product added Successfully";
        } catch (Exception e) {
            message = "Product Fail: " + e.getMessage();
        }
        return message;
    }

    public static void main(String[] args) {
        Product product = new Product();
        product.setName("Polo T-shirt");
//        product.setSlug("");
        product.setBrand("U.S. Polo");
        product.setColor("");
        ProductDao p = new ProductDao();
        p.setProduct(product);
    }
}
