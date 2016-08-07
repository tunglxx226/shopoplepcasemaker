package phonecase.shopoplep.phonecasedesign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;


public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent();
                i.setClass(LoadingActivity.this, HomeActivity.class);
                startActivity(i);
                LoadingActivity.this.finish();
            }
        }, 2*1000);

    }
}
