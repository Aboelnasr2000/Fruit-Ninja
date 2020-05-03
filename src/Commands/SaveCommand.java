package Commands;

import DesignPatterns.CareTaker;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

public class SaveCommand implements Command{

    CareTaker careTaker;
    int score;
    String level;

    public SaveCommand(CareTaker careTaker, int score, String level) {
        this.careTaker = careTaker;
        this.score = score;
        this.level = level;
    }

    @Override
    public void execute() {
        try {
            careTaker.addMomento(score,level);
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
            Logger.getLogger(SaveCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
