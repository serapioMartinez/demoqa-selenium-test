package com.ipte.webapp.base;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class WebDriverInstance {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            try{
                driver.set(createDriver());
            } catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }
        }
        return driver.get();
    }

    private static WebDriver createDriver() throws IOException{
        WebDriver driver = null;
        Properties prop = new Properties();
        prop.load(WebDriverInstance.class.getResourceAsStream("/data.properties"));
            
        String browser = prop.getProperty("browser");
        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
                if(prop.getProperty("headless").equals("true")){
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--window-size=1920x1080");
                }
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
                if(prop.getProperty("headless").equals("true")){
                    edgeOptions.addArguments("--headless");
                    edgeOptions.addArguments("--disable-gpu");
                    edgeOptions.addArguments("--window-size=1920x1080");
                }
                driver = new EdgeDriver(edgeOptions);
                break;
            default:
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        return driver;
    }

    public static void cleanUpDriver(){
        WebDriver temp = driver.get();
        if (temp != null) temp.quit();
        driver.remove();
    }

}
