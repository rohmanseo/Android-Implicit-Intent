package com.rohman.implicitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_open_web;
    Button btn_open_maps;
    Button btn_share;
    EditText edt_url;
    EditText edt_loc;
    EditText edt_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_open_web = findViewById(R.id.btn_open_web);
        btn_open_maps = findViewById(R.id.btn_open_maps);
        btn_share = findViewById(R.id.btn_share);
        edt_loc = findViewById(R.id.edt_loc);
        edt_share = findViewById(R.id.edt_share);
        edt_url = findViewById(R.id.edt_url);

        btn_open_maps.setOnClickListener(this);
        btn_open_web.setOnClickListener(this);
        btn_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_open_maps:

                String loc = edt_loc.getText().toString();
                Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
                intent = new Intent(Intent.ACTION_VIEW, addressUri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }else {
                    Log.d("ImplicitIntent","Cant Handle This Intent");
                }
                break;

            case R.id.btn_open_web:

                String url = edt_url.getText().toString();
                Uri webpage = Uri.parse(url);
                intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }else{
                    Log.d("ImplicitIntent","Cant Handle This Intent");
                }
                break;

            case R.id.btn_share:

                String share = edt_share.getText().toString();
                String type = "text/plain";
                ShareCompat.IntentBuilder
                        .from(this)
                        .setType(type)
                        .setChooserTitle("SHARE WITH ...")
                        .setText(share)
                        .startChooser();

                break;
        }
    }
}
