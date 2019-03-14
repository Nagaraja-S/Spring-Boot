package com.equitysync.processor.util;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.equitysync.processor.dto.EquityDTO;
import com.equitysync.processor.service.EquityService;

public class EquityCSVReader {
	private static final Logger logger = LoggerFactory.getLogger(EquityCSVReader.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

	
	public static List<EquityDTO> readFromCSV(String fileName) {

		List<EquityDTO> equityDTOList = new ArrayList<>();

		Path pathToFile = Paths.get(fileName);

		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {

			String line = br.readLine();
			
			boolean firstLine = true;
			while (line != null) {

					if (firstLine) {
						line = br.readLine();
						firstLine = false;
					}
				String[] attributes = line.split(",");

				EquityDTO equityDTO = createEquityDTO(attributes);
				
				//System.out.println(equityDTO);
				
				equityDTOList.add(equityDTO);
				
				line = br.readLine();
			}

		} catch (Exception e) {
			logger.error("Exception :"+e);
		}

		return equityDTOList;
	}

	private static EquityDTO createEquityDTO(String[] attributes) {

		EquityDTO equityDTO = new EquityDTO();
		try {
			
			//System.out.println(count++);
			//System.out.println(attributes[0].trim());
			equityDTO.setSymbol(attributes[0].trim());
			equityDTO.setSeries(attributes[1].trim());
			equityDTO.setDate(sdf.parse(attributes[2].trim()));
			
			String att3 = attributes[3].trim();
			equityDTO.setPrevClose(Double.parseDouble((att3.equals("-") || att3.equals("")) ? "0.0" : att3));
			
			String att4 = attributes[4].trim();
			equityDTO.setOpenPrice(Double.parseDouble((att4.equals("-") || att4.equals("")) ? "0.0" : att4));
		
			String att5 = attributes[5].trim();
			equityDTO.setHighPrice(Double.parseDouble((att5.equals("-") || att5.equals("")) ? "0.0" : att5));
			
			String att6 = attributes[6].trim();
			equityDTO.setLowPrice(Double.parseDouble((att6.equals("-") || att6.equals("")) ? "0.0" : att6));
			
			String att7 = attributes[7].trim();
			equityDTO.setLastPrice(Double.parseDouble((att7.equals("-") || att7.equals("")) ? "0.0" : att7));
			
			String att8 = attributes[8].trim();
			equityDTO.setClosePrice(Double.parseDouble((att8.equals("-") || att8.equals("")) ? "0.0" : att8));
			
			String att9 = attributes[9].trim();
			equityDTO.setAvgPrice(Double.parseDouble((att9.equals("-") || att9.equals("")) ? "0.0" : att9));
			
			String att10 = attributes[10].trim();
			equityDTO.setTtlTrdQuantity(Double.parseDouble((att10.equals("-") || att10.equals("")) ? "0.0" : att10));
			
			String att11 = attributes[11].trim();
			equityDTO.setTurnoverLacs(Double.parseDouble((att11.equals("-") || att11.equals("")) ? "0.0" : att11));
			
			String att12 = attributes[12].trim();
			equityDTO.setNoOfTrades(Double.parseDouble((att12.equals("-") || att12.equals("")) ? "0.0" : att12));
			
			String att13 = attributes[13].trim();
			equityDTO.setDeliveryQuantity(Double.parseDouble((att13.equals("-") || att13.equals("")) ? "0.0" : att13));
			
			String att14 = attributes[14].trim();
			equityDTO.setDeliveryPercentage(Double.parseDouble((att14.equals("-") || att14.equals("")) ? "0.0" : att14));
		} catch (Exception e) {
			logger.error("Exception :"+e);
		}
		return equityDTO;
	}
}
