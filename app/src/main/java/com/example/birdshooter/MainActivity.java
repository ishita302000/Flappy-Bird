package com.example.birdshooter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private  boolean isMute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , GameActivity.class));
            }
        });

        TextView highScoreTxt = findViewById(R.id.highscore);

        final SharedPreferences prefs = getSharedPreferences("game" , MODE_PRIVATE);
        highScoreTxt.setText("HighScore: " + prefs.getInt("highScore" , 0));

        prefs.getBoolean("isMute" , false);

        final ImageView volumectrl = findViewById(R.id.volume);

        if(isMute)
            volumectrl.setImageResource(R.drawable.ic_baseline_volume_off_24);
        else
            volumectrl.setImageResource(R.drawable.ic_baseline_volume_up_24);

        volumectrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMute = !isMute;
                if(isMute)
                    volumectrl.setImageResource(R.drawable.ic_baseline_volume_off_24);
                else
                    volumectrl.setImageResource(R.drawable.ic_baseline_volume_up_24);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isMute" , isMute);
                editor.apply();
            }
        });

    }
}