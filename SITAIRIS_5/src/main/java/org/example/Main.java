package org.example;

import org.example.Strategy.DomXmlParsingStrategy;
import org.example.Strategy.SaxXmlParsingStrategy;
import org.example.Strategy.XmlParser;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            String xmlData = new String(Files.readAllBytes(Paths.get("D:\\БГУИР\\стрWEB\\Marshalling\\DOM\\src\\resources\\employee.xml"))); // чтение данных из файла
            XmlParser xmlParser = new XmlParser();

            // Использование DOM стратегии
            System.out.println("==== DOM Strategy ====");
            xmlParser.setParsingStrategy(new DomXmlParsingStrategy());
            xmlParser.parseXml(xmlData);

            // Использование SAX стратегии
            System.out.println("==== SAX Strategy ====");
            xmlParser.setParsingStrategy(new SaxXmlParsingStrategy());
            xmlParser.parseXml(xmlData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}