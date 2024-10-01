package de.androidcrypto.android_hce_emulate_a_creditcard;

import static de.androidcrypto.android_hce_emulate_a_creditcard.InternalFilesHelper.writeTextToInternalStorage;
import static de.androidcrypto.android_hce_emulate_a_creditcard.Utils.bytesToHexNpe;
import static de.androidcrypto.android_hce_emulate_a_creditcard.Utils.hexStringToByteArray;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnonymizeActivity extends AppCompatActivity {

    private TextView tvHceServiceLog;
    private com.google.android.material.textfield.TextInputEditText etCardEmulation;
    private Button btnCardEmulation, btnSaveLogfile, btnClearLog, btnImportEmulationData, btnAnonymize;
    private static final String CARD_EMULATION_FILENAME = "cardemulation.txt"; // any changes need to done in MainActivity and HceCcEmulationService
    private String cardEmulation;
    private String hceServiceLog = "";
    private Context contextImportFile; // used for read a file from uri
    private Gson gson;
    private Aids_Model aidsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_anonymizer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.viewAnonymizeActivity), (v, insets) -> {
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
            }
        });


        btnAnonymize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Girocard PAN: 4666416802046846003
                // The first 4 bytes (8 numbers) are BIN:                  46664168
                // followed by 5 bytes (10 numbers) of the account number: 0204684600
                // the last number is the card number ? :                  3
                // in total 19 numbers
                // change the data between position 8 and 17

                String oldAccountNumber = "0204684600";
                String newAccountNumber = "0204692600";
                int numberOfExchanges = 0;

                System.out.println("====== START SEARCHING & REPLACE ======");
                numberOfExchanges = findStringInAidsModel(aidsModel, oldAccountNumber, newAccountNumber);
                System.out.println("numberOfExchanges: " + numberOfExchanges);
                System.out.println("====== END SEARCHING & REPLACE ======");



            }
        });

    }

    // searches just in responses
    private Aids_Model replaceStringInAidsModel(Aids_Model aidsModel, String oldString, String newString) {
        if (oldString.length() != newString.length()) {
            System.out.println("length differs between oldString and newString, aborted");
            return null;
        }
        int numberOfReplacements = 0;

        int numberOfAids = aidsModel.getNumberOfAids();
        byte[] getSelectPpseCommand = aidsModel.getSelectPpseCommand();
        byte[] getSelectPpseResponse = aidsModel.getSelectPpseResponse();
        List<Aid_Model> aidModelList;
        List<byte[]> oldCommandsList;
        Aids_Model newAidsModel;

        // although the PPSE won't have an entry for the PAN I'm searching for it here as well

        String replacedString = bytesToHexNpe(aidsModel.getSelectPpseResponse()).replaceAll(oldString, newString);
        aidsModel.setSelectPpseResponse(hexStringToByteArray(replacedString));
        aidModelList = new ArrayList<>();
        newAidsModel = new Aids_Model(numberOfAids);
        Aid_Model aidSingleModel;
        byte[] aid;
        // go through all aids and all records
        for (int i = 0; i < numberOfAids; i++) {
            aidSingleModel = aidsModel.getAidModel().get(i);
            aid = aidSingleModel.getAid();
            // todo get all data from old AIDS_MODEL and save it in new AIDS_MODEL variable
            oldCommandsList = aidSingleModel.getCommand();
            List<byte[]> oldResponsesList;
            List<byte[]> newResponsesList;
            oldResponsesList = aidSingleModel.getRespond();
            newResponsesList = new ArrayList<>();
            // now searching in each (AID Model) oldResponsesList entry
            int numberOfResponses = aidSingleModel.getRespond().size();
            System.out.println("In AIDS_MODEL index " + i + " are " + numberOfResponses + " responses");
            for (int j = 0; j < numberOfResponses; j++) {
                String replacedResponseString = bytesToHexNpe(oldResponsesList.get(j)).replaceAll(oldString, newString);
                // write back
                newResponsesList.add(hexStringToByteArray(replacedResponseString));
            }
            // write the new list to the new entry
            newAidsModel.setSelectPpseCommand(getSelectPpseCommand);
            newAidsModel.setSelectPpseResponse(getSelectPpseResponse);
            aidModelList.add(aidSingleModel);
            System.out.println("==== end of responses ====");
        }
        System.out.println("==== end of aid ====");
        // add the aidModel list
        //newAidsModel.setAidModel(aidS);
        return newAidsModel;
    }

    // searches just in responses
    private int findStringInAidsModel(Aids_Model aidsModel, String oldString, String newString) {
        int numberOfReplacements = 0;
        int numberOfAids = aidsModel.getNumberOfAids();
        // although the PPSE won't have an entry for the PAN I'm searching for it here as well
        int pos;
        pos = findASubStringInString(bytesToHexNpe(aidsModel.getSelectPpseResponse()), oldString);
        if (pos > -1) numberOfReplacements ++;
        System.out.println("pos in getSelectPpseResponse: " + pos);

        // go through all aids and all records
        for (int i = 0; i < numberOfAids; i++) {
            Aid_Model aidModel = aidsModel.getAidModel().get(i);
            // now searching in each AID Model
            int numberOfResponses = aidModel.getRespond().size();
            System.out.println("In AIDS_MODEL index " + i + " are " + numberOfResponses + " responses");
            for (int j = 0; j < numberOfResponses; j++) {
                pos = findASubStringInString(bytesToHexNpe(aidModel.getRespond().get(j)), oldString);
                if (pos > -1) numberOfReplacements ++;
                System.out.println("pos in findASubStringInString: " + pos);
            }
            System.out.println("==== end of responses ====");
        }
        System.out.println("==== end of aid ====");
        return numberOfReplacements;
    }

    private int findASubStringInString(String fullString, String searchString) {
        return fullString.indexOf(searchString);
    }

    private void showAToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

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

                                gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
                                aidsModel = convertJsonToAidsModelClass(cardEmulation);
                                if (aidsModel == null) {
                                    System.out.println("FAILURE on using imported file");
                                }
                                aidsModel.dump();

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
        //try (InputStream inputStream = getContentResolver().openInputStream(uri);
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


    private Aids_Model convertJsonToAidsModelClass(String jsonResponse) {
        try {
            Aids_Model aidsModel = gson.fromJson(jsonResponse, Aids_Model.class);
            return aidsModel;
        } catch (Exception e) {
            return null;
        }
    }

}