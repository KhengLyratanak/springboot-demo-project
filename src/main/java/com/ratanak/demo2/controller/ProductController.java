package com.ratanak.demo2.controller;

import com.ratanak.demo2.model.BaseResponseModel;
import com.ratanak.demo2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vi/products")
public class ProductController {
     @Autowired
    private ProductService productService;

     @GetMapping()
    public ResponseEntity<BaseResponseModel> getProducts() {
         return productService.ListProduct();
     }
     @PostMapping()
    public ResponseEntity<BaseResponseModel>createProduct(@RequestBody ProductModel)
}
