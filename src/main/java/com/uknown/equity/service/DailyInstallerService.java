package com.uknown.equity.service;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.uknown.equity.dto.EquityDTO;
import com.uknown.equity.util.DBUtility;
import com.uknown.equity.util.DateUtility;

@Component("scheduler")
public class DailyInstallerService {

	private final Logger logger = LoggerFactory.getLogger(DailyInstallerService.class);

	public static final String fileLocation = "/home/nagaraj/Desktop/preetham/sec_bhavdata_full.csv";

	@Autowired
	private DataSource datasource;

	private JdbcTemplate jdbctemplate;

	@Autowired
	private EquityService equityService;

	public DailyInstallerService(DataSource datasource) {
		this.datasource = datasource;
		jdbctemplate = new JdbcTemplate(datasource);
	}

	// @Scheduled(cron = "0 00 00 * * ?")
	// @Scheduled(cron = "0 57 22 * * ?")
	public void install() {

		logger.debug("Daily Insaller Started.");

		boolean tableExist = DBUtility.checkForTable(datasource);

		String date = DateUtility.getCurentDate();

		String tableName = "share_information_" + date;

		logger.debug("Does table exist for current date :" + date + " : " + tableExist);

		if (!tableExist) {

			DBUtility.createTable(datasource);
		}

		List<EquityDTO> equityList = equityService.parseEquityCSV(fileLocation);
		equityService.persistEquities(equityList, datasource, tableName);

	}

	public void installDaily() {

		logger.debug("Daily Insaller Started.");

		List<EquityDTO> equityList = equityService.parseEquityCSV(fileLocation);
		equityService.persistEquitiesDaily(equityList, datasource);

	}
}
