package com.billex.generator;

import com.billex.utils.PROPERTY_MAP;
import com.billex.utils.PropertyReader;

import java.util.ArrayList;
import java.util.HashMap;

public class BillNumberGenerator {

    String startingDigit = "11";


    String addSectionCode(String billNumber,String sectionCode){

        return  billNumber+sectionCode;

    }

   String incrementRandomNumber(String billPart1,String billPart2,int currentRandomNumber)
   {
       currentRandomNumber++;
       return billPart1+currentRandomNumber+billPart2;
   }

   ArrayList<String> getAllBillSeriesOfConsumerNumber(String sectionCode,String consumerNumber)
   {
       String baseBillNumber = addSectionCode(startingDigit,sectionCode);
       ArrayList<String> billSeries = new ArrayList<>();
       for(int i=0;i<10;i++)
       {
         String billNumber = baseBillNumber+i+consumerNumber;
           billSeries.add(billNumber);
       }
       return billSeries;
   }

   public HashMap<String,ArrayList<String>> getBillNumbersOfRangeOfConsumers(String sectionCode, String prefix, String startNumber, String endNumber)
   {
       HashMap<String,ArrayList<String>> consumerBillMap = new HashMap<String,ArrayList<String>>();

       int start = Integer.parseInt(startNumber);
       int end = Integer.parseInt(endNumber);
       for(int i= start; i<end;i++)
       {
           String consumerNumber = prefix+i;
           ArrayList<String> billSeries = getAllBillSeriesOfConsumerNumber(sectionCode,consumerNumber);
           consumerBillMap.put(consumerNumber,billSeries);
       }

       return consumerBillMap;

   }


    public static void main(String[] args) {

        BillNumberGenerator billGen = new BillNumberGenerator();

        String sectionCode = PropertyReader.getProperty(PROPERTY_MAP.SECTION_CODE);
        String prefix = PropertyReader.getProperty(PROPERTY_MAP.CONSUMER_NUMBER_PREFIX);
        String startNumber = PropertyReader.getProperty(PROPERTY_MAP.CONSUMER_NUMBER_START);
        String endNumber = PropertyReader.getProperty(PROPERTY_MAP.CONSUMER_NUMBER_END);

        HashMap<String,ArrayList<String>> consumerBillNumberSeries = billGen.getBillNumbersOfRangeOfConsumers(sectionCode,prefix,startNumber,endNumber);
        for(String consumer:consumerBillNumberSeries.keySet()){
            ArrayList<String> billSeries = consumerBillNumberSeries.get(consumer);
            System.out.println(consumer + "\t" + "->" + "\t" + billSeries);

        }
    }
}
