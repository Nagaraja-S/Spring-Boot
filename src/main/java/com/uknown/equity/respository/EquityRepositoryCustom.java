package com.uknown.equity.respository;

import java.util.List;

import com.uknown.equity.dto.EquityDTO;

public interface EquityRepositoryCustom {
	
	EquityDTO getEquityBySymbolAndSeries(String symbol,String series,String date);
	
	List<EquityDTO> getEquityByDate(String symbol,String series,String fromDate,String toDate);
	
	void saveEquities(List<EquityDTO> equityList,String tableName);
}
