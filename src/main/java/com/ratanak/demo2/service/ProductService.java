package com.ratanak.demo2.service;

import com.ratanak.demo2.dto.product.ProductDto;
import com.ratanak.demo2.entity.Product;
import com.ratanak.demo2.mapper.ProductMapper;
import com.ratanak.demo2.model.BaseResponseModel;
import com.ratanak.demo2.model.BaseResponseWithDataModel;
import com.ratanak.demo2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper mapper;
    public ResponseEntity<BaseResponseWithDataModel> listProducts(){
        List<Product> products = productRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseWithDataModel("succes","successfully retrieve product",
                mapper.toDtoList(products)));
    }
    public ResponseEntity<BaseResponseWithDataModel>getProduct(Long productId){
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponseWithDataModel("fail","Product not found with id: "+productId,null));

        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseWithDataModel("success","product found",product.get()));
    }

    public ResponseEntity<BaseResponseModel> createProduct(ProductDto product){
        if (productRepository.existsByProductName(product.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new BaseResponseModel("fail","product is already existed"));
        }
        Product productEntity = mapper.toEntity(product);

        productRepository.save(productEntity);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseModel("success","success fully created product"));

    }
    public ResponseEntity<BaseResponseModel> updateProduct(Long productId,ProductDto product){
        Optional<Product> existing = productRepository.findById(productId);
        if(existing.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponseModel("Fail","product not found with id "+productId));

        }
        Product updatedProduct = existing.get();
        updatedProduct.setProductName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setUpdatedAt(LocalDateTime.now());

        productRepository.save(updatedProduct);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModel("success","successfully updated product"));
    }
    public ResponseEntity<BaseResponseModel> deleteProduct(Long productId){
        if(!productRepository.existsById(productId)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponseModel("fail","product not found with id: "+productId));
        }
        productRepository.deleteById(productId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModel("success","successfully deleted product id : "+productId));
    }
    public ResponseEntity<BaseResponseWithDataModel> searchProducts
            (String name , Double minPrice, Double maxPrice){
        String formattedName = name !=null?
                name.toLowerCase()
                : null;
    List<Product> product = productRepository.findProductsWithFilters(formattedName,minPrice,maxPrice);
    return ResponseEntity.status(HttpStatus.OK)
            .body(new BaseResponseWithDataModel("success","product retrieved", product));
    }
}
