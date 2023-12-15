package org.example.Strategy;

// Конкретная реализация стратегии для DOM парсинга

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import java.io.StringReader;

public class DomXmlParsingStrategy implements XmlParsingStrategy {
    public void parseXml(String xmlData) {
        // Реализация парсинга XML через DOM
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xmlData)));

            NodeList employees = document.getElementsByTagName("Employee");
            for (int i = 0; i < employees.getLength(); i++) {
                Node employee = employees.item(i);
                if (employee.getNodeType() == Node.ELEMENT_NODE) {
                    Element employeeElement = (Element) employee;
                    System.out.println("Employee id: " + employeeElement.getAttribute("id"));
                    System.out.println("Name: " + employeeElement.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Gender: " + employeeElement.getElementsByTagName("gender").item(0).getTextContent());
                    System.out.println("Age: " + employeeElement.getElementsByTagName("age").item(0).getTextContent());
                    System.out.println("Role: " + employeeElement.getElementsByTagName("role").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}