package com.niveus.CSVLoader.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.enums.CSVReaderNullFieldIndicator;

public class CSVUtility {

	public static List<Object> loadCSVDataToBean(String path, ColumnPositionMappingStrategy columMapping) {

		List<Object> list = new ArrayList<Object>();
		try {

			URL fileUrl = CSVUtility.class.getClassLoader().getResource(path);
			// CSVReader csvReader = new CSVReader(new FileReader(fileUrl.getFile()));

			CSVReader csvReader = new CSVReaderBuilder(new FileReader(fileUrl.getFile()))
					.withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
					// Skip the header
					.withSkipLines(1).build();

			CsvToBean csv = new CsvToBean();
			csv.setCsvReader(csvReader);
			csv.setMappingStrategy(columMapping);

			list = csv.parse();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<Object> csvToBean(HeaderColumnNameTranslateMappingStrategy mappingStrategy,String path) {

		List<Object> list = new ArrayList<Object>();

		try {
		            
			Reader reader = new BufferedReader(new FileReader(path));

			CsvToBean<Object> csvReader = new CsvToBeanBuilder(reader).withType(Object.class).withSeparator(',')
					.withIgnoreLeadingWhiteSpace(true).withIgnoreEmptyLine(true).withMappingStrategy(mappingStrategy)
					.build();

			list = csvReader.parse();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return list;
	}

	public static String[] getHeadersFromFile(String filePath) {
		String[] header = null;
		try {
			CSVReader reader = new CSVReader(new FileReader(filePath));
			// if the first line is the header
			header = reader.readNext();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return header;
	}

}
