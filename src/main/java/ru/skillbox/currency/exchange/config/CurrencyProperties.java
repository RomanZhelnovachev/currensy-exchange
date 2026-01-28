package ru.skillbox.currency.exchange.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "currency")
public record CurrencyProperties(String url) {
}
