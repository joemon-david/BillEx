package com.billex.main;

import com.billex.dto.BillStatus;
import com.billex.generator.BillNumberGenerator;
import com.billex.ui.BillExtractor;
import com.billex.utils.PROPERTY_MAP;
import com.billex.utils.PropertyReader;
import com.billex.utils.common.Delay;
import com.billex.utils.file.TextFileWriter;
import com.billex.utils.pdf.PDFReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BillAnalyser {

    public static void main(String[] args) throws IOException {
        BillNumberGenerator billGen = new BillNumberGenerator();
        BillExtractor bill = new BillExtractor();

        String sectionCode = PropertyReader.getProperty(PROPERTY_MAP.SECTION_CODE);
        String csvFileName = PropertyReader.getProperty(PROPERTY_MAP.CSV_OUTPUT_FILE_NAME);
        String pendingBillFileName = PropertyReader.getProperty(PROPERTY_MAP.PENDING_BILL_CONSUMERS_FILE);
        String prefix = PropertyReader.getProperty(PROPERTY_MAP.CONSUMER_NUMBER_PREFIX);
        String startNumber = PropertyReader.getProperty(PROPERTY_MAP.CONSUMER_NUMBER_START);
        String endNumber = PropertyReader.getProperty(PROPERTY_MAP.CONSUMER_NUMBER_END);

        HashMap<String, ArrayList<String>> consumerBillNumberSeries = billGen.getBillNumbersOfRangeOfConsumers(sectionCode,prefix,startNumber,endNumber);
        int index = 0;
        for(String consumer:consumerBillNumberSeries.keySet()){
            index++;
            ArrayList<String> billSeries = consumerBillNumberSeries.get(consumer);
            for(String consumerNumber: billSeries)
            {
                BillStatus status = bill.downloadBillByConsumerNumber(consumerNumber);
                Delay.addDelay(20);
                System.out.println("Processing consumer number "+consumerNumber+" and the status is "+status);
                if(status.equals(BillStatus.GENERATED))
                {
                    String fileName="KsebBill_"+consumerNumber+".pdf";
                    PDFReader reader = new PDFReader();
                    reader.readPDFFileAndWriteToCSVFile(index,fileName,csvFileName);
                    break;
                }else if(status.equals(BillStatus.PENDING))
                {
                    TextFileWriter.appendDataToTextFile(pendingBillFileName,consumerNumber);
                    break;
                }
            }



        }

    }
}
