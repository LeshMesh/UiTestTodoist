package ru.cian;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.cian.helpers.Attach;
import ru.cian.helpers.DriverConfig;
import ru.cian.pages.CianPage;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    CianPage cianPage = new CianPage();

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        DriverConfig.configure();

        Configuration.baseUrl = "https://cian.ru";
    }

    @BeforeEach
    void beforeEach() {
        open("/");
        sleep(2000); //обход защиты
    }

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
