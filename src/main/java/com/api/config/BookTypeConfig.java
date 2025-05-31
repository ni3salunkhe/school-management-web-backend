package com.api.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.api.entity.account.BookSideMaster;
import com.api.entity.account.BookTypeMaster;
import com.api.entity.account.HeadMaster;
import com.api.service.account.BookSideMasterService;
import com.api.service.account.BookTypeMasterService;
import com.api.service.account.HeadMasterService;

@Configuration
public class BookTypeConfig {

	@Bean
	public CommandLineRunner addBookTypeData(BookTypeMasterService bookTypeMasterService,
			BookSideMasterService bookSideMasterService, HeadMasterService headMasterService) {

		return args -> {

			java.sql.Date sqlDate = java.sql.Date.valueOf(LocalDate.now());

			if (bookTypeMasterService.getByBookTypeName("Balance Sheet") == null) {
				BookTypeMaster bookTypeMaster = new BookTypeMaster();

				bookTypeMaster.setBooktypeName("Balance Sheet");
				bookTypeMaster.setEntryDate(sqlDate);

				bookTypeMasterService.postData(bookTypeMaster);

			}
			if (bookTypeMasterService.getByBookTypeName("Profit And Loss") == null) {
				BookTypeMaster bookTypeMaster = new BookTypeMaster();
				bookTypeMaster.setBooktypeName("Profit And Loss");
				bookTypeMaster.setEntryDate(sqlDate);
				bookTypeMasterService.postData(bookTypeMaster);
			}
			if (bookTypeMasterService.getByBookTypeName("Trading A/C") == null) {
				BookTypeMaster bookTypeMaster = new BookTypeMaster();
				bookTypeMaster.setBooktypeName("Trading A/C");
				bookTypeMaster.setEntryDate(sqlDate);
				bookTypeMasterService.postData(bookTypeMaster);
			}

			if (bookSideMasterService.getByBookSideName("Profit And Loss") == null) {
				BookSideMaster bookSideMaster = new BookSideMaster();
				bookSideMaster.setBooksideName("Profit And Loss");
				bookSideMaster.setBooktypeId(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				bookSideMaster.setEntryDate(sqlDate);

				bookSideMasterService.postData(bookSideMaster);
			}
			if (bookSideMasterService.getByBookSideName("Asset") == null) {
				BookSideMaster bookSideMaster = new BookSideMaster();
				bookSideMaster.setBooksideName("Asset");
				bookSideMaster.setBooktypeId(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				bookSideMaster.setEntryDate(sqlDate);
				bookSideMasterService.postData(bookSideMaster);

			}
			if (bookSideMasterService.getByBookSideName("Liabilities") == null) {
				BookSideMaster bookSideMaster = new BookSideMaster();
				bookSideMaster.setBooksideName("Liabilities");
				bookSideMaster.setBooktypeId(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				bookSideMaster.setEntryDate(sqlDate);
				bookSideMasterService.postData(bookSideMaster);
			}

//			---------------------------------------------------------------------------------------
//			set heads

			if (headMasterService.getByHeadName("Bank OCC A/c") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Bank OCC A/c");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Liabilities"));

				headMasterService.postData(headMaster);

			}

			if (headMasterService.getByHeadName("Bank OD A/c.") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Bank OD A/c.");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Liabilities"));

				headMasterService.postData(headMaster);

			}

			if (headMasterService.getByHeadName("Branch / Divisions") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Branch / Divisions");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Asset"));

				headMasterService.postData(headMaster);

			}

			if (headMasterService.getByHeadName("Capital Account") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Capital Account");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Liabilities"));

				headMasterService.postData(headMaster);

			}

			if (headMasterService.getByHeadName("Cash In Hand") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Cash In Hand");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Asset"));

				headMasterService.postData(headMaster);

			}

			if (headMasterService.getByHeadName("Current Liabilities") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Current Liabilities");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Liabilities"));

				headMasterService.postData(headMaster);

			}

			if (headMasterService.getByHeadName("Deposits (Asset)") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Deposits (Asset)");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Asset"));

				headMasterService.postData(headMaster);

			}

			if (headMasterService.getByHeadName("Direct Expenses") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Direct Expenses");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Profit And Loss"));

				headMasterService.postData(headMaster);

			}

			if (headMasterService.getByHeadName("Direct Incomes") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Direct Incomes");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Profit And Loss"));

				headMasterService.postData(headMaster);

			}

			if (headMasterService.getByHeadName("Duties & Taxes") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Duties & Taxes");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Liabilities"));

				headMasterService.postData(headMaster);

			}

			if (headMasterService.getByHeadName("Expenses (Direct)") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Expenses (Direct)");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Profit And Loss"));

				headMasterService.postData(headMaster);

			}

			if (headMasterService.getByHeadName("Expenses (Indirect)") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Expenses (Indirect)");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Profit And Loss"));

				headMasterService.postData(headMaster);

			}
			
			if (headMasterService.getByHeadName("Fixed Assets") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Fixed Assets");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Asset"));

				headMasterService.postData(headMaster);

			}
			
			if (headMasterService.getByHeadName("Income (Direct)") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Income (Direct)");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Profit And Loss"));

				headMasterService.postData(headMaster);

			}
			
			if (headMasterService.getByHeadName("Income (Indirect)") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Income (Indirect)");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Profit And Loss"));

				headMasterService.postData(headMaster);

			}
			
			if (headMasterService.getByHeadName("Indirect Expenses") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Indirect Expenses");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Profit And Loss"));

				headMasterService.postData(headMaster);

			}
			
			if (headMasterService.getByHeadName("Indirect Incomes") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Indirect Incomes");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Profit And Loss"));

				headMasterService.postData(headMaster);

			}
			
			if (headMasterService.getByHeadName("Investments") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Investments");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Asset"));

				headMasterService.postData(headMaster);

			}
			
			if (headMasterService.getByHeadName("Loans & Advances (Asset)") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Loans & Advances (Asset)");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Asset"));

				headMasterService.postData(headMaster);

			}
			
			if (headMasterService.getByHeadName("Loans (Liability)") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Loans (Liability)");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Liabilities"));

				headMasterService.postData(headMaster);

			}
			
			if (headMasterService.getByHeadName("Misc. Expenses (ASSET)") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Misc. Expenses (ASSET)");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Asset"));

				headMasterService.postData(headMaster);

			}
			
			if (headMasterService.getByHeadName("Provisions") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Provisions");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Liabilities"));

				headMasterService.postData(headMaster);

			}
			
			if (headMasterService.getByHeadName("Purchase Accounts") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Purchase Accounts");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Profit And Loss"));

				headMasterService.postData(headMaster);

			}
			
			if (headMasterService.getByHeadName("Reserves & Surplus") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Reserves & Surplus");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Liabilities"));

				headMasterService.postData(headMaster);

			}
			
			if (headMasterService.getByHeadName("Retained Earnings") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Retained Earnings");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Profit And Loss"));

				headMasterService.postData(headMaster);

			}
			
			if (headMasterService.getByHeadName("Sales Accounts") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Sales Accounts");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Profit And Loss"));

				headMasterService.postData(headMaster);

			}
			
			
			if (headMasterService.getByHeadName("Secured Loans") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Secured Loans");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Liabilities"));

				headMasterService.postData(headMaster);

			}
			
			if (headMasterService.getByHeadName("Sundry Creditors") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Sundry Creditors");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Liabilities"));

				headMasterService.postData(headMaster);

			}
			
			if (headMasterService.getByHeadName("Sundry Debtors") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Sundry Debtors");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Asset"));

				headMasterService.postData(headMaster);

			}
			
			if (headMasterService.getByHeadName("Suspence A/C") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Suspence A/C");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Liabilities"));

				headMasterService.postData(headMaster);

			}
			
			if (headMasterService.getByHeadName("Unsecured Loans") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Unsecured Loans");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Liabilities"));

				headMasterService.postData(headMaster);

			}
			

		};
	}

}
