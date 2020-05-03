package Fruits;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class fruitLife extends Fruit {
    private Fruit.fruit lifeFruit ;
    public fruitLife() {
        points=0;
        lifeFruit =Fruit.fruit.LIFEFRUIT;
        File input=new File("heart.png");
        try {
            fruitImage = ImageIO.read(input);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.rec = new Rectangle2D(posX,posY, fruitImage.getWidth(), fruitImage.getHeight());
        File input1=new File("heart1.png");
        try {
            fruitImage1 = ImageIO.read(input1);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        File input2=new File("heart2.png");
        try {
            fruitImage2 = ImageIO.read(input2);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    @Override
    public javafx.scene.image.Image getImage(){
        Image image = SwingFXUtils.toFXImage(this.fruitImage, null);
        return  image;
    }

    @Override
    public javafx.scene.image.Image getImage1(){
        Image image = SwingFXUtils.toFXImage(this.fruitImage1, null);
        return  image;
    }

    @Override
    public javafx.scene.image.Image getImage2(){
        Image image = SwingFXUtils.toFXImage(this.fruitImage2, null);
        return  image;
    }


    @Override
    public Enum getObjectType() {
        return lifeFruit;
    }



}
