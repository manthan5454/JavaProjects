package com.ex.FirstWebStore.Setting;

public class MenuSetting {
    int id,menu_order;
    String menu_slug, menu_title;

    @Override
    public String toString() {
        return "MenuSetting{" +
                "id=" + id +
                ", menu_slug='" + menu_slug + '\'' +
                ", menu_title='" + menu_title + '\'' +
                '}';
    }

    public int getMenu_order() {
        return menu_order;
    }

    public void setMenu_order(int menu_order) {
        this.menu_order = menu_order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenu_slug() {
        return menu_slug;
    }

    public void setMenu_slug(String menu_slug) {
        menu_slug = menu_slug.replace("/","");
        this.menu_slug = menu_slug;
    }

    public String getMenu_title() {

        return menu_title;
    }

    public void setMenu_title(String menu_title) {
        menu_title = menu_title.toUpperCase();
        this.menu_title = menu_title;
    }
}
