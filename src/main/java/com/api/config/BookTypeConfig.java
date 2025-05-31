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

			if (headMasterService.getByHeadName("Cash In Hand") == null) {
				HeadMaster headMaster = new HeadMaster();
				headMaster.setHeadName("Cash In Hand");
				headMaster.setBookTypeMaster(bookTypeMasterService.getByBookTypeName("Balance Sheet"));
				headMaster.setBookSideMaster(bookSideMasterService.getByBookSideName("Asset"));

				headMasterService.postData(headMaster);

			}

		};
	}

}
