package com.ratanak.demo2.service;

import com.ratanak.demo2.dto.stock.StockDto;
import com.ratanak.demo2.entity.Product;
import com.ratanak.demo2.entity.Stock;
import com.ratanak.demo2.exception.model.ResourceNotFoundException;
import com.ratanak.demo2.mapper.StockMapper;
import com.ratanak.demo2.model.BaseResponseModel;
import com.ratanak.demo2.model.BaseResponseWithDataModel;
import com.ratanak.demo2.dto.stock.UpdateStockDto;
import com.ratanak.demo2.repository.ProductRepository;
import com.ratanak.demo2.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StockMapper mapper;
    public ResponseEntity<BaseResponseWithDataModel> listStock(){
        List<Stock> stocks = stockRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseWithDataModel("Success ","Successfully retrieve stock",
                        mapper.toDtoList(stocks)));
    }
    public ResponseEntity<BaseResponseWithDataModel> getStock(Long stockId){
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("stock not found with id :"+stockId));

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseWithDataModel("success ","stock found",stock));
    }
    public ResponseEntity<BaseResponseModel> createStock(StockDto stock){
        Product existingProduct = productRepository.findById(stock.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("product not found:" +stock.getProductId()));
       Stock stockEntity = mapper.toEntity(stock,existingProduct);
        stockRepository.save(stockEntity);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseModel("success","successfully created stock") );
    }
    public ResponseEntity<BaseResponseModel> adjustQuantity(Long stockId, UpdateStockDto updateStock){
   Stock existingStock = stockRepository.findById(stockId)
           .orElseThrow(() -> new ResourceNotFoundException("stock not found with id:" + stockId));
    //stock not found in db
     if(updateStock.getOperationType() == 1){
         int newQty = existingStock.getQuantity() + updateStock.getQuantity();
         existingStock.setQuantity(newQty);
     }else if (updateStock.getOperationType()==2){
         //when remove amount >existing amount
         if(existingStock.getQuantity() < updateStock.getQuantity()){

             return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                     .body(new BaseResponseModel("fail","quantity to remove can not be exceeded than existing stock "));
         }
             int newQty = existingStock.getQuantity() - updateStock.getQuantity();
         existingStock.setQuantity(newQty);
      }else {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                 .body(new BaseResponseModel("fail","invalid operation type"));
     }
     existingStock.setUpdatedAt(LocalDateTime.now());
     stockRepository.save(existingStock);
     return ResponseEntity.status(HttpStatus.OK)
             .body(new BaseResponseModel("success","successfully adjusted stock"));
    }
    public ResponseEntity<BaseResponseModel> deleteStock(Long stockId){
        if(!stockRepository.existsById(stockId)) {
            throw new ResourceNotFoundException("stock is not fouond " + stockId);
        }
        stockRepository.deleteById(stockId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModel("success","successfully deleled stock"));
    }
}
