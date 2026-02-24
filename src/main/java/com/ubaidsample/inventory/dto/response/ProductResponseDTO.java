/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubaidsample.inventory.dto.common.AuditHistoryDTO;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    @JsonProperty("store_id")
    private Long storeId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("sku")
    private String sku;

    @JsonProperty("barcode")
    private String barcode;

    @JsonProperty("cost_price")
    private BigDecimal costPrice;

    @JsonProperty("tax_type")
    private String taxType;

    @JsonProperty("tax_rate")
    private BigDecimal taxRate;

    @JsonProperty("is_perishable")
    private boolean isPerishable;

    @JsonProperty("expiry_date")
    private LocalDate expiryDate;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("is_visible_in_mobile_app")
    private boolean isVisibleInMobileApp = true;

    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("auditHistoryDTO")
    private AuditHistoryDTO auditHistoryDTO;
}