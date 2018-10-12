package polirats.ratstats;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import java.util.HashMap;

import static polirats.ratstats.JogadaAtaque.nJogadas;
import static polirats.ratstats.NovaPartida.adversario;
import static polirats.ratstats.NovaPartida.campeonato;
import static polirats.ratstats.NovaPartida.dataPartida;
import static polirats.ratstats.NovaPartida.fileName;
import static polirats.ratstats.NovaPartida.idPartida;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button novoJogadorButton, novaPartidaButton, deletarJogadoresButton, exportarArquivosButton, retomarPartidaButton;

    public static HashMap<String, Jogador> mapJogador = new HashMap<String, Jogador>();

    public static boolean novoDriveActivity;

    public static boolean driveAtaque;


    //0 = X, 1 = R, 2 = LG, 3 = C, 4 = RG, 5 = QB, 6 = Y, 7 = Z
    public static Jogador[] ataqueEmCampo = new Jogador[8];
    public static Jogador[] defesaEmCampo = new Jogador[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();
        File path = context.getFilesDir();
        File jogadores = new File(path, "jogadores.txt");

        //String teste = "id,nome,Teste,15,Ataque,WR,2018\n";
        //writeOnFile(teste, "jogadores.txt", jogadores);

        if (jogadores.exists())
            readAndCreate("jogadores.txt");

        Typeface font = Typeface.createFromAsset(getAssets(), "rats_font.ttf");

        novoJogadorButton = (Button) findViewById(R.id.novoJogadorButton);
        novoJogadorButton.setOnClickListener(this);
        novoJogadorButton.setTypeface(font);

        novaPartidaButton = (Button) findViewById(R.id.novaPartidaButton);
        novaPartidaButton.setOnClickListener(this);
        novaPartidaButton.setTypeface(font);

        deletarJogadoresButton = (Button) findViewById(R.id.deletarJogadoresButton);
        deletarJogadoresButton.setOnClickListener(this);
        deletarJogadoresButton.setTypeface(font);

        exportarArquivosButton = (Button) findViewById(R.id.exportarArquivosButton);
        exportarArquivosButton.setOnClickListener(this);
        exportarArquivosButton.setTypeface(font);

        retomarPartidaButton = (Button) findViewById(R.id.retomarPartidaButton);
        retomarPartidaButton.setOnClickListener(this);
        retomarPartidaButton.setTypeface(font);

        //Área de Testes
        //Teste.CriaJogadores(5, "QB");
        //Teste.CriaJogadores(10, "OL");
        //Teste.CriaJogadores(20, "WR");
        Teste.colocaAtaqueEmCampo();
        Teste.colocaDefesaEmCampo();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.novoJogadorButton:
                Intent nj = new Intent(this, NovoJogador.class);
                startActivity(nj);
                break;
            case R.id.novaPartidaButton:
                Intent np = new Intent(this, NovaPartida.class);
                startActivity(np);
                break;
            case R.id.exportarArquivosButton:
                Intent ea = new Intent(this, ExportarArquivos.class);
                startActivity(ea);
                break;
            case R.id.deletarJogadoresButton:
                Intent dj = new Intent(this, ApagarJogador.class);
                startActivity(dj);
                break;
            case R.id.retomarPartidaButton:
                GameSituation.gameStart();
                readGameSituation("game_situation.txt");
                fileName = adversario.toLowerCase() + dataPartida;
                Intent in = new Intent (this, NovoDrive.class);
                startActivity(in);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void readAndCreate (String fileName) {

        Jogador J;
        try {
            FileInputStream fileInputStream = openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            String line = "";
            String[] s = null;
            while((line = br.readLine()) != null) {
                s = line.split(",");
                //Toast.makeText(getApplicationContext(), s[0]+s[1]+s[2]+s[3]+s[4]+s[5]+Integer.parseInt(s[6]), Toast.LENGTH_LONG).show();
                //String nome, String apelido, String numero, String unidade, String posicao, int anoEntrada
                if (s[0] != null && s.length == 7)
                    J = new Jogador(s[1], s[2], s[3], s[4], s[5], Integer.parseInt(s[6]));
                //J = new Jogador ("a", "b", "15", "Ataque", "WR", 2018);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readGameSituation(String fileName) {
        try {
            FileInputStream fileInputStream = openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);
            String line = "";
            String[] s = null;
            while((line = br.readLine()) != null) {
                s = line.split(",");
                //idPartida, dataPartida, adversario, campeonato, nJogadas, drive, down, distance, scrimmage, pontRats, pontAdv, half
                if (s[0] != null && s.length == 12){
                    idPartida = s[0];
                    dataPartida = s[1];
                    adversario = s[2];
                    campeonato = s[3];
                    nJogadas = Integer.parseInt(s[4]);
                    GameSituation.setDrive(Integer.parseInt(s[5]));
                    GameSituation.setDown(Integer.parseInt(s[6]));
                    GameSituation.setDistance(Integer.parseInt(s[7]));
                    GameSituation.setScrimmageBruto(Integer.parseInt(s[8]));
                    GameSituation.setPontRats(Integer.parseInt(s[9]));
                    GameSituation.setPontAdv(Integer.parseInt(s[10]));
                    GameSituation.setHalf(Integer.parseInt(s[11]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeOnFile (String text, String fileName, File f) {

        FileOutputStream outputStream;
        try {
            //outputStream = openFileOutput(fileName, MODE_PRIVATE);
            outputStream = new FileOutputStream(f, true);
            outputStream.write(text.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
