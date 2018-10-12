package polirats.ratstats;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

import static polirats.ratstats.MainActivity.ataqueEmCampo;
import static polirats.ratstats.NovaPartida.fileName;

public class NovoPasse extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    EditText editJds;
    Button registrarPassadorButton, registrarPasseButton, completoButton, incompletoButton, interceptacaoButton, resultadoButton;
    ImageButton xButton, rButton, lgButton, cButton, rgButton, qbButton, yButton, zButton;
    TextView textDownDistance, textSelecioneJogadores, textX, textR, textLG, textC, textRG, textQB, textY, textZ;
    String jdsConquistadas, text, drive;
    Switch switchDrop;

    JogadaAtaque pass;
    Jogador jogador, passador, recebedor;

    int down, distance, scrimmage, half, pontRats, pontAdv, jds;
    boolean corrida, passe, completo, incompleto, interceptado, drop, sack, badSnap, TD, safety, falta, twoPointTry, twoPointGood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_passe);

        Typeface font = Typeface.createFromAsset(getAssets(), "rats_font.ttf");

        jdsConquistadas = "";

        textDownDistance = (TextView) findViewById(R.id.textDownDistance);
        textDownDistance.setText(GameSituation.printGameSituation());
        textDownDistance.setTypeface(font);

        textSelecioneJogadores = (TextView) findViewById(R.id.textSelecioneJogadores);
        textSelecioneJogadores.setTypeface(font);
        textSelecioneJogadores.setText("Selecione o passador:");

        completoButton = (Button) findViewById(R.id.completoButton);
        completoButton.setTypeface(font);
        completoButton.setOnClickListener(this);

        incompletoButton = (Button) findViewById(R.id.incompletoButton);
        incompletoButton.setTypeface(font);
        incompletoButton.setOnClickListener(this);

        interceptacaoButton = (Button) findViewById(R.id.interceptacaoButton);
        interceptacaoButton.setTypeface(font);
        interceptacaoButton.setOnClickListener(this);

        resultadoButton = (Button) findViewById(R.id.resultadoButton);
        resultadoButton.setTypeface(font);
        resultadoButton.setOnClickListener(this);

        registrarPassadorButton = (Button) findViewById(R.id.registrarPassadorButton);
        registrarPassadorButton.setTypeface(font);
        registrarPassadorButton.setOnClickListener(this);

        registrarPasseButton = (Button) findViewById(R.id.registrarPasseButton);
        registrarPasseButton.setTypeface(font);
        registrarPasseButton.setOnClickListener(this);

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

        switchDrop = (Switch) findViewById(R.id.switchDrop);
        switchDrop.setTypeface(font);

        //por padrão, o passador é o QB em campo
        jogador = ataqueEmCampo[5];
        paleViews();
        qbButton.setImageResource(R.drawable.botao_qb);
        textQB.setTextColor(ContextCompat.getColor(this, R.color.ratsYellow));


        TD = safety = falta = twoPointTry = twoPointGood = false;
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

        String[] vetorResultado;

        PopupMenu popupResultado = new PopupMenu(this, v);
        vetorResultado = new String[]{"Touchdown", "Safety", "Falta", "2pt NO Good", "2pt Good", "Jogada Normal"};
        for (String r : vetorResultado)
            popupResultado.getMenu().add(r);
        popupResultado.setOnMenuItemClickListener(this);
        popupResultado.inflate(R.menu.popup_menu);

        switch (v.getId()) {
            case R.id.completoButton:
                editJds.setVisibility(View.VISIBLE);
                resultadoButton.setVisibility(View.VISIBLE);
                switchDrop.setVisibility(View.GONE);
                completo = true;
                incompleto = interceptado = false;
                break;
            case R.id.incompletoButton:
                editJds.setVisibility(View.GONE);
                resultadoButton.setVisibility(View.VISIBLE);
                switchDrop.setVisibility(View.VISIBLE);
                incompleto = true;
                completo = false;
                interceptado = false;
                break;
            case R.id.interceptacaoButton:
                editJds.setVisibility(View.GONE);
                resultadoButton.setVisibility(View.VISIBLE);
                switchDrop.setVisibility(View.GONE);
                interceptado = true;
                completo = incompleto = false;
                break;

            case R.id.xButton:
                jogador = ataqueEmCampo[0];
                paleViews();
                xButton.setImageResource(R.drawable.botao_x2);
                textX.setTextColor(ContextCompat.getColor(this, R.color.ratsYellow));
                break;
            case R.id.rButton:
                jogador = ataqueEmCampo[1];
                paleViews();
                rButton.setImageResource(R.drawable.botao_r);
                textR.setTextColor(ContextCompat.getColor(this, R.color.ratsYellow));
                break;
            case R.id.lgButton:
                jogador = ataqueEmCampo[2];
                paleViews();
                lgButton.setImageResource(R.drawable.botao_lg);
                textLG.setTextColor(ContextCompat.getColor(this, R.color.ratsYellow));
                break;
            case R.id.cButton:
                jogador = ataqueEmCampo[3];
                paleViews();
                cButton.setImageResource(R.drawable.botao_c);
                textC.setTextColor(ContextCompat.getColor(this, R.color.ratsYellow));
                break;
            case R.id.rgButton:
                jogador = ataqueEmCampo[4];
                paleViews();
                rgButton.setImageResource(R.drawable.botao_rg);
                textRG.setTextColor(ContextCompat.getColor(this, R.color.ratsYellow));
                break;
            case R.id.qbButton:
                jogador = ataqueEmCampo[5];
                paleViews();
                qbButton.setImageResource(R.drawable.botao_qb);
                textQB.setTextColor(ContextCompat.getColor(this, R.color.ratsYellow));
                break;
            case R.id.yButton:
                jogador = ataqueEmCampo[6];
                paleViews();
                yButton.setImageResource(R.drawable.botao_y);
                textY.setTextColor(ContextCompat.getColor(this, R.color.ratsYellow));
                break;
            case R.id.zButton:
                jogador = ataqueEmCampo[7];
                paleViews();
                zButton.setImageResource(R.drawable.botao_z);
                textZ.setTextColor(ContextCompat.getColor(this, R.color.ratsYellow));
                break;

            case R.id.resultadoButton:
                popupResultado.show();
                break;

            case R.id.registrarPassadorButton:
                if (jogador != null) {
                    passador = jogador;
                    textSelecioneJogadores.setText("Selecione o recebedor:");
                    paleViews();
                    registrarPassadorButton.setVisibility(View.GONE);
                    registrarPasseButton.setVisibility(View.VISIBLE);
                    jogador = null;
                }
            case R.id.registrarPasseButton:
                if (jogador != null) {
                    if (completo) jds = Integer.parseInt(editJds.getText().toString());
                    else jds = 0;

                    if (incompleto)
                        drop = switchDrop.isChecked();
                    else drop = false;
                    drive = Integer.toString(GameSituation.getDrive());
                    recebedor = jogador;
                    down = GameSituation.getDown();
                    distance = GameSituation.getDistance();
                    scrimmage = GameSituation.getScrim();
                    half = GameSituation.getHalf();
                    pontRats = GameSituation.getRatsScore();
                    pontAdv = GameSituation.getAdvScore();
                    corrida = false;
                    sack = badSnap = false;
                    passe = true;

                    pass = new JogadaAtaque(drive, down, distance, scrimmage, jds, half, pontRats, pontAdv,
                            corrida, null, passe, completo, incompleto, drop,interceptado, passador, recebedor,
                            sack, badSnap, TD, safety,  twoPointTry, twoPointGood,  falta, "na", "na", 0, null, null, ataqueEmCampo);
                    text = pass.getCsvText();
                    writeOnFile(text, fileName + "atq.txt");

                    String especial = "";
                    if (TD)
                        especial = " (Touchdown)";
                    else if (twoPointGood)
                        especial = " (2pt GOOD)";
                    else if (twoPointTry)
                        especial = " (2pt NO good)";
                    else if (safety)
                        especial = " (Safety)";
                    else if (GameSituation.getDown() > 4)
                        especial = " (Turnover on Downs)";

                    Toast.makeText(getApplicationContext(), "Passe (" + jds + " jardas) registrado!" + especial, Toast.LENGTH_LONG).show();

                    Intent in;
                    if (safety || GameSituation.getDown() > 4 || twoPointTry || interceptado)
                        in = new Intent(this, NovoDrive.class);
                    else if (falta)
                        in = new Intent(this, NovaFalta.class);
                    else
                        in = new Intent(this, NovaJogada.class);
                    startActivity(in);
                }
            default:
                break;
        }
    }

    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getTitle().toString()){
            case "Touchdown":
                TD = true;
                safety = falta = twoPointTry = twoPointGood = false;
                break;
            case "Safety":
                safety = true;
                TD = falta = twoPointTry = twoPointGood = false;
                break;
            case "Falta":
                falta = true;
                TD = safety = twoPointGood = twoPointTry = false;
                break;
            case "2pt NO Good":
                twoPointTry = true;
                TD = safety = falta = twoPointGood = false;
                break;
            case "2pt Good":
                twoPointTry = twoPointGood = true;
                TD = safety = falta = false;
                break;
            case "Jogada Normal":
                TD = safety = falta = twoPointTry = twoPointGood = false;
                break;
            default:
                break;
        }
        resultadoButton.setText(item.getTitle().toString());
        return true;
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
