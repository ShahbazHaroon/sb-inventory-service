/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubaidsample.inventory.dto.common.AuditHistoryDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierResponseDTO {

    @JsonProperty("store_id")
    private Long storeId;

    @JsonProperty("address_id")
    private Long addressId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("contact_person_name")
    private String contactPersonName;

    @JsonProperty("contact_person_email")
    private String contactPersonEmail;

    @JsonProperty("contact_person_phone")
    private String contactPersonPhone;

    @JsonProperty("auditHistoryDTO")
    private AuditHistoryDTO auditHistoryDTO;
}