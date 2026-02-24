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
public class SupplierPartialUpdateRequestDTO {

    private Optional<Long> storeId = Optional.empty();
    private Optional<Long> addressId = Optional.empty();
    private Optional<String> name = Optional.empty();
    private Optional<String> contactPersonName = Optional.empty();
    private Optional<String> contactPersonEmail = Optional.empty();
    private Optional<String> contactPersonPhone = Optional.empty();
}