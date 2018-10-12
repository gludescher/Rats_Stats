package polirats.ratstats;

import java.util.HashMap;

import static java.lang.Math.abs;
import static polirats.ratstats.JogadaAtaque.nJogadas;
import static polirats.ratstats.MainActivity.ataqueEmCampo;
import static polirats.ratstats.NovaPartida.adversario;
import static polirats.ratstats.NovaPartida.campeonato;
import static polirats.ratstats.NovaPartida.dataPartida;
import static polirats.ratstats.NovaPartida.idPartida;

public class GameSituation {

    static HashMap<String, Integer> gameSituation;
    public static void gameStart (){
        gameSituation = new HashMap<String, Integer>();
        gameSituation.put("down", 1);
        gameSituation.put("distance", 10);
        gameSituation.put("scrim", -20);
        gameSituation.put("half", 1);
        gameSituation.put("rats", 0);
        gameSituation.put("adv", 0);
        gameSituation.put("drive", 0);
    }

    public static void updateDownDistance(Integer jds){
        Integer newDist = gameSituation.get("distance") - jds;
        Integer newDown = gameSituation.get("down") + 1;

        gameSituation.put("scrim", gameSituation.get("scrim") + jds);
        if (newDist > 0) {
            gameSituation.put("distance", newDist);
            gameSituation.put("down", newDown);
        }
        else {
            gameSituation.put("distance", 10);
            gameSituation.put("down", 1);
        }
    }

    public static void updateScore (int pontuacao, String equipe) {
        gameSituation.put(equipe, gameSituation.get(equipe) + pontuacao);
        if (equipe.equals("rats") && pontuacao == 6) {
            gameSituation.put("down", 4);
            gameSituation.put("distance", 3);
            gameSituation.put("scrim", 27);
        }
    }

    public static void nextDrive (int scrimmage, boolean ataque){
        gameSituation.put("drive", gameSituation.get("drive") + 1);
        gameSituation.put("down", 1);
        gameSituation.put("distance", 10);

        setScrimmage(scrimmage, ataque);
    }

    public static String nextHalf() {
        gameSituation.put("half", gameSituation.get("half")+1);

        if (gameSituation.get("half") == 2)
            return "Fim do 1º tempo.";
        else
            return "Fim de jogo.";
    }

    public static String printDown() {
        switch (gameSituation.get("down")) {
            case 1:
                return "1st";
            case 2:
                return "2nd";
            case 3:
                return "3rd";
            case 4:
                return "4th";
            default:
                return "ERRO_DOWN";
        }
    }

    public static void updateFalta (int jdsFalta) {
        Integer newDist = gameSituation.get("distance") - jdsFalta;
        gameSituation.put("scrim", gameSituation.get("scrim") + jdsFalta);

        if (newDist > 0)
            gameSituation.put("distance", newDist);
        else {
            gameSituation.put("distance", 10);
            gameSituation.put("down", 1);
        }
    }

    public static String printDistance() {
        return gameSituation.get("distance").toString();
    }
    public static String printScrim() {
        int pos = gameSituation.get("scrim");
        if (pos < 0)
            return Integer.toString(30+pos) + " (DEFESA)";
        else if (pos > 0)
            return Integer.toString(30-pos) + " (ATAQUE)";
        else
            return "30";
    }
    public static String printRatsScore() {
        return gameSituation.get("rats").toString();
    }
    public static String printAdvScore() {
        return gameSituation.get("adv").toString();
    }
    public static String printHalf () {
        switch (gameSituation.get("half")){
            case 1:
                return "1st";
            case 2:
                return "2nd";
            default:
                return "FIM DE JOGO";
        }
    }
    /*public static String getPlacar() {
        return "RATS " + gameSituation.get("rats") + "-" + gameSituation.get("adv") + " ADV";
    }*/

    public static String printGameSituation() {
        return (printDown() + " AND " + printDistance() + ", " +  printScrim() + "   " +
                "RATS " + printRatsScore() + "-" + printAdvScore() + " ADV" + "   " + printHalf() + " HALF");
    }

    /*public static Jogada putGameSituation(Jogada J) {
        J.setDistance(gameSituation.get("distance"));
        J.setDown(gameSituation.get("down"));
        J.setScrimmage(gameSituation.get("scrim"));
        return J;
    }*/
    public static int getDown() {
        return gameSituation.get("down");
    }

    public static int getDistance() {
        return gameSituation.get("distance");
    }

    public static int getScrim() {
        return gameSituation.get("scrim");
    }

    public static int getHalf() {
        return gameSituation.get("half");
    }

    public static int getRatsScore() {
        return gameSituation.get("rats");
    }

    public static int getAdvScore() {
        return gameSituation.get("adv");
    }

    public static int getDrive(){ return gameSituation.get("drive");}

    public static void setHalf(int half) { gameSituation.put("half", half); }

    public static void setPontRats(int pontRats) { gameSituation.put("rats", pontRats); }

    public static void setPontAdv(int pontAdv) { gameSituation.put("adv", pontAdv); }

    public static void setScrimmage(int scrimmage, boolean ataque) {
        if (ataque)
            gameSituation.put("scrim", 30 - scrimmage);
        else
            gameSituation.put("scrim", scrimmage - 30);
    }
    public static void setDown(int down) { gameSituation.put("down", down); }

    public static void setDistance(int distance) { gameSituation.put("distance", distance); }

    public static String writeGameSituation () {
        //idPartida, dataPartida, adversario, campeonato, nJogadas, drive, down, distance, scrimmage, pontRats, pontAdv, half
        return idPartida + "," + dataPartida + "," + adversario + "," + campeonato + "," + nJogadas + "," + gameSituation.get("drive") +
                "," + gameSituation.get("down") + "," + gameSituation.get("distance") + "," + gameSituation.get("scrim") +
                "," + gameSituation.get("rats") + "," + gameSituation.get("adv") + "," + gameSituation.get("half");
    }

    public static void setDrive (int drive) {gameSituation.put("drive",drive); }

    public static void setScrimmageBruto (int scrimmage) {gameSituation.put("scrim", scrimmage);}

    //2nd and 7, 27(atq), rats 24x8 bosta, 1st half
}
