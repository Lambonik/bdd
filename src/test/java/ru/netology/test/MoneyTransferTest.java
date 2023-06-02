package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import ru.netology.data.DataHelper;
import ru.netology.page.DashBoardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;

import static com.codeborne.selenide.Selenide.*;


public class MoneyTransferTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCard() {
        //Configuration.holdBrowserOpen=true;
        var loginData = DataHelper.getValidAuthInfo();
        var cardToTransferFrom = DataHelper.getSecondCardInfo();
        int amount = 200;
        new LoginPage()
                .validLogin(loginData)
                .validVerify(DataHelper.getVerificationCodeFor());
        DashBoardPage balance = new DashBoardPage();
        int initialBalanceFirstCard = balance.getFirstCardBalance();
        int initialBalanceSecondCard = balance.getSecondCardBalance();
        balance.selectTransferToFirstCard();
        new TransferPage().replenishmentCard(cardToTransferFrom, amount);
        int actualFinalBalanceFirstCard = balance.getFirstCardBalance();
        int actualFinalBalanceSecondCard = balance.getSecondCardBalance();
        int expectedFinalBalanceFirstCard = initialBalanceFirstCard + amount;
        int expectedFinalBalanceSecondCard = initialBalanceSecondCard - amount;
        Assert.assertEquals(actualFinalBalanceFirstCard, expectedFinalBalanceFirstCard);
        Assert.assertEquals(actualFinalBalanceSecondCard, expectedFinalBalanceSecondCard);

    }

    @Test
    void shouldTransferMoneyFromFirstToSecondCard() {
        //Configuration.holdBrowserOpen=true;
        var loginData = DataHelper.getValidAuthInfo();
        var cardToTransferFrom = DataHelper.getFirstCardInfo();
        int amount = 200;
        new LoginPage()
                .validLogin(loginData)
                .validVerify(DataHelper.getVerificationCodeFor());
        DashBoardPage balance = new DashBoardPage();
        int initialBalanceFirstCard = balance.getFirstCardBalance();
        int initialBalanceSecondCard = balance.getSecondCardBalance();
        balance.selectTransferToSecondCard();
        new TransferPage().replenishmentCard(cardToTransferFrom, amount);
        int actualFinalBalanceFirstCard = balance.getFirstCardBalance();
        int actualFinalBalanceSecondCard = balance.getSecondCardBalance();
        int expectedFinalBalanceFirstCard = initialBalanceFirstCard - amount;
        int expectedFinalBalanceSecondCard = initialBalanceSecondCard + amount;
        Assert.assertEquals(actualFinalBalanceFirstCard, expectedFinalBalanceFirstCard);
        Assert.assertEquals(actualFinalBalanceSecondCard, expectedFinalBalanceSecondCard);
    }

}
