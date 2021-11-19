package com.billex.utils.csv;

import com.billex.utils.FilePath;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenCsvWriterExample {

    public static void main(String[] args) throws IOException {

        List<String[]> csvData = createCsvDataSimple();

        // default all fields are enclosed in double quotes
        // default separator is a comma
        try (CSVWriter writer = new CSVWriter(new FileWriter(FilePath.PDF_DIR_PATH+"\\test.csv",true))) {
            writer.writeAll(csvData);
        }

    }

    private static List<String[]> createCsvDataSimple() {
        String[] header = {"id", "name", "address", "phone"};
        String[] record1 = {"1", "first name", "address 1", "11111"};
        String[] record2 = {"2", "second name", "address 2", "22222"};

        List<String[]> list = new ArrayList<>();
        list.add(header);
        list.add(record1);
        list.add(record2);

        return list;
    }
}
