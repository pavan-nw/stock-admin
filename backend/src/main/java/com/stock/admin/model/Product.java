package com.stock.admin.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Document(collection = "products")
public class Product {
    private String type="product";
    @Id
    private String id;
    private String code; //yet to decide on the data type of it
    private String name;
    private String shopCode;

    @DateTimeFormat(style = "M-")
    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date upatedAt;

    //@DBRef
    private List packaging;
    private List productType;

    public Product(String code, String name, String shopCode, Date createdAt,Date upatedAt) {
        this.code = code;
        this.name = name;
        this.shopCode = shopCode;
        this.createdAt= createdAt;
        this.upatedAt = upatedAt;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpatedAt() {
        return upatedAt;
    }

    public void setUpatedAt(Date upatedAt) {
        this.upatedAt = upatedAt;
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


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Product))
            return false;
        Product product = (Product) o;
        return Objects.equals(this.id, product.id) && Objects.equals(this.name, product.name)
                && Objects.equals(this.code
                , product.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.code);
    }

    @Override
    public String toString() {
        return "Product{" + "code=" + this.code +
                        ",name='" + this.name + '\'' +
                        ",shopCode='" + this.shopCode + '\'' +
                        ",packaging='" + this.packaging + '\''+
                        ",productType='" + this.productType + '\''+
                        ",createdAt='" + this.createdAt + '\''+
                        ",upatedAt='" + this.upatedAt + '\''+
                '}';
    }
}
