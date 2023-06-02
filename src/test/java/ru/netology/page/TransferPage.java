package ru.netology.page;

import com.codeborne.selenide.Condition;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    public TransferPage() {
        $("h1.heading")
                .shouldHave(Condition.text("Пополнение карты"))
                .shouldBe(Condition.visible);
    }

    public DashBoardPage replenishmentCard(DataHelper.CardInfo cardInfo, int amount) {
        $("[data-test-id='amount'] input").setValue(String.valueOf(amount));
        $("[data-test-id='from'] input").setValue(cardInfo.getNumber());
        $("[data-test-id='action-transfer']").click();
        return new DashBoardPage();
    }
}
