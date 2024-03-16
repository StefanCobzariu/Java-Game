package PaooGAME.GameWindow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import PaooGAME.GamePanel;

public class GameWindow {
    private JFrame jframe; //Membrul wndFrame este un obiect de tip JFrame care va avea utilitatea unei ferestre grafice si totodata si cea a unui container (toate elementele
    //grafice vor fi continute de fereastra).
   // private int wndWidth;       /*!< latimea ferestrei in pixeli*/
   // private int wndHeight;      /*!< inaltimea ferestrei in pixeli*/


    public GameWindow(GamePanel Gp) {
        jframe = new JFrame();

        // Implicit o fereastra cand este creata nu este vizibila motiv pentru care trebuie setata aceasta proprietate
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Operatia de inchidere (fereastra sa poata fi inchisa atunci cand este apasat butonul x din dreapta sus al ferestrei). Totodata acest lucru garanteaza ca nu doar fereastra va fi inchisa ci intregul program
        jframe.add(Gp); // Game Panel si gameW sunt separate de asta trebuie adaugat
        //jframe.setVisible(true);
        jframe.setTitle("PAO");
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }
            @Override
            public void windowLostFocus(WindowEvent e) {
                Gp.getGame().windowFocusLost();
            }
        });// modalitatea prin care se opresc actiunile daca pierdem focus-ul
    }
}

