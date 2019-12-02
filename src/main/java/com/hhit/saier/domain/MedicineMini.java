package com.hhit.saier.domain;

public class MedicineMini {
    private Integer id;
    private String pzwh;
    private String format;
    private String name;
    private String manufacturer;
    private String barcode;
    private String form;
    private String wholesalePrice;
    private String retailPrice;
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPzwh() {
        return pzwh;
    }

    public void setPzwh(String pzwh) {
        this.pzwh = pzwh;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(String wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    @Override
    public String toString() {
        return "MedicineMini{" +
                "id=" + id +
                ", pzwh='" + pzwh + '\'' +
                ", format='" + format + '\'' +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", barcode='" + barcode + '\'' +
                ", form='" + form + '\'' +
                ", wholesalePrice='" + wholesalePrice + '\'' +
                ", retailPrice='" + retailPrice + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
