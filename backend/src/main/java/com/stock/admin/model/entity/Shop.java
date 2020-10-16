package com.stock.admin.model.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * The type Shop.
 */
@Document("shops")
public class Shop {
    /**
     * The constant type.
     */
    public static final String type = "shop";
    @Id
    private String id;

    /**
     * The constant SEQUENCE_NAME.
     */
    @Transient
    public static final String SEQUENCE_NAME = "SHOP";

    @NotNull(message = "Shop name is the mandatory field")
    @Indexed(name = "shopName")
    private String name;

    @NotNull(message = "Shop code is the mandatory field")
    private String shopCode;
    @NotNull(message = "Shop location is the mandatory field")
    private String location;

    @DateTimeFormat
    @CreatedDate
    private Date createdAt;

    @DateTimeFormat
    @LastModifiedDate
    private Date updatedAt;

    /**
     * Instantiates a new Shop.
     *
     * @param shopCode the shopCode
     * @param name     the name
     * @param location the location
     */
    public Shop(String shopCode, String name, String location) {
        this.shopCode = shopCode;
        this.name = name;
        this.location = location;
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
     * Gets shopCode.
     *
     * @return the shopCode
     */
    public String getShopCode() {
        return shopCode;
    }

    /**
     * Sets shopCode.
     *
     * @param shopCode the shopCode
     */
    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
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
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets created at.
     *
     * @return the created at
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets created at.
     *
     * @param createdAt the created at
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets upated at.
     *
     * @return the upated at
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets upated at.
     *
     * @param updatedAt the upated at
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
