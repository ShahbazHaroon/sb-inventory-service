/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {

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

    @NotBlank(message = "Slug cannot be empty")
    @Size(min = 4, max = 255)
    @JsonProperty("slug")
    private String slug;

    @Min(1)
    @Max(999999)
    @Positive
    @JsonProperty("parent_category_id")
    private Long parentCategoryId;
}