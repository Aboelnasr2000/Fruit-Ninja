package Bombs;

import java.awt.image.BufferedImage;
import java.util.Random;

import Fruitninja.GameObject;
import javafx.geometry.Rectangle2D;

public abstract class Bombs implements GameObject {

    int posX, posY;
    Rectangle2D rec;
    BufferedImage bi = new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);
    float deltaX, deltaY, downY;
    float rand;

    public Bombs() {
        Random r = new Random();
        posX = (int) (100 + r.nextDouble() * 800);
        posY = 561;
        rand = (float) (100 + r.nextDouble() * 800);
    }

    @Override
    public void setRec() {
        rec = new Rectangle2D(posX, posY, bi.getWidth(), bi.getHeight());
    }

    @Override
    public Rectangle2D getRec() {
        if (rec != null) {
            return rec;
        } else {
            return null;
        }
    }

    @Override
    public float getRand() {
        return rand;
    }

    @Override
    public abstract Enum getObjectType();

    public enum bomb {

        DEADLY, NORM;
    }

    @Override
    public void setPosX(float x) {
        this.posX += x;
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
    public int getDownY() {
        return (int) downY;
    }

    @Override
    public void setDeltaY(int y) {
        deltaY = y;
    }

}