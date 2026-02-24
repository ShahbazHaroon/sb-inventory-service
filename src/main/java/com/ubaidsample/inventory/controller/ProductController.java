/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.controller;

import com.ubaidsample.inventory.dto.request.ProductPartialUpdateRequestDTO;
import com.ubaidsample.inventory.dto.request.ProductRequestDTO;
import com.ubaidsample.inventory.dto.response.ProductResponseDTO;
import com.ubaidsample.inventory.service.ProductService;
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
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @Operation(summary = "Create new product")
    @ApiResponse(responseCode = "201", description = "product created")
    @PostMapping
    public ResponseEntity<ProductResponseDTO> save(@Valid @RequestBody ProductRequestDTO request) {
        log.info("ProductController -> save() called");
        var response = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get all products", description = "Retrieves all products")
    @ApiResponse(responseCode = "200", description = "product found")
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        log.info("ProductController -> findAll() called");
        var response = service.findAll();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get product by ID", description = "Retrieves a product")
    @ApiResponse(responseCode = "200", description = "product found")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable(value = "id") Long id) {
        log.info("ProductController -> findById() called with ID: {}", id);
        var response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update product by ID", description = "Updates a product")
    @ApiResponse(responseCode = "200", description = "product updated")
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO request) {
        log.info("ProductController -> update() called with ID: {}", id);
        var response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update product by ID", description = "Updates a product")
    @ApiResponse(responseCode = "200", description = "product updated")
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> partialUpdate(@PathVariable Long id, @Valid @RequestBody ProductPartialUpdateRequestDTO updates) {
        log.info("ProductController -> partialUpdate() called with ID: {}", id);
        var response = service.partialUpdate(id, updates);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete product by ID", description = "Deletes a product")
    @ApiResponse(responseCode = "204", description = "product deleted")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id) {
        log.info("ProductController -> deleteById() called with ID: {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}