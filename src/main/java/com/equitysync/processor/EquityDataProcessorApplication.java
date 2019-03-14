package com.equitysync.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.equitysync.processor.service.EquityService;

@SpringBootApplication
@EnableScheduling
@ImportResource("classpath:spring.xml")
//@ImportResource("file:/config/spring.xml")
public class EquityDataProcessorApplication implements CommandLineRunner {

	public static final String fileLocation = "/home/nagaraj/Desktop/preetham/sec_bhavdata_full.csv";
	
	@Autowired
	private EquityService equityService;

	public static void main(String[] args) {
		SpringApplication.run(EquityDataProcessorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		/*System.out.println("Executing before the application Start ");
		List<EquityDTO> equityList = equityService.parseEquityCSV(fileLocation);

		equityService.saveEquities(equityList);*/
	}

}
