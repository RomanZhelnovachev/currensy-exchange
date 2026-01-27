package ru.skillbox.currency.exchange.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.currency.exchange.dto.CurrencyDto;
import ru.skillbox.currency.exchange.dto.SummaryCurrencyDto;
import ru.skillbox.currency.exchange.entity.Currency;
import ru.skillbox.currency.exchange.mapper.CurrencyMapper;
import ru.skillbox.currency.exchange.repository.CurrencyRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyMapper mapper;
    private final CurrencyRepository repository;

    @Transactional(readOnly = true)
    public CurrencyDto getById(Long id) {
        log.info("CurrencyService method getById executed");
        Currency currency = repository.findById(id).orElseThrow(() -> new RuntimeException("Currency not found with id: " + id));
        return mapper.convertToDto(currency);
    }

    @Transactional(readOnly = true)
    public Double convertValue(Long value, Long numCode) {
        log.info("CurrencyService method convertValue executed");
        Currency currency = repository.findByIsoNumCode(numCode);
        return value * currency.getValue();
    }

    @Transactional
    public CurrencyDto create(CurrencyDto dto) {
        log.info("CurrencyService method create executed");
        return  mapper.convertToDto(repository.save(mapper.convertToEntity(dto)));
    }

    @Transactional(readOnly = true)
    public List<SummaryCurrencyDto> getAll(){
        return repository.findAll().stream()
                .map(mapper::convertToSummaryDto)
                .toList();
    }

    @Transactional
    public void syncCurrencies(List<Currency> currencies){
        for(Currency currency:currencies){
            Currency existCurrency = repository.findByIsoAlphaCode(currency.getIsoAlphaCode());
            if(existCurrency != null){
                existCurrency.setName(currency.getName());
                existCurrency.setNominal(currency.getNominal());
                existCurrency.setValue(currency.getValue());
                repository.save(existCurrency);
            }else {
                repository.save(currency);
            }
        }
    }
}
