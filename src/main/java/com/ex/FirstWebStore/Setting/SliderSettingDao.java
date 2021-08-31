package com.ex.FirstWebStore.Setting;
import com.ex.FirstWebStore.ConnectDB.ConnectDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SliderSettingDao {
    private PreparedStatement preparedStatement = null;
    private ConnectDB connectDb = null;

    public SliderSettingDao() {
        connectDb = new ConnectDB();
    }

    public List<SliderSetting> getSliderSetting() throws SQLException {
        List<SliderSetting> SliderSettingList = new ArrayList<>();
        String SELECT = "SELECT * FROM slider_setting";
        try (ResultSet resultSet = connectDb.getConnection().createStatement().executeQuery(SELECT);) {
            while (resultSet.next()) {
                SliderSetting s = new SliderSetting();
                s.setId(resultSet.getInt(1));
                s.setSlider_title(resultSet.getString(2));
                s.setSlider_link(resultSet.getString(3));
                s.setSlider_image(resultSet.getString(4));
                s.setSlider_description(resultSet.getString(5));
                s.setSlider_order(resultSet.getInt(6));
                SliderSettingList.add(s);
            }
        } catch (Exception e) {
            System.out.println("SERVER_ERROR" + e.getMessage());

        }
        connectDb.getDisconnect();
        return SliderSettingList;
    }

    private SliderSetting getSliderSetting(String slider_title) {
        SliderSetting slider = null;
        String SELECT = "SELECT * FROM slider_setting where `slider_title` = ?";
        try {
            preparedStatement = connectDb.getConnection().prepareStatement(SELECT);
            preparedStatement.setString(1, slider_title);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                slider = new SliderSetting();
                slider.setId(resultSet.getInt(1));
                slider.setSlider_title(resultSet.getString(2));
                slider.setSlider_link(resultSet.getString(3));
                slider.setSlider_image(resultSet.getString(4));
                slider.setSlider_description(resultSet.getString(5));
                slider.setSlider_order(resultSet.getInt(6));
            }
        } catch (Exception e) {
            System.out.println("SERVER_ERROR" + e.getMessage());
        }
        return slider;
    }

    public boolean setSliderSettings(SliderSetting setslider) {
        SliderSetting s = getSliderSetting(setslider.getSlider_title());
        if(setslider.getSlider_image() == null){
           setslider.slider_image = s.getSlider_image();
        }
        try {
            if (s != null) {
                String update = "UPDATE `slider_setting` SET `slider_link`=?,`slider_image`=? ,`slider_description`=?, `slider_order` = ?  WHERE `slider_title`=?";
                preparedStatement = connectDb.getConnection().prepareStatement(update);
                preparedStatement.setString(1, setslider.getSlider_link());
                preparedStatement.setString(2, setslider.getSlider_image());
                preparedStatement.setString(3,setslider.getSlider_description());
                preparedStatement.setInt(4, setslider.getSlider_order());
                preparedStatement.setString(5, setslider.getSlider_title());
                preparedStatement.executeUpdate();
                System.out.println("update");
            } else{
                String insert = "INSERT INTO `slider_setting` VALUES (NULL,?,?,?,?,?)";
                preparedStatement = connectDb.getConnection().prepareStatement(insert);
                preparedStatement.setString(1, setslider.getSlider_title());
                preparedStatement.setString(2, setslider.getSlider_link());
                preparedStatement.setString(3, setslider.getSlider_image());
                preparedStatement.setString(4,setslider.getSlider_description());
                preparedStatement.setInt(5, setslider.getSlider_order());
                preparedStatement.executeUpdate();
                System.out.println("Insert");
         }
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    public static void main(String[] args) {
        SliderSettingDao s = new SliderSettingDao();
        SliderSetting slider = new SliderSetting();
        slider.setSlider_title("helo");
        slider.setSlider_order(0);
        slider.setSlider_image("./assets/manually");
        slider.setSlider_link("hello.com");
        slider.setSlider_description("helllllloooooooooo");
        s.setSliderSettings(slider);

    }
}