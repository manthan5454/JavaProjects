package com.ex.FirstWebStore.Setting;

import com.ex.FirstWebStore.ConnectDB.ConnectDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuSettingDao {
    private PreparedStatement preparedStatement = null;
    private ConnectDB connection = null;

    public MenuSettingDao(){
        connection = new ConnectDB();
    }

    public boolean setMenuSettings(MenuSetting menuSetting) {
        try {
            MenuSetting s = getMenuSetting(menuSetting.getMenu_slug());
            if (s != null) {
                String update = "UPDATE `menu_setting` SET `menu_title`=?, `menu_order` = ?  WHERE `menu_slug`=?";
                preparedStatement = connection.getConnection().prepareStatement(update);
                preparedStatement.setString(1, menuSetting.getMenu_title());
                preparedStatement.setInt(2, menuSetting.getMenu_order());
                preparedStatement.setString(3, menuSetting.getMenu_slug());

            } else {
                String insert = "INSERT INTO `menu_setting` VALUES (NULL,?,?,?)";
                preparedStatement = connection.getConnection().prepareStatement(insert);
                preparedStatement.setString(1, menuSetting.getMenu_slug());
                preparedStatement.setString(2, menuSetting.getMenu_title());
                preparedStatement.setInt(3, menuSetting.getMenu_order());
            }
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Not Inserted" + e.getMessage());
            return false;
        }

    }

    private MenuSetting getMenuSetting(String menu_slug) {
        MenuSetting menu = null;
        String SELECT = "SELECT * FROM menu_setting where `menu_slug` = ?";
        try {
            preparedStatement = connection.getConnection().prepareStatement(SELECT);
            preparedStatement.setString(1, menu_slug);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                menu = new MenuSetting();
                menu.setId(resultSet.getInt(1));
                menu.setMenu_slug(resultSet.getString(2));
                menu.setMenu_title(resultSet.getString(3));
                menu.setMenu_order(resultSet.getInt(4));
            }
        } catch (Exception e) {
            System.out.println("SERVER_ERROR" + e.getMessage());
        }
        return menu;
    }

    public List<MenuSetting> getMenuSetting() throws SQLException {
        List<MenuSetting> MenuSettingList = new ArrayList<>();
        String SELECT = "SELECT * FROM menu_setting order by menu_order";
        try(ResultSet resultSet = connection.getConnection().createStatement().executeQuery(SELECT)) {
            while (resultSet.next()) {
                MenuSetting m = new MenuSetting() ;
                m.setId(resultSet.getInt(1));
                m.setMenu_slug(resultSet.getString(2));
                m.setMenu_title(resultSet.getString(3));
                m.setMenu_order(resultSet.getInt(4));
                MenuSettingList.add(m);
            }
        } catch (Exception e) {
            System.out.println("SERVER_ERROR" + e.getMessage());
        }
        return MenuSettingList;
    }

    public static void main(String[] args) throws SQLException {
        MenuSettingDao s = new MenuSettingDao();
        MenuSetting menuSetting = new MenuSetting();
        menuSetting.setMenu_slug("/home");
        menuSetting.setMenu_title("Home");
        menuSetting.setMenu_order(Integer.parseInt("0"));
        System.out.println(s.setMenuSettings(menuSetting));
    }
}
