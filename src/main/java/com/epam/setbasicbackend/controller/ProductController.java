package com.epam.setbasicbackend.controller;

import com.epam.setbasicbackend.exception.NoSuchBookingException;
import com.epam.setbasicbackend.exception.NoSuchProductException;
import com.epam.setbasicbackend.model.Booking;
import com.epam.setbasicbackend.model.Product;
import com.epam.setbasicbackend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get a product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product is found by id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "404", description = "No such product have been found by id",
                    content = @Content) })
    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@Parameter(description = "Product ID") @PathVariable Long id) throws NoSuchProductException {
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @Operation(summary = "Get a list of products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products is returned",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) })})
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @Operation(summary = "Create new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "New product is created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)) })})
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @Operation(summary = "Edit product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product has been edited",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)),
                    }),
            @ApiResponse(responseCode = "404", description = "No such product have been found by id",
                    content = @Content)})
    @PutMapping("{id}")
    public ResponseEntity<Product> editProduct(@Parameter(description = "Product ID") @PathVariable Long id, @RequestBody Product product) throws NoSuchBookingException {
        return ResponseEntity.ok(productService.editProduct(id, product));
    }

    @Operation(summary = "Delete a product by id")
    @ApiResponse(responseCode = "204", description = "Product has been deleted", content = @Content)
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@Parameter(description = "PRODUCT ID") @PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
