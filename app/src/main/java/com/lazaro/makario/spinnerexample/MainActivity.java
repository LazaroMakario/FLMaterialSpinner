package com.lazaro.makario.spinnerexample;

/*               ////
                (O O)
  --------oOO----(_)------------------------------
  Created by Làzaro, Makario Felipe on 27/09/2017.
  Email: lazaro.makario@gmail.com
  Complaint  Received, 10 year later...
  ----------------------oOO-----------------------*/

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.lazaro.makario.flmaterialspinner.FLMaterialSpinner;
import com.lazaro.makario.flmaterialspinner.FLMaterialSpinnerInterface;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FLMaterialSpinner flMaterialSpinner;
    Button button_validate;
    Button button_selected_position;
    Button button_selected_by_key;
    Button button_selected_by_value;
    Button button_get_key_selected;
    Button button_get_value_selected;
    Button button_get_item_position_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flMaterialSpinner = (FLMaterialSpinner) findViewById(R.id.flmspinner_test);

        button_validate = (Button) findViewById(R.id.button_validate);
        button_selected_by_key = (Button) findViewById(R.id.button_selected_by_key);
        button_selected_by_value = (Button) findViewById(R.id.button_selected_by_value);
        button_selected_position = (Button) findViewById(R.id.button_selected_position);
        button_get_key_selected = (Button) findViewById(R.id.button_get_key_selected);
        button_get_value_selected = (Button) findViewById(R.id.button_get_value_selected);
        button_get_item_position_selected = (Button) findViewById(R.id.button_get_item_position_selected);

        final Map<String, String> mapFLMaterialSpinner = new LinkedHashMap<>();
        mapFLMaterialSpinner.put("MEX", "MEXICO");
        mapFLMaterialSpinner.put("AFG", "AFGHANISTAN");
        mapFLMaterialSpinner.put("BHS", "BAHAMAS");
        mapFLMaterialSpinner.put("KHM", "CAMBODIA");
        mapFLMaterialSpinner.put("COD", "DEMOCRATIC REPUBLIC OF THE CONGO");
        mapFLMaterialSpinner.put("EGY", "EGYPT");

        flMaterialSpinner.setMapAdapter(mapFLMaterialSpinner);
        flMaterialSpinner.setOnItemClickListener(new FLMaterialSpinnerInterface() {
            @Override
            public void setOnItemClickListener(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "setOnItemClickListener(): position = " + position, Toast.LENGTH_LONG).show();
            }
        });

        button_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!flMaterialSpinner.isValidSelected()) {
                    flMaterialSpinner.setError("This field is required");
                } else {
                    flMaterialSpinner.setError(null);
                }

            }
        });

        button_selected_by_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flMaterialSpinner.setSelectedItemKey("MEX");
            }
        });

        button_selected_by_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flMaterialSpinner.setSelectedItemValue("EGYPT");
            }
        });

        button_get_key_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "getSelectedItemKey() = " + flMaterialSpinner.getSelectedItemKey(), Toast.LENGTH_LONG).show();
            }
        });

        button_get_value_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "getSelectedItemValue() = " + flMaterialSpinner.getSelectedItemValue(), Toast.LENGTH_LONG).show();
            }
        });

        button_get_item_position_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "getSelectedItemPosition() = " + flMaterialSpinner.getSelectedItemPosition(), Toast.LENGTH_LONG).show();
            }
        });

        button_selected_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flMaterialSpinner.setSelectedItemPosition(3);
                flMaterialSpinner.setSpinnerTitle("Test");
            }
        });

    }
}
