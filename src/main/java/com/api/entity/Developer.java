package com.api.entity;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class Developer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String Password;
	private String phone;
	private String email;
	private String role;
	private Date createdAt;

	@Lob
	private byte[] qrCode;
	private LocalDate accountExpiryDate;
	private Boolean active;
}
