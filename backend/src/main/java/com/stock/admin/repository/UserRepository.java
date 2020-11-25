package com.stock.admin.repository;

import com.stock.admin.model.entity.ApplicationUser;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The interface User repository.
 */
public interface UserRepository extends MongoRepository<ApplicationUser, String> {
    /**
     * Find by userName application user.
     *
     * @param Username the userName
     * @return the application user
     */
    Optional<ApplicationUser> findByUsername(String userName);
    
    Optional<ApplicationUser> findByUsernameAndPassword(String userName, String passWord);
}