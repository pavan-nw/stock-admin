package com.stock.admin.model.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * The type Product.
 */
@Document(collection = "products")
public class Product {
    /**
     * The constant type.
     */
    public static final String type = "product";
    @Id
    private String id;
    private String code;
    private String name;
    private String shopCode;

    @CreatedDate
    private long createdAt;

    @LastModifiedDate
    private long updatedAt;

    private String packaging;

    /**
     * Instantiates a new Product.
     *
     * @param code      the code
     * @param name      the name
     * @param shopCode  the shop code
     * @param createdAt the created at
     * @param updatedAt the upated at
     */
    public Product(String code, String name, String shopCode, long createdAt, long updatedAt) {
        this.code = code;
        this.name = name;
        this.shopCode = shopCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets shop code.
     *
     * @return the shop code
     */
    public String getShopCode() {
        return shopCode;
    }

    /**
     * Sets shop code.
     *
     * @param shopCode the shop code
     */
    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    /**
     * Gets created at.
     *
     * @return the created at
     */
    public long getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets upated at.
     *
     * @return the upated at
     */
    public long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets upated at.
     *
     * @param updatedAt the upated at
     */
    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Gets packaging.
     *
     * @return the packaging
     */
    public String getPackaging() {
        return packaging;
    }

    /**
     * Sets packaging.
     *
     * @param packaging the packaging
     */
    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
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
                ",packaging='" + this.packaging + '\'' +
                ",createdAt='" + this.createdAt + '\'' +
                ",upatedAt='" + this.updatedAt + '\'' +
                '}';
    }
}
