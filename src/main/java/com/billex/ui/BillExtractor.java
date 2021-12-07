package com.billex.ui;

import com.billex.dto.BillStatus;
import com.billex.utils.PROPERTY_MAP;
import com.billex.utils.PropertyReader;
import com.billex.utils.common.Delay;
import com.billex.utils.selenium.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class BillExtractor {



    public BillStatus chooseSectionCodeAndDownloadBill(String consumerNumber){
        BillStatus status = BillStatus.GENERATED;

        PropertyReader prop = new PropertyReader();
        String browser = prop.getProperty(PROPERTY_MAP.BROWSER);
        String sectionCode = prop.getProperty(PROPERTY_MAP.SECTION_CODE);
        WebDriver driver = new DriverProvider().getDriver(browser);
        driver.get(prop.getProperty(PROPERTY_MAP.BILL_VIEW_URL));
        Select section = new Select(driver.findElement(By.id("office")));
        section.selectByValue(sectionCode);
        Delay.addDelay(2);
        driver.findElement(By.id("t_consumer-no_5")).sendKeys(consumerNumber);
        driver.findElement(By.id("b_submit_0")).click();
        Delay.addDelay(5);

        try {
            String errorText = driver.findElement(By.cssSelector(".alert.alert-danger")).getText();
//            Bill not found or Bill view generation failed.No details availableNo such bills exist
            if (errorText.contains("Invalid Consumer Number"))
                status = BillStatus.INVALID;
            else if(errorText.contains("Bill not found or Bill view generation failed"))
                status = BillStatus.PENDING;
            else
                status = BillStatus.INVALID;


        }catch (Exception e){
            status = BillStatus.GENERATED;
        }

        driver.close();
        driver.quit();
        return status;

    }


    public BillStatus downloadBillByConsumerNumber(String consumerNumber)
    {

        BillStatus status = BillStatus.GENERATED;
        PropertyReader prop = new PropertyReader();
        String browser = prop.getProperty(PROPERTY_MAP.BROWSER);
        WebDriver driver = new DriverProvider().getDriver(browser);
        driver.get(prop.getProperty(PROPERTY_MAP.BILL_VIEW_URL));
        driver.findElement(By.id("t_consumer-no_5")).sendKeys(consumerNumber);
        driver.findElement(By.id("b_submit_0")).click();
        Delay.addDelay(10);
//        driver.findElement(By.cssSelector("#download")).click();

        try {
            String errorText = driver.findElement(By.cssSelector(".alert.alert-danger")).getText();
//            Bill not found or Bill view generation failed.No details availableNo such bills exist
            if (errorText.contains("Invalid Consumer Number"))
                status = BillStatus.INVALID;
            else if(errorText.contains("Bill not found or Bill view generation failed"))
                status = BillStatus.PENDING;
            else
                status = BillStatus.INVALID;


        }catch (Exception e){
            status = BillStatus.GENERATED;
        }

        driver.close();
        driver.quit();
        return status;

    }

    public static void main(String[] args) {
        BillExtractor bill = new BillExtractor();
        bill.chooseSectionCodeAndDownloadBill("2609");

    }
}
