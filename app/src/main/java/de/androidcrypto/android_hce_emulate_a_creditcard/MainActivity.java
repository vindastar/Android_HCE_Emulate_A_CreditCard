package de.androidcrypto.android_hce_emulate_a_creditcard;

import static de.androidcrypto.android_hce_emulate_a_creditcard.InternalFilesHelper.writeTextToInternalStorage;
import static de.androidcrypto.android_hce_emulate_a_creditcard.Utils.getTimestampMillisFile;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity {


    private TextView tvHceServiceLog;
    private com.google.android.material.textfield.TextInputEditText etCardEmulation;
    private Button btnCardEmulation, btnSaveLogfile, btnClearLog;
    private static final String CARD_EMULATION_FILENAME = "cardemulation.txt"; // any changes need to done in MainActivity and HceCcEmulationService
    private String hceServiceLog = "";

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
        tvHceServiceLog = findViewById(R.id.tvHceServiceLog);

        /**
         * Note on this setting: Do NOT remove this setting or the app will fail when the screen goes
         * from portrait to landscape and vice versa. The app will then run the onDestroy() method, but
         * the BroadcastReceiver is not registered. The only way would be to use a STATIC BroadcastReceiver.
         */
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.registerReceiver(mMessageReceiver, new IntentFilter("HceCcEmulatorService"), Context.RECEIVER_NOT_EXPORTED);
        } else {
            LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(
                    mMessageReceiver, new IntentFilter("HceCcEmulatorService"));
        }

        btnCardEmulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cardEmulation = etCardEmulation.getText().toString();
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
    }

    @Override
    public void onResume() {
        super.onResume();


    /*
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(
                mMessageReceiver, new IntentFilter("HceCcEmulatorService"));

     */
    }

    /*
        protected void onPostResume() {
            super.onPostResume();
            registerReceiver(mMessageReceiver,  new IntentFilter("HceCcEmulatorService"));
        }
    */
    @Override
    public void onPause() {
        super.onPause();
        //this.unregisterReceiver(mMessageReceiver );
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

}