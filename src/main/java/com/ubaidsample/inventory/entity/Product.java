/*
 * @author Muhammad Ubaid Ur Raheem Ahmad AKA Shahbaz Haroon
 * Email: shahbazhrn@gmail.com
 * Cell: +923002585925
 * GitHub: https://github.com/ShahbazHaroon
 */

package com.ubaidsample.inventory.entity;

import com.ubaidsample.inventory.dto.common.AuditHistoryDTO;
import com.ubaidsample.inventory.enums.TaxType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Comment("Product information")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "products",
        indexes = {
                //@Index(name = "idx_product_sku", columnList = "sku", unique = true),
                //@Index(name = "idx_product_name_description", columnList = "name, description", type = IndexType.FULLTEXT)
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_product_sku", columnNames = "sku")
        })
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "product_id", nullable = false, updatable = false)
    @Comment("Unique identifier for each product")
    private Long productId;

    @Column(name = "store_id", nullable = false, updatable = false)
    @Comment("Unique identifier of the associated store")
    private Long storeId;

    @Column(name = "name", nullable = false, length = 255)
    @Comment("Product name")
    private String name;

    @Column(name = "description", nullable = false, length = 255)
    @Comment("Product description")
    private String description;

    @Column(name = "sku", nullable = false, length = 255)
    @Comment("Product sku")
    private String sku;

    @Column(name = "barcode", length = 255)
    @Comment("Product barcode")
    private String barcode;

    @Column(name = "cost_price", precision = 10, scale = 2, nullable = false)
    @Comment("Product cost price")
    private BigDecimal costPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "tax_type", length = 20)
    @Comment("Product tax type")
    private TaxType taxType;

    @Column(name = "tax_rate", precision = 5, scale = 2)
    @Comment("Product tax rate")
    private BigDecimal taxRate;

    @Column(name = "is_perishable", nullable = false)
    @Comment("Product is perishable: 1 = yes, 0 = no")
    private boolean isPerishable = false;

    @Column(name = "expiry_date")
    @Comment("Product expiry date")
    private LocalDate expiryDate;

    @Column(name = "image_url", length = 500)
    @Comment("Product image url")
    private String imageUrl;

    @Column(name = "is_visible_in_mobile_app", nullable = false)
    @Comment("Product is visible in mobile application")
    private boolean isVisibleInMobileApp = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @Comment("Product category reference")
    private Category category;

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