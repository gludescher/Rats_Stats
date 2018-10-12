package polirats.ratstats;

import static polirats.ratstats.MainActivity.*;
import static polirats.ratstats.MainActivity.mapJogador;

public class Jogador {

    private String idJogador;
    private String nome;
    private String apelido;
    private String numero;
    private String unidade;
    private String posicao;
    private int anoEntrada;


    public Jogador(String nome, String apelido, String numero, String unidade, String posicao, int anoEntrada) {

        setIdJogador(unidade, posicao, numero);
        this.nome = nome;
        this.numero = numero;
        this.apelido = apelido;
        this.unidade = unidade;
        this.posicao = posicao;
        this.anoEntrada = anoEntrada;

        mapJogador.put(apelido, this);
    }

    public Jogador() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(String unidade, String posicao, String numero) {
        String id;
        switch (unidade) {
            case "Ataque":
                id = "1";
                switch (posicao) {
                    case "QB":
                        id += "1" + numero;
                        break;
                    case "OL":
                        id += "2" + numero;
                        break;
                    case "WR":
                        id += "3" + numero;
                        break;
                    default:
                        id = "ERRO";
                        break;
                }
                break;
            case "Defesa":
                id = "2";
                switch (posicao) {
                    case "DL":
                        id += "1" + numero;
                        break;
                    case "LB":
                        id += "2" + numero;
                        break;
                    case "DB":
                        id += "3" + numero;
                        break;
                    default:
                        id = "ERRO";
                        break;
                }
                break;
            default:
                id = "ERRO";
                break;
        }
        this.idJogador = id;
    }

    public String getText() {
        String text = this.idJogador + "," + this.nome + "," + this.apelido + "," + this.numero + ","
                + this.unidade + "," + this.posicao + "," + Integer.toString(this.anoEntrada) + "\n";
        return text;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getAnoEntrada() {
        return anoEntrada;
    }

    public void setAnoEntrada(int anoEntrada) {
        this.anoEntrada = anoEntrada;
    }
}


