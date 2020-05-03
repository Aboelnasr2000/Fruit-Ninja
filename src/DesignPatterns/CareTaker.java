package DesignPatterns;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CareTaker {

    Momento momento;
    Originator originator;
    int high;

    public CareTaker() {
        this.high = 0;
        this.originator = new Originator();
        this.momento = new Momento();
    }

    public void addMomento(int highscore, String level) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        if (level != null) {
            String highscoree = " ";
            File xmlfile = new File("game.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlfile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("Level");
            switch (level) {
                case "easy": {
                    Node node = nodeList.item(0);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        highscoree = element.getElementsByTagName("HighScore").item(0).getTextContent();

                    }
                    break;
                }
                case "Medium": {
                    Node node = nodeList.item(1);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        highscoree = element.getElementsByTagName("HighScore").item(0).getTextContent();
                    }
                    break;
                }
                case "Hard": {
                    Node node = nodeList.item(2);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        highscoree = element.getElementsByTagName("HighScore").item(0).getTextContent();
                    }
                    break;
                }
            }
            int highscoreStoI = Integer.parseInt(highscoree);
            if (highscore > highscoreStoI) {
                originator.save(level, highscore);
            }

        }

    }
}