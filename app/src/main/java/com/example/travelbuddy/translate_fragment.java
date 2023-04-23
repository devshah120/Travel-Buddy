package com.example.travelbuddy;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;


public class translate_fragment extends Fragment {

    private TextInputEditText sourceedt;
    private TextView translatedTV;

    String[] fromLangueges = {"From", "English" , "Afrikaans" , "Arabic", "Belarusian" , "Bulgarian" , "Catalan" , "Czech","Gujarati",
    "Welsh" , "Hindi" , "Urdu"};

    String[] toLangueges = {"To", "English" , "Afrikaans" , "Arabic", "Belarusian" , "Bulgarian" , "Catalan" , "Czech","Gujarati",
            "Welsh" , "Hindi" , "Urdu"};

    private static final int SPEECH_REQUEST_CODE = 0;



    int languegeCode;
    String fromLanguegeCode;
    String toLanguegeCode;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.translate_fragment1, container, false);


        Spinner fromSpinner = view.findViewById(R.id.idFromSpinner);
        Spinner toSpinner = view.findViewById(R.id.idtospinner);
        sourceedt = view.findViewById(R.id.idEdtSource);
        ImageView micIV = view.findViewById(R.id.idIVMic);
        MaterialButton translateBtn = view.findViewById(R.id.idBtnTranslate);
        translatedTV = view.findViewById(R.id.idTVTranslatedTV);
        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fromLanguegeCode = getLanguegeCode(fromLangueges[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> fromAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, fromLangueges);
        fromAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromSpinner.setAdapter(fromAdapter);

        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                toLanguegeCode = getLanguegeCode(toLangueges[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> toAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, toLangueges);
        toAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        toSpinner.setAdapter(toAdapter);

        translateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                translatedTV.setText("");
                if (Objects.requireNonNull(sourceedt.getText()).toString().isEmpty())
                {
                    Toast.makeText(translate_fragment.this.getContext(), "please enter your text to translate", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    translate_fragment.this.translateText(fromLanguegeCode, toLanguegeCode, sourceedt.getText().toString());
                }
            }


        });

        micIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e(TAG, "inside displaySpeechRecognizer() and speechRequestCode = "+SPEECH_REQUEST_CODE);
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                i.putExtra(RecognizerIntent.EXTRA_PROMPT, "speak to convert into text");
                try {

                    startActivityForResult(i, SPEECH_REQUEST_CODE);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(translate_fragment.this.getContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

//        return inflater.inflate(R.layout.translate_fragment1, container, false);
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.e(TAG, "inside onActivityForResult in child fragment. requestCode = "+requestCode+ "resultcode = "+resultCode);
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SPEECH_REQUEST_CODE){
            if(resultCode == RESULT_OK && data!=null){
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                sourceedt.setText(result.get(0));
            }
        }
    }


    @SuppressLint("SetTextI18n")
    private void translateText(String fromLanguagecode, String toLanguageCode, String source)
    {
        translatedTV.setText("downloading model...");
        TranslatorOptions options = new TranslatorOptions.Builder()
                .setSourceLanguage(fromLanguagecode)
                .setTargetLanguage(toLanguageCode)
                .build();
        final Translator translator = Translation.getClient(options);

        DownloadConditions conditions = new DownloadConditions.Builder().build();
        (translator).downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void unused)
            {
                translatedTV.setText("translating..");
                translator.translate(source).addOnSuccessListener(new OnSuccessListener<String>()
                {
                    @Override
                    public void onSuccess(String s)
                    {
                        translatedTV.setText(s);
                    }
                }).addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(translate_fragment.this.getContext(), "fail to translate : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                Toast.makeText(translate_fragment.this.getContext(), "fail to download languege model" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getLanguegeCode(String languege){
        String languegecode = String.valueOf(0);
        switch (languege){
            case "English":
                languegecode = TranslateLanguage.ENGLISH;
                break;
            case "Afrikaans":
                languegecode = TranslateLanguage.AFRIKAANS;
                break;
            case "Arabic":
                languegecode = TranslateLanguage.ARABIC;
                break;
            case "Belarusian":
                languegecode = TranslateLanguage.BELARUSIAN;
                break;
            case "catalan":
                languegecode = TranslateLanguage.CATALAN;
                break;
            case "Czech":
                languegecode = TranslateLanguage.CZECH;
                break;
            case "Welsh":
                languegecode = TranslateLanguage.WELSH;
                break;
            case "Hindi":
                languegecode = TranslateLanguage.HINDI;
                break;
            case "Urdu":
                languegecode = TranslateLanguage.URDU;
                break;
            case "Gujarati":
                languegecode = TranslateLanguage.GUJARATI;
            default:
                languegeCode = 0;


        }

        return languegecode;
    }
}