package ru.skillbox.currency.exchange.parser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
public class Valute {

    @XmlElement(name = "CharCode")
    private String isoAlphaCode;

    @XmlElement(name = "NumCode")
    private Long isoNumCode;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Nominal")
    private Integer nominal;

    @XmlElement(name = "Value")
    private String valueToStr;

    public Double getValue(){
        return Double.parseDouble(valueToStr.replace(',', '.'));
    }
}
