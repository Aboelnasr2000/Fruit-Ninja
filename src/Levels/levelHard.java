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

public class levelHard implements Level {

    private final String level = "Hard";
    private final Factory factory;
    private final GameActions actions;
    private boolean[] flag;
    private boolean[] flag1;
    private boolean[] flag2;
    private GameObject go;
    private GameObject go1;
    private GameObject go2;
    private GameObject go3;
    private GameObject go4;
    private final Scene scene;
    private final Complete complete;
    private int score, lives, time;
    private final Originator originator;
    private final Momento momento;
    private final CareTaker careTaker;
    private int highscore;
    private double x, y;
    private boolean p = false;

    public levelHard(Factory factory, Scene scene) throws ParserConfigurationException, SAXException, IOException {
        originator = new Originator();
        momento = new Momento();
        careTaker = new CareTaker();
        this.lives = 3;
        this.score = 0;
        this.flag = new boolean[5];
        this.flag1 = new boolean[5];
        this.flag2 = new boolean[5];
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
        factory.showHighScore(highscore);
        factory.drawP();
        factory.drawGx(780, 25);
        factory.drawGx(860, 25);
        factory.drawGx(940, 25);
        if (p) {
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
            factory.drawW(this, score, highscore);
            factory.saveScore(careTaker, level, score);
        }
        if (lives > 0 && !p) {
            try {
                factory.showTime();
                time = factory.getSeconds();
                objectMotion(go, 0);
                objectMotion(go1, 1);
                objectMotion(go2, 2);
                objectMotion(go3, 3);
                objectMotion(go4, 4);
                go = checkEnd(go, 0);
                go1 = checkEnd(go1, 1);
                go2 = checkEnd(go2, 2);
                go3 = checkEnd(go3, 3);
                go4 = checkEnd(go4, 4);
            } catch (ParserConfigurationException | IOException | SAXException ex) {
                Logger.getLogger(levelEasy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        scene.setOnMouseDragged(
                (EventHandler<MouseEvent>) e -> {
                    x = e.getX();
                    y = e.getY();
                    if (go.getRec().contains(e.getX(), e.getY())) {
                        flag1[0] = true;
                    }
                    if (go1.getRec().contains(e.getX(), e.getY())) {
                        flag1[1] = true;
                    }
                    if (go2.getRec().contains(e.getX(), e.getY())) {
                        flag1[2] = true;
                    }
                    if (go3.getRec().contains(e.getX(), e.getY())) {
                        flag1[3] = true;
                    }
                    if (go4.getRec().contains(e.getX(), e.getY())) {
                        flag1[4] = true;
                    }
                });
        factory.shadow(x, y, this);
        scene.setOnMouseClicked(
                (EventHandler<MouseEvent>) e -> {
                    if (complete.getRec1().contains(e.getX(), e.getY())) {
                        factory.setLevel(0);
                        p = false;
                    } else if (complete.getRec2().contains(e.getX(), e.getY())) {
                        factory.setLevel(3);
                        p = false;
                        try {
                            initGame();
                        } catch (ParserConfigurationException | IOException | SAXException ex) {
                            Logger.getLogger(levelEasy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                    } else if (complete.getRec3().contains(e.getX(), e.getY())) {
                        p = true;
                    } else if (complete.getRec4().contains(e.getX(), e.getY())) {
                        complete.removeRecs();
                        factory.setSeconds(time);
                        p = false;
                    }
                });

    }

    public void objectMotion(GameObject go, int i) {
        if (flag[i] == true || flag1[i] == true) {
            go.setDeltaY(go.getDownY());
        } else if (go.getPosY() <= 30) {
            flag[i] = true;
        }
        if (flag1[i] == false) {
            actions.updateObjectPlace(go);
            factory.drawObject(go);
        } else if (flag1[i] == true) {
            if (go.getObjectType() == fruit.BANANA || go.getObjectType() == fruit.MELON
                    || go.getObjectType() == fruit.APPLE || go.getObjectType() == fruit.SUPERFRUIT || go.getObjectType()==fruit.ORANGE) {
                if (flag2[i] == false) {
                    factory.swordSound();
                    score = factory.setScore((Fruit) go, score);
                    flag2[i] = true;
                }
                actions.updateHalf((Fruit) go);
                factory.drawHalf((Fruit) go);
            }
            if (go.getObjectType() == bomb.NORM) {
                if (flag2[i] == false) {
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
                if (flag2[i] == false) {
                    flag2[i] = true;
                    if (this.go.getObjectType() != bomb.DEADLY && this.go.getObjectType() != bomb.NORM) {
                        flag1[0] = true;
                    } else this.go.setPosY(563);
                    if (this.go1.getObjectType() != bomb.DEADLY && this.go1.getObjectType() != bomb.NORM) {
                        flag1[1] = true;
                    } else this.go1.setPosY(563);
                    if (this.go2.getObjectType() != bomb.DEADLY && this.go2.getObjectType() != bomb.NORM) {
                        flag1[2] = true;
                    } else this.go2.setPosY(563);
                    if (this.go3.getObjectType() != bomb.DEADLY && this.go3.getObjectType() != bomb.NORM) {
                        flag1[3] = true;
                    } else this.go3.setPosY(563);
                    if (this.go4.getObjectType() != bomb.DEADLY && this.go4.getObjectType() != bomb.NORM) {
                        flag1[4] = true;
                    } else this.go4.setPosY(563);
                }
                actions.updateHalf((Fruit) go);
                factory.drawHalf((Fruit) go);
            }
            if (go.getObjectType() == fruit.LIFEFRUIT) {
                if (flag2[i] == false) {
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
            if (flag1[i] == false && (go.getObjectType() == fruit.APPLE || go.getObjectType() == fruit.BANANA
                    || go.getObjectType() == fruit.MELON || go.getObjectType()==fruit.ORANGE)) {
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
        this.go4 = actions.createGameObject(factory.getLevel());
        this.go3 = actions.createGameObject(factory.getLevel());
        this.go2 = actions.createGameObject(factory.getLevel());
        this.go1 = actions.createGameObject(factory.getLevel());
        this.go = actions.createGameObject(factory.getLevel());
        factory.setSeconds(0);
        score = 0;
        lives = 3;
        p = false;
        complete.removeRecs();
        for (int i = 0; i < 5; i++) {
            this.flag[i] = false;
            this.flag1[i] = false;
            this.flag2[i] = false;
        }
    }

    @Override
    public int getScore() {
        return score;
    }

    public GameObject getGo() {
        return go;
    }

    public GameObject getGo1() {
        return go1;
    }

    public GameObject getGo2() {
        return go2;
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
        this.highscore = highscore;
    }

    @Override
    public void setXY(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
