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
public class GeneralLedger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private long entryNo;
	
	private Date entrydate;
	
	@ManyToOne
	@JoinColumn(name = "SchoolUdiseNo")
	private School shopId;
	
	private String entryType;
	
	@ManyToOne
	@JoinColumn(name="CustId")
	private CustomerMaster custId;
	
	private Double crAmt;
	
	private Double drAmt;
	
	private String year;
	
	private String narr;
	
	@ManyToOne
	@JoinColumn(name="HeadId")
	private HeadMaster headId;
	
	private String daybookname;
	
	private Date salepaydate;
	
	@ManyToOne
	@JoinColumn(name="SubHeadId")
	private SubHeadMaster subhead;
	
	private String entrynochar;
	
	private long billno;
	
	private long maxno;
	
	//perid
	
	//perhead
	
	//daybookid;
	
	//daybookheadid;
	
	private String saledup;
	
//	private int siteid;
		
}
