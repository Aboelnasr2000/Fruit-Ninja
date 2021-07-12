package Fruitninja;

import DesignPatterns.CareTaker;
import DesignPatterns.Originator;
import Commands.Command;
import Commands.Invoker;
import Commands.LoadCommand;
import Commands.SaveCommand;
import Fruits.Fruit;
import javafx.animation.AnimationTimer;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import Levels.levelArcade;
import Levels.levelEasy;
import Levels.levelHard;
import Levels.levelMedium;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class Factory {


    private final Invoker control = new Invoker();
    private final String themeString, sliceString, bombString, nuclearString, lifelostString, congratsString, loserString;
    private final MediaPlayer player;
    private MediaPlayer mediaPlayer;
    private final GraphicsContext graphicsContext;
    private final Scene scene;
    private final Menu menu;
    private final Warnning warnning;
    private int Level;
    private final levelEasy easy;
    private final levelMedium medium;
    private final levelHard hard;
    private final levelArcade am;
    private boolean first;
    private Timer timer;
    private int seconds;
    private final Complete complete;
    private final Background background;
    private Boolean flag;
    TimerTask task = new TimerTask() {

        @Override
        public void run() {

            if (Level == 4) {
                seconds--;
            } else {
                seconds++;
            }
        }
    };

    public Factory(GraphicsContext graphicsContext, Scene scene) throws ParserConfigurationException, SAXException, IOException {

        this.Level = 0;
        this.themeString = new File("theme.mp3").toURI().toString();
        this.sliceString = new File("Slice.mp3").toURI().toString();
        this.nuclearString = new File("Nuclear.mp3").toURI().toString();
        this.bombString = new File("Regular.mp3").toURI().toString();
        this.lifelostString = new File("beep.mp3").toURI().toString();
        this.congratsString = new File("Win.mp3").toURI().toString();
        this.loserString = new File("Lose.wav").toURI().toString();
        this.player = new MediaPlayer(new Media(themeString));
        this.graphicsContext = graphicsContext;
        this.scene = scene;
        this.menu = new Menu(this, graphicsContext, scene);
        this.complete = new Complete();
        this.warnning = new Warnning();
        easy = new levelEasy(this, scene);
        medium = new levelMedium(this, scene);
        hard = new levelHard(this, scene);
        am = new levelArcade(this, scene);
        background = Background.getInstance();
    }
    GraphicsEnvironment Commando = GraphicsEnvironment.getLocalGraphicsEnvironment();



    public void saveScore(CareTaker careTaker, String level, int score) {
        Command saveC = new SaveCommand(careTaker, score, level);
        control.setCommand(saveC);
        control.action();
    }

    public void loadScore(Originator originator, String level, Level l) {
        Command loadC = new LoadCommand(originator, level, l);
        control.setCommand(loadC);
        control.action();
    }

    public void shadow(double x, double y, Level level) {
        graphicsContext.fillOval(x, y, 8, 8);
        level.setXY(-10, -10);
        graphicsContext.setFill(Color.BLACK);
    }

    public void time() {
        timer = new Timer();
        timer.schedule(task, 900, 900);
    }

    public void showTime() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText("Time : " + seconds, 70, 45);
    }

    public void media() {
        player.setCycleCount(Level);
        player.play();
    }

    public void redSound() {
        this.mediaPlayer = new MediaPlayer(new Media(nuclearString));
        mediaPlayer.play();
    }

    public void loseSound() {
        this.mediaPlayer = new MediaPlayer(new Media(loserString));
        mediaPlayer.play();
    }

    public void winSound() {
        this.mediaPlayer = new MediaPlayer(new Media(congratsString));
        mediaPlayer.play();
    }

    public void beepSound() {
        this.mediaPlayer = new MediaPlayer(new Media(lifelostString));
        mediaPlayer.play();
    }

    public void bombSound() {
        this.mediaPlayer = new MediaPlayer(new Media(bombString));
        mediaPlayer.play();
    }

    public void swordSound() {
        this.mediaPlayer = new MediaPlayer(new Media(sliceString));
        mediaPlayer.play();
    }

    public void Start() {
        media();
        time();
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (Level == 0) {
                    menu.setRecs();
                    drawMenu();
                } else if (Level == 1) {
                    menu.removeRecs();
                    if (!first) {
                        try {
                            flag = false;
                            first = true;
                            easy.initGame();
                        } catch (ParserConfigurationException | IOException | SAXException ex) {
                            Logger.getLogger(Factory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                    }
                    gotoLevel(easy);
                } else if (Level == 2) {
                    menu.removeRecs();
                    if (!first) {
                        try {
                            flag = false;
                            medium.initGame();
                        } catch (ParserConfigurationException | SAXException | IOException ex) {
                            Logger.getLogger(Factory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                        first = true;
                    }
                    gotoLevel(medium);
                } else if (Level == 3) {
                    menu.removeRecs();
                    if (!first) {
                        try {
                            hard.initGame();
                            flag = false;
                        } catch (ParserConfigurationException | SAXException | IOException ex) {
                            Logger.getLogger(Factory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                        first = true;
                    }
                    gotoLevel(hard);
                } else if (Level == 4) {
                    menu.removeRecs();
                    if (!first) {
                        am.initGame();
                        flag = false;
                        first = true;
                    }
                    gotoLevel(am);
                }
            }
        }.start();
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public void gotoLevel(Level level) {
        level.manageLevel();
    }

    public void clearCanvas() {
        graphicsContext.clearRect(0, 0, 1000, 562);
    }

    public void drawObject(GameObject object) {
        graphicsContext.drawImage(object.getImage(), object.getPosX(), object.getPosY());
    }

    public void drawHalf(Fruit object) {
        graphicsContext.drawImage(object.getImage1(), object.getHalfX1(), object.getPosY());
        graphicsContext.drawImage(object.getImage2(), object.getHalfX2(), object.getPosY());
    }

    public void drawRx(int x, int y) {
        graphicsContext.drawImage(warnning.getRImage(), x, y);
    }

    public void drawGx(int x, int y) {
        graphicsContext.drawImage(warnning.getGImage(), x, y);
    }

    public int setScore(Fruit go, int score) {
        score += go.getPoints();
        return score;
    }
    public void showPause() {
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setTextBaseline(VPos.CENTER);
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(Font.font("commando"));
        graphicsContext.setFont(Font.font(30));
        graphicsContext.fillText("Pause", 500, 140);
    }

    public void showScore(int score) {
        graphicsContext.setFont(Font.font("commando"));
        graphicsContext.setFont(Font.font(30));
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText("Score : " + score, 70, 15);
    }

    public void showHighScore(int score) {
        graphicsContext.setFont(Font.font("commando"));
        graphicsContext.setFont(Font.font(30));
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText("High score : " + score, 300, 15);
    }

    public void drawPause() {
        graphicsContext.drawImage(complete.getWImage(), 300, 125);
        graphicsContext.drawImage(complete.getRImage(), 610, 315);
        graphicsContext.drawImage(complete.getBImage(), 325, 315);
        graphicsContext.drawImage(complete.getSImage(), 460, 300);
    }

    public void drawP() {
        graphicsContext.drawImage(complete.getPImage(), 30, 70);
    }

    public void drawW(Level level, int score, int highscore) {
        graphicsContext.drawImage(complete.getWImage(), 300, 125);
        graphicsContext.drawImage(complete.getRImage(), 610, 315);
        graphicsContext.drawImage(complete.getBImage(), 325, 315);
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setTextBaseline(VPos.CENTER);
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillText("Game Over", 500, 180);
        graphicsContext.fillText("Score :" + level.getScore(), 505, 220);
        graphicsContext.fillText("Time :" + level.getTime() + " Seconds", 505, 260);
        if (score > highscore) {
            graphicsContext.fillText("New Highscore!", 500, 295);
            if (flag == false) {
                winSound();
                flag = true;
            }
        } else {
            if (flag == false) {
                flag = true;
                loseSound();
            }
        }
    }

    public void options() {
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setTextBaseline(VPos.CENTER);
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(Font.font(35));
        graphicsContext.fillText("Easy", 200, 170);
        graphicsContext.fillText("Medium", 200, 270);
        graphicsContext.fillText("Hard", 200, 370);
        graphicsContext.fillText("Arcade", 200, 470);
    }


    public void Title()
    {
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setTextBaseline(VPos.CENTER);
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setFont(Font.font(50));
        graphicsContext.fillText("FRUIT NINJA",610,100);
        graphicsContext.drawImage(complete.getNinja(), 460, 150);
    }


    public void drawMenu() {
        menu.Draw();
    }

    public void drawBackGround() {
        background.drawBackground(graphicsContext);
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        this.Level = level;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }

}
