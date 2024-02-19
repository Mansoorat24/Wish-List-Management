package com.xindus.WishList.Management.Repository;

import com.xindus.WishList.Management.Entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListProductRepository extends JpaRepository<Product,Long> {

}
