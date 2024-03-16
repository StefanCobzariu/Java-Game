package PaooGAME.Inputs;
import PaooGAME.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import PaooGAME.Graphics.LoadAnim;

//Inputs = interfata
// keyboard trb sa implementeze keyTyped,keyReleased,keyPreessed;
// imi genereaza evenimentul

public class Keyboard implements KeyListener {
    public static boolean up = false, down = false, left = false, right = false, attacking = false,jump=false;;   // ii fac statici ca sa apartina clasei nu obiectului , import pachet ,clasa si pot folosi in orice clasa
    private GamePanel gamePanel;

    public Keyboard(GamePanel gamePanel) {
        this.gamePanel = gamePanel;    // astefel cand apsam o tasta sunt modificate valori si in GAmePAnel
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int codek = e.getKeyCode();
        if (codek == KeyEvent.VK_W) {
            //gamePanel.getGame().getHero().setUp(true);
            up = true;
        } else if (codek == KeyEvent.VK_A) {
            //gamePanel.getGame().getHero().setLeft(true);
            left = true;
        } else if (codek == KeyEvent.VK_S) {
            //    gamePanel.getGame().getHero().setDown(true);
            down = true;
        } else if (codek == KeyEvent.VK_D) {
            //gamePanel.getGame().getHero().setRight(true);
          //  System.out.println("Apasat tasta d");
            right = true;
        }else if (codek == KeyEvent.VK_SPACE) {
           // System.out.println("space");
            jump = true;
        }
        else if (codek == KeyEvent.VK_P) {
            //gamePanel.getGame().getHero().setRight(true);
           // System.out.println("p");
            attacking = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int codek = e.getKeyCode();
        if (codek == KeyEvent.VK_W) {
            //gamePanel.getGame().getHero().setUp(false);
            up = false;
        } else if (codek == KeyEvent.VK_A) {
            //gamePanel.getGame().getHero().setLeft(false);
            left = false;
        } else if (codek == KeyEvent.VK_S) {
            //gamePanel.getGame().getHero().setDown(false);
            down = false;
        } else if (codek == KeyEvent.VK_D) {
            //gamePanel.getGame().getHero().setRight(false);
            right = false;
        } else if (codek == KeyEvent.VK_P) {
            attacking = false;
        } else if (codek == KeyEvent.VK_SPACE) {
            jump= false;
    }
    }
}
