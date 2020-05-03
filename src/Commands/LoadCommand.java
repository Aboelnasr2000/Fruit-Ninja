package Commands;

import DesignPatterns.Originator;
import Fruitninja.Level;
import java.io.IOException;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class LoadCommand implements Command {

    Originator originator;
    String level;
    Level l;

    public LoadCommand(Originator originator, String level, Level l) {
        this.originator = originator;
        this.level = level;
        this.l = l;
    }

    @Override
    public void execute() {
        try {
            l.setHighScore(originator.restore(level));
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(LoadCommand.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

}
