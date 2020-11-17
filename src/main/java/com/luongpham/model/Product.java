package com.luongpham.model;

import java.util.Date;

public class Product {

    private int id;

    private String name;

    private double price;

    private Date createDate;

    private boolean deleted;

    private String image; // lưu trữ đường của hình ảnh

    private String introduction; // miêu tả về sản phẩm

    private String specification;// thông số kỹ thuật về sản phẩm

    private boolean soldOut; // true la het hang, false la con hang

    private int guarantee; // luu thang bao hanh

    private int categoryId;

    private int bouth; // số lượng sản phẩm đã bán được

    private int promotion; // phần trăm khuyến mãi của sản phẩm

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", createDate=" + createDate +
                ", deleted=" + deleted +
                ", image='" + image + '\'' +
                ", introduction='" + introduction + '\'' +
                ", specification='" + specification + '\'' +
                ", soldOut=" + soldOut +
                ", guarantee=" + guarantee +
                ", categoryId=" + categoryId +
                ", bouth=" + bouth +
                ", promotion=" + promotion +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public void setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
    }

    public int getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(int guarantee) {
        this.guarantee = guarantee;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBouth() {
        return bouth;
    }

    public void setBouth(int bouth) {
        this.bouth = bouth;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public Product() {
    }

    public Product(int id, String name, double price, Date createDate, boolean deleted, String image, String introduction, String specification, boolean soldOut, int guarantee, int categoryId, int bouth, int promotion) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.createDate = createDate;
        this.deleted = deleted;
        this.image = image;
        this.introduction = introduction;
        this.specification = specification;
        this.soldOut = soldOut;
        this.guarantee = guarantee;
        this.categoryId = categoryId;
        this.bouth = bouth;
        this.promotion = promotion;
    }
}
