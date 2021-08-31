package com.ex.FirstWebStore.Setting;

import com.ex.FirstWebStore.ConnectDB.ConnectDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDao {

    private PreparedStatement preparedStatement = null;

    private ConnectDB connection = new ConnectDB();

    public ProductCategoryDao() {
    connection = new ConnectDB();
}

public List<ProductCategory> getProductCategory() throws SQLException {
        List<ProductCategory> ProductCategoryList = new ArrayList<>();
        String SELECT = "SELECT * FROM product_category";
        try(ResultSet resultSet = connection.getConnection().createStatement().executeQuery(SELECT);) {
            while (resultSet.next()) {
                ProductCategory p = new ProductCategory() ;
                p.setId(resultSet.getInt(1));
                p.setName(resultSet.getString(2));
                p.setSlug(resultSet.getString(3));
                p.setImage(resultSet.getString(4));
                ProductCategoryList.add(p);
            }
        } catch (Exception e) {
            System.out.println("SERVER_ERROR" + e.getMessage());
        }
        return ProductCategoryList;
    }



    public ProductCategory getProductCategory(String name) {
        ProductCategory product = null;
        String SELECT = "SELECT * FROM product_category where `slug` = ?";
        try {
            preparedStatement = connection.getConnection().prepareStatement(SELECT);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new ProductCategory();
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setSlug(resultSet.getString(3));
                product.setImage(resultSet.getString(4));
            }
        } catch (Exception e) {
            System.out.println("SERVER_ERROR" + e.getMessage());
        }
        return product;
    }

    public ProductCategory getProductCategory(int id) {
        ProductCategory product = null;
        String SELECT = "SELECT * FROM product_category where `id` = ?";
        try {
            preparedStatement = connection.getConnection().prepareStatement(SELECT);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new ProductCategory();
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setSlug(resultSet.getString(3));
                product.setImage(resultSet.getString(4));
            }
        } catch (Exception e) {
            System.out.println("SERVER_ERROR" + e.getMessage());
        }
        return product;
    }
    public ProductCategory getProductCategory(int id,String slug) {
        ProductCategory product = null;
        String SELECT = "SELECT * FROM product_category where `id` = ? and slug = ?";
        try {
            preparedStatement = connection.getConnection().prepareStatement(SELECT);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2,slug);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new ProductCategory();
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setSlug(resultSet.getString(3));
                product.setImage(resultSet.getString(4));
            }
        } catch (Exception e) {
            System.out.println("SERVER_ERROR" + e.getMessage());
        }
        return product;
    }

    public void setProductCategory(ProductCategory product) {
        ProductCategory p = getProductCategory(product.getName());
        try {
            if (p != null) {
                String update = "UPDATE `product_category` SET `slug`=? ,`image`=? WHERE `name`=?";
                preparedStatement = connection.getConnection().prepareStatement(update);
                preparedStatement.setString(1, product.getSlug());
                preparedStatement.setString(2, product.getImage());
                preparedStatement.setString(3, product.getName());
                preparedStatement.executeUpdate();
                System.out.println("update");
            } else{
                String insert = "INSERT INTO `product_category` VALUES (NULL,?,?,?)";
                preparedStatement = connection.getConnection().prepareStatement(insert);
                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getSlug());
                preparedStatement.setString(3, product.getImage());
                preparedStatement.executeUpdate();
                System.out.println("Insert");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // permenant delete
    public boolean deleteProductCat(int id){
        String SELECT = "DELETE FROM product_category where `id` = ?";
        try {
            preparedStatement = connection.getConnection().prepareStatement(SELECT);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("SERVER_ERROR" + e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) throws SQLException {
        ProductCategoryDao pro = new ProductCategoryDao();
        System.out.println(pro.getProductCategory(2,"/womens"));
    }
}