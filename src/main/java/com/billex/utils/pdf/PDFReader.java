package com.billex.utils.pdf;

import com.billex.utils.FilePath;
import com.billex.utils.PROPERTY_MAP;
import com.billex.utils.common.StringUtility;
import com.billex.utils.csv.csvFileWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PDFReader {



    private int  getFirstLineNumberOfSpecificText(String lines[], String specificText)
    {

        int ln=0;
                for (int lineNumber = 0; lineNumber<lines.length; lineNumber++ ) {
                    String line = lines[lineNumber];
                    if (line.contains(specificText))
                    {
                        ln = lineNumber;
                        break;
                    }

                }

                return ln;
    }


    String extractConsumerNumber(String lines[])
    {
    String consumerNumber = null;
    int lineNumber = getFirstLineNumberOfSpecificText( lines,PROPERTY_MAP.CONSUMER_NUMBER_TEXT);
    consumerNumber = lines[lineNumber].split(" ")[1].trim();
    return consumerNumber;
    }

    String extractConsumerMobileNumber(String lines[])
    {
        int lineNumber = getFirstLineNumberOfSpecificText( lines,PROPERTY_MAP.ADDRESS_END_TEXT);
        String mobileNumber = lines[lineNumber].split(" ")[2].trim();
        return mobileNumber;
    }
    String extractConsumerEmail(String lines[])
    {
        int lineNumber = getFirstLineNumberOfSpecificText( lines,PROPERTY_MAP.EMAIL_TEXT);
        String email = lines[lineNumber].split(" ")[2].trim();
        return email;
    }

    String extractConsumerBillNumber(String lines[])
    {
        int lineNumber = getFirstLineNumberOfSpecificText( lines,PROPERTY_MAP.BILL_NUMBER_TEXT);
        String billNumber = lines[lineNumber].split(" ")[1].replace("Bill#","").trim();
        return billNumber;
    }
    String extractAvgConsumption(String lines[])
    {
        int lineNumber = getFirstLineNumberOfSpecificText( lines,PROPERTY_MAP.AVG_CONSUMPTION_TEXT);
        String avgConsumption = lines[lineNumber].split(" ")[1].trim();
        return avgConsumption;
    }
    String extractNetAmount(String lines[])
    {
        int lineNumber = getFirstLineNumberOfSpecificText( lines,PROPERTY_MAP.NET_PAYABLE_TEXT);
        String [] lineData = lines[lineNumber].split(" ");
        String netAmount = lineData[5].trim();
        return netAmount;
    }

    String extractTotalUnits(String lines[])
    {
        int lineNumber = getFirstLineNumberOfSpecificText( lines,PROPERTY_MAP.TOTAL_UNITS_TEXT);

        String [] lineArray = StringUtility.removeNullAndEmpty(lines[lineNumber].split(" "));//[5].trim();

        return lineArray[6];
    }


    String extractAddress(String lines[])
    {
        String address = null;
        int startNumber = getFirstLineNumberOfSpecificText( lines,PROPERTY_MAP.ADDRESS_START_TEXT);
        int endNumber = getFirstLineNumberOfSpecificText( lines,PROPERTY_MAP.ADDRESS_END_TEXT);
        StringBuilder sb = new StringBuilder();
        for(int i=startNumber+1;i<endNumber;i++)
        {
            sb.append(lines[i]).append("\n");
        }

        return sb.toString();
    }
    
    public String readPDFAndGetContent(String fileName) throws IOException {
        String pdfFileInText = null;
        try (PDDocument document = PDDocument.load(new File(FilePath.PDF_DIR_PATH+fileName))) {

            document.getClass();

            if (!document.isEncrypted()) {

                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                pdfFileInText = tStripper.getText(document);
//                System.out.println(pdfFileInText);

                // split by whitespace
//                String lines[] = pdfFileInText.split("\\r?\\n");
//                for (String line : lines) {
//                    System.out.println(line);
//                }

            }

        }
        
        return pdfFileInText;
    }


    public void readPDFFileAndWriteToCSVFile(int index,String fileName,String csvPath) throws IOException {


        String content = readPDFAndGetContent(fileName);
        String lines[] = content.split("\\r?\\n");
        List<String[]> csvData = new ArrayList<>();
       String consNumber = extractConsumerNumber(lines);
       String address = extractAddress(lines);
        String consMobileNumber = extractConsumerMobileNumber(lines);
        String consEmail = extractConsumerEmail(lines);
        String billNumber = extractConsumerBillNumber(lines);
       String avgConsumption = extractAvgConsumption(lines);
        String netAmount = extractNetAmount(lines);
        String totalUnit = extractTotalUnits(lines);
        String[] record = {String.valueOf(index),consNumber,address,consMobileNumber,consEmail,billNumber,avgConsumption,netAmount,totalUnit};
        csvData.add(record);
        csvFileWriter.writeDataToCSVFile(csvData,csvPath);

    }

    public static void main(String[] args) throws IOException {
        String fileName="KsebBill_1156200002916.pdf";
        String csvPath = "output.csv";
        PDFReader reader = new PDFReader();

//        String content = reader.readPDFAndGetContent(fileName);
//        String lines[] = content.split("\\r?\\n");
//        System.out.println(reader.extractNetAmount(lines));

//        csvFileWriter.writeCSVFileHeaderValues(csvPath);
       reader.readPDFFileAndWriteToCSVFile(1,fileName,csvPath);

    }
}
