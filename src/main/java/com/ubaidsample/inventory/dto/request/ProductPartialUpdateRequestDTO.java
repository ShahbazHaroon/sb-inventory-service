/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.dto.request;

import com.ubaidsample.inventory.enums.TaxType;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Getter
public class ProductPartialUpdateRequestDTO {

    private Optional<Long> storeId = Optional.empty();
    private Optional<String> name = Optional.empty();
    private Optional<String> description = Optional.empty();
    private Optional<String> sku = Optional.empty();
    private Optional<String> barcode = Optional.empty();
    private Optional<BigDecimal> costPrice = Optional.empty();
    private Optional<TaxType> taxType = Optional.empty();
    private Optional<BigDecimal> taxRate = Optional.empty();
    private Optional<Boolean> isPerishable = Optional.empty();
    private Optional<LocalDate> expiryDate = Optional.empty();
    private Optional<String> imageUrl = Optional.empty();
    private Optional<Boolean> isVisibleInMobileApp = Optional.empty();
    private Optional<Long> categoryId = Optional.empty();
}