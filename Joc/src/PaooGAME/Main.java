package PaooGAME;
import PaooGAME.GameWindow.*;
public class  Main {

    public static void main(String[] args)
    {
       Game r=  Game.GetSingleton();
       r.startGameLoop();
    }
}
