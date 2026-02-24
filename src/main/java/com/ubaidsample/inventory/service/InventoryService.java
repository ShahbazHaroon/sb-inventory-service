/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.service;

import com.ubaidsample.inventory.dto.request.InventoryPartialUpdateRequestDTO;
import com.ubaidsample.inventory.dto.request.InventoryRequestDTO;
import com.ubaidsample.inventory.dto.response.InventoryResponseDTO;
import com.ubaidsample.inventory.entity.Category;
import com.ubaidsample.inventory.entity.Inventory;
import com.ubaidsample.inventory.entity.Product;
import com.ubaidsample.inventory.entity.Supplier;
import com.ubaidsample.inventory.exception.MissingInputException;
import com.ubaidsample.inventory.exception.ResourceNotFoundException;
import com.ubaidsample.inventory.repository.InventoryRepository;
import com.ubaidsample.inventory.repository.ProductRepository;
import com.ubaidsample.inventory.repository.SupplierRepository;
import com.ubaidsample.inventory.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;

    private final SupplierRepository supplierRepository;

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Transactional
    public InventoryResponseDTO save(InventoryRequestDTO request) {
        log.info("InventoryService -> save() called");
        if (request == null) {
            throw new MissingInputException("Info must not be null");
        }
        // Fetch supplier if provided
        Supplier supplier = null;
        if (request.getSupplierId() != null) {
            supplier = supplierRepository.findById(request.getSupplierId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Supplier not found with id " + request.getSupplierId()));
        }
        // Fetch product if provided
        Product product = null;
        if (request.getProductId() != null) {
            product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Product not found with id " + request.getProductId()));
        }
        // Convert the DTO to the entity
        Inventory entity = modelMapper.map(request, Inventory.class);
        // Set supplier manually
        entity.setSupplier(supplier);
        // Set product manually
        entity.setProduct(product);
        // Save the new inventory
        Inventory repositoryResponse = repository.save(entity);
        // Convert the entity to the DTO
        InventoryResponseDTO dto = modelMapper.map(repositoryResponse, InventoryResponseDTO.class);
        dto.setSupplierId(repositoryResponse.getSupplier().getSupplierId() != null
                ? repositoryResponse.getSupplier().getSupplierId()
                : null);
        dto.setProductId(repositoryResponse.getProduct().getProductId() != null
                ? repositoryResponse.getProduct().getProductId()
                : null);
        return dto;
    }

    @Transactional(readOnly = true)
    public List<InventoryResponseDTO> findAll() {
        log.info("InventoryService -> findAll() called");
        List<Inventory> entity = repository.findAll();
        if (entity.isEmpty()) {
            throw new ResourceNotFoundException("Nothing found in the database");
        }
        return entity.stream()
                .map(inventory -> {
                    // Convert the entity to the DTO
                    InventoryResponseDTO dto = MapperUtil.map(inventory, InventoryResponseDTO.class);
                    if (inventory.getSupplier().getSupplierId() != null) {
                        dto.setSupplierId(inventory.getSupplier().getSupplierId());
                    }
                    if (inventory.getProduct().getProductId() != null) {
                        dto.setProductId(inventory.getProduct().getProductId());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public InventoryResponseDTO findById(Long id) {
        log.info("InventoryService -> findById() called");
        if (id == null) {
            throw new MissingInputException("ID must not be null");
        }
        Inventory entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nothing found in the database with id " + id));
        // Convert the entity to the DTO
        InventoryResponseDTO dto = modelMapper.map(entity, InventoryResponseDTO.class);
        if (entity.getSupplier().getSupplierId() != null) {
            dto.setSupplierId(entity.getSupplier().getSupplierId());
        }
        if (entity.getProduct().getProductId() != null) {
            dto.setProductId(entity.getProduct().getProductId());
        }
        return dto;
    }

    @Transactional
    public InventoryResponseDTO update(Long id, InventoryRequestDTO request) {
        log.info("InventoryService -> update() called");
        if (id == null || request == null) {
            throw new MissingInputException("ID and update info must not be null");
        }
        // Fetch existing inventory
        Inventory entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nothing found in the database with id " + id));
        // Fetch supplier if provided
        Supplier supplier = null;
        if (request.getSupplierId() != null) {
            supplier = supplierRepository.findById(request.getSupplierId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Supplier not found with id " + request.getSupplierId()));
        }
        // Fetch product if provided
        Product product = null;
        if (request.getProductId() != null) {
            product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Product not found with id " + request.getProductId()));
        }
        // Update fields
        entity.setStoreId(request.getStoreId());
        entity.setInventoryReceivedDate(request.getInventoryReceivedDate());
        entity.setStockQuantity(request.getStockQuantity());
        entity.setMinStockLevel(request.getMinStockLevel());
        entity.setMaxStockLevel(request.getMaxStockLevel());
        entity.setBatchNo(request.getBatchNo());
        entity.setSupplier(supplier);
        entity.setProduct(product);
        // Save updated inventory
        Inventory repositoryResponse = repository.save(entity);
        // Convert the entity to the DTO
        InventoryResponseDTO dto = modelMapper.map(repositoryResponse, InventoryResponseDTO.class);
        if (repositoryResponse.getSupplier().getSupplierId() != null) {
            dto.setSupplierId(repositoryResponse.getSupplier().getSupplierId());
        }
        if (repositoryResponse.getProduct().getProductId() != null) {
            dto.setProductId(repositoryResponse.getProduct().getProductId());
        }
        return dto;
    }

    @Transactional
    public InventoryResponseDTO partialUpdate(Long id, InventoryPartialUpdateRequestDTO updates) {
        log.info("InventoryService -> partialUpdate() called");
        if (id == null || updates == null) {
            throw new MissingInputException("ID and update info must not be null");
        }
        // Fetch existing inventory
        Inventory entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nothing not found with id " + id));
        // Apply updates
        updates.getStoreId().ifPresent(entity::setStoreId);
        updates.getInventoryReceivedDate().ifPresent(entity::setInventoryReceivedDate);
        updates.getStockQuantity().ifPresent(entity::setStockQuantity);
        updates.getMinStockLevel().ifPresent(entity::setMinStockLevel);
        updates.getMaxStockLevel().ifPresent(entity::setMaxStockLevel);
        updates.getBatchNo().ifPresent(entity::setBatchNo);
        updates.getSupplierId().ifPresent(supplierId -> {
            Supplier supplier = supplierRepository.findById(supplierId)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + supplierId));
            entity.setSupplier(supplier);
        });
        updates.getProductId().ifPresent(productId -> {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + productId));
            entity.setProduct(product);
        });
        // Save updated inventory
        Inventory repositoryResponse = repository.save(entity);
        // Convert the entity to the DTO
        InventoryResponseDTO dto = modelMapper.map(repositoryResponse, InventoryResponseDTO.class);
        if (repositoryResponse.getSupplier().getSupplierId() != null)
            dto.setSupplierId(repositoryResponse.getSupplier().getSupplierId());
        if (repositoryResponse.getProduct().getProductId() != null)
            dto.setProductId(repositoryResponse.getProduct().getProductId());
        return dto;
    }

    @Transactional
    public void delete(Long id) {
        log.info("InventoryService -> delete() called");
        if (id == null) {
            throw new MissingInputException("ID must not be null");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Nothing found in the database with id " + id);
        }
        repository.deleteById(id);
    }
}