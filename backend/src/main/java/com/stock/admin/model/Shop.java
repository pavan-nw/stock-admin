package com.stock.admin.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

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


    public Shop(String code, String name, String location) {
        this.code = code;
        this.name = name;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Product))
            return false;
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
                ",createdAt='" + this.createdAt + '\''+
                ",upatedAt='" + this.upatedAt + '\''+
                '}';
    }

}
