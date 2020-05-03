package Fruitninja;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class Menu {

    private final Factory factory;
    private final GraphicsContext gc;
    private final Scene scene;
    private Rectangle2D easy;
    private Rectangle2D medium;
    private Rectangle2D hard;
    private Rectangle2D arcade;
    private Rectangle2D game;

    public Menu(Factory factory, GraphicsContext gc, Scene scene) {
        this.factory = factory;
        this.gc = gc;
        this.scene = scene;

        this.easy = new Rectangle2D(110, 150, 190, 50);
        this.medium = new Rectangle2D(110, 250, 190, 50);
        this.hard = new Rectangle2D(110, 350, 190, 50);
        this.arcade = new Rectangle2D(110, 450, 190, 50);
        this.game = new Rectangle2D(110,550,190,100);
    }

    public void Draw() {
        factory.clearCanvas();
        factory.drawBackGround();
        factory.media();
        factory.options();
        factory.Title();
        scene.setOnMouseClicked(
                (EventHandler<MouseEvent>) e -> {
                    if (easy.contains(e.getX(), e.getY())) {
                        factory.setLevel(1);
                        factory.setFirst(false);
                    } else if (medium.contains(e.getX(), e.getY())) {
                        factory.setLevel(2);
                        factory.setFirst(false);
                    } else if (hard.contains(e.getX(), e.getY())) {
                        factory.setLevel(3);
                        factory.setFirst(false);
                    } else if (arcade.contains(e.getX(), e.getY())) {
                        factory.setLevel(4);
                        factory.setFirst(false);
                    }
                });

    }

    public void setRecs() {
        this.easy = new Rectangle2D(110, 150, 190, 50);
        this.medium = new Rectangle2D(110, 250, 190, 50);
        this.hard = new Rectangle2D(110, 350, 190, 50);
        this.arcade = new Rectangle2D(110, 450, 190, 50);
        this.game = new Rectangle2D(110,550,190,100);
    }

    public void removeRecs() {
        this.easy = new Rectangle2D(1000, 150, 190, 50);
        this.medium = new Rectangle2D(1000, 250, 190, 50);
        this.hard = new Rectangle2D(1000, 350, 190, 50);
        this.arcade = new Rectangle2D(1000, 450, 190, 50);
        this.game = new Rectangle2D(1000,550,190,100);
    }
}
