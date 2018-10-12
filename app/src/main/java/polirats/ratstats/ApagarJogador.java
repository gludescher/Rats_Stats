package polirats.ratstats;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static polirats.ratstats.MainActivity.mapJogador;


public class ApagarJogador extends AppCompatActivity implements View.OnClickListener {

    Button selecionarTodosButton, desfazerSelecaoButton, apagarJogadoresButton;
    List<CheckBox> boxList;
    List<String> jogadoresSelecionados;
    String selecionado;
    int nSelec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apagar_jogador);

        Typeface font = Typeface.createFromAsset(getAssets(), "rats_font.ttf");

        selecionarTodosButton = (Button) findViewById(R.id.selecionarTodosButton);
        selecionarTodosButton.setOnClickListener(this);
        selecionarTodosButton.setTypeface(font);

        desfazerSelecaoButton = (Button) findViewById(R.id.desfazerSelecaoButton);
        desfazerSelecaoButton.setOnClickListener(this);
        desfazerSelecaoButton.setTypeface(font);

        apagarJogadoresButton = (Button) findViewById(R.id.apagarJogadoresButton);
        apagarJogadoresButton.setOnClickListener(this);
        apagarJogadoresButton.setTypeface(font);

        //textApagar = (TextView) findViewById(R.id.textApagar);

        LinearLayout lay0 = (LinearLayout) findViewById(R.id.apagarLinear);

        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked}, // unchecked
                        new int[]{android.R.attr.state_checked} , // checked
                },
                new int[]{
                        getResources().getColor(R.color.mediumGrey),
                        getResources().getColor(R.color.darkYellow),
                }
        );

        boxList = new ArrayList<CheckBox>();
        List<String> alfaJogadores = new ArrayList<String>(mapJogador.keySet());
        Collections.sort(alfaJogadores);

        String boxText;
        int tag = 0;
        for (String apelido : alfaJogadores) {

            boxText = "(" + mapJogador.get(apelido).getPosicao() +")" + "    " + apelido;

            CheckBox jogadorBox = new CheckBox(getApplicationContext());
            jogadorBox.setText(boxText);
            jogadorBox.setTextColor(getResources().getColor(R.color.lightGrey));
            jogadorBox.setTypeface(font);
            CompoundButtonCompat.setButtonTintList(jogadorBox,colorStateList);
            jogadorBox.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            lay0.addView(jogadorBox);
            boxList.add(jogadorBox);
        }
    }

    @Override
    public void onClick(View v) {
        String fileName = "jogadores.txt";
        switch (v.getId()){
            case R.id.selecionarTodosButton:
                for (CheckBox box : boxList){
                    box.setChecked(true);
                }
                break;
            case R.id.desfazerSelecaoButton:
                for (CheckBox box : boxList){
                    box.setChecked(false);
                }
                break;
            case R.id.apagarJogadoresButton:
                apagaSelecionados(fileName);
                                /*try {
                    FileOutputStream writer;
                    writer = openFileOutput("jogadores.txt", MODE_PRIVATE);
                    writer.write("".getBytes());
                    writer.close();
                    Toast.makeText(getApplicationContext(), "Aplicativo tem que ser reiniciado", Toast.LENGTH_LONG).show();
                    this.finishAffinity();
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                Intent in = new Intent(this, ApagarJogador.class);
                startActivity(in);
                break;
            default:
                break;
        }

    }

    public List<String> getSelecionados (){
        nSelec = 0;
        jogadoresSelecionados = new ArrayList<String>();
        for (CheckBox box : boxList){
            if (box.isChecked()) {
                selecionado = box.getText().toString();
                jogadoresSelecionados.add(selecionado.substring(8, selecionado.length()));
                nSelec++;
            }
        }
        return jogadoresSelecionados;
    }

    public void apagaSelecionados (String fileName) {
        List<String> jogadoresSelecionados =  getSelecionados();
        String text;
        //remove todos os jogadores selecionados
        for (String sel : jogadoresSelecionados) {
            mapJogador.remove(sel);
        }

        //sobrescreve o arquivo com "", apagando todos os jogadores
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(fileName, MODE_PRIVATE);
            //outputStream = new FileOutputStream(jogadores, true);
            outputStream.write("".getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //para cada jogador ainda existente, faz append no arquivo, criando o jogador
        for (String key : mapJogador.keySet()) {
            text = mapJogador.get(key).getText();

            try {
                outputStream = openFileOutput(fileName, MODE_APPEND);
                //outputStream = new FileOutputStream(jogadores, true);
                outputStream.write(text.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
