package com.equitysync.processor.respository.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.equitysync.processor.dto.EquityDTO;
import com.equitysync.processor.respository.EquityRepositoryCustom;
import com.equitysync.processor.service.DailyInstallerService;
import com.equitysync.processor.util.DateUtility;

@Repository
@Transactional(readOnly = true)
public class EquityRepositoryImpl implements EquityRepositoryCustom {
	
	private final Logger logger = LoggerFactory.getLogger(EquityRepositoryImpl.class);
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");


	@PersistenceContext
	EntityManager entityManager;

	@Override
	public EquityDTO getEquityBySymbolAndSeries(String symbol, String series, String date) {

		String hqlQuery = "select eq from EquityDTO eq where eq.symbol=:symbol and eq.series=:series and eq.date=:date";
		EquityDTO equity = null;
		try {

			Query query = (Query) entityManager.createQuery(hqlQuery);

			query.setParameter("symbol", symbol);

			query.setParameter("series", series);
			
			query.setParameter("date", sdf.parse(date));

			equity = (EquityDTO) query.uniqueResult();
			
			/*if (equity.getDate() != null) {
				DateUtility.parseDateFromDB(equity);
			}*/

		} catch (Exception e) {
			logger.error("Exception :" + e);
		}
		
		return equity;
	}

	@Override
	public void saveEquities(List<EquityDTO> equityList, String tableName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EquityDTO> getEquityByDate(String symbol, String series, String fromDate, String toDate) {
		String hqlQuery = "select eq from EquityDTO eq where eq.symbol=:symbol and eq.series=:series and eq.date between :stDate AND :edDate";
		List<EquityDTO> equity = null;
		try {

			Query query = (Query) entityManager.createQuery(hqlQuery);

			query.setParameter("symbol", symbol);

			query.setParameter("series", series);
			
			query.setParameter("stDate", sdf.parse(fromDate));
			
			query.setParameter("edDate", sdf.parse(toDate));
			
			logger.debug("Query :"+hqlQuery);

			equity = (List<EquityDTO>) query.list();

		} catch (Exception e) {
			logger.error("Exception :" + e);
		}
		
		return equity;
		
	}

}
