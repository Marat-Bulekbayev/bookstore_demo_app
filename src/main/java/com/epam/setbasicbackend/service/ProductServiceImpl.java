package com.epam.setbasicbackend.service;

import com.epam.setbasicbackend.exception.NoSuchBookingException;
import com.epam.setbasicbackend.exception.NoSuchProductException;
import com.epam.setbasicbackend.model.Product;
import com.epam.setbasicbackend.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(Long id) throws NoSuchProductException {
        return productRepository.findById(id).orElseThrow(() -> new NoSuchProductException("No such product have been found by id: " + id));
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product editProduct(Long id, Product product) throws NoSuchBookingException {
        Product originalProduct = productRepository.findById(id).orElseThrow(() -> new NoSuchBookingException("No such booking have been found by id: " + id));

        if (!originalProduct.getName().equals(product.getName())) {
            originalProduct.setName(product.getName());
        }
        if (!originalProduct.getDescription().equals(product.getDescription())) {
            originalProduct.setDescription(product.getDescription());
        }
        if (!originalProduct.getAuthor().equals(product.getAuthor())) {
            originalProduct.setAuthor(product.getAuthor());
        }
        if (!originalProduct.getPrice().equals(product.getPrice())) {
            originalProduct.setPrice(product.getPrice());
        }
        if (!originalProduct.getImagePath().equals(product.getImagePath())) {
            originalProduct.setImagePath(product.getImagePath());
        }

        return productRepository.save(originalProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
