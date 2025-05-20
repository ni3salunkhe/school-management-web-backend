package com.api.entity.account;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "head_master")
@Data
public class HeadMaster {
	
	@Id
	private long headId;
	
	private String head_name;
}
