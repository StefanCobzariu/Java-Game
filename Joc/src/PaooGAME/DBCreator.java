package PaooGAME;

import PaooGAME.Items.Hero;

import java.sql.*;

import static PaooGAME.Items.Hero.xp;

// in baza de date salvez scorul actualizat dupa fecare nivel  si viata eroului dupa fiecare nivel -o folosesc sa desenez pe ecran scorul final la terminarea celor 3 niveluri
import static PaooGAME.Game.scorFinal;
public class DBCreator {
    public void BazaDate(Game game, Hero h){
        Connection c = null;
        Statement stmt = null;
        int x=h.GetCurrentLife();
        int scor=xp;
        //int level= game.currentMap+1;
        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:OBX.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS OBX " +
                    "(LIFE INT NOT NULL, " +
                    "SCOR INT NOT NULL)";
            stmt.executeUpdate(sql);
            System.out.println("Tabela 'OBX' a fost creata cu succes.");

            sql = "INSERT INTO OBX (LIFE, SCOR) VALUES (" + x + ", " + scor + ");";
            stmt.executeUpdate(sql);

            String selectDataSql = "SELECT SCOR FROM OBX";
            ResultSet rs = stmt.executeQuery(selectDataSql);

            while (rs.next()) {
                scorFinal = rs.getInt("SCOR");
            }

            rs.close();


            stmt.close();
            c.commit();
            c.close();
        } catch (ClassNotFoundException e) {
            System.err.println("Driverul sqlite JDBC nu a fost gasit.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Eroare la conectare sau la creare.");
            e.printStackTrace();
        }
}
}





