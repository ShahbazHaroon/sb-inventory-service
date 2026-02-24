/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.controller;

import com.ubaidsample.inventory.dto.request.SupplierPartialUpdateRequestDTO;
import com.ubaidsample.inventory.dto.request.SupplierRequestDTO;
import com.ubaidsample.inventory.dto.response.SupplierResponseDTO;
import com.ubaidsample.inventory.service.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService service;

    @Operation(summary = "Create new supplier")
    @ApiResponse(responseCode = "201", description = "supplier created")
    @PostMapping
    public ResponseEntity<SupplierResponseDTO> save(@Valid @RequestBody SupplierRequestDTO request) {
        log.info("SupplierController -> save() called");
        var response = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get all suppliers", description = "Retrieves all suppliers")
    @ApiResponse(responseCode = "200", description = "supplier found")
    @GetMapping
    public ResponseEntity<List<SupplierResponseDTO>> findAll() {
        log.info("SupplierController -> findAll() called");
        var response = service.findAll();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get supplier by ID", description = "Retrieves a supplier")
    @ApiResponse(responseCode = "200", description = "supplier found")
    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> findById(@PathVariable(value = "id") Long id) {
        log.info("SupplierController -> findById() called with ID: {}", id);
        var response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update supplier by ID", description = "Updates a supplier")
    @ApiResponse(responseCode = "200", description = "supplier updated")
    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> update(@PathVariable Long id, @Valid @RequestBody SupplierRequestDTO request) {
        log.info("SupplierController -> update() called with ID: {}", id);
        var response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update supplier by ID", description = "Updates a supplier")
    @ApiResponse(responseCode = "200", description = "supplier updated")
    @PatchMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> partialUpdate(@PathVariable Long id, @Valid @RequestBody SupplierPartialUpdateRequestDTO updates) {
        log.info("SupplierController -> partialUpdate() called with ID: {}", id);
        var response = service.partialUpdate(id, updates);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete supplier by ID", description = "Deletes a supplier")
    @ApiResponse(responseCode = "204", description = "supplier deleted")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id) {
        log.info("SupplierController -> deleteById() called with ID: {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}