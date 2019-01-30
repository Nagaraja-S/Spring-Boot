package com.uknown.equity.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uknown.equity.dto.EquityDTO;
import com.uknown.equity.respository.EquityRepository;
import com.uknown.equity.util.DBUtility;
import com.uknown.equity.util.EquityCSVReader;

@Service
public class EquityService {
	
	private final Logger logger = LoggerFactory.getLogger(EquityService.class);

	@Autowired
	EquityRepository equityRep;

	@Transactional
	public void saveEquities(List<EquityDTO> equityList) {

		int size = equityList.size();

		int counter = 0;

		List<EquityDTO> tmpEquity = new ArrayList<>();

		try {

			logger.debug("Size :" + size);

			logger.debug("equityRep :" + equityRep);

			// equityRep.saveAll(tmpEquity);

			for (EquityDTO equity : equityList) {

				tmpEquity.add(equity);
				if (((counter + 1) % 100 == 0) || ((counter + 1) == size)) {
					logger.debug("Inside Save Method ! ! ! ");
					equityRep.saveAll(tmpEquity);
					tmpEquity.clear();
				}

				counter++;
			}

			logger.debug(""+counter);

		} catch (Exception e) {
			logger.error("Exception During Inserting :" + e);
		}

	}
	
	public void persistEquities(List<EquityDTO> equityList,DataSource datasources,String tableName) {

		int size = equityList.size();

		int counter = 0;
		
		int pkValue = 0;

		Connection connection = null;
		
		PreparedStatement pstmt = null;

		try {

			String query = "INSERT INTO "+tableName+ ""
					+ "(id,symbol,series,date,prev_close,open_price,high_price,low_price,last_price,close_price,avg_price,ttl_trd_quantity,turnover_lacs,\n" + 
					"no_of_trades,delivery_quantity,delivery_percentage)\n" + 
					"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			logger.debug("Insert Query is "+query);
			
			connection = datasources.getConnection();
			
			pstmt = connection.prepareStatement(query);
			
			for (EquityDTO equity : equityList) {
				
				pstmt.setInt(1, pkValue++);
				pstmt.setString(2, equity.getSymbol());
				pstmt.setString(3, equity.getSeries());
				pstmt.setDate(4, new Date(equity.getDate().getTime()));
				pstmt.setDouble(5, equity.getPrevClose());
				pstmt.setDouble(6, equity.getOpenPrice());
				pstmt.setDouble(7, equity.getHighPrice());
				pstmt.setDouble(8, equity.getLowPrice());
				pstmt.setDouble(9, equity.getLastPrice());
				pstmt.setDouble(10,equity.getClosePrice());
				pstmt.setDouble(11, equity.getAvgPrice());
				pstmt.setDouble(12, equity.getTtlTrdQuantity());
				pstmt.setDouble(13,equity.getTurnoverLacs());
				pstmt.setDouble(14, equity.getNoOfTrades());
				pstmt.setDouble(15, equity.getDeliveryQuantity());
				pstmt.setDouble(16, equity.getDeliveryPercentage());
				pstmt.addBatch();
				
				if (((counter + 1) % 100 == 0) || ((counter + 1) == size)) {
					logger.debug("Inside Save Method ! ! ! "+counter);
					pstmt.executeBatch();
				}

				counter++;
			}

		} catch (Exception e) {
			logger.error("Exception During Inserting :" + e);
		}finally {
			DBUtility.closePSAndConnection(pstmt, connection);
		}

	}
	
	public void persistEquitiesDaily(List<EquityDTO> equityList,DataSource datasources) {

		int size = equityList.size();

		int counter = 0;
		
		int pkValue = 0;

		Connection connection = null;
		
		PreparedStatement pstmt = null;

		try {

			String query = "INSERT INTO share_information"
					+ "(symbol,series,date,prev_close,open_price,high_price,low_price,last_price,close_price,avg_price,ttl_trd_quantity,turnover_lacs,\n" + 
					"no_of_trades,delivery_quantity,delivery_percentage)\n" + 
					"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			logger.debug("Insert Query is "+query);
			
			connection = datasources.getConnection();
			
			pstmt = connection.prepareStatement(query);
			
			for (EquityDTO equity : equityList) {
				
				//pstmt.setInt(1, pkValue++);
				pstmt.setString(1, equity.getSymbol());
				pstmt.setString(2, equity.getSeries());
				pstmt.setDate(3, new Date(equity.getDate().getTime()));
				pstmt.setDouble(4, equity.getPrevClose());
				pstmt.setDouble(5, equity.getOpenPrice());
				pstmt.setDouble(6, equity.getHighPrice());
				pstmt.setDouble(7, equity.getLowPrice());
				pstmt.setDouble(8, equity.getLastPrice());
				pstmt.setDouble(9,equity.getClosePrice());
				pstmt.setDouble(10, equity.getAvgPrice());
				pstmt.setDouble(11, equity.getTtlTrdQuantity());
				pstmt.setDouble(12,equity.getTurnoverLacs());
				pstmt.setDouble(13, equity.getNoOfTrades());
				pstmt.setDouble(14, equity.getDeliveryQuantity());
				pstmt.setDouble(15, equity.getDeliveryPercentage());
				pstmt.addBatch();
				
				if (((counter + 1) % 100 == 0) || ((counter + 1) == size)) {
					logger.debug("Inside Save Method ! ! ! "+counter);
					pstmt.executeBatch();
				}

				counter++;
			}

		} catch (Exception e) {
			logger.error("Exception During Inserting :" + e);
		}finally {
			DBUtility.closePSAndConnection(pstmt, connection);
		}

	}

	public List<EquityDTO> parseEquityCSV(String fileLoc) {

		List<EquityDTO> shareList = EquityCSVReader.readFromCSV(fileLoc);

		return shareList;
	}

	public EquityDTO findEquity(String symbol, String series,String date) {

		return equityRep.getEquityBySymbolAndSeries(symbol, series,date);
	}

	public List<EquityDTO> findEquities(String symbol, String series, String fromDate, String toDate) {
		
		return  equityRep.getEquityByDate(symbol, series, fromDate, toDate);
	}

}
