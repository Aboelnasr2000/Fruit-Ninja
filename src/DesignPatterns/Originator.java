package DesignPatterns;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Originator {
    Momento momento;
    int highscoreStoI;

    public Originator() {
        this.highscoreStoI=0;
        this.momento = new Momento();
    }



    public void save(String level,int high) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException{
        String highscoreItoS = String.valueOf(high);
        String highscoree =" ";
        File xmlfile= new File("game.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlfile);
        doc.getDocumentElement().normalize();

        switch (level) {
            case "easy":
            {
                Node levell = doc.getElementsByTagName("Level").item(0);
                NodeList nodeList = levell.getChildNodes();
                Node node = nodeList.item(0);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {

                    node.setTextContent(highscoreItoS);
                }       break;
            }
            case "Medium":
            {
                Node levell = doc.getElementsByTagName("Level").item(1);
                NodeList nodeList = levell.getChildNodes();
                Node node = nodeList.item(0);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    node.setTextContent(highscoreItoS);
                }       break;
            }
            case "Hard":
            {
                Node levell = doc.getElementsByTagName("Level").item(2);
                NodeList nodeList = levell.getChildNodes();
                Node node = nodeList.item(0);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    node.setTextContent(highscoreItoS);
                }       break;
            }
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(xmlfile);
        transformer.transform(source, result);



    }
    public int restore(String level) throws ParserConfigurationException, SAXException, IOException{
        String highscoree =" ";
        File xmlfile= new File("game.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlfile);
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("Level");
        switch (level) {
            case "easy":
            {
                Node node = nodeList.item(0);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    highscoree = element.getElementsByTagName("HighScore").item(0).getTextContent();
                }       break;
            }
            case "Medium":
            {
                Node node = nodeList.item(1);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    highscoree = element.getElementsByTagName("HighScore").item(0).getTextContent();
                }       break;
            }
            case "Hard":
            {
                Node node = nodeList.item(2);
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    highscoree = element.getElementsByTagName("HighScore").item(0).getTextContent();
                }       break;
            }
        }

        highscoreStoI = Integer.parseInt(highscoree);
        return highscoreStoI;
    }


}