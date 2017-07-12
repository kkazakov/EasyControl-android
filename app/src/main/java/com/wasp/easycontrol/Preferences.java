package com.wasp.easycontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_preferences)
public class Preferences extends AppCompatActivity {

    @Pref
    MyPrefs_ myPrefs;

    @ViewById
    EditText txtServer;

    @AfterViews
    void afterViews() {
        setTitle("Preferences");

        txtServer.setText(myPrefs.url().getOr(""));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
    }

    @Click(R.id.buttonSave)
    void save() {

        myPrefs.edit().url().put(txtServer.getText().toString()).apply();

        setResult(RESULT_OK);
        finish();
    }

}
