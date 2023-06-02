package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Selenide.$;

public class DashBoardPage {
    private SelenideElement testIdFirstCard = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement testIdSecondCard = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

    public DashBoardPage() {
        $("[data-test-id='dashboard']")
                .shouldHave(Condition.text("Личный кабинет"))
                .shouldBe(Condition.visible);
        $("h1.heading")
                .shouldHave(Condition.text("Ваши карты"))
                .shouldBe(Condition.visible);
    }

    public int getFirstCardBalance() {
        val balanceText = $(testIdFirstCard).text();
        return extractBalance(balanceText);
    }

    public int getSecondCardBalance() {
        val balanceText = $(testIdSecondCard).text();
        return extractBalance(balanceText);
    }

    private int extractBalance(String balanceText) {
        String[] extractSplit = balanceText.split(":");
        String extractBalance = extractSplit[1].substring(0, extractSplit[1].indexOf("р.")).trim();
        return Integer.parseInt(extractBalance);
    }

    public TransferPage selectTransferToFirstCard() {
        $(testIdFirstCard).find("button").click();
        return new TransferPage();
    }

    public TransferPage selectTransferToSecondCard() {
        $(testIdSecondCard).find("button").click();
        return new TransferPage();
    }

}
