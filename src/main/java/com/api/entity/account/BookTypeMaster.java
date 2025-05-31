package com.api.entity.account;

import java.sql.Date;

import com.api.entity.School;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class BookTypeMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long booktypeId;
	
	@ManyToOne
	@JoinColumn(name = "school_udise")
	private School school_udise;
	
	private String booktypeName;
	
	private Date entryDate;
	
	
}
