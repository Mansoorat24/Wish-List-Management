package com.xindus.WishList.Management.Controller;


import com.xindus.WishList.Management.Entites.Product;
import com.xindus.WishList.Management.Entites.User;
import com.xindus.WishList.Management.Repository.UserRepository;
import com.xindus.WishList.Management.Service.Implementation.UserDetailsServiceImpl;
import com.xindus.WishList.Management.Service.Implementation.WishListProductImpl;
import com.xindus.WishList.Management.Service.UserDetailsService;
import com.xindus.WishList.Management.Service.WishListProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@NoArgsConstructor
@RestController
@RequestMapping("/api/wishlist")
public class WishListController {
    @Autowired
    WishListProductImpl wishListProductImpl ;
    @Autowired
    SecurityController securityController;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    UserDetailsService userDetailsService;

//    @DeleteMapping("/remove/{id}")
//    public ResponseEntity<String> removeProductFromWishList(@PathVariable Long id) {
//        HttpSession session = securityController.request.getSession(false);
//        if (session != null && session.getAttribute("email") != null) {
//            String email = (String) session.getAttribute("email");
//            User user = userDetailsServiceImpl.findUserByMail(email);
//            Product product = wishListProductImpl.findProduct(id);
//            boolean removed  =user.getWishList().remove(product);
//            if (removed) {
//                return new ResponseEntity<>("Product removed from wishlist", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Failed to remove product from wishlist", HttpStatus.BAD_REQUEST);
//            }
//        } else {
//            return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
//        }
//    }
//    @PostMapping("/add/{id}")
//    public ResponseEntity<String> addToWishlist(@PathVariable Long productId) {
//        HttpSession session = securityController.request.getSession(false);
//        if (session != null && session.getAttribute("email") != null) {
//            String email = (String) session.getAttribute("email");
//            User user = userDetailsServiceImpl.findUserByMail(email);
//            if(user==null) return null;
//            Product product = wishListProductImpl.findProduct(productId);
//            boolean added = user.getWishList().add(product);
//            if (added) {
//                return new ResponseEntity<>("Product added to wishlist", HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>("Failed to add product to wishlist", HttpStatus.BAD_REQUEST);
//            }
//        } else {
//            return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
//        }
//    }
@DeleteMapping("/remove/{id}")
public ResponseEntity<String> removeProductFromWishList(@PathVariable Long id) {
    HttpSession session = securityController.request.getSession(false);
    if (session != null && session.getAttribute("email") != null) {
        String email = (String) session.getAttribute("email");
        User user = userDetailsServiceImpl.findUserByMail(email);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        Product product = wishListProductImpl.findProduct(id);
        if (product == null) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        boolean removed = user.getWishList().remove(product);
        if (removed) {
            wishListProductImpl.removeProductFromWishList(id);
            return new ResponseEntity<>("Product removed from wishlist", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to remove product from wishlist", HttpStatus.BAD_REQUEST);
        }
    } else {
        return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
    }
}
@PostMapping("/add/{id}")
public ResponseEntity<String> addToWishlist(@PathVariable Long id) {
    HttpSession session = securityController.request.getSession(false);
    if (session != null && session.getAttribute("email") != null) {
        String email = (String) session.getAttribute("email");
        User user = userDetailsServiceImpl.findUserByMail(email);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        Product product = wishListProductImpl.findProduct(id);
        if (product == null) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        user.getWishList().add(product);
        return new ResponseEntity<>("Product added to wishlist", HttpStatus.OK);
    } else {
        return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
    }
}
    @GetMapping("/getWishlist")
    public ResponseEntity<String> getUserWishlist() {
        HttpSession session = securityController.request.getSession(false);
        if (session != null && session.getAttribute("email") != null) {
            String email = (String) session.getAttribute("email");
            User user = userDetailsServiceImpl.findUserByMail(email);
            List<Product> wishlist = user.getWishList();
            return new ResponseEntity(wishlist, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
        }
    }
}
