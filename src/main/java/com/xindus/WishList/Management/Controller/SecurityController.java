package com.xindus.WishList.Management.Controller;


import com.xindus.WishList.Management.Dto.Credentials;
import com.xindus.WishList.Management.Entites.Product;
import com.xindus.WishList.Management.Repository.WishListProductRepository;
import com.xindus.WishList.Management.Service.Implementation.UserDetailsServiceImpl;
import com.xindus.WishList.Management.Service.Implementation.WishListProductImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SecurityController {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private WishListProductImpl wishListProductImpl;

    @Autowired
    HttpServletRequest request;
    @Autowired
    private WishListProductRepository wishListProductRepository;

    @PostMapping("/signup")
    public String signup(@RequestBody Credentials credentials){
      return userDetailsServiceImpl.signup(credentials);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Credentials credentials) {
        String email = credentials.getEmailId();
        String password = credentials.getPassword();
        boolean loggedIn = userDetailsServiceImpl.login(email, password);
        if (loggedIn) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email); // Store user's email in the session
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/product/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        Product savedProduct = wishListProductImpl.addProduct(product);
        if(savedProduct != null) {
            return new ResponseEntity<>("Product added", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to add product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/product/getAll")
    public List<Product> getAllProducts(){
        return wishListProductRepository.findAll();
    }
    @PostMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Retrieve existing session, do not create a new one
        if (session != null) {
            session.invalidate(); // Invalidate the session
            return "Logged out successfully";
        } else {
            return "No session found, already logged out";
        }
    }
    }

