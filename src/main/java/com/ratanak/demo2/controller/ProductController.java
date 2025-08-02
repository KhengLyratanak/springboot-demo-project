package com.ratanak.demo2.controller;

import com.ratanak.demo2.model.BaseResponseModel;
import com.ratanak.demo2.model.BaseResponseWithDataModel;
import com.ratanak.demo2.model.ProductModel;
import com.ratanak.demo2.service.ProductService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<BaseResponseWithDataModel> listproduct(){
        return productService.listProducts();
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseWithDataModel> getProduct(@PathVariable("id") Long productId){
        return productService.getProduct(productId);
    }
    @GetMapping("/search")
    public ResponseEntity<BaseResponseWithDataModel> searchProductsByFilters(
            @RequestParam(value = "name",required = false) String name
    ){
        return productService.searchProducts(name);
    }

    @PostMapping
    public ResponseEntity<BaseResponseModel> createProduct(@RequestBody ProductModel payload){
    return  productService.createProduct(payload);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseModel> updateProduct(@PathVariable("id") Long productId,@RequestBody ProductModel payload){
        return productService.updateProduct(productId,payload);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseModel> deleteProduct(@PathVariable("id") Long productId){
        return productService.deleteProduct(productId);
    }

}
