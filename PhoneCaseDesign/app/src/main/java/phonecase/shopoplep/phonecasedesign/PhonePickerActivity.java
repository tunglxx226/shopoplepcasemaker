package phonecase.shopoplep.phonecasedesign;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import phonecase.shopoplep.phonecasedesign.database.DBHelper;

public class PhonePickerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{


    private Spinner mSpinner;
    private Button okBtn;
    List<String> phoneType = new ArrayList<String>();
    List<String> phoneTypeId = new ArrayList<String>();
    String chosenPhoneTypeId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_picker);
        DBHelper mDB = null;
        try {
            mDB = new DBHelper(PhonePickerActivity.this);
            mDB.createDataBase();
            mDB.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }


        phoneType = new ArrayList<String>();
        phoneType.addAll(mDB.getAllPhoneType());
        phoneTypeId = new ArrayList<String>();
        phoneTypeId.addAll(mDB.getAllPhoneTypeId());

        mSpinner = (Spinner)findViewById(R.id.spinner);
        mSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, phoneType);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(dataAdapter);

        okBtn = (Button) findViewById(R.id.okBtn);
        okBtn.setOnClickListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        chosenPhoneTypeId = phoneTypeId.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        chosenPhoneTypeId = phoneTypeId.get(0);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.okBtn){
            Intent intent = new Intent();
            intent.setClass(PhonePickerActivity.this, AdjustmentActivity_Final.class);
            intent.putExtra("phoneType", chosenPhoneTypeId);
            startActivity(intent);
            this.finish();
        }
    }
}
