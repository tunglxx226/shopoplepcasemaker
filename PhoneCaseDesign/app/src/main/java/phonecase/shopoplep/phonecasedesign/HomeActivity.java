package phonecase.shopoplep.phonecasedesign;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnAds1, btnAds2, btnAds3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent i = new Intent();
                i.setClass(HomeActivity.this, AdjustmentActivity_Final.class);
                startActivity(i);
            }
        });


        btnAds1 = (Button) findViewById(R.id.btnAds1);
        btnAds1.setOnClickListener(this);
        btnAds2 = (Button) findViewById(R.id.btnAds2);
        btnAds2.setOnClickListener(this);
        btnAds3 = (Button) findViewById(R.id.btnAds3);
        btnAds3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(R.id.btnAds1 == view.getId()){
            Intent i = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://youtu.be/iVveRQVGZNY?list=PLAkydBeea8dw4cCZmCfnY0aIeZHuvXuCU"));
            startActivity(i);
        } else if(R.id.btnAds2 == view.getId()){
            Intent i = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/ShopOpLep/photos?ref=page_internal"));
            startActivity(i);

        } else if(R.id.btnAds3 == view.getId()){
            Intent i = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/playlist?list=PLAkydBeea8dwTfxCE6lzN-bzY40S7LX52"));
            startActivity(i);
        }
    }
}
