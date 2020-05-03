package Fruitninja;

import Bombs.Bombs;
import Bombs.Nuclear;
import Bombs.Bomb;


import Fruits.*;

import java.util.Random;

public class Actions implements GameActions {

    float deltaX, deltaY;

    @Override
    public GameObject createGameObject(int x) {
        Random r = new Random();
        int i;
        if (x == 4) {
            i = (int) (1 + r.nextDouble() * 16);
        } else {
            i = (int) (1 + r.nextDouble() * 22);
        }
        if (i <= 4) {
            fruitOrange fruitOrange = new fruitOrange();
            return  fruitOrange;
        } else if (i <= 6) {
            Fruit banana = new fruitBanana();
            return banana;
        }else if (i <= 8){
            Fruit melon = new fruitMelon();
            return melon;
        } else if (i <= 10) {
            Fruit apple = new fruitApple();
            return apple;
        } else if (i <= 12) {
            fruitSpecial1 superFruit1 = new fruitSpecial1();
            return superFruit1;
        }else if (i <= 14) {
            fruitSpecial2 superFruit2 = new fruitSpecial2();
            return superFruit2;
        } else if (i <= 16) {
            fruitMagic magicFruit = new fruitMagic();
            return magicFruit;
        } else if (i <= 18) {
            Bombs nuclear = new Nuclear();
            return nuclear;
        } else if (i <= 20) {
            Bombs bomb = new Bomb();
            return bomb;
        } else if (i <= 22) {
            fruitLife lifeFruit = new fruitLife();
            return lifeFruit;
        } else {
            return null;
        }
    }

    @Override
    public void updateObjectPlace(GameObject go) {
        go.setPosX(go.getDeltaX());
        go.setPosY(go.getDeltaY());
        go.setRec();
    }

    @Override
    public void updateHalf(Fruit go) {
        go.setPosY(go.getDeltaY());
        go.setHalfX1(-go.getDeltaX());
        go.setHalfX2(go.getDeltaX());
    }



    @Override
    public void setDeltaX(float deltaX) {
        this.deltaX = deltaX;
    }
    @Override
    public void setDeltaY(float deltaY) {
        this.deltaY = deltaY;
    }
    @Override
    public void saveGame() {}
    @Override
    public void loadGame() {}
    @Override
    public void ResetGame() {}
}
