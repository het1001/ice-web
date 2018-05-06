package com.het.ice.model;

public class ShoppingCart extends BaseModel {

    private static final long serialVersionUID = 302868524715214345L;

    private String phone;

    private long comId;

    private String comName;

    private String imgKey;

    /** 单价 */
    private double price;

    /** 数量 */
    private int comNum;

    /** 库存 */
    private int comStock;

    /** 总价 */
    private double totalPrice;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getComId() {
        return comId;
    }

    public void setComId(long comId) {
        this.comId = comId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getComNum() {
        return comNum;
    }

    public void setComNum(int comNum) {
        this.comNum = comNum;
    }

    public String getImgKey() {
        return imgKey;
    }

    public void setImgKey(String imgKey) {
        this.imgKey = imgKey;
    }

    public int getComStock() {
        return comStock;
    }

    public void setComStock(int comStock) {
        this.comStock = comStock;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
