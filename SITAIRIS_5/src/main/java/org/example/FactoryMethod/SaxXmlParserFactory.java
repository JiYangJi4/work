package org.example.FactoryMethod;

public class SaxXmlParserFactory implements XmlParserFactory {
    public XmlParser createParser() {
        return new SaxXmlParser();
    }
}
