package com.ratanak.demo2.controller;

import com.ratanak.demo2.dto.supplier.SupplierDto;
import com.ratanak.demo2.dto.supplier.UpdateSupplierDto;
import com.ratanak.demo2.model.BaseResponseModel;
import com.ratanak.demo2.model.BaseResponseWithDataModel;
import com.ratanak.demo2.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/suppliers")

public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<BaseResponseWithDataModel> listSuppliers() {
        return supplierService.listSuppliers();
    }

    @PostMapping
    public ResponseEntity<BaseResponseModel> createSupplier(@Valid @RequestBody SupplierDto payload) {
        return supplierService.createSupplier(payload);
    }

    @PutMapping("{supplier_id}")
    public ResponseEntity<BaseResponseModel> updateSupplier(
            @PathVariable("supplier_id") Long supplierId,
            @RequestBody UpdateSupplierDto payload
    ) {
        return supplierService.updateSupplier(supplierId,payload);
    }

    @DeleteMapping("{supplier_id}")
    public ResponseEntity<BaseResponseModel> deleteSupplier(@PathVariable("supplier_id") Long supplierId) {
        return supplierService.deleteSupplier(supplierId);
    }
}