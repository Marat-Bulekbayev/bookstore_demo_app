package com.epam.setbasicbackend.service;

import com.epam.setbasicbackend.exception.NoSuchBookingException;
import com.epam.setbasicbackend.exception.NoSuchProductException;
import com.epam.setbasicbackend.model.Product;
import com.epam.setbasicbackend.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setName("Test_Product");
        when(productRepository.save(ArgumentMatchers.any(Product.class))).thenReturn(product);
        Product actualProduct = productService.createProduct(product);
        Assertions.assertEquals(actualProduct.getName(), product.getName());
        verify(productRepository).save(product);
    }

    @Test
    void testFindProductById() throws NoSuchProductException {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Product actualProduct = productService.findProductById(product.getId());
        assertThat(actualProduct).isSameAs(product);
        verify(productRepository).findById(product.getId());
    }

    @Test
    void testFindAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        given(productRepository.findAll()).willReturn(productList);
        List<Product> actualProductList = productService.findAllProducts();
        Assertions.assertEquals(actualProductList, productList);
        verify(productRepository).findAll();
    }

    @Test
    void testEditProduct() throws NoSuchBookingException {
        Product product = new Product("Test_Name",
                "Test_Description",
                "Test_Author",
                100.00f,
                "Test_Image_Path");
        Product updatedProduct = new Product("Test_Name_Updated",
                "Test_Description_Updated",
                "Test_Author_Updated",
                101.00f,
                "Test_Image_Path_Updated");
        given(productRepository.findById(product.getId())).willReturn(Optional.of(product));
        productService.editProduct(product.getId(), updatedProduct);
        verify(productRepository).save(updatedProduct);
        verify(productRepository).findById(product.getId());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setId(1L);
        productService.deleteProduct(product.getId());
        verify(productRepository).deleteById(product.getId());
    }
}