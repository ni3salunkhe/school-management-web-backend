package com.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Division {
	
	@Id
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "SchoolUdiseNo")
	private School schoolUdiseNo;
	
	private String name;
}
