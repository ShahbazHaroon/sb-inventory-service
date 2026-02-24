/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.dto.request;

import lombok.Getter;

import java.util.Optional;

@Getter
public class CategoryPartialUpdateRequestDTO {

    private Optional<Long> storeId = Optional.empty();
    private Optional<String> name = Optional.empty();
    private Optional<String> description = Optional.empty();
    private Optional<String> slug = Optional.empty();
    private Optional<Long> parentCategoryId = Optional.empty();
}