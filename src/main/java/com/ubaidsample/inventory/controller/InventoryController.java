/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.controller;

import com.ubaidsample.inventory.dto.request.InventoryPartialUpdateRequestDTO;
import com.ubaidsample.inventory.dto.request.InventoryRequestDTO;
import com.ubaidsample.inventory.dto.response.InventoryResponseDTO;
import com.ubaidsample.inventory.service.InventoryService;
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
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService service;

    @Operation(summary = "Create new inventory")
    @ApiResponse(responseCode = "201", description = "inventory created")
    @PostMapping
    public ResponseEntity<InventoryResponseDTO> save(@Valid @RequestBody InventoryRequestDTO request) {
        log.info("InventoryController -> save() called");
        var response = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get all inventories", description = "Retrieves all inventories")
    @ApiResponse(responseCode = "200", description = "inventory found")
    @GetMapping
    public ResponseEntity<List<InventoryResponseDTO>> findAll() {
        log.info("InventoryController -> findAll() called");
        var response = service.findAll();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get inventory by ID", description = "Retrieves a inventory")
    @ApiResponse(responseCode = "200", description = "inventory found")
    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponseDTO> findById(@PathVariable(value = "id") Long id) {
        log.info("InventoryController -> findById() called with ID: {}", id);
        var response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update inventory by ID", description = "Updates a inventory")
    @ApiResponse(responseCode = "200", description = "inventory updated")
    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponseDTO> update(@PathVariable Long id, @Valid @RequestBody InventoryRequestDTO request) {
        log.info("InventoryController -> update() called with ID: {}", id);
        var response = service.update(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update inventory by ID", description = "Updates a inventory")
    @ApiResponse(responseCode = "200", description = "inventory updated")
    @PatchMapping("/{id}")
    public ResponseEntity<InventoryResponseDTO> partialUpdate(@PathVariable Long id, @Valid @RequestBody InventoryPartialUpdateRequestDTO updates) {
        log.info("InventoryController -> partialUpdate() called with ID: {}", id);
        var response = service.partialUpdate(id, updates);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete inventory by ID", description = "Deletes a inventory")
    @ApiResponse(responseCode = "204", description = "inventory deleted")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id) {
        log.info("InventoryController -> deleteById() called with ID: {}", id);
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}