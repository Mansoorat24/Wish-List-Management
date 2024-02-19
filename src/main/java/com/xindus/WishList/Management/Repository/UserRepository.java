package com.xindus.WishList.Management.Repository;

import com.xindus.WishList.Management.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmailId(String emailId);
}
