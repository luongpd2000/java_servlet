package com.luongpham.model;

import java.time.LocalDate;

public class Comment {

    private int id;

    private String name;

    private String comment;

    private int productId;

    private int accepted; // 0 la cho duyet, 1 la da duyet, 2 duyet khong dong y

    private boolean deleted;

    private LocalDate createTime;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", productId=" + productId +
                ", accepted=" + accepted +
                ", deleted=" + deleted +
                ", createTime=" + createTime +
                '}';
    }

    public Comment() {
    }

    public Comment(int id, String name, String comment, int productId, int accepted, boolean deleted, LocalDate createTime) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.productId = productId;
        this.accepted = accepted;
        this.deleted = deleted;
        this.createTime = createTime;
    }
}
