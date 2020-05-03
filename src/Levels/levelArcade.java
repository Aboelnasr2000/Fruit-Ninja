package Levels;

import java.io.IOException;

import Bombs.Bombs;
import Fruitninja.*;
import Fruits.Fruit;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class levelArcade implements Level {

//    private final Originator originator;
//    private final Momento momento;
//    private final CareTaker careTaker;
    private GameObject gameObject;
    private GameObject gameObject1;
    private GameObject gameObject2;
    private GameObject gameObject3;
    private GameObject gameObject4;
    private GameObject gameObject5;
    private int highscore;
    private final Factory factory;
    private final GameActions actions;
    private boolean[] flag;
    private boolean[] flag1;
    private boolean[] flag2;
    private final Scene scene;
    private final Complete complete;
    private int score, time;
    String level = "Arcade";
    private double x, y;
    private boolean p = false;

    public levelArcade(Factory factory, Scene scene) throws ParserConfigurationException, SAXException, IOException {
//        originator = new Originator();
//        momento = new Momento();
//        careTaker = new CareTaker();
        this.time = 60;
        this.score = 0;
        this.flag = new boolean[6];
        this.flag1 = new boolean[6];
        this.flag2 = new boolean[6];
        this.actions = new Actions();
        this.factory = factory;
        this.scene = scene;
        this.complete = new Complete();
//        this.factory.loadScore(this.originator, this.level, this);
    }

    @Override
    public void manageLevel() {
        factory.clearCanvas();
        factory.drawBackGround();
        factory.showScore(score);
        factory.drawP();
        //factory.showHighScore(highscore);
        if (p) {
            factory.drawPause();
            factory.showPause();
            complete.setRec1();
            complete.setRec2();
        }
        if (time == 0) {
            //  momento.setHighscore(score);
            complete.setRec1();
            complete.setRec2();
            factory.drawW(this, score, highscore);
            //       factory.saveScore(careTaker, level, score);
        }
        if (time > 0 && !p) {
            factory.showTime();
            time = factory.getSeconds();
            objectMotion(gameObject, 0);
            objectMotion(gameObject1, 1);
            objectMotion(gameObject2, 2);
            objectMotion(gameObject3, 3);
            objectMotion(gameObject4, 4);
            objectMotion(gameObject5, 5);
            gameObject = checkEnd(gameObject, 0);
            gameObject1 = checkEnd(gameObject1, 1);
            gameObject2 = checkEnd(gameObject2, 2);
            gameObject3 = checkEnd(gameObject3, 3);
            gameObject4 = checkEnd(gameObject4, 4);
            gameObject5 = checkEnd(gameObject5, 5);
        }
        scene.setOnMouseDragged(
                (EventHandler<MouseEvent>) e -> {
                    x = e.getX();
                    y = e.getY();
                    if (gameObject.getRec().contains(e.getX(), e.getY())) {
                        flag1[0] = true;
                    } else if (gameObject1.getRec().contains(e.getX(), e.getY())) {
                        flag1[1] = true;
                    } else if (gameObject2.getRec().contains(e.getX(), e.getY())) {
                        flag1[2] = true;
                    } else if (gameObject3.getRec().contains(e.getX(), e.getY())) {
                        flag1[3] = true;
                    } else if (gameObject4.getRec().contains(e.getX(), e.getY())) {
                        flag1[4] = true;
                    } else if (gameObject5.getRec().contains(e.getX(), e.getY())) {
                        flag1[5] = true;
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
                        factory.setLevel(4);
                        initGame();
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
            if (go.getObjectType() != Bombs.bomb.DEADLY && go.getObjectType() != Bombs.bomb.NORM && go.getObjectType() != Fruit.fruit.MAGICFRUIT) {
                if (flag2[i] == false) {
                    factory.swordSound();
                    score = factory.setScore((Fruit) go, score);
                    flag2[i] = true;
                }
                actions.updateHalf((Fruit) go);
                factory.drawHalf((Fruit) go);
            }
            if (go.getObjectType() == Bombs.bomb.NORM) {
                if (flag2[i] == false) {
                    factory.bombSound();
                    go.setPosY(563);
                    flag2[i] = true;
                }
                actions.updateObjectPlace(go);
            }
            if (go.getObjectType() == Bombs.bomb.DEADLY) {
                factory.redSound();
            }
            if (go.getObjectType() == Fruit.fruit.MAGICFRUIT) {
                if (flag2[i] == false) {
                    flag2[i] = true;
                    if (this.gameObject.getObjectType() != Bombs.bomb.DEADLY && this.gameObject.getObjectType() != Bombs.bomb.NORM) {
                        flag1[0] = true;
                    }
                    if (this.gameObject1.getObjectType() != Bombs.bomb.DEADLY && this.gameObject1.getObjectType() != Bombs.bomb.NORM) {
                        flag1[1] = true;
                    }
                    if (this.gameObject2.getObjectType() != Bombs.bomb.DEADLY && this.gameObject2.getObjectType() != Bombs.bomb.NORM) {
                        flag1[2] = true;
                    }
                    if (this.gameObject3.getObjectType() != Bombs.bomb.DEADLY && this.gameObject3.getObjectType() != Bombs.bomb.NORM) {
                        flag1[3] = true;
                    }
                    if (this.gameObject4.getObjectType() != Bombs.bomb.DEADLY && this.gameObject4.getObjectType() != Bombs.bomb.NORM) {
                        flag1[4] = true;
                    }
                    if (this.gameObject5.getObjectType() != Bombs.bomb.DEADLY && this.gameObject5.getObjectType() != Bombs.bomb.NORM) {
                        flag1[5] = true;
                    }
                }
                actions.updateHalf((Fruit) go);
                factory.drawHalf((Fruit) go);
            }
        }
    }

    public GameObject checkEnd(GameObject go, int i) {
        if (go.getPosY() > 562) {
            if (flag1[i] == false && (go.getObjectType() == Fruit.fruit.APPLE || go.getObjectType() == Fruit.fruit.BANANA
                    || go.getObjectType() == Fruit.fruit.MELON || go.getObjectType() == Fruit.fruit.ORANGE)) {
            }
            flag[i] = false;
            go = actions.createGameObject(factory.getLevel());
            flag1[i] = false;
            flag2[i] = false;
        }
        return go;
    }

    public void initGame() {
        //factory.loadScore(originator, level, this);
        this.gameObject5 = actions.createGameObject(factory.getLevel());
        this.gameObject4 = actions.createGameObject(factory.getLevel());
        this.gameObject3 = actions.createGameObject(factory.getLevel());
        this.gameObject2 = actions.createGameObject(factory.getLevel());
        this.gameObject1 = actions.createGameObject(factory.getLevel());
        this.gameObject = actions.createGameObject(factory.getLevel());
        factory.setSeconds(60);
        score = 0;
        time = 60;
        p = false;
        complete.removeRecs();
        for (int i = 0; i < 6; i++) {
            this.flag[i] = false;
            this.flag1[i] = false;
            this.flag2[i] = false;
        }
    }

    @Override
    public int getScore() {
        return score;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public GameObject getGameObject1() {
        return gameObject1;
    }

    public GameObject getGameObject2() {
        return gameObject2;
    }

    public GameObject getGameObject3() {
        return gameObject3;
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