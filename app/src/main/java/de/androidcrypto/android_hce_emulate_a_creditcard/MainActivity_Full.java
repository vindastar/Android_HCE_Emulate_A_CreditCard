package de.androidcrypto.android_hce_emulate_a_creditcard;

import static de.androidcrypto.android_hce_emulate_a_creditcard.InternalFilesHelper.writeTextToInternalStorage;
import static de.androidcrypto.android_hce_emulate_a_creditcard.Utils.getTimestampMillisFile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class MainActivity_Full extends AppCompatActivity {


    private TextView tvHceServiceLog;
    private com.google.android.material.textfield.TextInputEditText etCardEmulation;
    private Button btnCardEmulation, btnSaveLogfile, btnClearLog, btnImportEmulationData, btnAnonymize;
    private static final String CARD_EMULATION_FILENAME = "cardemulation.txt"; // any changes need to done in MainActivity and HceCcEmulationService
    private String cardEmulation;
    private String hceServiceLog = "";
    private Context contextImportFile; // used for read a file from uri

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.viewMainActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etCardEmulation = findViewById(R.id.etCardEmulation);
        btnCardEmulation = findViewById(R.id.btnCardEmulation);
        btnClearLog = findViewById(R.id.btnClearLog);
        btnSaveLogfile = findViewById(R.id.btnSaveLog);
        btnImportEmulationData = findViewById(R.id.btnImport);
        btnAnonymize = findViewById(R.id.btnAnonymize);
        tvHceServiceLog = findViewById(R.id.tvHceServiceLog);

        /**
         * Note on this setting: Do NOT remove this setting or the app will fail when the screen goes
         * from portrait to landscape and vice versa. The app will then run the onDestroy() method, but
         * the BroadcastReceiver is not registered yet. The only way to solve this issue would be to
         * use a STATIC BroadcastReceiver.
         */
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.registerReceiver(mMessageReceiver, new IntentFilter("HceCcEmulatorService"), Context.RECEIVER_NOT_EXPORTED);
        } else {
            LocalBroadcastManager.getInstance(MainActivity_Full.this).registerReceiver(
                    mMessageReceiver, new IntentFilter("HceCcEmulatorService"));
        }

        /*
        A German Girocard requires these AIDs
        Found tag 0x4F 5 times:
        application Id (AID): a00000005945430100
        application Id (AID): a0000003591010028001
        application Id (AID): d27600002547410100
        application Id (AID): a0000000032020
        application Id (AID): a0000000032010

         */

        btnCardEmulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardEmulation = etCardEmulation.getText().toString();
                boolean success;
                if (cardEmulation.equals("-1")) {
                     success = writeTextToInternalStorage(view.getContext(), CARD_EMULATION_FILENAME, null, cardEmulation);
                     showAToast(view.getContext(), "Success CardEmulation is " + cardEmulation);
                } else if (cardEmulation.equals("0")) {
                    success = writeTextToInternalStorage(view.getContext(), CARD_EMULATION_FILENAME, null, cardEmulation);
                    showAToast(view.getContext(), "Success CardEmulation is " + cardEmulation);
                } else if (cardEmulation.equals("1")) {
                    success = writeTextToInternalStorage(view.getContext(), CARD_EMULATION_FILENAME, null, cardEmulation);
                    showAToast(view.getContext(), "Success CardEmulation is " + cardEmulation);
                } else if (cardEmulation.equals("2")) {
                    success = writeTextToInternalStorage(view.getContext(), CARD_EMULATION_FILENAME, null, cardEmulation);
                    showAToast(view.getContext(), "Success CardEmulation is " + cardEmulation);
                } else if (cardEmulation.equals("3")) {
                    success = writeTextToInternalStorage(view.getContext(), CARD_EMULATION_FILENAME, null, cardEmulation);
                    showAToast(view.getContext(), "Success CardEmulation is " + cardEmulation);
                } else if (cardEmulation.equals("4")) {
                    success = writeTextToInternalStorage(view.getContext(), CARD_EMULATION_FILENAME, null, cardEmulation);
                    showAToast(view.getContext(), "Success CardEmulation is " + cardEmulation);
                } else if (cardEmulation.equals("5")) {
                    success = writeTextToInternalStorage(view.getContext(), CARD_EMULATION_FILENAME, null, cardEmulation);
                    showAToast(view.getContext(), "Success CardEmulation is " + cardEmulation);
                } else if (cardEmulation.equals("6")) {
                    success = writeTextToInternalStorage(view.getContext(), CARD_EMULATION_FILENAME, null, cardEmulation);
                    showAToast(view.getContext(), "Success CardEmulation is " + cardEmulation);
                } else {
                    showAToast(view.getContext(), "FAILURE - Card not allowed");
                }
            }
        });

        btnSaveLogfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //System.out.println("Save the Logfile");
                // get the timestamp for filename
                String fileName = getTimestampMillisFile() + ".txt";
                //System.out.println("Filename: " + fileName);
                // write the messageLog to the file
                writeTextToInternalStorage(view.getContext(), fileName, null, hceServiceLog);
                // delete the log file
                hceServiceLog = "";
                tvHceServiceLog.setText("");
                showAToast(view.getContext(), "Logfile saved to\n" + fileName);
            }
        });

        btnClearLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // delete the log file
                hceServiceLog = "";
                tvHceServiceLog.setText("");
                showAToast(view.getContext(), "Logfile cleared");
            }
        });

        btnImportEmulationData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // import a file in download folder and use it for emulation
                contextImportFile = view.getContext();
                // https://developer.android.com/training/data-storage/shared/documents-files
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                //intent.setType("application/json");
                // Optionally, specify a URI for the file that should appear in the
                // system file picker when it loads.
                boolean pickerInitialUri = false;
                //intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri);
                fileChooserActivityResultLauncher.launch(intent);

/*
                String cardEmulation = "abd";
                boolean success;
                success = writeTextToInternalStorage(view.getContext(), CARD_EMULATION_FILENAME, null, cardEmulation);
                showAToast(view.getContext(), "Success CardEmulation is " + cardEmulation);
                etCardEmulation.setText("file: xxx.json");
*/
            }
        });

        btnAnonymize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_Full.this, AnonymizeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(mMessageReceiver);
    }

    private void appendMessageToLog(String message, String data) {
        hceServiceLog += message + " | " + data + "\n";
        tvHceServiceLog.setText(hceServiceLog);
    }

    private void showAToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private final BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getStringExtra("Message");
            String data = intent.getStringExtra("Data");
            appendMessageToLog(message, data);
        }
    };

    // import an Emulation File

    ActivityResultLauncher<Intent> fileChooserActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent resultData = result.getData();
                        // The result data contains a URI for the document or directory that
                        // the user selected.
                        Uri uri = null;
                        if (resultData != null) {
                            uri = resultData.getData();
                            // Perform operations on the document using its URI.
                            try {
                                cardEmulation = readTextFromUri(uri);
                                //String fileContent = readTextFromUri(uri);
                                if (TextUtils.isEmpty(cardEmulation)) {
                                    showAToast(contextImportFile, "FAILURE on reading the file, aborted");
                                    return;
                                }
                                //Log.d(TAG, "import data:\n" + fileContent);
                                etCardEmulation.setText("Emulation from imported file");
                                boolean success = writeTextToInternalStorage(contextImportFile, CARD_EMULATION_FILENAME, null, cardEmulation);
                                showAToast(contextImportFile, "Success CardEmulation is IMPORTED FILE");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });

    private String readTextFromUri(Uri uri) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream = contextImportFile.getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
        }
        return stringBuilder.toString();
    }

}