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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierRequestDTO {

    @NotNull(message = "Store id cannot be empty")
    @Positive
    @JsonProperty("store_id")
    private Long storeId;

    @NotNull(message = "Address id cannot be empty")
    @Positive
    @JsonProperty("address_id")
    private Long addressId;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 4, max = 255)
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 4, max = 255)
    @JsonProperty("contact_person_name")
    private String contactPersonName;

    @NotBlank
    @Email(message = "Invalid email format")
    @Size(min = 4, max = 255)
    @JsonProperty("contact_person_email")
    private String contactPersonEmail;

    @NotBlank(message = "Phone cannot be empty")
    @Size(min = 4, max = 20)
    @JsonProperty("contact_person_phone")
    private String contactPersonPhone;
}