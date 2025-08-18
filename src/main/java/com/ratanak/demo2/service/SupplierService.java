package com.ratanak.demo2.service;

import com.ratanak.demo2.dto.supplier.SupplierDto;
import com.ratanak.demo2.dto.supplier.UpdateSupplierDto;
import com.ratanak.demo2.entity.Supplier;
import com.ratanak.demo2.exception.model.DuplicateResourceException;
import com.ratanak.demo2.exception.model.ResourceNotFoundException;
import com.ratanak.demo2.mapper.SupplierMapper;
import com.ratanak.demo2.model.BaseResponseModel;
import com.ratanak.demo2.model.BaseResponseWithDataModel;
import com.ratanak.demo2.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierMapper mapper;

    public ResponseEntity<BaseResponseWithDataModel> listSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new BaseResponseWithDataModel(
                                "success",
                                "successfully retrieved suppliers",
                                mapper.toDtoList(suppliers)
                        )
                );
    }

    public ResponseEntity<BaseResponseModel> createSupplier(SupplierDto dto) {
        // if duplicate supplier name , then reject
        if(supplierRepository.existsByName(dto.getName())) {
            throw new DuplicateResourceException("supplier is already existed with name :" + dto.getName());
        }

        Supplier supplier = mapper.toEntity(dto);

        supplierRepository.save(supplier);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModel("success","successfully created supplier"));
    }

    public ResponseEntity<BaseResponseModel> updateSupplier(Long supplierId, UpdateSupplierDto dto) {
        Supplier existingSupplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException("not found with id :"+supplierId));

        // if supplier not found, return 404

        mapper.updateEntityFromDto(existingSupplier,dto);

        supplierRepository.save(existingSupplier);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModel("success","successfully updated supplier"));
    }

    public ResponseEntity<BaseResponseModel> deleteSupplier(Long supplierId) {
        if (!supplierRepository.existsById(supplierId)) {
            throw new ResourceNotFoundException("supplier not found with id :" + supplierId);
        }
            supplierRepository.deleteById(supplierId);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new BaseResponseModel("success", "successfully deleted supplier"));
        }

}