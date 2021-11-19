package com.billex.utils.selenium;
import com.billex.utils.FilePath;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class DriverProvider {

    public WebDriver getDriver(String browser)
    {
        WebDriver driver = null;

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();

                String downloadFilepath = FilePath.PDF_DIR_PATH;
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("plugins.always_open_pdf_externally", true);

                chromePrefs.put("download.default_directory", downloadFilepath);
                chromePrefs.put("plugins.plugins_disabled", new String[] {
                        "Chrome PDF Viewer"
                });
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);

                //Create driver object for Chrome
                driver = new ChromeDriver(options);

                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver=new EdgeDriver();
                break;
            default:
                break;
        }
        return driver;
    }
}
