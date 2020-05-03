package Fruits;


import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class fruitApple extends Fruit{
    private Fruit.fruit apple ;
    public fruitApple() {
        points=1;
        apple =Fruit.fruit.APPLE;
        File input=new File("Apple.png");
        try {
            fruitImage = ImageIO.read(input);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.rec = new Rectangle2D(posX,posY, fruitImage.getWidth(), fruitImage.getHeight());
        File input1=new File("Apple1.png");
        try {
            fruitImage1 = ImageIO.read(input1);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        File input2=new File("Apple2.png");
        try {
            fruitImage2 = ImageIO.read(input2);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.rec = new Rectangle2D(posX,posY, fruitImage.getWidth(), fruitImage.getHeight());
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
        return apple;
    }


}
