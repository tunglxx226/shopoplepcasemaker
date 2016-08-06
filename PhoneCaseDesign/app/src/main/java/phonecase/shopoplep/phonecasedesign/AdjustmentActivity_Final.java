package phonecase.shopoplep.phonecasedesign;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MASK;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_POINTER_DOWN;
import static android.view.MotionEvent.ACTION_POINTER_UP;
import static android.view.MotionEvent.ACTION_UP;

public class AdjustmentActivity_Final extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener{

    private ImageView img, overlayImage;
    RelativeLayout overlayLayer;
    private android.widget.RelativeLayout.LayoutParams layoutParams;

    // For moving:
    private int _xDelta;
    private int _yDelta;

    // For scaling
    private float scale = 1f;
    private ScaleGestureDetector SGD;
    private Matrix matrix = new Matrix();

    FloatingActionButton fab;

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent i = new Intent();
        i.setClass(AdjustmentActivity_Final.this, HomeActivity.class);
        startActivity(i);
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String uri = this.getIntent().getStringExtra("uri");
        if(uri == null || "".equals(uri))
            uri = "uri";
        String phoneType = this.getIntent().getStringExtra("phoneType");
        if(phoneType == null || "".equals(phoneType))
            phoneType = "pt000000";

        setContentView(R.layout.activity_adjustment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        img = (ImageView) findViewById(R.id.imgToPrint);
        overlayImage = (ImageView) findViewById(R.id.overlayImage);
        img.setOnTouchListener(this);
        loadImg(uri, phoneType);

        overlayLayer = (RelativeLayout) findViewById(R.id.overlayLayer);
        TextView watermark = new TextView(AdjustmentActivity_Final.this);
        watermark.setText("Shop Ốp Lếp");
        watermark.setAlpha(Float.valueOf("0.5"));
        watermark.setTextColor(getResources().getColor(R.color.white_overlay));
        watermark.setTextSize(40);
        overlayLayer.addView(watermark);
        RelativeLayout.LayoutParams layoutParams =
                (RelativeLayout.LayoutParams)watermark.getLayoutParams();
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        watermark.setLayoutParams(layoutParams);

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(this);

        SGD = new ScaleGestureDetector(this,new ScaleListener());
    }

    private void loadImg(String uri, String phoneType){
        overlayImage.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(phoneType, "drawable", getPackageName())));
    }

    /**
     * Moving imageview around sample code:
     */

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        SGD.onTouchEvent(event);
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & ACTION_MASK) {
            case ACTION_DOWN:
                LinearLayout.LayoutParams lParams = (LinearLayout.LayoutParams) view.getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                break;
            case ACTION_UP:
                break;
            case ACTION_POINTER_DOWN:
                break;
            case ACTION_POINTER_UP:
                break;
            case ACTION_MOVE:
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                view.setLayoutParams(layoutParams);
                break;
        }
//        _root.invalidate();
        return true;
    }

    /**
     * End of Moving imageview around sample code:
     */


    /**
     * Get image from gallary sample code:
     */
    private static final int SELECT_PICTURE = 1;
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.fab){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);

        }

    }

    Bitmap bitmap=null;
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                img.setImageURI(selectedImageUri);
            }
        }
    }

    public String getPath(Uri uri)
    {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor == null) return null;
        int column_index =             cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s=cursor.getString(column_index);
        cursor.close();
        return s;
    }

    private class ScaleListener extends ScaleGestureDetector.

            SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 5.0f));

            matrix.setScale(scale, scale);
            img.setImageMatrix(matrix);
            return true;
        }
    }
    /**
     * end of get image from gallery sample code
     */

}
