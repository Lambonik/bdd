package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.testng.Assert;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.Conversions.trim;

public class DashBoardPage {
    private static SelenideElement testIdFirstCard = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private static SelenideElement testIdSecondCard = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

    public DashBoardPage() {
        $("[data-test-id='dashboard']")
                .shouldHave(Condition.text("Личный кабинет"))
                .shouldBe(Condition.visible);
        $("h1.heading")
                .shouldHave(Condition.text("Ваши карты"))
                .shouldBe(Condition.visible);
    }

    public static int getFirstCardBalance() {
        val balanceText = $(testIdFirstCard).text();
        return extractBalance(balanceText);
    }

    public static int getSecondCardBalance() {
        val balanceText = $(testIdSecondCard).text();
        return extractBalance(balanceText);
    }

    private static int extractBalance(String balanceText) {
        String[] extractSplit = balanceText.split(":");
        String extractBalance = extractSplit[1].substring(0, extractSplit[1].indexOf("р.")).trim();
        return Integer.parseInt(extractBalance);
    }

    public static TransferPage SelectTransferToFirstCard() {
        $(testIdFirstCard).find("button").click();
        return new TransferPage();
    }

    public static TransferPage SelectTransferToSecondCard() {
        $(testIdSecondCard).find("button").click();
        return new TransferPage();
    }

}
