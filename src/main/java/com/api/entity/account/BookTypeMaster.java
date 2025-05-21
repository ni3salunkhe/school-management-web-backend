package com.api.entity.account;

import java.sql.Date;

import com.api.entity.School;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class BookTypeMaster {
	
	@Id
	private long booktypeId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "school_udise")
	private School school_udise;
	
	private String booktypeName;
	
	private Date entryDate;
	
	
}
