package com.epam.setbasicbackend.service;

import com.epam.setbasicbackend.exception.NoSuchBookingException;
import com.epam.setbasicbackend.exception.NoSuchProductException;
import com.epam.setbasicbackend.model.Product;
import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    Product findProductById(Long id) throws NoSuchProductException;

    List<Product> findAllProducts();

    Product editProduct(Long id, Product product) throws NoSuchBookingException;

    void deleteProduct(Long id);
}
