package com.example.habrtest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManePageTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.habr.com");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void supportTest() {
        WebElement userIcon = driver.findElement(By.xpath("//a[contains(text(), 'Войти')]"));
        userIcon.click();
        WebElement supportLink = driver.findElement(By.xpath("//a[contains(text(), 'Обратная связь')]"));
        supportLink.click();
        assertTrue(driver.findElement(By.xpath("//label[contains(text(), 'Электронная почта')]")).isDisplayed(),
                "Поле 'Электронная почта' на странице 'Обратная связь' не найдено");
    }

    @Test
    public void newsTest() {
        WebElement flowLink = driver.findElement(By.xpath("//a[contains(text(), 'Все потоки')]"));
        flowLink.click();
        By newsXpath = By.xpath("//a[contains(text(), 'Новости')]");
        List<WebElement> newsLinks = driver.findElements(newsXpath);
        WebElement newsLink = driver.findElement(newsXpath);
        Assertions.assertFalse(newsLinks.isEmpty(), "На странице нет ссылки про новости");
        Assertions.assertTrue(newsLinks.getFirst().isDisplayed(), "Ссылка про новости");
    }
}