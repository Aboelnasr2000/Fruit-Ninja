package Fruitninja;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class Warnning {
    private BufferedImage Red =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    private BufferedImage Green =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    public Warnning(){
        File input1=new File("GreenX.png");
        try {
            Green = ImageIO.read(input1);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        File input2=new File("RedX.png");
        try {
            Red = ImageIO.read(input2);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    public javafx.scene.image.Image getGImage(){
        Image image = SwingFXUtils.toFXImage(this.Green, null);
        return  image;
    }

    public javafx.scene.image.Image getRImage(){
        Image image = SwingFXUtils.toFXImage(this.Red, null);
        return  image;
    }


}
