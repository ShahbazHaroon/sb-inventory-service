/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.entity;

import com.ubaidsample.inventory.dto.common.AuditHistoryDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Comment("Category information")
@ToString(exclude = {"parentCategory", "subCategories"})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "categories",
        indexes = {
                @Index(name = "idx_category_name", columnList = "name", unique = true)
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_category_slug", columnNames = "slug")
        })
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "category_id", nullable = false, updatable = false)
    @Comment("Unique identifier for each category")
    private Long categoryId;

    @Column(name = "store_id", nullable = false, updatable = false)
    @Comment("Unique identifier of the associated store")
    private Long storeId;

    @Column(name = "name", nullable = false, length = 255)
    @Comment("Category name")
    private String name;

    @Column(name = "description", nullable = false, length = 255)
    @Comment("Category description")
    private String description;

    @Column(name = "slug", nullable = false, length = 255)
    @Comment("URL-friendly unique category identifier")
    private String slug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    @Comment("Parent category reference")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Category> subCategories = new HashSet<>();

    public void addSubCategory(Category subCategory) {
        subCategories.add(subCategory);
        subCategory.setParentCategory(this);
    }

    public void removeSubCategory(Category subCategory) {
        subCategories.remove(subCategory);
        subCategory.setParentCategory(null);
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "createdBy", column = @Column(name = "created_by")),
            @AttributeOverride(name = "createdDate", column = @Column(name = "created_date")),
            @AttributeOverride(name = "updatedBy", column = @Column(name = "updated_by")),
            @AttributeOverride(name = "updatedDate", column = @Column(name = "updated_date")),
    })
    @Comment("Auditing related fields")
    private AuditHistoryDTO auditHistoryDTO  = new AuditHistoryDTO();
}