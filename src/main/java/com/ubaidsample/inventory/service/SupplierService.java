/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.service;

import com.ubaidsample.inventory.dto.request.SupplierPartialUpdateRequestDTO;
import com.ubaidsample.inventory.dto.request.SupplierRequestDTO;
import com.ubaidsample.inventory.dto.response.SupplierResponseDTO;
import com.ubaidsample.inventory.entity.Supplier;
import com.ubaidsample.inventory.exception.MissingInputException;
import com.ubaidsample.inventory.exception.ResourceNotFoundException;
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
public class SupplierService {

    private final SupplierRepository repository;

    private final ModelMapper modelMapper;

    @Transactional
    public SupplierResponseDTO save(SupplierRequestDTO request) {
        log.info("SupplierService -> save() called");
        if (request == null) {
            throw new MissingInputException("Info must not be null");
        }
        // Convert the DTO to the entity
        Supplier entity = modelMapper.map(request, Supplier.class);
        // Save the new supplier
        Supplier repositoryResponse = repository.save(entity);
        // Convert the entity to the DTO and return
        return modelMapper.map(repositoryResponse, SupplierResponseDTO.class);
    }

    @Transactional(readOnly = true)
    public List<SupplierResponseDTO> findAll() {
        log.info("SupplierService -> findAll() called");
        List<Supplier> entity = repository.findAll();
        if (entity.isEmpty()) {
            throw new ResourceNotFoundException("Nothing found in the database");
        }
        return entity.stream()
                .map(supplier -> {
                    // Convert the entity to the DTO
                    return MapperUtil.map(supplier, SupplierResponseDTO.class);
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public SupplierResponseDTO findById(Long id) {
        log.info("SupplierService -> findById() called");
        if (id == null) {
            throw new MissingInputException("ID must not be null");
        }
        Supplier entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nothing found in the database with id " + id));
        // Convert the entity to the DTO and return
        return modelMapper.map(entity, SupplierResponseDTO.class);
    }

    @Transactional
    public SupplierResponseDTO update(Long id, SupplierRequestDTO request) {
        log.info("SupplierService -> update() called");
        if (id == null || request == null) {
            throw new MissingInputException("ID and update info must not be null");
        }
        // Fetch existing supplier
        Supplier entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nothing found in the database with id " + id));
        // Update fields
        entity.setStoreId(request.getStoreId());
        entity.setAddressId(request.getAddressId());
        entity.setName(request.getName());
        entity.setContactPersonName(request.getContactPersonName());
        entity.setContactPersonEmail(request.getContactPersonEmail());
        entity.setContactPersonPhone(request.getContactPersonPhone());
        // Save updated supplier
        Supplier repositoryResponse = repository.save(entity);
        // Convert the entity to the DTO and return
        return modelMapper.map(repositoryResponse, SupplierResponseDTO.class);
    }

    @Transactional
    public SupplierResponseDTO partialUpdate(Long id, SupplierPartialUpdateRequestDTO updates) {
        log.info("SupplierService -> partialUpdate() called");
        if (id == null || updates == null) {
            throw new MissingInputException("ID and update info must not be null");
        }
        // Fetch existing supplier
        Supplier entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nothing not found with id " + id));
        // Apply updates
        updates.getStoreId().ifPresent(entity::setStoreId);
        updates.getAddressId().ifPresent(entity::setAddressId);
        updates.getName().ifPresent(entity::setName);
        updates.getContactPersonName().ifPresent(entity::setContactPersonName);
        updates.getContactPersonEmail().ifPresent(entity::setContactPersonEmail);
        updates.getContactPersonPhone().ifPresent(entity::setContactPersonPhone);
        // Save updated supplier
        Supplier repositoryResponse = repository.save(entity);
        // Convert the entity to the DTO and return
        return modelMapper.map(repositoryResponse, SupplierResponseDTO.class);
    }

    @Transactional
    public void delete(Long id) {
        log.info("SupplierService -> delete() called");
        if (id == null) {
            throw new MissingInputException("ID must not be null");
        }
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Nothing found in the database with id " + id);
        }
        repository.deleteById(id);
    }
}