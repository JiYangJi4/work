package org.example.Strategy;

public class XmlParser {
    private XmlParsingStrategy parsingStrategy;

    public void setParsingStrategy(XmlParsingStrategy strategy) {
        this.parsingStrategy = strategy;
    }

    public void parseXml(String xmlData) {
        if (parsingStrategy != null) {
            parsingStrategy.parseXml(xmlData);
        } else {
            // Обработка отсутствия стратегии
        }
    }
}
