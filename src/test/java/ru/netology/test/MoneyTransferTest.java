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
    void shouldTransferMoneyFromFirstToSecondCard() {
        //Configuration.holdBrowserOpen=true;
        var loginData = DataHelper.getValidAuthInfo();
        var cardToTransferFrom = DataHelper.getSecondCardInfo();
        int amount = 200;
        new LoginPage()
                .validLogin(loginData)
                .validVerify(DataHelper.getVerificationCodeFor());
        int initialBalanceFirstCard = DashBoardPage.getFirstCardBalance();
        int initialBalanceSecondCard = DashBoardPage.getSecondCardBalance();
        DashBoardPage.SelectTransferToFirstCard();
        TransferPage.replenishmentCard(cardToTransferFrom, amount);
        int finalBalanceFirstCard = DashBoardPage.getFirstCardBalance();
        int finalBalanceSecondCard = DashBoardPage.getSecondCardBalance();
        int calculateBalanceFirstCard = finalBalanceFirstCard - initialBalanceFirstCard;
        int calculateBalanceSecondCard = finalBalanceSecondCard - initialBalanceSecondCard;
        Assert.assertEquals(calculateBalanceFirstCard + calculateBalanceSecondCard, 0);
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCard() {
        //Configuration.holdBrowserOpen=true;
        var loginData = DataHelper.getValidAuthInfo();
        var cardToTransferFrom = DataHelper.getFirstCardInfo();
        int amount = 200;
        new LoginPage()
                .validLogin(loginData)
                .validVerify(DataHelper.getVerificationCodeFor());
        int initialBalanceFirstCard = DashBoardPage.getFirstCardBalance();
        int initialBalanceSecondCard = DashBoardPage.getSecondCardBalance();
        DashBoardPage.SelectTransferToSecondCard();
        TransferPage.replenishmentCard(cardToTransferFrom, amount);
        int finalBalanceFirstCard = DashBoardPage.getFirstCardBalance();
        int finalBalanceSecondCard = DashBoardPage.getSecondCardBalance();
        int calculateBalanceFirstCard = finalBalanceFirstCard - initialBalanceFirstCard;
        int calculateBalanceSecondCard = finalBalanceSecondCard - initialBalanceSecondCard;
        Assert.assertEquals(calculateBalanceFirstCard + calculateBalanceSecondCard, 0);
    }
}
