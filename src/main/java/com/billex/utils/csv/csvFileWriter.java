package com.billex.utils.csv;

import com.billex.utils.FilePath;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class csvFileWriter {


    public static void writeDataToCSVFile(List<String[]> csvData, String path) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(FilePath.CSV_DIR_PATH+path,true))) {
            writer.writeAll(csvData);
        }
    }

    public static void writeCSVFileHeaderValues(String path) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(FilePath.CSV_DIR_PATH+path,true))) {
            List<String[]> csvData = new ArrayList<>();
            String[] header = {"SI", "Consumer Number", "Address", "Mobile","Email","Bill Number","Avg. Consumption","Net Amount","Total Unit"};
            csvData.add(header);
            writer.writeAll(csvData);
        }
    }
}
