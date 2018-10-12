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

import static java.lang.StrictMath.abs;
import static polirats.ratstats.MainActivity.ataqueEmCampo;
import static polirats.ratstats.NovaPartida.fileName;

public class NovoBadSnap extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    EditText editJds;
    Button registrarBadSnapButton, resultadoButton;
    ImageButton xButton, rButton, lgButton, cButton, rgButton, qbButton, yButton, zButton;
    TextView textDownDistance, textX, textR, textLG, textC, textRG, textQB, textY, textZ;
    int down, distance, scrimmage, half, pontRats, pontAdv, jds;
    boolean corrida, passe, completo, incompleto, interceptado, drop, sack, badSnap, TD, safety, falta, twoPointTry, twoPointGood;
    JogadaAtaque snapRuim;

    String jdsConquistadas, apelido, text, drive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_bad_snap);

        Typeface font = Typeface.createFromAsset(getAssets(), "rats_font.ttf");

        jdsConquistadas = "";

        resultadoButton = (Button) findViewById(R.id.resultadoButton);
        resultadoButton.setOnClickListener(this);
        resultadoButton.setTypeface(font);

        textDownDistance = (TextView) findViewById(R.id.textDownDistance);
        textDownDistance.setText(GameSituation.printGameSituation());
        textDownDistance.setTypeface(font);

        registrarBadSnapButton = (Button) findViewById(R.id.registrarBadSnapButton);
        registrarBadSnapButton.setTypeface(font);
        registrarBadSnapButton.setOnClickListener(this);

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

        PopupMenu popupResultado = new PopupMenu(this, v);
        String vetorResultado[] = new String[]{"Safety", "Falta", "2pt NO Good", "Jogada Normal"};
        for (String r : vetorResultado)
            popupResultado.getMenu().add(r);
        popupResultado.setOnMenuItemClickListener(this);
        popupResultado.inflate(R.menu.popup_menu);

        switch (v.getId()){
            case R.id.resultadoButton:
                popupResultado.show();
            case R.id.xButton:
                break;
            case R.id.rButton:
                break;
            case R.id.lgButton:
                break;
            case R.id.cButton:
                break;
            case R.id.rgButton:
                break;
            case R.id.qbButton:
                break;
            case R.id.yButton:
                break;
            case R.id.zButton:
                break;
            case R.id.registrarBadSnapButton:
                jdsConquistadas = editJds.getText().toString();
                if (!jdsConquistadas.equals("")) {
                    drive = Integer.toString(GameSituation.getDrive());
                    jds = -abs(Integer.parseInt(jdsConquistadas));
                    down = GameSituation.getDown();
                    distance = GameSituation.getDistance();
                    scrimmage = GameSituation.getScrim();
                    half = GameSituation.getHalf();
                    pontRats = GameSituation.getRatsScore();
                    pontAdv = GameSituation.getAdvScore();
                    TD = corrida = passe = completo = incompleto = interceptado = sack= false;
                    badSnap = true;

                    snapRuim = new JogadaAtaque(drive, down, distance, scrimmage, jds, half, pontRats, pontAdv,
                            corrida, null, passe, completo, incompleto, drop, interceptado, null, null,
                            sack, badSnap, TD, safety,  twoPointTry, twoPointGood,  falta, "na", "na", 0, null, null, ataqueEmCampo);
                    text = snapRuim.getCsvText();
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

                    Toast.makeText(getApplicationContext(), "Bad Snap (" + jds + " jardas) registrado!" + especial, Toast.LENGTH_LONG).show();

                    Intent in;
                    if (safety || GameSituation.getDown() > 4 || twoPointTry)
                        in = new Intent(this, NovoDrive.class);
                    else if (falta)
                        in = new Intent(this, NovaFalta.class);
                    else
                        in = new Intent(this, NovaJogada.class);
                    startActivity(in);
                }
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getTitle().toString()){
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
            case "Jogada Normal":
                TD = safety = falta = twoPointTry = twoPointGood = false;
                break;
            default:
                break;
        }
        resultadoButton.setText(item.getTitle().toString());
        return true;
    }
}

