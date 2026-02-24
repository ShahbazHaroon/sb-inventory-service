/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.dto.request;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Optional;

@Getter
public class InventoryPartialUpdateRequestDTO {

    private Optional<Long> storeId = Optional.empty();
    private Optional<LocalDate> inventoryReceivedDate = Optional.empty();
    private Optional<Integer> stockQuantity = Optional.empty();
    private Optional<Integer> minStockLevel = Optional.empty();
    private Optional<Integer> maxStockLevel = Optional.empty();
    private Optional<String> batchNo = Optional.empty();
    private Optional<Long> supplierId = Optional.empty();
    private Optional<Long> productId = Optional.empty();

}