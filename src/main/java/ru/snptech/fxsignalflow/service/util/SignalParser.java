package ru.snptech.fxsignalflow.service.util;

import ru.snptech.fxsignalflow.exception.FxSignalFlowDomainLogicException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignalParser {

    private static final Pattern pairPattern = Pattern.compile("(?i)([A-Z]+)\\s+(BUY|SELL)\\s+NOW\\s+@\\s*(\\d+)");
    private static final Pattern stopLossPattern = Pattern.compile("(?i)SL\\s*(\\d+)");
    private static final Pattern takeProfitPatter = Pattern.compile("(?i)TP(\\d+)\\s*(\\d+)");

    public static String parseSignal(String text) {
        try {
            return parse(text);
        } catch (Throwable t) {
            throw new FxSignalFlowDomainLogicException.UNABLE_TO_PARSE_SIGNAL();
        }

    }

    private static String parse(String text) {
        StringBuilder result = new StringBuilder();

        Matcher entryMatcher = pairPattern.matcher(text);
        if (entryMatcher.find()) {
            var template = """ 
                💱Pair: %s 📊Entry: %s
                NOW @ %s
                 """;
            result.append(template.formatted(entryMatcher.group(1), entryMatcher.group(2).toUpperCase(), entryMatcher.group(3)));
        }

        Matcher slMatcher = stopLossPattern.matcher(text);
        if (slMatcher.find()) {
            result.append("⛔Stop Loss: ").append(slMatcher.group(1)).append("\n");
        }

        Matcher tpMatcher = takeProfitPatter.matcher(text);
        while (tpMatcher.find()) {
            result.append("🎯Take Profit ").append(tpMatcher.group(1)).append(": ").append(tpMatcher.group(2)).append("\n");
        }

        return result.toString().trim();
    }
}