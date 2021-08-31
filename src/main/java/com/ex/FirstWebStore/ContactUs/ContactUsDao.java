package com.ex.FirstWebStore.ContactUs;



import java.sql.*;
import java.util.ArrayList;

public class ContactUsDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/storedb?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "mysql";
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    String UPDATE = "UPDATE `contactus` SET `status`='Done' WHERE `id`= ? AND 'status'='Pending';";
    public ContactUsDao(){};


    public ContactUsDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
    }
    public void ContactUsDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = "jdbc:mysql://localhost:3306/storedb?useSSL=false";
        this.jdbcUsername = "root";
        this.jdbcPassword = "mysql";
    }
    protected void getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
    protected void getDisconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public ArrayList<ContactUs> getContactArrayList() throws SQLException {
        getConnection();
        ArrayList<ContactUs> ContactUsArrayList = new ArrayList<>();
        String SELECT = "SELECT * FROM contactus";
        try(Statement preparedStatement = connection.createStatement()) {
            ResultSet resultSet = preparedStatement.executeQuery(SELECT);
            while (resultSet.next()) {
                ContactUs Contact = new ContactUs();
                Contact.setId(resultSet.getInt(1));
                Contact.setName(resultSet.getString(2));
                Contact.setEmail(resultSet.getString(3));
                Contact.setSubject(resultSet.getString(4));
                Contact.setMessage(resultSet.getString(5));
                Contact.setStatus(resultSet.getString(6));
                ContactUsArrayList.add(Contact);
            }
        } catch (Exception e) {
            System.out.println("SERVER_ERROR" + e.getMessage());
        }
        getDisconnect();
        return ContactUsArrayList;
    }

    public String insertContactUs(ContactUs contactIn) throws SQLException {
        try {
            getConnection();
            String INSERT = "INSERT INTO `contactus` (`name`, `email`, `subject`, `message`) VALUES (?,?,?,?);";
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1,contactIn.name);
            preparedStatement.setString(2,contactIn.email);
            preparedStatement.setString(3,contactIn.subject);
            preparedStatement.setString(4,contactIn.message);
            preparedStatement.executeUpdate();
            getDisconnect();
            return "success";
        }
        catch (Exception e){
            return "error";
        }
    }

    public static void main(String[] args) throws SQLException {
        ContactUsDao c = new ContactUsDao();
        c.getConnection();
        System.out.println(c.getContactArrayList());
    }
}