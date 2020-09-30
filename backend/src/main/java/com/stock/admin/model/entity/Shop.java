package com.stock.admin.model.entity;

import java.util.Date;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The type Shop.
 */
@Document("shops")
public class Shop {
    @Id
    private String id;

    private String code; //yet to decide on the data type of it

    @Indexed(name = "shopName")
    private String name;
    private String location;
    private Date createdAt;
    private Date upatedAt;

    /**
     * Instantiates a new Shop.
     *
     * @param code     the code
     * @param name     the name
     * @param location the location
     */
    public Shop(String code, String name, String location) {
        this.code = code;
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
    public Date getUpatedAt() {
        return upatedAt;
    }

    /**
     * Sets upated at.
     *
     * @param upatedAt the upated at
     */
    public void setUpatedAt(Date upatedAt) {
        this.upatedAt = upatedAt;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        Shop shop = (Shop) o;
        return Objects.equals(this.id, shop.id) && Objects.equals(this.name, shop.name)
                && Objects.equals(this.code
                , shop.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.code);
    }

    @Override
    public String toString() {
        return "Shop{" + "code=" + this.code +
                ",name='" + this.name + '\'' +
                ",location='" + this.location + '\'' +
                ",createdAt='" + this.createdAt + '\'' +
                ",upatedAt='" + this.upatedAt + '\'' +
                '}';
    }
}
