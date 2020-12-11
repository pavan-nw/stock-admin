package com.stock.admin.repository;

import com.stock.admin.model.entity.ApplicationUser;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

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
    
    
    @Query(value = "{ 'username' :?0, 'shopCodes':?1}")
    Optional<ApplicationUser> findByUsernameAndShopCode(String userName,String shopCode);
    
    /**
     * Find by username and password.
     *
     * @param userName the user name
     * @param passWord the pass word
     * @return the optional
     */
    Optional<ApplicationUser> findByUsernameAndPassword(String userName, String passWord);
}