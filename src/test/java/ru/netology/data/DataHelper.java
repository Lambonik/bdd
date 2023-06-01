package ru.netology.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getValidAuthInfo() {

        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getInValidAuthInfo() {

        return new AuthInfo("user", "user");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor() {

        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        private String number;
        private Integer balance;
    }

    public static CardInfo getFirstCardInfo() {

        return new CardInfo("5559 0000 0000 0001", 10000);
    }

    public static CardInfo getSecondCardInfo() {

        return new CardInfo("5559 0000 0000 0002", 10000);
    }
}
