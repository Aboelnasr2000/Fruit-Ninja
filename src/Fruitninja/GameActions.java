package Fruitninja;

import Fruits.Fruit;

public interface GameActions {
    public GameObject createGameObject(int x);
    public void updateObjectPlace(GameObject go);
    public void updateHalf(Fruit go);
    public void setDeltaX(float x);
    public void setDeltaY(float y);
    public void saveGame();
    public void loadGame();
    public void ResetGame();
}
