package Bombs;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class Nuclear extends Bombs {

    private bomb nuclear;
    public Nuclear() {
        nuclear = bomb.DEADLY;
        File input = new File("Nuclear.png");
        try {
            bi = ImageIO.read(input);
        } catch (IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.rec = new Rectangle2D(posX, posY, bi.getWidth(), bi.getHeight());
        Random rx= new Random();
        deltaX = 2+rx.nextFloat()*1;
        Random ry= new Random();
        deltaY = -(12+ry.nextFloat()*12);
        downY=-deltaY;
    }

    @Override
    public javafx.scene.image.Image getImage() {
        Image image = SwingFXUtils.toFXImage(this.bi, null);
        return image;
    }

    @Override
    public Enum getObjectType() {
        return nuclear;
    }
}

