/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubaidsample.inventory.dto.common.AuditHistoryDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponseDTO {

    @JsonProperty("store_id")
    private Long storeId;

    @JsonProperty("inventory_received_date")
    private LocalDate inventoryReceivedDate;

    @JsonProperty("stock_quantity")
    private Integer stockQuantity = 0;

    @JsonProperty("min_stock_level")
    private Integer minStockLevel;

    @JsonProperty("max_stock_level")
    private Integer maxStockLevel;

    @JsonProperty("batch_no")
    private String batchNo;

    @JsonProperty("supplier_id")
    private Long supplierId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("auditHistoryDTO")
    private AuditHistoryDTO auditHistoryDTO;
}