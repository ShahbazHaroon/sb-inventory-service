/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.controller;

import com.ubaidsample.inventory.dto.request.CategoryPartialUpdateRequestDTO;
import com.ubaidsample.inventory.dto.request.CategoryRequestDTO;
import com.ubaidsample.inventory.dto.response.CategoryResponseDTO;
import com.ubaidsample.inventory.service.CategoryService;
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
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @Operation(summary = "Create new category")
    @ApiResponse(responseCode = "201", description = "category created")
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> save(@Valid @RequestBody CategoryRequestDTO request) {
        log.info("CategoryController -> save() called");
        var response = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get all categories", description = "Retrieves all categories")
    @ApiResponse(responseCode = "200", description = "category found")
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {
        log.info("CategoryController -> findAll() called");
        var response = service.findAll();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get category by ID", description = "Retrieves a category")
    @ApiResponse(responseCode = "200", description = "category found")
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable(value = "id") Long id) {
        log.info("CategoryController -> findById() called with ID: {}", id);
        var response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update category by ID", description = "Updates a category")
    @ApiResponse(responseCode = "200", description = "category updated")
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CategoryRequestDTO request) {
        log.info("CategoryController -> update() called with ID: {}", id);
        var response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update category by ID", description = "Updates a category")
    @ApiResponse(responseCode = "200", description = "category updated")
    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> partialUpdate(@PathVariable Long id, @Valid @RequestBody CategoryPartialUpdateRequestDTO updates) {
        log.info("CategoryController -> partialUpdate() called with ID: {}", id);
        var response = service.partialUpdate(id, updates);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete category by ID", description = "Deletes a category")
    @ApiResponse(responseCode = "204", description = "category deleted")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id) {
        log.info("CategoryController -> deleteById() called with ID: {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}