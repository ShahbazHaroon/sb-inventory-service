/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ubaidsample.inventory.dto.common.AuditHistoryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {

    @JsonProperty("store_id")
    private Long storeId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("parent_category_id")
    private Long parentCategoryId;

    @JsonProperty("auditHistoryDTO")
    private AuditHistoryDTO auditHistoryDTO;
}