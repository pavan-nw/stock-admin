package com.stock.admin.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "product")
public class Product {
    @Id
    private String id;

    private String code; //yet to decide on the data type of it

    private String name;
    private String shopCode;
    private Date createDate;
    private Date upateDate;

    //@DBRef
    private List packaging;
    private List productType;

    public Product(String code, String name, String shopCode) {
        this.code = code;
        this.name = name;
        this.shopCode = shopCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopCode() {
        return shopCode;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpateDate() {
        return upateDate;
    }

    public void setUpateDate(Date upateDate) {
        this.upateDate = upateDate;
    }

    public List getPackaging() {
        return packaging;
    }

    public void setPackaging(List packaging) {
        this.packaging = packaging;
    }

    public List getProductType() {
        return productType;
    }

    public void setProductType(List productType) {
        this.productType = productType;
    }
}
