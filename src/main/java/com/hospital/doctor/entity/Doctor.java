package com.hospital.doctor.entity;

import com.hospital.auth.entity.User;
import com.hospital.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "doctors",
        indexes = {

                @Index(name = "idx_doctor_license", columnList = "license_number")
        }
)
@Getter
@Setter
public class Doctor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false,
            length = 100
    )
    private String specialization;

    @Column(
            name = "license_number",
            nullable = false,
            unique = true
    )
    private String licenseNumber;

    @OneToOne(fetch = FetchType.LAZY)

    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private User user;
}