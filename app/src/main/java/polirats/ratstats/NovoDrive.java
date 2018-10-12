package polirats.ratstats;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Map;

import static polirats.ratstats.MainActivity.ataqueEmCampo;
import static polirats.ratstats.MainActivity.defesaEmCampo;
import static polirats.ratstats.MainActivity.mapJogador;
import static polirats.ratstats.MainActivity.novoDriveActivity;

public class NovoDrive extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    Button ataqueButton, defesaButton, specialTeamsButton;
    Button ataqueProntoButton, defesaProntaButton;
    ImageButton xButton, rButton, lgButton, cButton, rgButton, qbButton, yButton, zButton, dickButton;
    ImageButton cb1Button, cb2Button, deButton, dtButton, sButton, wButton, mButton, fsButton;
    TextView textX, textR, textLG, textC, textRG, textQB, textY, textZ, textDownDistance;
    TextView textFS, textCB1, textCB2, textW, textM, textS, textDE, textDT;
    String clique;
    EditText editScrimmage;
    ToggleButton togglePosicao;
    //ataqueEmCampo ataqueEmCampo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_drive);

        Typeface font = Typeface.createFromAsset(getAssets(), "rats_font.ttf");
        //ataqueEmCampo = new ataqueEmCampo();

        textDownDistance = (TextView) findViewById(R.id.textDownDistance);
        textDownDistance.setTypeface(font);
        //textDownDistance.setText(down + " AND " + distance + ", " +  scrim + "       " + placar + "       " + half + " HALF");
        String text = "RATS " + GameSituation.printRatsScore() + "-" + GameSituation.printAdvScore() + " ADV   " + GameSituation.printHalf()+ " HALF";
        textDownDistance.setText(text);

        textX = (TextView) findViewById(R.id.textX);
        textX.setTypeface(font);
        textX.setText(ataqueEmCampo[0].getApelido());

        textR = (TextView) findViewById(R.id.textR);
        textR.setTypeface(font);
        textR.setText(ataqueEmCampo[1].getApelido());

        textLG = (TextView) findViewById(R.id.textLG);
        textLG.setTypeface(font);
        textLG.setText(ataqueEmCampo[2].getApelido());

        textC = (TextView) findViewById(R.id.textC);
        textC.setTypeface(font);
        textC.setText(ataqueEmCampo[3].getApelido());

        textRG = (TextView) findViewById(R.id.textRG);
        textRG.setTypeface(font);
        textRG.setText(ataqueEmCampo[4].getApelido());

        textQB = (TextView) findViewById(R.id.textQB);
        textQB.setTypeface(font);
        textQB.setText(ataqueEmCampo[5].getApelido());

        textY = (TextView) findViewById(R.id.textY);
        textY.setTypeface(font);
        textY.setText(ataqueEmCampo[6].getApelido());

        textZ = (TextView) findViewById(R.id.textZ);
        textZ.setTypeface(font);
        textZ.setText(ataqueEmCampo[7].getApelido());


        ataqueButton = (Button) findViewById(R.id.ataqueButton);
        ataqueButton.setOnClickListener(this);
        ataqueButton.setTypeface(font);

        defesaButton = (Button) findViewById(R.id.defesaButton);
        defesaButton.setOnClickListener(this);
        defesaButton.setTypeface(font);

        specialTeamsButton = (Button) findViewById(R.id.specialTeamsButton);
        specialTeamsButton.setOnClickListener(this);
        specialTeamsButton.setTypeface(font);

        ataqueProntoButton = (Button) findViewById(R.id.ataqueProntoButton);
        ataqueProntoButton.setOnClickListener(this);
        ataqueProntoButton.setTypeface(font);

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

        cb1Button = (ImageButton) findViewById(R.id.cb1Button);
        cb1Button.setOnClickListener(this);

        cb2Button = (ImageButton) findViewById(R.id.cb2Button);
        cb1Button.setOnClickListener(this);

        fsButton = (ImageButton) findViewById(R.id.fsButton);
        fsButton.setOnClickListener(this);

        sButton = (ImageButton) findViewById(R.id.sButton);
        sButton.setOnClickListener(this);

        mButton = (ImageButton) findViewById(R.id.mButton);
        mButton.setOnClickListener(this);

        wButton = (ImageButton) findViewById(R.id.wButton);
        wButton.setOnClickListener(this);

        deButton = (ImageButton) findViewById(R.id.deButton);
        deButton.setOnClickListener(this);

        dtButton = (ImageButton) findViewById(R.id.dtButton);
        dtButton.setOnClickListener(this);

        textCB1 = (TextView) findViewById(R.id.textCB1);
        textCB1.setTypeface(font);
        textCB1.setText(defesaEmCampo[0].getApelido());

        textCB2 = (TextView) findViewById(R.id.textCB2);
        textCB2.setTypeface(font);
        textCB2.setText(defesaEmCampo[1].getApelido());

        textFS = (TextView) findViewById(R.id.textFS);
        textFS.setTypeface(font);
        textFS.setText(defesaEmCampo[2].getApelido());

        textS = (TextView) findViewById(R.id.textS);
        textS.setTypeface(font);
        textS.setText(defesaEmCampo[3].getApelido());

        textM = (TextView) findViewById(R.id.textM);
        textM.setTypeface(font);
        textM.setText(defesaEmCampo[4].getApelido());

        textW = (TextView) findViewById(R.id.textW);
        textW.setTypeface(font);
        textW.setText(defesaEmCampo[5].getApelido());

        textDE = (TextView) findViewById(R.id.textDE);
        textDE.setTypeface(font);
        textDE.setText(defesaEmCampo[6].getApelido());

        textDT = (TextView) findViewById(R.id.textDT);
        textDT.setTypeface(font);
        textDT.setText(defesaEmCampo[7].getApelido());

        defesaProntaButton = (Button) findViewById(R.id.defesaProntaButton);
        defesaProntaButton.setTypeface(font);
        defesaProntaButton.setOnClickListener(this);

        editScrimmage = (EditText) findViewById(R.id.editScrimmage);
        editScrimmage.setTypeface(font);

        togglePosicao = (ToggleButton) findViewById(R.id.togglePosicao);
        togglePosicao.setTypeface(font);
        togglePosicao.setChecked(false);

        dickButton = (ImageButton) findViewById(R.id.dickButton);
        dickButton.setOnClickListener(this);

        writeOnFile("", "game_situation.txt");
        writeOnFile(GameSituation.writeGameSituation(), "game_situation.txt");
    }

    public void viewGone (String unidade){

        switch (unidade){
            case "ataque":
                xButton.setVisibility(View.GONE);
                rButton.setVisibility(View.GONE);
                lgButton.setVisibility(View.GONE);
                cButton.setVisibility(View.GONE);
                rgButton.setVisibility(View.GONE);
                qbButton.setVisibility(View.GONE);
                yButton.setVisibility(View.GONE);
                zButton.setVisibility(View.GONE);
                ataqueProntoButton.setVisibility(View.GONE);

                textX.setVisibility(View.INVISIBLE);
                textR.setVisibility(View.INVISIBLE);
                textLG.setVisibility(View.INVISIBLE);
                textC.setVisibility(View.INVISIBLE);
                textRG.setVisibility(View.INVISIBLE);
                textQB.setVisibility(View.INVISIBLE);
                textY.setVisibility(View.INVISIBLE);
                textZ.setVisibility(View.INVISIBLE);
            break;

        case "defesa":
            cb1Button.setVisibility(View.INVISIBLE);
            cb2Button.setVisibility(View.INVISIBLE);
            fsButton.setVisibility(View.INVISIBLE);
            sButton.setVisibility(View.INVISIBLE);
            mButton.setVisibility(View.INVISIBLE);
            wButton.setVisibility(View.INVISIBLE);
            deButton.setVisibility(View.INVISIBLE);
            dtButton.setVisibility(View.INVISIBLE);
            defesaProntaButton.setVisibility(View.INVISIBLE);

            textCB1.setVisibility(View.INVISIBLE);
            textCB2.setVisibility(View.INVISIBLE);
            textFS.setVisibility(View.INVISIBLE);
            textS.setVisibility(View.INVISIBLE);
            textM.setVisibility(View.INVISIBLE);
            textW.setVisibility(View.INVISIBLE);
            textDE.setVisibility(View.INVISIBLE);
            textDT.setVisibility(View.INVISIBLE);
            break;
        case "st":
            break;
        default:
            break;
        }
    }

    public void buttonVisible (String unidade){

        switch (unidade){
            case "ataque":
                xButton.setVisibility(View.VISIBLE);
                rButton.setVisibility(View.VISIBLE);
                lgButton.setVisibility(View.VISIBLE);
                cButton.setVisibility(View.VISIBLE);
                rgButton.setVisibility(View.VISIBLE);
                qbButton.setVisibility(View.VISIBLE);
                yButton.setVisibility(View.VISIBLE);
                zButton.setVisibility(View.VISIBLE);
                ataqueProntoButton.setVisibility(View.VISIBLE);

                if (ataqueEmCampo[0] != null) textX.setVisibility(View.VISIBLE);
                if (ataqueEmCampo[1] != null) textR.setVisibility(View.VISIBLE);
                if (ataqueEmCampo[2] != null) textLG.setVisibility(View.VISIBLE);
                if (ataqueEmCampo[3] != null) textC.setVisibility(View.VISIBLE);
                if (ataqueEmCampo[4] != null) textRG.setVisibility(View.VISIBLE);
                if (ataqueEmCampo[5] != null) textQB.setVisibility(View.VISIBLE);
                if (ataqueEmCampo[6] != null) textY.setVisibility(View.VISIBLE);
                if (ataqueEmCampo[7] != null) textZ.setVisibility(View.VISIBLE);

            case "defesa":
                cb1Button.setVisibility(View.VISIBLE);
                cb2Button.setVisibility(View.VISIBLE);
                fsButton.setVisibility(View.VISIBLE);
                sButton.setVisibility(View.VISIBLE);
                mButton.setVisibility(View.VISIBLE);
                wButton.setVisibility(View.VISIBLE);
                deButton.setVisibility(View.VISIBLE);
                dtButton.setVisibility(View.VISIBLE);
                defesaProntaButton.setVisibility(View.VISIBLE);

                if (defesaEmCampo[0] != null) textCB1.setVisibility(View.VISIBLE);
                if (defesaEmCampo[1] != null) textCB2.setVisibility(View.VISIBLE);
                if (defesaEmCampo[2] != null) textFS.setVisibility(View.VISIBLE);
                if (defesaEmCampo[3] != null) textS.setVisibility(View.VISIBLE);
                if (defesaEmCampo[4] != null) textM.setVisibility(View.VISIBLE);
                if (defesaEmCampo[5] != null) textW.setVisibility(View.VISIBLE);
                if (defesaEmCampo[6] != null) textDE.setVisibility(View.VISIBLE);
                if (defesaEmCampo[7] != null) textDT.setVisibility(View.VISIBLE);
                break;
            case "st":
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        PopupMenu popupWR = new PopupMenu(this, v);
        PopupMenu popupQB = new PopupMenu(this, v);
        PopupMenu popupOL = new PopupMenu(this, v);

        PopupMenu popupDB = new PopupMenu(this, v);
        PopupMenu popupLB = new PopupMenu(this, v);
        PopupMenu popupDL = new PopupMenu(this, v);

        //popula os PopupMenu com todos os jogadores, colocando primeiro os da posição do botão
        for (String key : mapJogador.keySet()) {
            if (mapJogador.get(key).getPosicao().equalsIgnoreCase("WR"))
                popupWR.getMenu().add(key);
        }
        for (String key : mapJogador.keySet()) {
            if (!mapJogador.get(key).getPosicao().equalsIgnoreCase("WR"))
                popupWR.getMenu().add(key);
        }

        for (String key : mapJogador.keySet()) {
            if (mapJogador.get(key).getPosicao().equalsIgnoreCase("OL"))
                popupOL.getMenu().add(key);
        }
        for (String key : mapJogador.keySet()) {
            if (!mapJogador.get(key).getPosicao().equalsIgnoreCase("OL"))
                popupOL.getMenu().add(key);
        }

        for (String key : mapJogador.keySet()) {
            if (mapJogador.get(key).getPosicao().equalsIgnoreCase("QB"))
                popupQB.getMenu().add(key);
        }
        for (String key : mapJogador.keySet()) {
            if (!mapJogador.get(key).getPosicao().equalsIgnoreCase("QB"))
                popupQB.getMenu().add(key);
        }

        for (String key : mapJogador.keySet()) {
            if (mapJogador.get(key).getPosicao().equalsIgnoreCase("DL"))
                popupDL.getMenu().add(key);
        }
        for (String key : mapJogador.keySet()) {
            if (!mapJogador.get(key).getPosicao().equalsIgnoreCase("DL"))
                popupDL.getMenu().add(key);
        }

        for (String key : mapJogador.keySet()) {
            if (mapJogador.get(key).getPosicao().equalsIgnoreCase("LB"))
                popupLB.getMenu().add(key);
        }
        for (String key : mapJogador.keySet()) {
            if (!mapJogador.get(key).getPosicao().equalsIgnoreCase("LB"))
                popupLB.getMenu().add(key);
        }

        for (String key : mapJogador.keySet()) {
            if (mapJogador.get(key).getPosicao().equalsIgnoreCase("DB"))
                popupDB.getMenu().add(key);
        }
        for (String key : mapJogador.keySet()) {
            if (!mapJogador.get(key).getPosicao().equalsIgnoreCase("DB"))
                popupDB.getMenu().add(key);
        }

        popupWR.setOnMenuItemClickListener(this);
        popupWR.inflate(R.menu.popup_menu);

        popupOL.setOnMenuItemClickListener(this);
        popupWR.inflate(R.menu.popup_menu);

        popupQB.setOnMenuItemClickListener(this);
        popupQB.inflate(R.menu.popup_menu);

        popupDB.setOnMenuItemClickListener(this);
        popupDB.inflate(R.menu.popup_menu);

        popupDL.setOnMenuItemClickListener(this);
        popupDL.inflate(R.menu.popup_menu);

        popupLB.setOnMenuItemClickListener(this);
        popupLB.inflate(R.menu.popup_menu);

        switch (v.getId()){
            case R.id.ataqueButton:
                buttonVisible("ataque");
                viewGone("defesa");
                viewGone("st");
                togglePosicao.setVisibility(View.VISIBLE);
                editScrimmage.setVisibility(View.VISIBLE);
                break;
            case R.id.defesaButton:
                buttonVisible("defesa");
                viewGone("ataque");
                viewGone("st");
                togglePosicao.setVisibility(View.VISIBLE);
                editScrimmage.setVisibility(View.VISIBLE);
                break;
            case R.id.specialTeamsButton:
                buttonVisible("st");
                viewGone("ataque");
                viewGone("defesa");
                break;

            case R.id.xButton:
                popupWR.show();
                clique = "x";
                break;

            case R.id.rButton:
                popupWR.show();
                clique = "r";
                break;

            case R.id.lgButton:
                popupOL.show();
                clique = "lg";
                break;

            case R.id.cButton:
                popupOL.show();
                clique = "c";
                break;

            case R.id.rgButton:
                popupOL.show();
                clique = "rg";
                break;

            case R.id.qbButton:
                popupQB.show();
                clique = "qb";
                break;

            case R.id.yButton:
                popupWR.show();
                clique = "y";
                break;

            case R.id.zButton:
                popupWR.show();
                clique = "z";
                break;

            //======= DEFESA =========
            case R.id.cb1Button:
                popupDB.show();
                clique = "cb1";
                break;

            case R.id.cb2Button:
                popupDB.show();
                clique = "cb2";
                break;

            case R.id.fsButton:
                popupDB.show();
                clique = "fs";
                break;

            case R.id.sButton:
                popupLB.show();
                clique = "s";
                break;

            case R.id.mButton:
                popupLB.show();
                clique = "m";
                break;

            case R.id.wButton:
                popupLB.show();
                clique = "w";
                break;

            case R.id.deButton:
                popupDL.show();
                clique = "de";
                break;

            case R.id.dtButton:
                popupDL.show();
                clique = "dt";
                break;

            case R.id.dickButton:
                novoDriveActivity = true;
                Intent in7 = new Intent(this, AjustarGameSituation.class);
                startActivity(in7);
                break;

            case R.id.ataqueProntoButton:
                //String teste = ataqueEmCampo[0].getApelido()+", "+ ataqueEmCampo[1].getApelido() + ", " + ataqueEmCampo[2].getApelido()+", " + ataqueEmCampo[3].getApelido() + ", "
                //      + ataqueEmCampo[4].getApelido()+", " + ataqueEmCampo[5].getApelido()+", " + ataqueEmCampo[6].getApelido()+", " + ataqueEmCampo[7].getApelido()+", ";
                //Toast.makeText(getApplicationContext(), teste, Toast.LENGTH_SHORT).show();
                String scrim;
                boolean ataque;
                int scrimmage;

                ataque = togglePosicao.isChecked();
                scrim = editScrimmage.getText().toString();

                if (scrim.equals(""))
                    scrimmage = 10;
                else
                    scrimmage = Integer.parseInt(scrim);

                GameSituation.nextDrive(scrimmage, ataque);

                Intent in = new Intent(this, NovaJogada.class);
                //in.putExtra("ataqueEmCampo", ataqueEmCampo);
                startActivity(in);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        String apelido = item.getTitle().toString();
        //Toast.makeText(getApplicationContext(), apelido + " seleiconado!", Toast.LENGTH_SHORT).show();
        Jogador J;
        switch(clique){
            case "x":
                J = mapJogador.get(apelido);
                ataqueEmCampo[0] = J;
                textX.setText(ataqueEmCampo[0].getApelido());
                textX.setVisibility(View.VISIBLE);
                break;
            case "r":
                J = mapJogador.get(apelido);
                ataqueEmCampo[1] = J;
                textR.setText(ataqueEmCampo[1].getApelido());
                textR.setVisibility(View.VISIBLE);
                break;
            case "lg":
                J = mapJogador.get(apelido);
                ataqueEmCampo[2] = J;
                textLG.setText(ataqueEmCampo[2].getApelido());
                textLG.setVisibility(View.VISIBLE);
                break;
            case "c":
                J = mapJogador.get(apelido);
                ataqueEmCampo[3] = J;
                textC.setText(ataqueEmCampo[3].getApelido());
                textC.setVisibility(View.VISIBLE);
                break;
            case "rg":
                J = mapJogador.get(apelido);
                ataqueEmCampo[4] = J;
                textRG.setText(ataqueEmCampo[4].getApelido());
                textRG.setVisibility(View.VISIBLE);
                break;
            case "qb":
                J = mapJogador.get(apelido);
                ataqueEmCampo[5] = J;
                textQB.setText(ataqueEmCampo[5].getApelido());
                textQB.setVisibility(View.VISIBLE);
                break;
            case "y":
                J = mapJogador.get(apelido);
                ataqueEmCampo[6] = J;
                textY.setText(ataqueEmCampo[6].getApelido());
                textY.setVisibility(View.VISIBLE);
                break;
            case "z":
                J = mapJogador.get(apelido);
                ataqueEmCampo[7] = J;
                textZ.setText(ataqueEmCampo[7].getApelido());
                textZ.setVisibility(View.VISIBLE);
                break;
            case "cb1":
                J = mapJogador.get(apelido);
                defesaEmCampo[0] = J;
                textCB1.setText(defesaEmCampo[0].getApelido());
                textCB1.setVisibility(View.VISIBLE);
                break;
            case "cb2":
                J = mapJogador.get(apelido);
                defesaEmCampo[1] = J;
                textCB2.setText(defesaEmCampo[1].getApelido());
                textCB2.setVisibility(View.VISIBLE);
                break;
            case "fs":
                J = mapJogador.get(apelido);
                defesaEmCampo[2] = J;
                textFS.setText(defesaEmCampo[2].getApelido());
                textFS.setVisibility(View.VISIBLE);
                break;
            case "s":
                J = mapJogador.get(apelido);
                defesaEmCampo[3] = J;
                textS.setText(defesaEmCampo[3].getApelido());
                textS.setVisibility(View.VISIBLE);
                break;
            case "m":
                J = mapJogador.get(apelido);
                defesaEmCampo[4] = J;
                textM.setText(defesaEmCampo[4].getApelido());
                textM.setVisibility(View.VISIBLE);
                break;
            case "w":
                J = mapJogador.get(apelido);
                defesaEmCampo[5] = J;
                textW.setText(defesaEmCampo[5].getApelido());
                textW.setVisibility(View.VISIBLE);
                break;
            case "de":
                J = mapJogador.get(apelido);
                defesaEmCampo[6] = J;
                textDE.setText(defesaEmCampo[6].getApelido());
                textDE.setVisibility(View.VISIBLE);
                break;
            case "dt":
                J = mapJogador.get(apelido);
                defesaEmCampo[7] = J;
                textDT.setText(defesaEmCampo[7].getApelido());
                textDT.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
        //Toast.makeText(getApplicationContext(), ataqueEmCampo[0].getApelido() + " selecionado!", Toast.LENGTH_SHORT).show();
        return true;
    }

    public void writeOnFile (String text, String fileName) {

        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(fileName, MODE_PRIVATE);
            //outputStream = new FileOutputStream(jogadores, true);
            outputStream.write(text.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
