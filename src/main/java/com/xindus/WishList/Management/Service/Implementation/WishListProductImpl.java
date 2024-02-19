package com.xindus.WishList.Management.Service.Implementation;

import com.xindus.WishList.Management.Controller.WishListController;
import com.xindus.WishList.Management.Entites.Product;
import com.xindus.WishList.Management.Repository.WishListProductRepository;
import com.xindus.WishList.Management.Service.WishListProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Service
public class WishListProductImpl implements WishListProductService{



    @Autowired
    private WishListProductRepository wishListProductRepository;
    @Override
    public List<Product> getAllProducts() {
        return wishListProductRepository.findAll();
    }
    @Override
    public Product addProduct(Product product) {
        return wishListProductRepository.save(product);
    }
    @Override
    public String deleteProduct(Long id) {
        wishListProductRepository.deleteById(id);
        return "product deleted successfully";
    }
    public ResponseEntity<String> removeProductFromWishList(@PathVariable Long id){
        return null;
    }

    @Override
    public ResponseEntity<String> addToWishlist(Long productId) {
        return null;
    }

//    @Override
//    public ResponseEntity<String> addToWishlist(Long id) {
//        return wishListController.addToWishlist(id);
//    }
    @Override
    public Product findProduct(Long productId){
     return   wishListProductRepository.findById(productId).get();
    }
}
