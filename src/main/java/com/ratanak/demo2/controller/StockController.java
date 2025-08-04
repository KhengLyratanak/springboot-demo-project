package com.ratanak.demo2.controller;

import com.ratanak.demo2.model.BaseResponseModel;
import com.ratanak.demo2.model.BaseResponseWithDataModel;
import com.ratanak.demo2.model.stock.StockModel;
import com.ratanak.demo2.model.stock.UpdateStockModel;
import com.ratanak.demo2.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {
    @Autowired
    private StockService stockService;

    @PatchMapping ("{id}")
    public ResponseEntity<BaseResponseModel> adjustQuantity(@PathVariable("id") Long stockId ,@RequestBody UpdateStockModel payload){
        return stockService.adjustQuantity(stockId,payload);
    }

    @GetMapping
    public ResponseEntity<BaseResponseWithDataModel> listStock(){
    return stockService.listStock();
    }

    @PostMapping
    public ResponseEntity<BaseResponseModel> createStock(@RequestBody StockModel payload){

    return stockService.createStock(payload);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<BaseResponseModel> deleteStock(@PathVariable("id") Long stockId){
        return stockService.deleteStock(stockId);

    }
}
