package com.ex.FirstWebStore.Setting;

import java.util.Arrays;

public class Product {
    int id, group_id;
    String name, slug, size, color, description, sku, stock,price, brand;


    public Product() {
    }

    Product(boolean isParent, int group_id) {
        if (isParent) this.group_id = 0;
        else this.group_id = group_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", group_id=" + group_id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", sku='" + sku + '\'' +
                ", stock='" + stock + '\'' +
                ", price='" + price + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSku(String sku,String color,String size) {
        this.sku = sku+"-"+color+"-"+size;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

