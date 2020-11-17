package com.luongpham.model;

public class BillHasProduct {

    private int productId;

    private int billId;

    private int quantity; // so luong cua san pham trong hoa don

    private double productPrice; // giá của sản phẩm tại thời điểm tạo hóa đơn

    public BillHasProduct() {
    }

    public BillHasProduct(int productId, int billId, int quantity, double productPrice) {
        this.productId = productId;
        this.billId = billId;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "BillHasProduct{" +
                "productId=" + productId +
                ", billId=" + billId +
                ", quantity=" + quantity +
                ", productPrice=" + productPrice +
                '}';
    }
}
