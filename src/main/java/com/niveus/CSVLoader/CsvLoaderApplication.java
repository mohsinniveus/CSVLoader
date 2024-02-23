package com.niveus.CSVLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.niveus.CSVLoader.service.CSVService;

@SpringBootApplication(scanBasePackages = "com.spring.beans")
@EnableAutoConfiguration
@ComponentScan({"com.niveus.CSVLoader"})
public class CsvLoaderApplication implements CommandLineRunner {
	
	private static Logger LOG = LoggerFactory
		      .getLogger(CsvLoaderApplication.class);
	
	@Autowired(required = true)
	CSVService csvService;

	public static void main(String[] args) {
		SpringApplication.run(CsvLoaderApplication.class, args);
	}
	
	@Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");
        
        csvService.processCSV();
        
    }

}
