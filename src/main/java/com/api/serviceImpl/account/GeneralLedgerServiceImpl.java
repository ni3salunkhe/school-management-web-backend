package com.api.serviceImpl.account;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entity.School;
import com.api.entity.account.GeneralLedger;
import com.api.entity.account.HeadMaster;
import com.api.entity.account.SubHeadMaster;
import com.api.repository.account.CustomerMasterRepository;
import com.api.repository.account.GeneralLedgerRepository;
import com.api.repository.account.HeadMasterRepository;
import com.api.repository.account.OpeningBalRepository;
import com.api.repository.account.SubHeadMasterRepository;
import com.api.service.SchoolService;
import com.api.service.account.CustomerMasterService;
import com.api.service.account.GeneralLedgerService;

@Service
public class GeneralLedgerServiceImpl implements GeneralLedgerService {
	
	@Autowired
	private GeneralLedgerRepository generalLedgerRepository;
	
	@Autowired
	private SchoolService schooService;
	
	@Autowired
	private CustomerMasterService customerMasterService;
	
	@Autowired 
	private OpeningBalRepository openingBalRepository;
	
	@Autowired
	private CustomerMasterRepository customerMasterRepository;
	
	@Autowired
	private HeadMasterRepository headMasterRepository;
	
	@Autowired
	private SubHeadMasterRepository subHeadMasterRepository;

	@Override
	public GeneralLedger post(GeneralLedger generalLedger) {
		// TODO Auto-generated method stub
		return generalLedgerRepository.save(generalLedger);
	}

	@Override
	public List<GeneralLedger> getbyudise(long udiseNo) {
		// TODO Auto-generated method stub
		School school=schooService.getbyid(udiseNo);
		return generalLedgerRepository.findByShopId(school);
	}

	@Override
	public GeneralLedger getbyid(long entryNo) {
	    return generalLedgerRepository.findById(entryNo)
	        .orElseThrow(() -> new RuntimeException("GeneralLedger not found with entryNo: " + entryNo));
	}


	@Override
	public List<GeneralLedger> getdata() {
		// TODO Auto-generated method stub
		return generalLedgerRepository.findAll();
	}
	
	@Override
	public List<GeneralLedger> getbysubhead(long subhead,long shopId,Date startDate,Date endDate) {
		// TODO Auto-generated method stub
		
		return generalLedgerRepository.findBySubheadSubheadIdAndShopIdUdiseNoAndEntrydateBetween(subhead, shopId,startDate, endDate);
	}
	
	@Override
	public BigDecimal getBalanceBySubhead(Long subheadId) {
	    SubHeadMaster subhead = subHeadMasterRepository.findBysubheadId(subheadId);
	    List<GeneralLedger> entries = generalLedgerRepository.findBySubhead(subhead);

	    BigDecimal totalDr = BigDecimal.ZERO;
	    BigDecimal totalCr = BigDecimal.ZERO;

	    for (GeneralLedger entry : entries) {
	        if (entry.getDrAmt() != null) {
	            totalDr = totalDr.add(BigDecimal.valueOf(entry.getDrAmt()));//8705
	            System.out.println(totalDr + "dr");
	        }
	        if (entry.getCrAmt() != null) {
	            totalCr = totalCr.add(BigDecimal.valueOf(entry.getCrAmt()));
	            System.out.println(totalCr + "cr");
	        }
	    }

	    // Get the Head Type (e.g., Asset, Liability, Income, Expense)
	    String headType = subhead.getHeadId().getBookSideMaster().getBooksideName(); // Assumes getHeadId() returns HeadMaster with getHeadType()

	    BigDecimal balance;

	    if (headType.equalsIgnoreCase("Asset") || headType.equalsIgnoreCase("Expense")) {
	        // Dr - Cr for Assets & Expenses
	    	System.out.println(headType);
	        balance = totalDr.subtract(totalCr);
	    } else if (headType.equalsIgnoreCase("Liabilities") || headType.equalsIgnoreCase("Income")) {
	        // Cr - Dr for Liabilities & Income
	    	System.out.println(headType);
	        balance = totalCr.subtract(totalDr);//8705-0
	    } else {
	        // Default fallback in case of unknown type
	        balance = totalCr.subtract(totalDr);
	    	System.out.println(headType);
	    }

	    return balance;
	}

	@Override
	public List<GeneralLedger> getByBookTypeName(String headName,long id) {
		// TODO Auto-generated method stub
		return generalLedgerRepository.findByBooksideNameAndShopId(headName,id);
	}

	@Override
	public List<GeneralLedger> getByHeadId(HeadMaster headMaster) {
		// TODO Auto-generated method stub
		return generalLedgerRepository.findByHeadId(headMaster);
	}

	@Override
	public List<GeneralLedger> getBySubHeadAndShop(long subheadId, long shopId) {
		// TODO Auto-generated method stub
		return generalLedgerRepository.findBySubheadAndShop(subheadId, shopId);
	}

	@Override
	public List<GeneralLedger> getByEntryDate(Date entryDate, long shopId) {
		// TODO Auto-generated method stub
		return generalLedgerRepository.getLedgersUpToDate(entryDate, shopId);
	}

	@Override
	public List<GeneralLedger> getByEntryDateSubHeadAndShop(long subheadId, Date entryDate, long shopId) {
		// TODO Auto-generated method stub
		return generalLedgerRepository.getBySubheadAndDateAndShop(subheadId, entryDate, shopId);
	}

	@Override
	public GeneralLedger getByEntryNoAndSubhead(long EntryNo, long SubheadId) {
		// TODO Auto-generated method stub
		return generalLedgerRepository.findByEntryNoAndSubheadSubheadId(EntryNo, SubheadId);
	}

	@Override
	public List<GeneralLedger> getBysubheadAndShopAndYear(long subhead, long shopId, String year) {
		// TODO Auto-generated method stub
		return generalLedgerRepository.findBySubheadSubheadIdAndShopIdUdiseNoAndYear(subhead, shopId, year);
	}
}
