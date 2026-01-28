package ru.skillbox.currency.exchange.dto;

import java.util.List;

public record CurrenciesResponseDto(List<SummaryCurrencyDto> currencies) {
}
