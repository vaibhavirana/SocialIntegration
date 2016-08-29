package com.webmyne.sample;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FacebookHashKeyActivity extends AppCompatActivity {


    TextView tv_keyhash;
    Button btn_keyhash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook_hashkey);

        tv_keyhash = (TextView) findViewById(R.id.tv_keyhash);
        btn_keyhash = (Button) findViewById(R.id.btn_keyhash);

        btn_keyhash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printHahKey();
            }
        });
    }

    private void printHahKey() {
        try {
            PackageInfo info=getPackageManager().getPackageInfo(getPackageName(),PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));

                tv_keyhash.setText(Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
