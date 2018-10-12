package polirats.ratstats;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Map;

import static polirats.ratstats.MainActivity.ataqueEmCampo;
import static polirats.ratstats.MainActivity.mapJogador;
import static polirats.ratstats.MainActivity.novoDriveActivity;

public class NovaJogada extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    Button passeButton, corridaButton, sackButton, badSnapButton;
    Button specialTeamsButton, fimDoPeriodoButton, faltaButton;
    ImageButton xButton, rButton, lgButton, cButton, rgButton, qbButton, yButton, zButton, dickButton;
    TextView textX, textR, textLG, textC, textRG, textQB, textY, textZ, textDownDistance;
    String clique;
    //int down, distance, scrim, placar, half;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_jogada);

        Typeface font = Typeface.createFromAsset(getAssets(), "rats_font.ttf");

        //down = GameSituation.getDown();
        //distance = GameSituation.getDistance();
        //scrim = GameSituation.getScrim();
        //placar = GameSituation.getPlacar();
        //half = GameSituation.getHalf();

        textDownDistance = (TextView) findViewById(R.id.textDownDistance);
        textDownDistance.setTypeface(font);
        //textDownDistance.setText(down + " AND " + distance + ", " +  scrim + "       " + placar + "       " + half + " HALF");
        textDownDistance.setText(GameSituation.printGameSituation());

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

        passeButton = (Button) findViewById(R.id.passeButton);
        passeButton.setOnClickListener(this);
        passeButton.setTypeface(font);

        corridaButton = (Button) findViewById(R.id.corridaButton);
        corridaButton.setOnClickListener(this);
        corridaButton.setTypeface(font);

        sackButton = (Button) findViewById(R.id.sackButton);
        sackButton.setOnClickListener(this);
        sackButton.setTypeface(font);

        badSnapButton = (Button) findViewById(R.id.badSnapButton);
        badSnapButton.setOnClickListener(this);
        badSnapButton.setTypeface(font);

        specialTeamsButton = (Button) findViewById(R.id.specialTeamsButton);
        specialTeamsButton.setOnClickListener(this);
        specialTeamsButton.setTypeface(font);

        fimDoPeriodoButton = (Button) findViewById(R.id.fimDoPeriodoButton);
        fimDoPeriodoButton.setOnClickListener(this);
        fimDoPeriodoButton.setTypeface(font);

        faltaButton = (Button) findViewById(R.id.faltaButton);
        faltaButton.setOnClickListener(this);
        faltaButton.setTypeface(font);

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

        dickButton = (ImageButton) findViewById(R.id.dickButton);
        dickButton.setOnClickListener(this);

        writeOnFile("", "game_situation.txt");
        writeOnFile(GameSituation.writeGameSituation(), "game_situation.txt");
    }

    @Override
    public void onClick(View v) {

        PopupMenu popupWR = new PopupMenu(this, v);
        PopupMenu popupQB = new PopupMenu(this, v);
        PopupMenu popupOL = new PopupMenu(this, v);

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

        popupWR.setOnMenuItemClickListener(this);
        popupWR.inflate(R.menu.popup_menu);

        popupOL.setOnMenuItemClickListener(this);
        popupWR.inflate(R.menu.popup_menu);

        popupQB.setOnMenuItemClickListener(this);
        popupQB.inflate(R.menu.popup_menu);

        switch (v.getId()){
            case R.id.passeButton:
                Intent in0 = new Intent(this, NovoPasse.class);
                startActivity(in0);
                break;

            case R.id.corridaButton:
                Intent in1 = new Intent(this, NovaCorrida.class);
                startActivity(in1);
                break;

            case R.id.sackButton:
                Intent in2 = new Intent(this, NovoSack.class);
                startActivity(in2);
                break;

            case R.id.badSnapButton:
                Intent in3 = new Intent(this, NovoBadSnap.class);
                startActivity(in3);
                break;

            case R.id.specialTeamsButton:
                Intent in4 = new Intent(this, NovoDrive.class);
                startActivity(in4);
                break;

            case R.id.faltaButton:
                Intent in5 = new Intent(this, NovaFalta.class);
                startActivity(in5);
                break;

            case R.id.fimDoPeriodoButton:
                String textHalf = GameSituation.nextHalf();
                Toast.makeText(getApplicationContext(), textHalf, Toast.LENGTH_LONG).show();
                Intent in6;
                if (GameSituation.getHalf() == 2)
                    in6 = new Intent(this, NovoDrive.class);
                else
                    in6 = new Intent(this, MainActivity.class);
                startActivity(in6);
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
            case R.id.dickButton:
                novoDriveActivity = false;
                Intent in7 = new Intent(this, AjustarGameSituation.class);
                startActivity(in7);
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        String apelido = item.getTitle().toString();

        Toast.makeText(getApplicationContext(), apelido + " selecionado", Toast.LENGTH_SHORT).show();

        Jogador J;
        switch(clique) {
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
            default:
                break;
        }
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
