/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubaidsample.inventory.dto.common.AuditHistoryDTO;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequestDTO {

    @NotNull(message = "Store id cannot be empty")
    @Positive
    @Min(1)
    @Max(999999)
    @JsonProperty("store_id")
    private Long storeId;

    @NotNull(message = "Inventory received date cannot be empty")
    @JsonProperty("inventory_received_date")
    private LocalDate inventoryReceivedDate;

    @NotNull(message = "Stock quantity cannot be empty")
    //@Positive
    @Min(1)
    @Max(999999)
    @JsonProperty("stock_quantity")
    private Integer stockQuantity;

    @NotNull(message = "minimum stock level cannot be empty")
    @Positive
    @Min(1)
    @Max(999999)
    @JsonProperty("min_stock_level")
    private Integer minStockLevel;

    @NotNull(message = "maximum stock level cannot be empty")
    @Positive
    @Min(1)
    @Max(999999)
    @JsonProperty("max_stock_level")
    private Integer maxStockLevel;

    @NotBlank(message = "Batch number cannot be empty")
    @Size(max = 50)
    @JsonProperty("batch_no")
    private String batchNo;

    @NotNull(message = "Supplier cannot be empty")
    @Positive
    @Min(1)
    @Max(999999)
    @JsonProperty("supplier_id")
    private Long supplierId;

    @NotNull(message = "Product cannot be empty")
    @Positive
    @Min(1)
    @Max(999999)
    @JsonProperty("product_id")
    private Long productId;
}