package com.stock.admin.model.entity;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The type Application user.
 */
@Document(collection = "user")
public class ApplicationUser {
    /**
     * The constant type.
     */
    public static final String type = "login";

    @Id
    private String id;
    @NotNull(message = "Username is the mandatory field")
    private String username;

    @NotNull(message = "Password is the mandatory field")
    private String password;    
    
    private List<String> shopCodes;    
    
    public List<String> getShopCodes() {
		return shopCodes;
	}

	public void setShopCodes(List<String> shopCodes) {
		this.shopCodes = shopCodes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static String getType() {
		return type;
	}

	/**
     * Gets userName.
     *
     * @return the userName
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets userName.
     *
     * @param username the userName
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
