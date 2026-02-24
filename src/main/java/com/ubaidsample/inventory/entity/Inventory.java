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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Comment("Inventory information")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "inventories",
        indexes = {
                //@Index(name = "idx_category_slug", columnList = "slug", unique = true)
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_inventory_batch_no", columnNames = "batch_no"),
        })
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "inventory_id", nullable = false, updatable = false)
    @Comment("Unique identifier for each inventory")
    private Long inventoryId;

    @Column(name = "store_id", nullable = false, updatable = false)
    @Comment("Unique identifier of the associated store")
    private Long storeId;

    @Column(name = "inventory_received_date", nullable = false)
    @Comment("Date when the inventory was Received")
    private LocalDate inventoryReceivedDate;

    @Column(name = "stock_quantity", nullable = false)
    @Comment("Stock quantity")
    private Integer stockQuantity;

    @Column(name = "min_stock_level", nullable = false)
    @Comment("Minimum stock quantity")
    private Integer minStockLevel;

    @Column(name = "max_stock_level", nullable = false)
    @Comment("Maximum stock quantity")
    private Integer maxStockLevel;

    @Column(name = "batch_no", nullable = false,length = 50)
    @Comment("Batch number")
    private String batchNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

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