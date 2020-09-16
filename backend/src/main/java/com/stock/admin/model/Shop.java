package com.stock.admin.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Document("shops")
public class Shop {
        @Id
        private String id;

        private String code; //yet to decide on the data type of it

    @Indexed(name = "shopName")
        private String name;
        private String location;
        private Date createDate;
        private Date upateDate;

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
}
