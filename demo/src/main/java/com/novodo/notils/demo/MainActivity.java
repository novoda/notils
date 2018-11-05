package com.novodo.notils.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.novoda.notils.devicedetection.AndroidVersion;
import com.novoda.notils.logger.simple.Log;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private AndroidVersion androidVersion;

    @BindView(R.id.android_icon_iv)
    ImageView androidIcon;
    @BindView(R.id.android_version_tv)
    TextView androidVersionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        androidVersion = AndroidVersion.newInstance();
        setAndroidIcon();
        setAndroidText();
    }

    private void setAndroidIcon() {
        Log.d("Setting android icon . . .");
        if (androidVersion.is23Marshmallow()) {
            androidIcon.setImageDrawable(getDrawable(R.drawable.ic_android_marshmallow));
        } else if (androidVersion.is24Nougat() || androidVersion.is25Nougat()) {
            androidIcon.setImageDrawable(getDrawable(R.drawable.ic_android_nougat));
        } else if (androidVersion.is26Oreo() | androidVersion.is27Oreo()) {
            androidIcon.setImageDrawable(getDrawable(R.drawable.ic_android_oreo));
        } else {
            androidIcon.setImageDrawable(getDrawable(R.drawable.ic_android_pie));
        }
    }

    private void setAndroidText() {
        String version = "Android " + androidVersion.versionName() + " (API " + androidVersion.apiLevel() + ")";
        androidVersionText.setText(version);
    }
}
