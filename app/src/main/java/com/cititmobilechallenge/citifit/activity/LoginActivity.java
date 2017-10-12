package com.cititmobilechallenge.citifit.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.cititmobilechallenge.citifit.BuildConfig;
import com.cititmobilechallenge.citifit.R;
import com.cititmobilechallenge.citifit.common.FontHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.concurrent.TimeUnit;

/**
 * Created by Ashwini Kumar
 */
public class LoginActivity extends Activity {

    Button btnLogin = null;
    private FirebaseRemoteConfig firebaseRemoteConfig;

    private String screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        FontHelper.applyFont(this, findViewById(R.id.rl_login_container));
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onLoginClicked();
            }
        });

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        firebaseRemoteConfig.setConfigSettings(configSettings);
        firebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);
        fetchEntry();
    }

    private void onLoginClicked() {
        screen = firebaseRemoteConfig.getString("entry_screen");
        Intent intent;
        if (screen.equalsIgnoreCase("Chose Goal")) {
            intent = new Intent(this, ChosenGoalActivity.class);
        } else {
            intent = new Intent(this, CitiFitDashboardActivity.class);
        }
        startActivity(intent);
    }

    private void fetchEntry() {

        firebaseRemoteConfig.fetch(BuildConfig.DEBUG ? 0 : TimeUnit.HOURS.toSeconds(12))
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            firebaseRemoteConfig.activateFetched();
                        } else {
                            // go with default
                        }
                    }
                });
    }

}
