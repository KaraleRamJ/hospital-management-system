package com.hospital.patient.entity;

import java.time.LocalDate;

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

@Entity
@Table(
		name = "patients",
		indexes = {
				
				@Index(name = "idx_patient_aadhar", columnList = "aadhar_number")
		}
	)
public class Patient extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(
			name = "first_name",
			nullable = false,
			length = 100
	)
	private String firstName;
	
	@Column(
			name = "middle_name",
			nullable = false,
			length = 100
		)
	private String middleName;
	
	@Column(
			name = "last_name",
			nullable = false,
			length = 100
	)
	private String lastName;
	
	@Column(
		name = "aadhar_number",
		nullable = false,
		unique = true,
		length = 12
		)
	private String aadharNumber;
	
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(
			    name = "user_id",
			    nullable = false,
			    unique = true
			)
	private User user;
	
}
