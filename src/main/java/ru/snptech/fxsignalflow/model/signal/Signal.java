package ru.snptech.fxsignalflow.model.signal;

import java.util.List;

public record Signal(
    String currencyPair,
    String entry,
    String stopLoss,
    List<String> takeProfits
) {

    public String toMessage() {
        var builder = new StringBuilder();

        builder
            .append(currencyPair).append(" ")
            .append(entry).append("\n\n")
            .append("SL ").append(stopLoss)
            .append("\n");

        takeProfits.forEach(tp ->
            builder
                .append("\n")
                .append("TP ")
                .append(tp)
        );

        return builder.toString();
    }

    public String toBeautyMessage() {
        var builder = new StringBuilder();
        builder
            .append("💱 Pair: ").append(currencyPair).append("\n")
            .append(resolveEntryEmoji()).append(entry).append("\n\n")
            .append("⛔ Stop Loss: ").append(stopLoss).append("\n\n");

        for (int i = 0; i < takeProfits.size(); i++) {
            String tp = takeProfits.get(i);
            builder.append("🎯 Take Profit ")
                .append(i + 1)
                .append(": ")
                .append(tp)
                .append("\n");
        }


        return builder.toString();
    }

    private String resolveEntryEmoji() {
        if (entry.contains("BUY")) {
            return "🟢 ";
        }

        return "🔻 ";
    }

}
