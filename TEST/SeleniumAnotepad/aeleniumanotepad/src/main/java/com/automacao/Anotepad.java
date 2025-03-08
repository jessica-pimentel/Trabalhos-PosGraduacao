package com.automacao;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class Anotepad {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://pt.anotepad.com/");
            Thread.sleep(2000); 

            WebElement titleField = driver.findElement(By.id("edit_title"));
            titleField.sendKeys("Entrega trabalho TEST DAS 2024");

            WebElement contentField = driver.findElement(By.id("edit_textarea"));
            contentField.sendKeys("Jéssica Pimentel - matrícula: 40001016398E1");

            System.out.println("Texto inserido no Anotepad. A aba do navegador permanecerá aberta por 60 segundos.");
            
            Thread.sleep(60000); 

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
