package com.hospital.auth.entity;

import java.util.HashSet;
import java.util.Set;

import com.hospital.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
		name = "users",
		indexes = {
				
				@Index(name = "idx_user_email", columnList = "email"),
				
				@Index(name = "idx_user_mobile", columnList = "mobile"),
				
				@Index(name = "idx_user_username", columnList = "username")
				
		}
	)
@Setter
@Getter
public class User extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(
			nullable = false,
			unique = true,
			length = 100
			)
	private String username;
	
	@Column(
			nullable = false,
			unique = true,
			length = 150
			)
	private String email;
	
	@Column(
			nullable = false,
			unique = true,
			length = 15
			)
	private String mobile;
	
	@Column(nullable = false)
	private String password;
	
	@Column(name = "account_non_locked")
	private Boolean accountNonLocked = true;
	
	@Column(name = "enabled")
	private Boolean enabled = true;
	
	@ManyToMany(fetch = FetchType.LAZY)
	
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private Set<Role> roles = new HashSet<>();
}
