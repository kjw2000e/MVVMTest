package twobeone.com.mvvmtest.View;

import androidx.appcompat.app.AppCompatActivity;
import twobeone.com.mvvmtest.databinding.ActivityMainBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        View view = mainBinding.getRoot();
        setContentView(view);

        mainBinding.rvGenieMusic.setOnClickListener(onClickListener);
        mainBinding.rvMelonMusic.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            if (v == mainBinding.rvGenieMusic) {
                intent = new Intent(MainActivity.this, GenieActivity.class);
            } else {
                intent = new Intent(MainActivity.this, MelonActivity.class);
            }

            startActivity(intent);
        }
    };
}