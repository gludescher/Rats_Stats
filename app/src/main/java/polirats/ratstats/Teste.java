package polirats.ratstats;

import static polirats.ratstats.MainActivity.ataqueEmCampo;
import static polirats.ratstats.MainActivity.defesaEmCampo;
import static polirats.ratstats.MainActivity.mapJogador;

public class Teste {

    public static void CriaJogadores(int n, String posicao){
        int i = 0;
        for (i = 0; i < n; i++){
            String nome = posicao + Integer.toString(i+1);
            Jogador J =  new Jogador (nome, nome+"A", Integer.toString(i), "ataque", posicao, 2018);
        }
    }

    public static void colocaAtaqueEmCampo (){
        ataqueEmCampo[0] = mapJogador.get("Araça");
        ataqueEmCampo[1] = mapJogador.get("Henna");
        ataqueEmCampo[6] = mapJogador.get("Seles");
        ataqueEmCampo[7] = mapJogador.get("Caravita");

        ataqueEmCampo[2] = mapJogador.get("Emoji");
        ataqueEmCampo[3] = mapJogador.get("Porra");
        ataqueEmCampo[4] = mapJogador.get("Paixão");

        ataqueEmCampo[5] = mapJogador.get("Stam");
    }

    public static void colocaDefesaEmCampo (){
        defesaEmCampo[0] = mapJogador.get("P2");
        defesaEmCampo[1] = mapJogador.get("Japonês");
        defesaEmCampo[2] = mapJogador.get("Lio");

        defesaEmCampo[3] = mapJogador.get("Shibata");
        defesaEmCampo[4] = mapJogador.get("Lorena");
        defesaEmCampo[5] = mapJogador.get("Zacka");

        defesaEmCampo[6] = mapJogador.get("Minei");
        defesaEmCampo[7] = mapJogador.get("Igor");
    }

}
