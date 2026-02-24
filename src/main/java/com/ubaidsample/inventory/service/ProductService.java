/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.service;

import com.ubaidsample.inventory.dto.request.ProductPartialUpdateRequestDTO;
import com.ubaidsample.inventory.dto.request.ProductRequestDTO;
import com.ubaidsample.inventory.dto.response.ProductResponseDTO;
import com.ubaidsample.inventory.entity.Category;
import com.ubaidsample.inventory.entity.Product;
import com.ubaidsample.inventory.exception.MissingInputException;
import com.ubaidsample.inventory.exception.ResourceAlreadyExistsException;
import com.ubaidsample.inventory.exception.ResourceNotFoundException;
import com.ubaidsample.inventory.repository.CategoryRepository;
import com.ubaidsample.inventory.repository.ProductRepository;
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
public class ProductService {

    private final ProductRepository repository;

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Transactional
    public ProductResponseDTO save(ProductRequestDTO request) {
        log.info("ProductService -> save() called");
        if (request == null) {
            throw new MissingInputException("Info must not be null");
        }
        // Fetch category if provided
        Category category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Category not found with id " + request.getCategoryId()));
        }
        // Convert the DTO to the entity
        Product entity = modelMapper.map(request, Product.class);
        // Set category manually
        entity.setCategory(category);
        // Save the new product
        Product repositoryResponse = repository.save(entity);
        // Convert the entity to the DTO
        ProductResponseDTO dto = modelMapper.map(repositoryResponse, ProductResponseDTO.class);
        dto.setCategoryId(repositoryResponse.getCategory() != null
                ? repositoryResponse.getCategory().getCategoryId()
                : null);
        return dto;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDTO> findAll() {
        log.info("ProductService -> findAll() called");
        List<Product> entity = repository.findAll();
        if (entity.isEmpty()) {
            throw new ResourceNotFoundException("Nothing found in the database");
        }
        return entity.stream()
                .map(product -> {
                    // Convert the entity to the DTO
                    ProductResponseDTO dto = MapperUtil.map(product, ProductResponseDTO.class);
                    if (product.getCategory().getCategoryId() != null) {
                        dto.setCategoryId(product.getCategory().getCategoryId());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO findById(Long id) {
        log.info("ProductService -> findById() called");
        if (id == null) {
            throw new MissingInputException("ID must not be null");
        }
        Product entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nothing found in the database with id " + id));
        // Convert the entity to the DTO
        ProductResponseDTO dto = modelMapper.map(entity, ProductResponseDTO.class);
        if (entity.getCategory().getCategoryId() != null) {
            dto.setCategoryId(entity.getCategory().getCategoryId());
        }
        return dto;
    }

    @Transactional
    public ProductResponseDTO update(Long id, ProductRequestDTO request) {
        log.info("ProductService -> update() called");
        if (id == null || request == null) {
            throw new MissingInputException("ID and update info must not be null");
        }
        // Fetch existing product
        Product entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nothing found in the database with id " + id));
        // Fetch category if provided
        Category category = null;
        if (request.getCategoryId() != null) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Category not found with id " + request.getCategoryId()));
        }
        // Update fields
        entity.setStoreId(request.getStoreId());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setSku(request.getSku());
        entity.setBarcode(request.getBarcode());
        entity.setCostPrice(request.getCostPrice());
        entity.setTaxType(request.getTaxType());
        entity.setTaxRate(request.getTaxRate());
        entity.setPerishable(request.isPerishable());
        entity.setExpiryDate(request.getExpiryDate());
        entity.setImageUrl(request.getImageUrl());
        entity.setVisibleInMobileApp(request.isVisibleInMobileApp());
        entity.setCategory(category);
        // Save updated product
        Product repositoryResponse = repository.save(entity);
        // Convert the entity to the DTO
        ProductResponseDTO dto = modelMapper.map(repositoryResponse, ProductResponseDTO.class);
        if (repositoryResponse.getCategory().getCategoryId() != null) {
            dto.setCategoryId(repositoryResponse.getCategory().getCategoryId());
        }
        return dto;
    }

    @Transactional
    public ProductResponseDTO partialUpdate(Long id, ProductPartialUpdateRequestDTO updates) {
        log.info("ProductService -> partialUpdate() called");
        if (id == null || updates == null) {
            throw new MissingInputException("ID and update info must not be null");
        }
        // Fetch existing product
        Product entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nothing not found with id " + id));
        // Apply updates
        updates.getStoreId().ifPresent(entity::setStoreId);
        updates.getName().ifPresent(entity::setName);
        updates.getDescription().ifPresent(entity::setDescription);
        updates.getSku().ifPresent(entity::setSku);
        updates.getBarcode().ifPresent(entity::setBarcode);
        updates.getCostPrice().ifPresent(entity::setCostPrice);
        updates.getTaxType().ifPresent(entity::setTaxType);
        updates.getTaxRate().ifPresent(entity::setTaxRate);
        updates.getIsPerishable().ifPresent(entity::setPerishable);
        updates.getExpiryDate().ifPresent(entity::setExpiryDate);
        updates.getImageUrl().ifPresent(entity::setImageUrl);
        updates.getIsVisibleInMobileApp().ifPresent(entity::setVisibleInMobileApp);
        updates.getCategoryId().ifPresent(categoryId -> {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + categoryId));
            entity.setCategory(category);
        });
        // Save updated product
        Product repositoryResponse = repository.save(entity);
        // Convert the entity to the DTO
        ProductResponseDTO dto = modelMapper.map(repositoryResponse, ProductResponseDTO.class);
        if (repositoryResponse.getCategory().getCategoryId() != null)
            dto.setCategoryId(repositoryResponse.getCategory().getCategoryId());
        return dto;
    }

    @Transactional
    public void delete(Long id) {
        log.info("ProductService -> delete() called");
        if (id == null) {
            throw new MissingInputException("ID must not be null");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Nothing found in the database with id " + id);
        }
        repository.deleteById(id);
    }
}