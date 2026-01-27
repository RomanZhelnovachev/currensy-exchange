package ru.skillbox.currency.exchange.scheduller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.skillbox.currency.exchange.client.CbrCurrencyClient;
import ru.skillbox.currency.exchange.service.CurrencyService;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UpdateScheduler {

    private final CbrCurrencyClient client;
    private final CurrencyService service;

    @Scheduled(fixedDelay = 1L, timeUnit = TimeUnit.HOURS)
    public void checkAndUpdateCurrencies(){
service.syncCurrencies(client.getCurrencies());
    }
}
