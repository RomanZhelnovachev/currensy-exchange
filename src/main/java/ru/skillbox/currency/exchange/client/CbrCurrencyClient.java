package ru.skillbox.currency.exchange.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.skillbox.currency.exchange.config.CurrencyProperties;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.mapper.CurrencyMapper;
import ru.skillbox.currency.exchange.parser.ValCurs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CbrCurrencyClient {

    private final RestTemplate restTemplate;
    private final CurrencyProperties properties;

    public List<Currency> getCurrencies(){
        String xml = restTemplate.getForObject(properties.url(), String.class);
        try{
        JAXBContext context = JAXBContext.newInstance(ValCurs.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ValCurs valCurs = (ValCurs) unmarshaller.unmarshal(new StringReader(xml));
        return valCurs.getValutes().stream()
                .map(valute -> new Currency( null,
                        valute.getName(),
                        Long.valueOf(valute.getNominal()),
                        valute.getValue(),
                        valute.getIsoNumCode(),
                        valute.getIsoAlphaCode()
                ))
                .collect(Collectors.toList());
        } catch (JAXBException e) {
            throw new RuntimeException("Ошибка парсинга XML", e);
        }
    }
}
