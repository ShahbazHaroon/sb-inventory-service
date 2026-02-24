/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.service;

import com.ubaidsample.inventory.dto.request.CategoryPartialUpdateRequestDTO;
import com.ubaidsample.inventory.dto.request.CategoryRequestDTO;
import com.ubaidsample.inventory.dto.response.CategoryResponseDTO;
import com.ubaidsample.inventory.entity.Category;
import com.ubaidsample.inventory.exception.MissingInputException;
import com.ubaidsample.inventory.exception.ResourceAlreadyExistsException;
import com.ubaidsample.inventory.exception.ResourceNotFoundException;
import com.ubaidsample.inventory.repository.CategoryRepository;
import com.ubaidsample.inventory.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    private final ModelMapper modelMapper;

    @Transactional
    public CategoryResponseDTO save(CategoryRequestDTO request) {
        log.info("CategoryService -> save() called");
        if (request == null) {
            throw new MissingInputException("Info must not be null");
        }
        // Retrieve parent category if provided
        Category parent = request.getParentCategoryId() != null
                ? repository.findById(request.getParentCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Parent category not found with id " + request.getParentCategoryId()))
                : null;
        // Check if a category with the same name already exists under the parent
        repository.findByNameAndParentCategory(request.getName(), parent)
                .ifPresent(existing -> {
                    // If exists, return the existing category to avoid duplicates
                    throw new ResourceAlreadyExistsException("Category already exists under this parent category");
                });
        // Convert the DTO to the entity
        Category entity = modelMapper.map(request, Category.class);
        entity.setParentCategory(parent);
        // Save the new category
        Category repositoryResponse = repository.save(entity);
        // Convert the entity to the DTO
        CategoryResponseDTO dto = modelMapper.map(repositoryResponse, CategoryResponseDTO.class);
        if (repositoryResponse.getParentCategory() != null) {
            dto.setParentCategoryId(repositoryResponse.getParentCategory().getCategoryId());
        }
        return dto;
    }

    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> findAll() {
        log.info("CategoryService -> findAll() called");
        List<Category> entity = repository.findAll();
        if (entity.isEmpty()) {
            throw new ResourceNotFoundException("Nothing found in the database");
        }
        return entity.stream()
                .map(category -> {
                    // Convert the entity to the DTO
                    CategoryResponseDTO dto = MapperUtil.map(category, CategoryResponseDTO.class);
                    if (category.getParentCategory() != null) {
                        dto.setParentCategoryId(category.getParentCategory().getCategoryId());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryResponseDTO findById(Long id) {
        log.info("CategoryService -> findById() called");
        if (id == null) {
            throw new MissingInputException("ID must not be null");
        }
        Category entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nothing found in the database with id " + id));
        // Convert the entity to the DTO
        CategoryResponseDTO dto = modelMapper.map(entity, CategoryResponseDTO.class);
        if (entity.getParentCategory() != null) {
            dto.setParentCategoryId(entity.getParentCategory().getCategoryId());
        }
        return dto;
    }

    @Transactional
    public CategoryResponseDTO update(Long id, CategoryRequestDTO request) {
        log.info("CategoryService -> update() called");
        if (id == null || request == null) {
            throw new MissingInputException("ID and update info must not be null");
        }
        // Fetch existing category
        Category entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nothing found in the database with id " + id));
        // Fetch parent category
        Category parent = Optional.ofNullable(request.getParentCategoryId())
                .map(pid -> repository.findById(pid)
                        .orElseThrow(() -> new ResourceNotFoundException("Parent category not found with id " + pid)))
                .orElse(null);
        // Check name uniqueness under parent (excluding itself)
        repository.findByNameAndParentCategory(request.getName(), parent)
                .filter(cat -> !cat.getCategoryId().equals(id))
                .ifPresent(cat -> {
                    throw new ResourceAlreadyExistsException("Category with the same name already exists under this parent");
                });
        // Update fields
        entity.setStoreId(request.getStoreId());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setSlug(request.getSlug());
        entity.setParentCategory(parent);
        // Save updated category
        Category repositoryResponse = repository.save(entity);
        // Convert the entity to the DTO
        CategoryResponseDTO dto = modelMapper.map(repositoryResponse, CategoryResponseDTO.class);
        if (repositoryResponse.getParentCategory() != null) {
            dto.setParentCategoryId(repositoryResponse.getParentCategory().getCategoryId());
        }
        return dto;
    }

    @Transactional
    public CategoryResponseDTO partialUpdate(Long id, CategoryPartialUpdateRequestDTO updates) {
        log.info("CategoryService -> partialUpdate() called");
        if (id == null || updates == null) {
            throw new MissingInputException("ID and update info must not be null");
        }
        // Fetch existing category
        Category entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nothing not found with id " + id));
        // Apply updates
        updates.getStoreId().ifPresent(entity::setStoreId);
        updates.getName().ifPresent(entity::setName);
        updates.getDescription().ifPresent(entity::setDescription);
        updates.getSlug().ifPresent(entity::setSlug);
        updates.getParentCategoryId().ifPresent(parentId -> {
            if (parentId.equals(id))
                throw new IllegalArgumentException("Category cannot be its own parent.");
            Category parent = repository.findById(parentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Parent category not found with id " + parentId));
            entity.setParentCategory(parent);
        });
        repository.findByNameAndParentCategory(entity.getName(), entity.getParentCategory())
                .filter(existing -> !existing.getCategoryId().equals(id))
                .ifPresent(existing -> {
                    throw new ResourceAlreadyExistsException("Category already exists under this parent");
                });
        // Save updated category
        Category repositoryResponse = repository.save(entity);
        // Convert the entity to the DTO
        CategoryResponseDTO dto = modelMapper.map(repositoryResponse, CategoryResponseDTO.class);
        if (repositoryResponse.getParentCategory() != null)
            dto.setParentCategoryId(repositoryResponse.getParentCategory().getCategoryId());
        return dto;
    }

    @Transactional
    public void delete(Long id) {
        log.info("CategoryService -> delete() called");
        if (id == null) {
            throw new MissingInputException("ID must not be null");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Nothing found in the database with id " + id);
        }
        repository.deleteById(id);
    }
}