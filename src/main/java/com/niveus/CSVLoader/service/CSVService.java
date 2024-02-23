package com.niveus.CSVLoader.service;

import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niveus.CSVLoader.repository.EmployeeRepository;
import com.niveus.CSVLoader.utility.DBUtility;
import com.opencsv.CSVReader;

@Service
public class CSVService {

	private static Logger LOG = LoggerFactory.getLogger(CSVService.class);

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	DBUtility dbUtility;

	public void processCSV() {
		LOG.info("Processing CSV file");

		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("First Name", "first_name");
		hashMap.put("Last Name", "last_name");

		try {
			
			
			
			String path = "/Users/apple/Documents/dev/communityworkspace/CSVLoader/src/main/resources/emp.csv";

			CSVReader reader = new CSVReader(new FileReader(path));
			String[] headerLine;
			String[] nextLine;
			int columnPosition;

			headerLine = reader.readNext();
			
			Map tableMap = new HashMap<String,String>();

			while ((nextLine = reader.readNext()) != null) {
				// nextLine[] is an array of values from the line
				// System.out.println(nextLine[columnPosition1]);
				// System.out.println(nextLine[columnPosition2]);

				for (String key : hashMap.keySet()) {
					// System.out.println("Key: " + key + ", Value: " + hashMap.get(key));

					columnPosition = getHeaderLocation(headerLine, key);
					System.out.println(nextLine[columnPosition]);
					tableMap.put(hashMap.get(key), nextLine[columnPosition]);

				}

				
				//System.out.println(Arrays.asList(tableMap));
				
				//tableMap.forEach((key, value) -> System.out.println(key + " " + value));
				
				
				String insertSQL = insertStatement("EMP",tableMap);
				System.out.println(insertSQL);
				


			}

		} catch (Exception e) {
			e.printStackTrace();
		}

//		List objects = CSVUtility.csvToBean(mappingStrategy,path);
//		
//		for (Object object : objects) {
//			Employee employee = (Employee) object;
//			//System.out.println(employee.getLname());
//			
//			employeeRepository.save(employee);
//		}
	}

	private int getHeaderLocation(String[] headers, String columnName) {
		return Arrays.asList(headers).indexOf(columnName);
	}

	private String insertStatement(String strTable, Map tableMap) {

		StringBuffer sb = new StringBuffer("insert into "+strTable+" ");
		StringBuffer sbColumn = new StringBuffer("(");
		StringBuffer sbValue = new StringBuffer("(");
		String quote = "'";
		String endQuoteComma = "',";
		String endingBracket = ")";
		
		int i = 0;
		for (Object objectName : tableMap.keySet()) {
//			    System.out.println(objectName);
//			    System.out.println(tableMap.get(objectName));
			   
			    sbColumn.append(quote);
			    sbColumn.append(objectName); 
				
			    sbValue.append(quote);
			    sbValue.append(tableMap.get(objectName));
				
				
				if(i++ == tableMap.size() - 1){
			        // Last iteration
					sbColumn.append(endingBracket);
					sbValue.append(endingBracket);
			    }
				else {
					sbColumn.append(endQuoteComma);
					sbValue.append(endQuoteComma);
				}
			 }
		
		
		
		System.out.println(sbColumn.toString());
		System.out.println(sbValue.toString());
		
		sb.append(sbColumn);
		sb.append(" values ");
		sb.append(sbValue);
		
		return sb.toString();
	}

}
