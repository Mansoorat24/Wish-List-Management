package com.xindus.WishList.Management.Service;

import com.xindus.WishList.Management.Entites.Product;
import com.xindus.WishList.Management.Entites.User;
import com.xindus.WishList.Management.Repository.WishListProductRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
public interface WishListProductService  {
    public List<Product> getAllProducts();
    public Product addProduct(Product product);
    public String deleteProduct(Long id);

    ResponseEntity<String> addToWishlist(@PathVariable Long productId);

    public Product findProduct(Long productId);

    }
