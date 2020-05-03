package Fruitninja;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Side;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class Background {

    private static Background instance;

    private Background() {

    }
    public static Background getInstance (){

        if (instance == null)

            instance = new Background();

        return instance;

    }

    public void drawBackground(GraphicsContext gc){
        BufferedImage bi =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
        File input_file = new File("Background.jpg");
        try {
            bi = ImageIO.read(input_file);
        } catch (IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        Image image = SwingFXUtils.toFXImage(bi, null);
        gc.drawImage(image,0,0);
    }

}