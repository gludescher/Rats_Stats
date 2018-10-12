package polirats.ratstats;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import static polirats.ratstats.MainActivity.novoDriveActivity;

public class AjustarGameSituation extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    Button downButton, halfButton, registrarAjustesButton;
    EditText editDistance, editScrimmage, editPontRats, editPontAdv;
    ToggleButton togglePosicao;
    TextView textDownDistance;
    int down, distance, scrimmage, half, pontRats, pontAdv;
    boolean ataque;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustar_game_situation);

        Typeface font = Typeface.createFromAsset(getAssets(), "rats_font.ttf");

        textDownDistance = (TextView) findViewById(R.id.textDownDistance);
        textDownDistance.setTypeface(font);
        //textDownDistance.setText(down + " AND " + distance + ", " +  scrim + "       " + placar + "       " + half + " HALF");
        textDownDistance.setText(GameSituation.printGameSituation());

        downButton = (Button) findViewById(R.id.downButton);
        downButton.setTypeface(font);
        downButton.setOnClickListener(this);

        halfButton = (Button) findViewById(R.id.halfButton);
        halfButton.setTypeface(font);
        halfButton.setOnClickListener(this);

        registrarAjustesButton = (Button) findViewById(R.id.registrarAjustesButton);
        registrarAjustesButton.setOnClickListener(this);
        registrarAjustesButton.setTypeface(font);

        editDistance = (EditText) findViewById(R.id.editDistance);
        editDistance.setTypeface(font);

        editScrimmage = (EditText) findViewById(R.id.editScrimmage);
        editScrimmage.setTypeface(font);

        editPontAdv = (EditText) findViewById(R.id.editPontAdv);
        editPontAdv.setTypeface(font);

        editPontRats = (EditText) findViewById(R.id.editPontRats);
        editPontRats.setTypeface(font);

        togglePosicao = (ToggleButton) findViewById(R.id.togglePosicao);
        togglePosicao.setTypeface(font);
        togglePosicao.setChecked(false);

        down = distance = half = pontAdv = pontRats = scrimmage = -1;

    }

    @Override
    public void onClick(View v) {

        PopupMenu popupDown = new PopupMenu(this, v);
        popupDown.getMenu().add("1st");
        popupDown.getMenu().add("2nd");
        popupDown.getMenu().add("3rd");
        popupDown.getMenu().add("4th");
        popupDown.setOnMenuItemClickListener(this);
        popupDown.inflate(R.menu.popup_menu);

        PopupMenu popupHalf = new PopupMenu(this, v);
        popupHalf.getMenu().add("1");
        popupHalf.getMenu().add("2");
        popupHalf.setOnMenuItemClickListener(this);
        popupHalf.inflate(R.menu.popup_menu);

        switch(v.getId()) {
            case R.id.downButton:
                popupDown.show();
                break;
            case R.id.halfButton:
                popupHalf.show();
                break;
            case R.id.registrarAjustesButton:

                if (!editDistance.getText().toString().equals(""))
                    distance = Integer.parseInt(editDistance.getText().toString());
                if (!editScrimmage.getText().toString().equals(""))
                    scrimmage = Integer.parseInt(editScrimmage.getText().toString());
                if (!editPontAdv.getText().toString().equals(""))
                    pontAdv = Integer.parseInt(editPontAdv.getText().toString());
                if (!editPontRats.getText().toString().equals(""))
                    pontRats = Integer.parseInt(editPontRats.getText().toString());

                ataque = togglePosicao.isChecked();

                if (half >= 0) GameSituation.setHalf(half);
                if (down >= 0) GameSituation.setDown(down);
                if (distance >= 0) GameSituation.setDistance(distance);
                if (scrimmage >= 0) GameSituation.setScrimmage(scrimmage, ataque);
                if (pontAdv >= 0) GameSituation.setPontAdv(pontAdv);
                if (pontRats >= 0) GameSituation.setPontRats(pontRats);

                Toast.makeText(getApplicationContext(), "Ajustes feitos com sucesso!", Toast.LENGTH_SHORT).show();
                Intent in;
                if (novoDriveActivity)
                    in = new Intent (this, NovoDrive.class);
                else
                    in = new Intent (this, NovaJogada.class);
                startActivity(in);
            default:
                break;
        }

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
         switch (item.getTitle().toString()) {
             case "1st":
                 down = 1;
                 downButton.setText("1st");
                 break;
             case "2nd":
                 down = 2;
                 downButton.setText("2nd");
                 break;
             case "3rd":
                 down = 3;
                 downButton.setText("3rd");
                 break;
             case "4th":
                 down = 4;
                 downButton.setText("4th");
                 break;
             case "1":
                 half = 1;
                 halfButton.setText("1");
                 break;
             case "2":
                 half = 2;
                 halfButton.setText("2");
                 break;
             default:
                 break;
         }
         return true;
    }
}
