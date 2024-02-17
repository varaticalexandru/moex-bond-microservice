package org.alexv.moexservice.utils;

import org.alexv.moexservice.model.Currency;

public class Utilities {
    public static String securityType() {
        return "Bond";
    }

    public static Currency currency() {
        return Currency.RUB ;
    }

    public static String source() {
        return "MOEX";
    }
}
