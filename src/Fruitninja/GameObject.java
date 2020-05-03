package Fruitninja;

import javafx.geometry.Rectangle2D;

public interface GameObject {
    public Enum getObjectType();
    public javafx.scene.image.Image getImage();
    public void setPosX(float x);
    public void setPosY(float y);
    public int getDownY();
    public int getPosX();
    public int getPosY();
    public int getDeltaX();
    public int getDeltaY();
    public void setDeltaY(int y);
    public float getRand();
    public void setRec();
    public Rectangle2D getRec();
}
