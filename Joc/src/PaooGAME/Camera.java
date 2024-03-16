package PaooGAME;
import PaooGAME.Items.Hero;

import static PaooGAME.Game.GAME_WIDTH;
import static PaooGAME.Maps.Map1.MAP_WIDTH;
import static PaooGAME.Tiles.Tile.TILES_IN_WIDTH;

// eroul si camera se muta amandoua in raport cu lumea , scopul este ca o sectiunea mai mica din harta sa aiba eroul in centr
// nu voi desena totul cu un update in raport cu camera , void desenea eroul si harta direct gandita cu un  offset pt camera , din pozitia in care desenez eroul scad offsetul
// cand fac asta eroul este la coordonata 85 , dar pare ca este la 80 , pt ca am scazut offestul 5 , adica cu 5 poxeli a fost muatata camera spre dreapta

public class Camera {

    //Game_Width = 1248;
  public static int xCameraOffset; // cu cat trebuie sa mut camera in stanga sau in dreapta
  public static final int centerPosX =1248/2; // aleg centrul ferestrei coordonata camerei
     public static final int maxCameraOffsetX =MAP_WIDTH-GAME_WIDTH; //pixeli   scad dim unui game_wnd pt cu cat se poate deplasa maxim

    public static void updateCamera(Hero h){
      // harta mea are 3* Game_Width(ceea ce este desenat pe ecran ,dimens camerei) =1248 pixeli * 3

        // daca trec de centrul ferestrei in partea drepata adaug (desenez cu cat trec in partea drt) adica mut camera spre dreapta
        // daca trec de centrul ferestrei in partea stanga adaug (desenez cu cat trec in partea stanga) adica mut camera spre stanga

        int playerX=h.getHitbox().x; // coordonata erou harta

        xCameraOffset=playerX-centerPosX; // cu cat se deplaseaza fata de pozitia de pe centru
        if(xCameraOffset > maxCameraOffsetX) {

          xCameraOffset = maxCameraOffsetX;
      }
      else if(xCameraOffset <0) { // minCameraOffsetX
          xCameraOffset = 0;
      }
  }


}

//cand jucatorul ajunge destul de aproape de marginea din dreapta ex 80 % din game width
// cand eroul trece de a marginea din dreapta, pixelii dincolo de linie = cantitatea de pixeli de care avem nevoie pt a muta nivelul
// dacamaximul este 80 in dreapta si eu trec cu 5 pixeli ccoord =85 , atunci adaug 5 pix in stg
// calculez offsetul si desenez x- Offset
// astfel eroul pare la coordonata 80 , dar el este la 85