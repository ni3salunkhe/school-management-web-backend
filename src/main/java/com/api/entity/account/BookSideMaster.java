package com.api.entity.account;

import java.sql.Date;

import com.api.entity.School;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class BookSideMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long booksideId;
	
	private String booksideName;
	
	@ManyToOne
	@JoinColumn(name = "school_udise")
	private School schoolUdise;
	
	@ManyToOne
	@JoinColumn(name = "booktype_id")
	private BookTypeMaster booktypeId;
	
	private Date entryDate;
}
