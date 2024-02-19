package com.xindus.WishList.Management;

import com.xindus.WishList.Management.Controller.WishListController;
import com.xindus.WishList.Management.Entites.Product;

import com.xindus.WishList.Management.Service.WishListProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private WishListProductService wishListProductService;

    @InjectMocks
    private WishListController userController;

    @Test
    public void testGetWishlist() {
        // Arrange
        Product prod1 = new Product();
        prod1.setId(1L);
        prod1.setProdName("product 1");

        Product prod2 = new Product();
        prod2.setId(2L);
        prod2.setProdName("product 2");

        List<Product> wishlist = Arrays.asList(prod1, prod2);
        Mockito.when(wishListProductService.getAllProducts()).thenReturn(wishlist);

        // Act
        List<Product> result = wishListProductService.getAllProducts();

        // Assert
        assertEquals(2, result.size());
        assertEquals("product 1", result.get(0).getProdName());
        assertEquals("product 2", result.get(1).getProdName());
    }

    @Test
    public void testAddProduct() {
        // Arrange
       Product newProduct = new Product();
        newProduct.setProdName("New Product");

        Mockito.when(wishListProductService.addProduct(Mockito.any(Product.class))).thenReturn(newProduct);

        // Act
       Product result = wishListProductService.addProduct(newProduct);

        // Assert
        assertNotNull(result);
        assertEquals("New Product", result.getProdName());
    }

    @Test
    public void testDeleteItem() {
        // Arrange
        Long Id = 1L;
        // Act
        userController.removeProductFromWishList(Id);
        // Assert
        Mockito.verify(wishListProductService, Mockito.times(1)).deleteProduct(Id);
    }
}
