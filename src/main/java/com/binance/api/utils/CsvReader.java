package com.binance.api.utils;

import com.binance.api.models.Talk;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
public class CsvReader {
    public static List<Talk> readCsvFile(InputStream fileInputStream) {

        //Create the CSVFormat object with no header mapping
        CSVFormat csvFileFormat = CSVFormat.DEFAULT;

        //Create a new list of talk objects to be filled by CSV file data
        List<Talk> talkObjects = Lists.newArrayList();

        try (InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             CSVParser csvFileParser = new CSVParser(inputStreamReader, csvFileFormat)) {

            //Get a list of CSV file records
            List<CSVRecord> csvRecords = csvFileParser.getRecords();

            //Read the CSV file records starting from the second record to skip the header
            for (int i = 0; i < csvRecords.size(); i++) {
                CSVRecord record = csvRecords.get(i);

                String title = record.get(0);
                int minutes = getMinutesFromString(record.get(1));

                talkObjects.add(new Talk(minutes, title));
            }
        } catch (Exception e) {
            log.error("Failed to parse talks input file : ", e);
        }

        return talkObjects;
    }

    private static int getMinutesFromString(String minuteString) {
        if (StringUtils.isEmpty(minuteString)) {
            return 0;
        }

        if ("lightning".equals(minuteString)) {
            return 5; // lightning (5 minutes).
        }

        try {
            return Integer.parseInt(minuteString.replaceAll("\\D+", ""));
        } catch (NumberFormatException e) {
            log.error(String.format("Could not extract minutes from string : %s", minuteString), e);
            throw e;
        }
    }

}
