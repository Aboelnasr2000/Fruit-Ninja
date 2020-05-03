package Levels;

import DesignPatterns.CareTaker;
import DesignPatterns.Momento;
import DesignPatterns.Originator;
import java.io.IOException;
import java.util.logging.Logger;

import Bombs.Bombs;
import Fruitninja.*;
import Fruits.Fruit;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class levelMedium implements Level {

    private final Factory factory;
    private final Originator originator;
    private final Momento momento;
    private final CareTaker careTaker;
    private final GameActions actions;
    private boolean[] flag;
    private boolean[] s;
    private boolean[] f;
    private GameObject go;
    private GameObject go1;
    private GameObject go2;
    private GameObject go3;
    private final Scene scene;
    private int score, lives, time;
    private final Complete complete;
    private int highScore;
    String level = "Medium";
    private double x, y;
    private boolean p = false;

    public levelMedium(Factory factory, Scene scene) throws ParserConfigurationException, SAXException, IOException {
        originator = new Originator();
        momento = new Momento();
        careTaker = new CareTaker();
        this.lives = 3;
        this.score = 0;
        this.flag = new boolean[4];
        this.s = new boolean[4];
        this.f = new boolean[4];
        this.actions = new Actions();
        this.factory = factory;
        this.scene = scene;
        this.complete = new Complete();
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
            factory.drawW(this, score, highScore);
            factory.saveScore(careTaker, level, score);
        }
        if (lives > 0 && !p) {
            factory.showTime();
            time = factory.getSeconds();
            objectMotion(go, 0);
            objectMotion(go1, 1);
            objectMotion(go2, 2);
            objectMotion(go3, 3);
            go = checkEnd(go, 0);
            go1 = checkEnd(go1, 1);
            go2 = checkEnd(go2, 2);
            go3 = checkEnd(go3, 3);
        }
        scene.setOnMouseDragged(
                (EventHandler<MouseEvent>) e -> {
                    x = e.getX();
                    y = e.getY();
                    if (go.getRec().contains(e.getX(), e.getY())) {
                        s[0] = true;
                    } else if (go1.getRec().contains(e.getX(), e.getY())) {
                        s[1] = true;
                    } else if (go2.getRec().contains(e.getX(), e.getY())) {
                        s[2] = true;
                    } else if (go3.getRec().contains(e.getX(), e.getY())) {
                        s[3] = true;
                    }
                });
        factory.shadow(x, y, this);
        scene.setOnMouseClicked(
                (EventHandler<MouseEvent>) e -> {

                    if (complete.getRec1().contains(e.getX(), e.getY())) {
                        p = false;
                        factory.setLevel(0);
                    } else if (complete.getRec2().contains(e.getX(), e.getY())) {
                        p = false;
                        factory.setLevel(2);
                        try {
                            initGame();
                        } catch (ParserConfigurationException | SAXException | IOException ex) {
                            Logger.getLogger(levelMedium.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        if (flag[i] == true || s[i] == true) {
            go.setDeltaY(go.getDownY());
        } else if (go.getPosY() <= 30) {
            flag[i] = true;
        }
        if (s[i] == false) {
            actions.updateObjectPlace(go);
            factory.drawObject(go);
        } else if (s[i] == true) {
            if (go.getObjectType() == Fruit.fruit.BANANA || go.getObjectType() == Fruit.fruit.MELON
                    || go.getObjectType() == Fruit.fruit.APPLE || go.getObjectType() == Fruit.fruit.SUPERFRUIT || go.getObjectType() == Fruit.fruit.ORANGE) {
                if (f[i] == false) {
                    factory.swordSound();
                    score = factory.setScore((Fruit) go, score);
                    f[i] = true;
                }
                actions.updateHalf((Fruit) go);
                factory.drawHalf((Fruit) go);
            } else if (go.getObjectType() == Bombs.bomb.NORM) {
                factory.bombSound();
                if (f[i] == false) {
                    lives--;
                    go.setPosY(563);
                    f[i] = true;
                }
                actions.updateObjectPlace(go);
            } else if (go.getObjectType() == Bombs.bomb.DEADLY) {
                factory.redSound();
                lives = 0;
            }
            if (go.getObjectType() == Fruit.fruit.MAGICFRUIT) {
                if (f[i] == false) {
                    f[i] = true;
                    if (this.go.getObjectType() != Bombs.bomb.DEADLY && this.go.getObjectType() != Bombs.bomb.NORM) {
                        s[0] = true;
                    } else this.go.setPosY(563);
                    if (this.go1.getObjectType() != Bombs.bomb.DEADLY && this.go1.getObjectType() != Bombs.bomb.NORM) {
                        s[1] = true;
                    }else this.go1.setPosY(563);
                    if (this.go2.getObjectType() != Bombs.bomb.DEADLY && this.go2.getObjectType() != Bombs.bomb.NORM) {
                        s[2] = true;
                    } else this.go2.setPosY(563);
                    if (this.go3.getObjectType() != Bombs.bomb.DEADLY && this.go3.getObjectType() != Bombs.bomb.NORM) {
                        s[3] = true;
                    } else this.go3.setPosY(563);
                }
                actions.updateHalf((Fruit) go);
                factory.drawHalf((Fruit) go);
            }
            if (go.getObjectType() == Fruit.fruit.LIFEFRUIT) {
                if (f[i] == false) {
                    f[i] = true;
                    if (lives < 3) {
                        lives++;
                    }
                }
                actions.updateHalf((Fruit) go);
                factory.drawHalf((Fruit) go);
            }
        }
    }

    public GameObject checkEnd(GameObject go, int i) {
        if (go.getPosY() > 562) {
            if (s[i] == false && (go.getObjectType() == Fruit.fruit.APPLE || go.getObjectType() == Fruit.fruit.BANANA
                    || go.getObjectType() == Fruit.fruit.MELON || go.getObjectType()==Fruit.fruit.ORANGE)) {
                lives--;
                factory.beepSound();
            }
            flag[i] = false;
            go = actions.createGameObject(factory.getLevel());
            s[i] = false;
            f[i] = false;
        }
        return go;
    }

    public void initGame() throws ParserConfigurationException, SAXException, IOException {
        factory.loadScore(originator, level, this);
        this.go3 = actions.createGameObject(factory.getLevel());
        this.go2 = actions.createGameObject(factory.getLevel());
        this.go1 = actions.createGameObject(factory.getLevel());
        this.go = actions.createGameObject(factory.getLevel());
        factory.setSeconds(0);
        score = 0;
        lives = 3;
        p = false;
        complete.removeRecs();
        for (int i = 0; i < 4; i++) {
            this.flag[i] = false;
            this.s[i] = false;
            this.f[i] = false;
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
