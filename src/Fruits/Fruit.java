package Fruits;

import java.awt.image.BufferedImage;
import java.util.Random;
import Fruitninja.GameObject;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public abstract class Fruit implements GameObject {

    Rectangle2D rec;
    BufferedImage fruitImage = new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);
    BufferedImage fruitImage1 = new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);
    BufferedImage fruitImage2 = new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);
    int points;
    int posX, posY, halfX1, halfX2;
    float rand;
    float deltaX, deltaY, downY;

    public Fruit() {
        Random r = new Random();
        posX = (int) (100 + r.nextDouble() * 800);
        posY = 561;
        rand = (float) (100 + r.nextDouble() * 800);
        Random randomx = new Random();
        deltaX = 1 + randomx.nextFloat() * 1 ;
        Random randomy = new Random();
        deltaY = -(6 + randomy.nextFloat() * 10);
        downY = -deltaY;
    }

    public int getPoints() {
        return points;
    }

    public int getHalfX1() {
        return halfX1;
    }

    public void setHalfX1(int halfX1) {
        this.halfX1 += halfX1;
    }

    public int getHalfX2() {
        return halfX2;
    }

    public void setHalfX2(int halfX2) {
        this.halfX2 += halfX2;
    }

    public void initHalf(int x) {
        this.halfX1 = x;
        this.halfX2 = x;
    }

    @Override
    public void setRec() {
        rec = new Rectangle2D(posX, posY, fruitImage.getWidth(), fruitImage.getHeight());
    }

    @Override
    public Rectangle2D getRec() {
        if(rec!=null)
            return rec;
        else return null;
    }

    @Override
    public float getRand() {
        return rand;
    }

    @Override
    public abstract Enum getObjectType();

    public enum fruit {

        MELON, BANANA, APPLE, SUPERFRUIT, MAGICFRUIT, LIFEFRUIT , ORANGE;

    }

    @Override
    public void setPosX(float x) {
        this.posX += x;
        initHalf(posX);
    }

    @Override
    public void setPosY(float y) {
        this.posY += y;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public int getDownY() {
        return (int) downY;
    }

    @Override
    public int getDeltaX() {
        if (rand >= 400) {
            return -(int) deltaX;
        }
        return (int) deltaX;
    }

    @Override
    public int getDeltaY() {
        return (int) deltaY;
    }

    @Override
    public void setDeltaY(int y) {
        deltaY = y;
    }

    public javafx.scene.image.Image getImage1() {
        Image image = SwingFXUtils.toFXImage(this.fruitImage1, null);
        return image;
    }

    public javafx.scene.image.Image getImage2() {
        Image image = SwingFXUtils.toFXImage(this.fruitImage2, null);
        return image;
    }

}