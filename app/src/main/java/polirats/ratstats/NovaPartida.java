package polirats.ratstats;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NovaPartida extends AppCompatActivity implements View.OnClickListener {

    Button comecarPartidaButton;
    public static String idPartida, dataPartida, adversario, campeonato, fileName;
    EditText editAdversario, editTorneio;

                /*idPartida + "," + dataPartida + "," + adversario + "," + campeonato + "," + this.idJogada + "," + this.horaJogada +

				"," + Integer.toString(this.down) + "," + Integer.toString(this.distance) + "," + Integer.toString(this.scrimmage) +
				"," + Integer.toString(this.jdsConquistadas) + "," + Integer.toString(this.half) + "," + Integer.toString(this.pontRats) +
				"," + Integer.toString(this.pontAdv) +

				"," + Boolean.toString(this.corrida) + "," + runnerApelido +
				"," + Boolean.toString(this.passe) + "," + Boolean.toString(this.completo) + "," + Boolean.toString(this.incompleto) +
				"," + Boolean.toString(this.drop) + "," + Boolean.toString(this.interceptado) + "," + quarterbackApelido + "," + targetApelido +
				"," + Boolean.toString(this.sack) + "," + Boolean.toString(this.badSnap) +

				"," + Boolean.toString(this.TD) + "," + Boolean.toString(this.safety) + "," + Boolean.toString(this.falta) +
				"," + this.nomeFalta + "," + this.unidadeFalta + "," + Integer.toString(this.jdsFalta) + fazedorApelido + "," + tomadorApelido +

				"," + emCampo[0].getApelido() + "," + emCampo[1].getApelido() + "," + emCampo[2].getApelido() + "," + emCampo[3].getApelido() +
				"," + emCampo[4].getApelido() + "," + emCampo[5].getApelido() + "," + emCampo[6].getApelido() + "," + emCampo[7].getApelido() +
				"\n";*/

    String headerJogadas = "idPartida,dataPartida,adversário,campeonato,idJogada,horaJogada,drive," +
            "down,distância,scrimmage,jdsConquistadas,half,pontRats,pontAdv,corrida,corredor,passe," +
            "completo,incompleto,drop,interceptado,quarterback,target,sack,badSnap,TD,safety,2ptTry,2ptGood,falta," +
            "tipoDeFalta,unidadeFalta,jdsFalta,fezFalta,sofreuFalta,X,Y,LG,C,RG,QB,R,Z\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_partida);

        Typeface font = Typeface.createFromAsset(getAssets(), "rats_font.ttf");

        comecarPartidaButton = (Button) findViewById(R.id.comecarPartidaButton);
        comecarPartidaButton.setTypeface(font);
        comecarPartidaButton.setOnClickListener(this);

        editAdversario = (EditText) findViewById(R.id.editAdversario);
        editTorneio = (EditText) findViewById(R.id.editTorneio);

        //TEST-PURPOSE ONLY!

        editAdversario.setText("AdvTeste");
        editTorneio.setText("TorneioTeste");
    }

    @Override
    public void onClick(View v) {
        editAdversario = (EditText) findViewById(R.id.editAdversario);
        editTorneio = (EditText) findViewById(R.id.editTorneio);

        switch(v.getId()){
            case R.id.comecarPartidaButton:
                adversario = editAdversario.getText().toString();
                campeonato = editTorneio.getText().toString();
                setDataPartida();
                setIdPartida();
                setFileName();
                writeOnFile(headerJogadas, fileName + "atq.txt");
                GameSituation.gameStart();
                Intent in = new Intent(this, NovoDrive.class);
                startActivity(in);
                break;
            default:
                break;
        }
    }

    public void writeOnFile (String text, String fileName) {

        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(fileName, MODE_APPEND);
            //outputStream = new FileOutputStream(jogadores, true);
            outputStream.write(text.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setIdPartida () {
        idPartida = adversario.substring(0,3).toUpperCase() + dataPartida;
    }

    public static void setDataPartida () {
        DateFormat dateOnly = new SimpleDateFormat("ddMMyy");
        dataPartida = dateOnly.format(Calendar.getInstance().getTime());
    }

    public static void setFileName () {
        fileName = adversario.toLowerCase() + dataPartida;
    }
}
