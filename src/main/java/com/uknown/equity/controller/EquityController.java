package com.uknown.equity.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uknown.equity.dto.EquityDTO;
import com.uknown.equity.service.EquityService;

@RestController
@RequestMapping("/equity")
public class EquityController {

	private final Logger logger = LoggerFactory.getLogger(EquityController.class);


	@Autowired
	EquityService equityService;
	
	@GetMapping(path="/symbol/{symbol}/series/{series}/{date}")
	public EquityDTO findEquityBySymbolAndSeries(@PathVariable String symbol,
											@PathVariable String series,
											@PathVariable String date) {
		logger.debug("Inside equity Controller ");
		logger.debug("Symbol :"+symbol +" series :"+series+" Date :"+date);
		return equityService.findEquity(symbol,series,date);
	}
	
	@GetMapping(path="/symbol/{symbol}/series/{series}/{fromDate}/{toDate}")
	public List<EquityDTO> findEquitiesByDate(@PathVariable String symbol,
											@PathVariable String series,
											@PathVariable String fromDate,
											@PathVariable String toDate) {
		logger.debug("Inside equity Controller ");
		logger.debug("Symbol :"+symbol +" series :"+series+" fromDate :"+fromDate+"toDate: "+toDate);
		return equityService.findEquities(symbol,series,fromDate,toDate);
	}

}
