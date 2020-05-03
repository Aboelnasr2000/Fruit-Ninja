package Fruits;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class fruitSpecial1 extends Fruit {
    private Fruit.fruit superFruit ;

    private BufferedImage bi =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    private BufferedImage bi1 =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    private BufferedImage bi2 =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    public fruitSpecial1() {
        points=2;
        Random ry = new Random();
        deltaY = -(8 + ry.nextFloat() * 8);
        downY = -deltaY;
        superFruit =Fruit.fruit.SUPERFRUIT;
        File input=new File("Superball.png");
        try {
            bi= ImageIO.read(input);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.rec = new Rectangle2D(posX,posY,bi.getWidth(),bi.getHeight());
        File input1=new File("Superball1.png");
        try {
            bi1= ImageIO.read(input1);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        File input2=new File("Superball2.png");
        try {
            bi2= ImageIO.read(input2);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.rec = new Rectangle2D(posX,posY,bi.getWidth(),bi.getHeight());
    }

    @Override
    public javafx.scene.image.Image getImage(){
        Image image = SwingFXUtils.toFXImage(this.bi, null);
        return  image;
    }

    @Override
    public javafx.scene.image.Image getImage1(){
        Image image = SwingFXUtils.toFXImage(this.bi1, null);
        return  image;
    }

    @Override
    public javafx.scene.image.Image getImage2(){
        Image image = SwingFXUtils.toFXImage(this.bi2, null);
        return  image;
    }


    @Override
    public Enum getObjectType() {
        return superFruit;
    }



}
