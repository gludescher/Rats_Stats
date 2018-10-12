package polirats.ratstats;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.FileOutputStream;

import static java.lang.StrictMath.abs;
import static polirats.ratstats.MainActivity.ataqueEmCampo;
import static polirats.ratstats.NovaPartida.fileName;

public class NovaFalta extends AppCompatActivity implements View.OnClickListener {

    AutoCompleteTextView autoCompleteFalta;
    ToggleButton toggleUnidade;
    EditText editJds;
    ImageButton xButton, rButton, lgButton, cButton, rgButton, qbButton, yButton, zButton;
    TextView textDownDistance, textSelecioneJogadores, textX, textR, textLG, textC, textRG, textQB, textY, textZ;
    Switch switchTime, switchSafety;
    Button registrarFaltaButton;
    String jdsConquistadas, nomeFalta, unidadeFalta, drive;
    Jogador jogador, fazedor, tomador;
    Boolean faltaDoTime;

    int down, distance, scrimmage, half, pontRats, pontAdv, jds, jdsFalta;
    boolean corrida, passe, completo, incompleto, interceptado, drop, sack, badSnap, TD, safety, falta, twoPointTry, twoPointGood;

    JogadaAtaque flag;

    final String faltas[] = {"False start", "Holding", "Intentional grounding", "Delay of game", "Proteção de flag",
                            "Salto", "Bloqueio ilegal pelas costas", "Contato ilegal", "Interferência de passe",
                            "Passe ilegal", "Jogador inelegível downfield", "Ataque ao passador", "Violência desnecessária",
                            "Atitude antidesportiva", "Taunting", "Neutral zone infraction", "Encroachment", "Formação ilegal"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_falta);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_dropdown_item_1line, faltas);

        Typeface font = Typeface.createFromAsset(getAssets(), "rats_font.ttf");

        jdsConquistadas = "";
        nomeFalta = "";

        autoCompleteFalta = (AutoCompleteTextView) findViewById(R.id.autoCompleteFalta);
        autoCompleteFalta.setThreshold(1);
        autoCompleteFalta.setAdapter(adapter);
        autoCompleteFalta.setTypeface(font);

        toggleUnidade = (ToggleButton)findViewById(R.id.toggleUnidade);
        toggleUnidade.setOnClickListener(this);
        toggleUnidade.setChecked(true);
        toggleUnidade.setTypeface(font);

        editJds = (EditText) findViewById(R.id.editJds);

        textDownDistance = (TextView) findViewById(R.id.textDownDistance);
        textDownDistance.setText(GameSituation.printGameSituation());
        textDownDistance.setTypeface(font);

        textSelecioneJogadores = (TextView) findViewById(R.id.textSelecioneJogadores);
        textSelecioneJogadores.setTypeface(font);

        registrarFaltaButton = (Button) findViewById(R.id.registrarFaltaButton);
        registrarFaltaButton.setTypeface(font);
        registrarFaltaButton.setOnClickListener(this);

        xButton = (ImageButton) findViewById(R.id.xButton);
        xButton.setOnClickListener(this);

        rButton = (ImageButton) findViewById(R.id.rButton);
        rButton.setOnClickListener(this);

        lgButton = (ImageButton) findViewById(R.id.lgButton);
        lgButton.setOnClickListener(this);

        rgButton = (ImageButton) findViewById(R.id.rgButton);
        rgButton.setOnClickListener(this);

        cButton = (ImageButton) findViewById(R.id.cButton);
        cButton.setOnClickListener(this);

        qbButton = (ImageButton) findViewById(R.id.qbButton);
        qbButton.setOnClickListener(this);

        yButton = (ImageButton) findViewById(R.id.yButton);
        yButton.setOnClickListener(this);

        zButton = (ImageButton) findViewById(R.id.zButton);
        zButton.setOnClickListener(this);

        textX = (TextView) findViewById(R.id.textX);
        textX.setTypeface(font);
        //textX.setText(ataqueEmCampo.getX().getApelido());
        textX.setText(ataqueEmCampo[0].getApelido());

        textR = (TextView) findViewById(R.id.textR);
        textR.setTypeface(font);
        //textR.setText(ataqueEmCampo.getR().getApelido());
        textR.setText(ataqueEmCampo[1].getApelido());

        textLG = (TextView) findViewById(R.id.textLG);
        textLG.setTypeface(font);
        //textLG.setText(ataqueEmCampo.getLG().getApelido());
        textLG.setText(ataqueEmCampo[2].getApelido());

        textC = (TextView) findViewById(R.id.textC);
        textC.setTypeface(font);
        //textC.setText(ataqueEmCampo.getC().getApelido());
        textC.setText(ataqueEmCampo[3].getApelido());

        textRG = (TextView) findViewById(R.id.textRG);
        textRG.setTypeface(font);
        //textRG.setText(ataqueEmCampo.getRG().getApelido());
        textRG.setText(ataqueEmCampo[4].getApelido());

        textQB = (TextView) findViewById(R.id.textQB);
        textQB.setTypeface(font);
        //textQB.setText(ataqueEmCampo.getQB().getApelido());
        textQB.setText(ataqueEmCampo[5].getApelido());

        textY = (TextView) findViewById(R.id.textY);
        textY.setTypeface(font);
        //textY.setText(ataqueEmCampo.getY().getApelido());
        textY.setText(ataqueEmCampo[6].getApelido());

        textZ = (TextView) findViewById(R.id.textZ);
        textZ.setTypeface(font);
        //textZ.setText(ataqueEmCampo.getZ().getApelido());
        textZ.setText(ataqueEmCampo[7].getApelido());

        editJds = (EditText) findViewById(R.id.editJds);
        editJds.setTypeface(font);

        switchTime = (Switch) findViewById(R.id.switchTime);
        switchTime.setTypeface(font);
        switchTime.setOnClickListener(this);
        switchTime.setChecked(true);

        switchSafety = (Switch) findViewById(R.id.switchSafety);
        switchSafety.setTypeface(font);
        switchSafety.setOnClickListener(this);
        switchSafety.setChecked(false);

        paleViews();
    }

    public void paleViews (){
        xButton.setImageResource(R.drawable.pale_botao_x);
        rButton.setImageResource(R.drawable.pale_botao_r);
        lgButton.setImageResource(R.drawable.pale_botao_lg);
        cButton.setImageResource(R.drawable.pale_botao_c);
        rgButton.setImageResource(R.drawable.pale_botao_rg);
        qbButton.setImageResource(R.drawable.pale_botao_qb);
        yButton.setImageResource(R.drawable.pale_botao_y);
        zButton.setImageResource(R.drawable.pale_botao_z);

        textX.setTextColor(ContextCompat.getColor(this, R.color.paleRatsYellow));
        textR.setTextColor(ContextCompat.getColor(this, R.color.paleRatsYellow));
        textLG.setTextColor(ContextCompat.getColor(this, R.color.paleRatsYellow));
        textC.setTextColor(ContextCompat.getColor(this, R.color.paleRatsYellow));
        textRG.setTextColor(ContextCompat.getColor(this, R.color.paleRatsYellow));
        textQB.setTextColor(ContextCompat.getColor(this, R.color.paleRatsYellow));
        textY.setTextColor(ContextCompat.getColor(this, R.color.paleRatsYellow));
        textZ.setTextColor(ContextCompat.getColor(this, R.color.paleRatsYellow));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.toggleUnidade:
                if (toggleUnidade.isChecked()) switchSafety.setVisibility(View.VISIBLE);
                else switchSafety.setVisibility(View.GONE);
                break;

            case R.id.xButton:
                jogador = ataqueEmCampo[0];
                paleViews();
                xButton.setImageResource(R.drawable.botao_x2);
                textX.setTextColor(ContextCompat.getColor(this, R.color.ratsYellow));
                switchTime.setChecked(false);
                break;
            case R.id.rButton:
                jogador = ataqueEmCampo[1];
                paleViews();
                rButton.setImageResource(R.drawable.botao_r);
                textR.setTextColor(ContextCompat.getColor(this, R.color.ratsYellow));
                switchTime.setChecked(false);
                break;
            case R.id.lgButton:
                jogador = ataqueEmCampo[2];
                paleViews();
                lgButton.setImageResource(R.drawable.botao_lg);
                textLG.setTextColor(ContextCompat.getColor(this, R.color.ratsYellow));
                switchTime.setChecked(false);
                break;
            case R.id.cButton:
                jogador = ataqueEmCampo[3];
                paleViews();
                cButton.setImageResource(R.drawable.botao_c);
                textC.setTextColor(ContextCompat.getColor(this, R.color.ratsYellow));
                switchTime.setChecked(false);
                break;
            case R.id.rgButton:
                jogador = ataqueEmCampo[4];
                paleViews();
                rgButton.setImageResource(R.drawable.botao_rg);
                textRG.setTextColor(ContextCompat.getColor(this, R.color.ratsYellow));
                switchTime.setChecked(false);
                break;
            case R.id.qbButton:
                jogador = ataqueEmCampo[5];
                paleViews();
                qbButton.setImageResource(R.drawable.botao_qb);
                textQB.setTextColor(ContextCompat.getColor(this, R.color.ratsYellow));
                switchTime.setChecked(false);
                break;
            case R.id.yButton:
                jogador = ataqueEmCampo[6];
                paleViews();
                yButton.setImageResource(R.drawable.botao_y);
                textY.setTextColor(ContextCompat.getColor(this, R.color.ratsYellow));
                switchTime.setChecked(false);
                break;
            case R.id.zButton:
                jogador = ataqueEmCampo[7];
                paleViews();
                zButton.setImageResource(R.drawable.botao_z);
                textZ.setTextColor(ContextCompat.getColor(this, R.color.ratsYellow));
                switchTime.setChecked(false);
                break;
            case R.id.switchTime:
                if (switchTime.isChecked()){
                    jogador = null;
                    faltaDoTime = true;
                    paleViews();
                }
                break;

            case R.id.registrarFaltaButton:
                jdsConquistadas = editJds.getText().toString();
                nomeFalta = autoCompleteFalta.getText().toString();
                faltaDoTime = switchTime.isChecked();
                if ((faltaDoTime || (jogador != null)) && !jdsConquistadas.equals("") && !nomeFalta.equals("")) {
                    jdsFalta = abs(Integer.parseInt(jdsConquistadas));
                    if (toggleUnidade.isActivated()) {
                        fazedor = jogador;
                        tomador = null;
                    } else {
                        tomador = jogador;
                        fazedor = null;
                    }
                    if (toggleUnidade.isChecked()) {
                        unidadeFalta = "ataque";
                        jdsFalta = -jdsFalta;
                    }
                    else unidadeFalta = "defesa";

                    if (faltaDoTime) {
                        tomador = fazedor = null;
                    }


                    safety = toggleUnidade.isChecked() && switchSafety.isChecked();

                    jds = 0;
                    down = GameSituation.getDown();
                    drive = Integer.toString(GameSituation.getDrive());
                    distance = GameSituation.getDistance();
                    scrimmage = GameSituation.getScrim();
                    half = GameSituation.getHalf();
                    pontRats = GameSituation.getRatsScore();
                    pontAdv = GameSituation.getAdvScore();
                    corrida = true;
                    corrida = passe = completo = incompleto = interceptado = drop = sack = badSnap = twoPointGood = twoPointTry = false;

                    flag = new JogadaAtaque(drive, down, distance, scrimmage, jds, half, pontRats, pontAdv,
                            corrida, null, passe, completo, incompleto, drop, interceptado, null, null,
                            sack, badSnap, TD, safety, twoPointTry, twoPointGood, falta, nomeFalta, unidadeFalta, jdsFalta, fazedor, tomador, ataqueEmCampo);

                    writeOnFile(flag.getCsvText(), fileName + "atq.txt");

                    Toast.makeText(getApplicationContext(), "Falta (" + nomeFalta + " " + jdsFalta + " jardas) registrada!", Toast.LENGTH_LONG).show();

                    Intent in;
                    if (safety)
                        in = new Intent(this, NovoDrive.class);
                    else
                        in = new Intent(this, NovaJogada.class);
                    startActivity(in);
                }

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
}
