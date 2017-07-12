package com.wasp.easycontrol;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;


@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private static final int CODE_PREFS = 1900;
    @ViewById
    Button buttonMain;

    @ViewById
    Button buttonSettings;
    private int state = -1;

    @Pref
    MyPrefs_ myPrefs;

    @AfterViews
    void afterViews() {


        updateStatus();


    }

    private void updateStatus() {

        buttonMain.setText("Loading ...");

        String url = myPrefs.url().getOr("");

        if (!url.equals("")) {
            url = "http://" + url + "/control?cmd=status,gpio,12";
        }

        Ion.with(this)
                .load(url)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        // do stuff with the result or error

                        if (e != null || result == null || !result.has("state")) {
                            buttonMain.setText("Error");
                            state = -1;
                        } else {

                            state = result.get("state").getAsInt();

                            if (state == 0) {
                                buttonMain.setText("Off");
                            } else {
                                buttonMain.setText("On");
                            }
                        }
                    }
                });

    }

    @Click(R.id.buttonMain)
    void pressed() {
        System.out.println("pressed");
    }

    @Click(R.id.buttonSettings)
    void settings() {
        Preferences_.intent(this).startForResult(CODE_PREFS);
    }

    @OnActivityResult(CODE_PREFS)
    void onActivityResult(int resultCode) {

        if (resultCode == RESULT_OK) {
            updateStatus();
        }

    }

}
