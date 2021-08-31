package com.ex.FirstWebStore.Setting;

import com.ex.FirstWebStore.ConnectDB.ConnectDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SysSettingDao {
    private PreparedStatement preparedStatement = null;
    private ConnectDB connectDb = null;

    public SysSettingDao() {
        connectDb = new ConnectDB();
    }

    public List<SysSetting> getSysSetting() throws SQLException {
        connectDb.getConnection();
        List<SysSetting> SysSettingList = new ArrayList<>();
        String SELECT = "SELECT * FROM system_settings";
        try (ResultSet resultSet = connectDb.getConnection().createStatement().executeQuery(SELECT);) {
            while (resultSet.next()) {
                SysSetting s = new SysSetting();
                s.setId(resultSet.getInt(1));
                System.out.println(s.id);
                s.setKey(resultSet.getString(2));
                s.setValue(resultSet.getString(3));
                System.out.println(s.value);
                SysSettingList.add(s);
            }
        } catch (Exception e) {
            System.out.println("SERVER_ERROR" + e.getMessage());
        }
        return SysSettingList;
    }

    public SysSetting getSysSetting(String key) throws SQLException {
        SysSetting sysSetting = null;
        String SELECT = "SELECT * FROM system_settings where `key` = ?";
        try {
            preparedStatement = connectDb.getConnection().prepareStatement(SELECT);
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                sysSetting = new SysSetting();
                sysSetting.setId(resultSet.getInt(1));
                sysSetting.setKey(resultSet.getString(2));
                sysSetting.setValue(resultSet.getString(3));
            }
        } catch (Exception e) {
            System.out.println("SERVER_ERROR" + e.getMessage());
        }
        return sysSetting;
    }

    public boolean setSysSettings(SysSetting sysSetting) {
        try {
            SysSetting s = getSysSetting(sysSetting.getKey());
            if (s != null) {
                String update = "UPDATE `system_settings` SET `value`=?  WHERE `key`=?";
                preparedStatement = connectDb.getConnection().prepareStatement(update);
                preparedStatement.setString(2, sysSetting.getKey());
                preparedStatement.setString(1, sysSetting.getValue());
            } else {
                String insert = "INSERT INTO `system_settings` VALUES (NULL,?,?)";
                preparedStatement = connectDb.getConnection().prepareStatement(insert);
                preparedStatement.setString(1, sysSetting.getKey());
                preparedStatement.setString(2, sysSetting.getValue());
            }
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Not Inserted" + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) throws SQLException {
        SysSettingDao s = new SysSettingDao();
    }
}

