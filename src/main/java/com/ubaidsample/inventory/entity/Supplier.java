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

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Comment("Supplier information")
@EntityListeners(AuditingEntityListener.class)
@Table(name = "suppliers",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_supplier_contact_person_email", columnNames = "contact_person_email"),
                @UniqueConstraint(name = "uk_supplier_contact_person_phone", columnNames = "contact_person_phone")
        })
public class Supplier implements Serializable {
	
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "supplier_id", nullable = false, updatable = false)
	@Comment("Unique identifier for each supplier")
    private Long supplierId;

    @Column(name = "store_id", nullable = false, updatable = false)
    @Comment("Unique identifier of the associated store")
    private Long storeId;

    @Column(name = "address_id", nullable = false, updatable = false)
    @Comment("Unique identifier for each address")
    private Long addressId;

    @Column(name = "name", nullable = false, length = 255)
	@Comment("Supplier name")
    private String name;

    @Column(name = "contact_person_name", nullable = false, length = 255)
    @Comment("Supplier contact person name")
    private String contactPersonName;

    @Column(name = "contact_person_email", nullable = false, length = 255)
    @Comment("Supplier contact person email")
    private String contactPersonEmail;

    @Column(name = "contact_person_phone", nullable = false, length = 20)
    @Comment("Supplier contact person phone number")
    private String contactPersonPhone;

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