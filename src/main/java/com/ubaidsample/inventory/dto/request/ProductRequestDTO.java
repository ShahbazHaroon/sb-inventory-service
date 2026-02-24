/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubaidsample.inventory.dto.common.AuditHistoryDTO;
import com.ubaidsample.inventory.enums.TaxType;
import jakarta.validation.constraints.*;
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
public class ProductRequestDTO {

    @NotNull(message = "Store id cannot be empty")
    @Positive
    @Min(1)
    @Max(999999)
    @JsonProperty("store_id")
    private Long storeId;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 4, max = 255)
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "Description cannot be empty")
    @Size(min = 4, max = 255)
    @JsonProperty("description")
    private String description;

    @NotBlank(message = "SKU cannot be empty")
    @Size(min = 4, max = 255)
    @JsonProperty("sku")
    private String sku;

    @NotBlank(message = "Barcode cannot be empty")
    //@Size(min = 4, max = 255)
    @JsonProperty("barcode")
    private String barcode;

    @NotNull(message = "Cost price cannot be empty")
    @Positive
    //@Min(1)
    //@Max(999999)
    @Digits(integer = 10, fraction = 2)
    @JsonProperty("cost_price")
    private BigDecimal costPrice;

    @NotNull(message = "Tax type cannot be empty")
    @JsonProperty("tax_type")
    private TaxType taxType;

    @NotNull(message = "Tax rate cannot be empty")
    @Positive
    @Min(1)
    @Max(999999)
    @Digits(integer = 5, fraction = 2)
    @JsonProperty("tax_rate")
    private BigDecimal taxRate;

    @NotNull(message = "Is perishable cannot be empty")
    @JsonProperty("is_perishable")
    private boolean isPerishable;

    @NotNull(message = "Expiry date cannot be empty")
    @JsonProperty("expiry_date")
    private LocalDate expiryDate;

    @NotNull(message = "Image URL cannot be empty")
    @Size(min = 4, max = 255)
    @JsonProperty("image_url")
    private String imageUrl;

    @NotNull(message = "Is visible in mobile app cannot be empty")
    @JsonProperty("is_visible_in_mobile_app")
    private boolean isVisibleInMobileApp = true;

    @NotNull(message = "Category id cannot be empty")
    @Positive
    @Min(1)
    @Max(999999)
    @JsonProperty("category_id")
    private Long categoryId;
}