package Levels;

import DesignPatterns.CareTaker;
import DesignPatterns.Momento;
import DesignPatterns.Originator;
import Fruitninja.*;
import Bombs.Bombs.bomb;
import Fruits.Fruit;
import Fruits.Fruit.fruit;
import java.io.IOException;
import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class levelEasy implements Level {
    private final Originator originator;
    private final Momento momento;
    private final CareTaker careTaker;
    private final Scene scene;
    private final Complete complete;
    private final Factory factory;

    private final String level = "easy";
    private GameObject gameObject;
    private GameObject gameObject1;
    private GameObject gameObject2;
    private final GameActions actions;
    private boolean[] flag;
    private boolean[] flag1;
    private boolean[] flag2;

    private int score, lives, time;
    private int highScore;
    private double x, y;
    private boolean pause = false;

    public levelEasy(Factory factory, Scene scene) throws ParserConfigurationException, SAXException, IOException {
        originator = new Originator();
        momento = new Momento();
        careTaker = new CareTaker();
        this.lives = 3;
        this.score = 0;
        this.flag = new boolean[3];
        this.flag1 = new boolean[3];
        this.flag2 = new boolean[3];
        this.actions = new Actions();
        this.factory = factory;
        this.complete = new Complete();
        this.scene = scene;
        this.factory.loadScore(this.originator, this.level, this);
    }

    @Override
    public void manageLevel() {

        factory.clearCanvas();
        factory.drawBackGround();
        factory.showScore(score);
        factory.showHighScore(highScore);
        factory.drawP();
        factory.drawGx(780, 25);
        factory.drawGx(860, 25);
        factory.drawGx(940, 25);
        if (pause) {
            factory.drawPause();
            factory.showPause();
            complete.setRec1();
            complete.setRec2();
        }
        if (lives == 2) {
            factory.drawRx(940, 25);
        } else if (lives == 1) {
            factory.drawRx(940, 25);
            factory.drawRx(860, 25);
        } else if (lives == 0) {
            momento.setHighscore(score);
            factory.drawRx(940, 25);
            factory.drawRx(860, 25);
            factory.drawRx(780, 25);
            complete.setRec1();
            complete.setRec2();
            factory.drawW(this, score, highScore);
            factory.saveScore(careTaker, level, score);
        }
        if (lives > 0 && !pause) {
            try {
                factory.showTime();
                time = factory.getSeconds();
                objectMotion(gameObject, 0);
                objectMotion(gameObject1, 1);
                objectMotion(gameObject2, 2);
                gameObject = checkEnd(gameObject, 0);
                gameObject1 = checkEnd(gameObject1, 1);
                gameObject2 = checkEnd(gameObject2, 2);
            } catch (ParserConfigurationException | IOException | SAXException ex) {
                Logger.getLogger(levelEasy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        scene.setOnMouseDragged(
                (EventHandler<MouseEvent>) e -> {
                    x = e.getX();
                    y = e.getY();
                    if (gameObject.getRec().contains(e.getX(), e.getY())) {
                        flag1[0] = true;
                    }
                    if (gameObject1.getRec().contains(e.getX(), e.getY())) {
                        flag1[1] = true;
                    }
                    if (gameObject2.getRec().contains(e.getX(), e.getY())) {
                        flag1[2] = true;
                    }
                });
        factory.shadow(x, y, this);
        scene.setOnMouseClicked(
                (EventHandler<MouseEvent>) e -> {
                    if (complete.getRec1().contains(e.getX(), e.getY())) {
                        factory.setLevel(0);
                        pause = false;
                    } else if (complete.getRec2().contains(e.getX(), e.getY())) {
                        factory.setLevel(1);
                        pause = false;
                        try {
                            initGame();
                        } catch (ParserConfigurationException | IOException | SAXException ex) {
                            Logger.getLogger(levelEasy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                    } else if (complete.getRec3().contains(e.getX(), e.getY())) {
                        pause = true;
                    } else if (complete.getRec4().contains(e.getX(), e.getY())) {
                        complete.removeRecs();
                        factory.setSeconds(time);
                        pause = false;
                    }
                });

    }

    public void objectMotion(GameObject go, int i) {
        if (flag[i] || flag1[i]) {
            go.setDeltaY(go.getDownY());
        } else if (go.getPosY() <= 30) {
            flag[i] = true;
        }
        if (!flag1[i]) {
            actions.updateObjectPlace(go);
            factory.drawObject(go);
        } else if (flag1[i]) {
            if (go.getObjectType() == fruit.BANANA || go.getObjectType() == fruit.MELON
                    || go.getObjectType() == fruit.APPLE || go.getObjectType() == fruit.SUPERFRUIT || go.getObjectType()==fruit.ORANGE) {
                if (!flag2[i]) {
                    factory.swordSound();
                    score = factory.setScore((Fruit) go, score);
                    flag2[i] = true;
                }
                actions.updateHalf((Fruit) go);
                factory.drawHalf((Fruit) go);
            }
            if (go.getObjectType() == bomb.NORM) {
                if (!flag2[i]) {
                    factory.bombSound();
                    lives--;
                    go.setPosY(563);
                    flag2[i] = true;

                }
                actions.updateObjectPlace(go);
            }
            if (go.getObjectType() == bomb.DEADLY) {
                factory.redSound();
                lives = 0;
            }
            if (go.getObjectType() == fruit.MAGICFRUIT) {
                if (!flag2[i]) {
                    flag2[i] = true;
                    if (this.gameObject.getObjectType() != bomb.DEADLY && this.gameObject.getObjectType() != bomb.NORM) {
                        flag1[0] = true;
                    } else this.gameObject.setPosY(563);
                    if (this.gameObject1.getObjectType() != bomb.DEADLY && this.gameObject1.getObjectType() != bomb.NORM) {
                        flag1[1] = true;
                    } else this.gameObject1.setPosY(563);
                    if (this.gameObject2.getObjectType() != bomb.DEADLY && this.gameObject2.getObjectType() != bomb.NORM) {
                        flag1[2] = true;
                    } else this.gameObject2.setPosY(563);
                }
                actions.updateHalf((Fruit) go);
                factory.drawHalf((Fruit) go);
            }
            if (go.getObjectType() == fruit.LIFEFRUIT) {
                if (!flag2[i]) {
                    flag2[i] = true;
                    if (lives < 3) {
                        lives++;
                    }
                }
                actions.updateHalf((Fruit) go);
                factory.drawHalf((Fruit) go);
            }
        }
    }

    public GameObject checkEnd(GameObject go, int i) throws ParserConfigurationException, IOException, SAXException {
        if (go.getPosY() > 562) {
            if (!flag1[i] && (go.getObjectType() == fruit.APPLE || go.getObjectType() == fruit.BANANA
                    || go.getObjectType() == fruit.MELON || go.getObjectType() == fruit.ORANGE))
            {
                lives--;
                factory.beepSound();
            }
            flag[i] = false;
            go = actions.createGameObject(factory.getLevel());
            flag1[i] = false;
            flag2[i] = false;

        }
        return go;
    }

    public void initGame() throws ParserConfigurationException, IOException, SAXException {
        factory.loadScore(originator, level, this);
        this.gameObject2 = actions.createGameObject(factory.getLevel());
        this.gameObject1 = actions.createGameObject(factory.getLevel());
        this.gameObject = actions.createGameObject(factory.getLevel());
        factory.setSeconds(0);
        score = 0;
        lives = 3;
        pause = false;
        complete.removeRecs();
        for (int i = 0; i < 3; i++) {
            this.flag[i] = false;
            this.flag1[i] = false;
            this.flag2[i] = false;
        }
    }

    @Override
    public int getScore() {
        return score;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public void setHighScore(int highscore) {
        this.highScore = highscore;
    }

    @Override
    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
