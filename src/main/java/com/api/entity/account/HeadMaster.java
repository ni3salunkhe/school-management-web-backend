package com.api.entity.account;

import com.api.entity.School;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "head_master")
@Data
public class HeadMaster {
	
	@Id
	private long headId;
	
	@ManyToOne
	@JoinColumn(name = "school_udise")
	private School schoolUdise;
	
	private String headName;
	
	@ManyToOne
	@JoinColumn(name = "BooksideId")
	private BookSideMaster bookSideMaster;
	
	@ManyToOne
	@JoinColumn(name = "BooktypeId")
	private BookTypeMaster bookTypeMaster;
	
}
