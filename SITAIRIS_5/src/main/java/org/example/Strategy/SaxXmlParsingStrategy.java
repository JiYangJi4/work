package org.example.Strategy;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.StringReader;

public class SaxXmlParsingStrategy implements XmlParsingStrategy {
    public void parseXml(String xmlData) {
        // Реализация парсинга XML через SAX
        try {
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            DefaultHandler handler = new DefaultHandler() {
                boolean bName = false;
                boolean bGender = false;
                boolean bAge = false;
                boolean bRole = false;

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("Employee")) {
                        System.out.println("Employee id: " + attributes.getValue("id"));
                    } else if (qName.equalsIgnoreCase("name")) {
                        bName = true;
                    } else if (qName.equalsIgnoreCase("gender")) {
                        bGender = true;
                    } else if (qName.equalsIgnoreCase("age")) {
                        bAge = true;
                    } else if (qName.equalsIgnoreCase("role")) {
                        bRole = true;
                    }
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {

                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    if (bName) {
                        System.out.println("Name: " + new String(ch, start, length));
                        bName = false;
                    } else if (bGender) {
                        System.out.println("Gender: " + new String(ch, start, length));
                        bGender = false;
                    } else if (bAge) {
                        System.out.println("Age: " + new String(ch, start, length));
                        bAge = false;
                    } else if (bRole) {
                        System.out.println("Role: " + new String(ch, start, length));
                        bRole = false;
                    }
                }
            };
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}