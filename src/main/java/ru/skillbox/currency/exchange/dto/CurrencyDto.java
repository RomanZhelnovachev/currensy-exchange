package ru.skillbox.currency.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public record  CurrencyDto (Long id, String name, Long nominal, Double value, Long isoNumCode, String isoAlphaCode) {

}