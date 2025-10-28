package ru.snptech.fxsignalflow.service.signal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.snptech.fxsignalflow.exception.FxSignalFlowDomainLogicException;
import ru.snptech.fxsignalflow.model.signal.Signal;
import ru.snptech.fxsignalflow.service.currency.CurrencyProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class SignalParser {

    private final CurrencyProvider currencyProvider;

    private static final Integer MAX_TAKE_PROFITS_COUNT = 6;
    private static final Pattern HEADER_PATTERN = Pattern.compile("([A-Z]+)\\s+(BUY|SELL)\\s+(\\d+(?:\\.\\d+)?)", Pattern.CASE_INSENSITIVE);
    private static final Pattern SL_PATTERN = Pattern.compile("SL\\s*(\\d+(?:\\.\\d+)?)", Pattern.CASE_INSENSITIVE);
    private static final Pattern TP_PATTERN = Pattern.compile("TP\\s*(\\d+(?:\\.\\d+)?)", Pattern.CASE_INSENSITIVE);

    public Signal parse(String text) {
        String pair = parsePair(text);
        String entry = parseEntry(text);
        String stopLoss = parseStopLoss(text);
        List<String> takeProfits = parseTakeProfits(text);

        return new Signal(pair, entry, stopLoss, takeProfits);
    }

    private String parsePair(String text) {
        Matcher matcher = HEADER_PATTERN.matcher(text);

        if (matcher.find()) {
            return matcher.group(1);
        }

        throw new FxSignalFlowDomainLogicException.UNABLE_TO_PARSE_SIGNAL();
    }

    private String parseEntry(String text) {
        Matcher matcher = HEADER_PATTERN.matcher(text);

        if (matcher.find()) {
            String direction = matcher.group(2).toUpperCase();
            String price = matcher.group(3);

            return direction + " " + price;
        }

        throw new FxSignalFlowDomainLogicException.UNABLE_TO_PARSE_SIGNAL();
    }

    private String parseStopLoss(String text) {
        Matcher matcher = SL_PATTERN.matcher(text);

        if (matcher.find()) return matcher.group(1);

        throw new FxSignalFlowDomainLogicException.UNABLE_TO_PARSE_SIGNAL();
    }

    private List<String> parseTakeProfits(String text) {
        List<String> takeProfits = new ArrayList<>();
        Matcher matcher = TP_PATTERN.matcher(text);

        while (matcher.find() && takeProfits.size() < MAX_TAKE_PROFITS_COUNT) {
            takeProfits.add(matcher.group(1));
        }

        if (takeProfits.isEmpty()) {
            throw new FxSignalFlowDomainLogicException.UNABLE_TO_PARSE_SIGNAL();
        }

        return takeProfits;
    }

    private String parseCurrencyPair(String pair) {
        var currencies = currencyProvider.getCurrencies();
        var normalized = pair.toLowerCase();

        for (int i = 1; i < normalized.length(); i++) {
            String left = normalized.substring(0, i);
            String right = normalized.substring(i);

            if (currencies.containsKey(left) && currencies.containsKey(right)) {
                return (left + "/" + right).toUpperCase();
            }
        }

        throw new FxSignalFlowDomainLogicException.CURRENCY_PAIR_NOT_FOUND(pair);
    }
}
