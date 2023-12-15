package org.example.FactoryMethod;

public class DomXmlParserFactory implements XmlParserFactory {
    public XmlParser createParser() {
        return new DomXmlParser();
    }
}
