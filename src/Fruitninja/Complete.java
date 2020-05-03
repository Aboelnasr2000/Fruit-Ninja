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

public class Complete {

    private BufferedImage pauseBackground =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    private BufferedImage restart =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    private BufferedImage back =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    private BufferedImage pause =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    private BufferedImage play =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    private BufferedImage ninja = new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    private Rectangle2D rec1;
    private Rectangle2D rec2;
    private Rectangle2D rec3;
    private Rectangle2D rec4;



    private Rectangle2D ninjaPic;
    public Complete() {
        File input8 = new File("Ninja.png") ;
        try {
            ninja=ImageIO.read(input8);
        } catch (IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.ninjaPic = new Rectangle2D(30,70,ninja.getWidth(),ninja.getHeight());
        File input3=new File("pause.png");
        try {
            pause = ImageIO.read(input3);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.rec3 = new Rectangle2D(30,70, restart.getWidth(), restart.getHeight());

        File input4=new File("play.png");
        try {
            play = ImageIO.read(input4);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.rec4 = new Rectangle2D(460,300, restart.getWidth(), restart.getHeight());
        File input=new File("PauseBackground.jpg");
        try {
            pauseBackground = ImageIO.read(input);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        File input1=new File("BackArrow.png");
        try {
            back = ImageIO.read(input1);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.rec1 = new Rectangle2D(325,315, back.getWidth(), back.getHeight());
        File input2=new File("restart.png");
        try {
            restart = ImageIO.read(input2);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.rec2 = new Rectangle2D(610,315, restart.getWidth(), restart.getHeight());

    }


    public javafx.scene.image.Image getWImage(){
        Image image = SwingFXUtils.toFXImage(this.pauseBackground, null);
        return  image;
    }

    public javafx.scene.image.Image getRImage(){
        Image image = SwingFXUtils.toFXImage(this.restart, null);
        return  image;
    }

    public javafx.scene.image.Image getBImage(){
        Image image = SwingFXUtils.toFXImage(this.back, null);
        return  image;
    }
    public javafx.scene.image.Image getPImage(){
        Image image = SwingFXUtils.toFXImage(this.pause, null);
        return  image;
    }
    public javafx.scene.image.Image getSImage(){
        Image image = SwingFXUtils.toFXImage(this.play, null);
        return  image;
    }
    public javafx.scene.image.Image  getNinja() {
        Image image = SwingFXUtils.toFXImage(this.ninja, null);
        return image;

    }

    public void setRec1() {
        rec1 = new Rectangle2D(325,315, back.getWidth(), back.getHeight());
    }

    public void setRec2() {
        rec2 = new Rectangle2D(610,315, restart.getWidth(), restart.getHeight());
    }

    public void removeRecs(){
        rec1 = new Rectangle2D(1000,315, back.getWidth(), back.getHeight());
        rec2 = new Rectangle2D(1000,315, restart.getWidth(), restart.getHeight());
    }
    public Rectangle2D getRec1() {
        return rec1;
    }
    public Rectangle2D getRec2() {
        return rec2;
    }
    public Rectangle2D getRec3() {
        return rec3;
    }
    public Rectangle2D getRec4() { return rec4; }
}
