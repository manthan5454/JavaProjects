package com.ex.FirstWebStore.Setting;

public class SliderSetting {
    @Override
    public String toString() {
        return "SliderSetting{" +
                "id=" + id +
                ", slider_order=" + slider_order +
                ", slider_title='" + slider_title + '\'' +
                ", slider_link='" + slider_link + '\'' +
                ", slider_image='" + slider_image + '\'' +
                ", slider_description='" + slider_description + '\'' +
                '}';
    }

    int id,slider_order;
    String slider_title, slider_link, slider_image, slider_description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSlider_order() {
        return slider_order;
    }

    public void setSlider_order(int slider_order) {
        this.slider_order = slider_order;
    }

    public String getSlider_title() {
        return slider_title;
    }

    public void setSlider_title(String slider_title) {
        this.slider_title = slider_title;
    }

    public String getSlider_link() {
        return slider_link;
    }

    public void setSlider_link(String slider_link) {
//        slider_link = slider_link.replace("/","");
        this.slider_link = slider_link;
    }

    public String getSlider_image() {
        return slider_image;
    }

    public void setSlider_image(String slider_image) {
        this.slider_image = slider_image;
    }

    public String getSlider_description() {
        return slider_description;
    }

    public void setSlider_description(String slider_description) {
        this.slider_description = slider_description;
    }
}
