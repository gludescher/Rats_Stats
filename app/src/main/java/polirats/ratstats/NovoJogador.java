package polirats.ratstats;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class NovoJogador extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    Button criarJogadorButton, posicaoButton;
    EditText editNomeJogador, editApelido, editAnoEntrada, editNumero;
    File jogadores;
    String posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_jogador);


        Typeface font = Typeface.createFromAsset(getAssets(), "rats_font.ttf");

        criarJogadorButton = (Button) findViewById(R.id.criarJogadorButton);
        criarJogadorButton.setOnClickListener(this);
        criarJogadorButton.setTypeface(font);

        posicaoButton = (Button) findViewById(R.id.posicaoButton);
        posicaoButton.setOnClickListener(this);
        posicaoButton.setTypeface(font);

        editNomeJogador = (EditText) findViewById(R.id.editNomeJogador);
        editNomeJogador.setTypeface(font);

        editApelido = (EditText) findViewById(R.id.editApelido);
        editApelido.setTypeface(font);

        editAnoEntrada = (EditText) findViewById(R.id.editAnoEntrada);
        editAnoEntrada.setTypeface(font);

        editNumero = (EditText) findViewById(R.id.editNumero);
        editNumero.setTypeface(font);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, posicoes);
        //adapter.setDropDownViewResource(R.layout.spinner_item);

    }

    @Override
    public void onClick(View v) {

        String nome;
        String apelido;
        String numero;
        String unidade;
        int anoEntrada;

        PopupMenu popupPosicao = new PopupMenu(this, v);
        String[] posicoes = new String[]{"QB", "WR", "OL", "DB", "LB", "DL"};
        for (String r : posicoes)
            popupPosicao.getMenu().add(r);
        popupPosicao.setOnMenuItemClickListener(this);
        popupPosicao.inflate(R.menu.popup_menu);

        Jogador J = null;

        switch (v.getId()) {
            case R.id.posicaoButton:
                popupPosicao.show();
                break;
            case R.id.criarJogadorButton:
                nome = editNomeJogador.getText().toString();
                apelido = editApelido.getText().toString();
                numero = editNumero.getText().toString();
                if (numero.length() == 1)
                    numero = "0" + numero;
                anoEntrada = Integer.parseInt(editAnoEntrada.getText().toString());



                if (posicao.equals("WR") || posicao.equals("OL") || posicao.equals("QB"))
                    unidade = "Ataque";
                else
                    unidade = "Defesa";

                J = new Jogador(nome, apelido, numero, unidade, posicao, anoEntrada);
                writeOnFile(J.getText(), "jogadores.txt");
                Toast.makeText(getApplicationContext(), J.getPosicao() + " " + J.getApelido() + " criado!", Toast.LENGTH_LONG).show();
                Intent in = new Intent(this, MainActivity.class);
                startActivity(in);
                break;
            default:
                break;
        }
    }

    public void writeOnFile (String text, String fileName) {

        //Context c = getApplicationContext();
        //File f = new File(c.getFilesDir(), "jogadores.txt");
        //Context context = getApplicationContext();
        //File path = context.getFilesDir();
        //jogadores = new File(path, "jogadores.txt");

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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {


    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        posicao = item.getTitle().toString();
        posicaoButton.setText(item.getTitle().toString());
        return true;
    }
}
