package polirats.ratstats;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.wifi.hotspot2.pps.Credential;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public class ExportarDados extends AppCompatActivity implements View.OnClickListener {

    GoogleSignInClient mGoogleSignInClient;
    GoogleAccountCredential mCredential;
    GoogleSignInAccount account;

    SignInButton signInButton;
    Button exportarDadosButton, signOutButton;
    EditText editNomeArquivo;
    TextView textSignIn;

    private static final String APPLICATION_NAME = "RatStats";
    File DATA_STORE_DIR;// = new File(getApplicationContext().getFilesDir(),".credentials/polirats.ratstats");
    //private static final File DATA_STORE_DIR = new java.io.File(
            //System.getProperty("user.home"), ".credentials/polirats.ratstats");
    private static FileDataStoreFactory DATA_STORE_FACTORY;
    //private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String[] SCOPES = { SheetsScopes.SPREADSHEETS};



    /*private static HttpTransport HTTP_TRANSPORT;
    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }*/


    static final int RC_SIGN_IN = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exportar_dados);

        Typeface font = Typeface.createFromAsset(getAssets(), "rats_font.ttf");

        signOutButton = (Button) findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(this);

        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(this);

        exportarDadosButton = (Button) findViewById(R.id.exportarDadosButton);
        exportarDadosButton.setOnClickListener(this);

        editNomeArquivo = (EditText) findViewById(R.id.editNomeArquivo);

        textSignIn = (TextView) findViewById(R.id.textSignIn);
        textSignIn.setTypeface(font);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mCredential = GoogleAccountCredential.usingOAuth2(
                getApplicationContext(), Arrays.asList(SCOPES))
                .setBackOff(new ExponentialBackOff());

    }

    protected void onStart() {
        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    @Override
    public void onClick(View v) {
        String fileName;
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.signOutButton:
                signOut();
                break;
            case R.id.exportarDadosButton:
                fileName = editNomeArquivo.getText().toString();
                try {
                    createSheet(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("erro", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    public void authorize() throws IOException, GeneralSecurityException {

        HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        //java.io.File DATA_STORE_DIR = new java.io.File(getApplicationContext().getFilesDir(), ".credentials/sheets.ratstats");
        DATA_STORE_DIR = new File(getApplicationContext().getFilesDir(),".credentials/polirats.ratstats");
        DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        InputStream in = ExportarDados.class.getResourceAsStream("/client_secret.json");

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory, clientSecrets, Arrays.asList(SCOPES))
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        LocalServerReceiver lsr = new LocalServerReceiver();
        String uri;
        if (lsr.getRedirectUri() == null)
            uri = "null";
        else
            uri = lsr.getRedirectUri();
        Toast.makeText(this, uri , Toast.LENGTH_SHORT).show();
        //AuthorizationCodeInstalledApp auth = new AuthorizationCodeInstalledApp(flow, lsr);
        //com.google.api.client.auth.oauth2.Credential credential = auth.authorize(account.getId());
        /*Credential credential = new AuthorizationCodeInstalledApp(
                flow, lsr).authorize(account.getId());
        return credential;*/
    }

    public void createSheetsService() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        authorize();
        /*Credential credential = authorize();
        return new Sheets.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();*/
    }

    public void createSheet(String fileName) throws IOException, GeneralSecurityException {
        String sheetName = fileName.substring(0, fileName.length()-4);

        Spreadsheet requestBody = new Spreadsheet();
        requestBody.setProperties(new SpreadsheetProperties().set("title", sheetName));
        //Sheets sheetsService = createSheetsService();
        createSheetsService();
        //Sheets.Spreadsheets.Create request = sheetsService.spreadsheets().create(requestBody);

        //Spreadsheet response = request.execute();
        Toast.makeText(getApplicationContext(), "Tabela " + sheetName + " criada com sucesso!", Toast.LENGTH_LONG).show();

    }


    private void updateUI (GoogleSignInAccount gAccount) {

        String userEmail;
        if (gAccount != null){
            userEmail = gAccount.getEmail();
            textSignIn.setText("Logado como " + userEmail);
            signInButton.setVisibility(View.GONE);
            signOutButton.setVisibility(View.VISIBLE);
        }
        else {
            textSignIn.setText("Faça login para exportar arquivos");
            signInButton.setVisibility(View.VISIBLE);
            signOutButton.setVisibility(View.GONE);
        }
    }
}
