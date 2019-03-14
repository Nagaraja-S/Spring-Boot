package com.equitysync.processor.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.equitysync.processor.dto.EquityDTO;

public class DateUtility {
	
	private static final Logger logger = LoggerFactory.getLogger(DateUtility.class);

	public static String getCurentDate() {
		
		LocalDate currentDate = LocalDate.now();
		
		int dayOfMonth = currentDate.getDayOfMonth();
		
		int month = currentDate.getMonthValue();

		int year = currentDate.getYear();
		
		String monthString = "";
		
		String dayOfMonthString = "";
		
		if (month < 10) {
			monthString = String.valueOf("0"+month);
		}else {
			monthString = String.valueOf(month);
		}
		
		if (dayOfMonth < 10) {
			dayOfMonthString = String.valueOf("0"+dayOfMonth);
		}else {
			dayOfMonthString = String.valueOf(dayOfMonth);
		}
		
		return dayOfMonthString +"_"+ monthString +"_"+ String.valueOf(year);
	}
	
	public static void parseDateFromDB(EquityDTO equity) {
		
		try {
			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ",Locale.ENGLISH);
			DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy",Locale.ENGLISH);
			
			LocalDate localDate = LocalDate.parse(equity.getDate().toString(), inputFormatter);
					
			String formattedDate = outputFormatter.format(localDate);
			
			equity.setDate(new Date(formattedDate));
				
		}catch (Exception e) {
			logger.debug("Exception :"+e);
		}
		
	}
}
