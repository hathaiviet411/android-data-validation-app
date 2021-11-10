package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.material.textfield.TextInputEditText;

import io.github.muddz.styleabletoast.StyleableToast;

public class MainActivity extends AppCompatActivity {
    // Initial variables
    RadioGroup propertyTypeRadioGroup, furnitureTypeRadioGroup;
    TextInputEditText bedrooms, date, monthlyRentPrice;
    Button submitBtn;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign variables
        propertyTypeRadioGroup = findViewById(R.id.propertyTypeRadioGroup);
        furnitureTypeRadioGroup = findViewById(R.id.furnitureTypeRadioGroup);
        bedrooms = findViewById(R.id.bedrooms);
        date = findViewById(R.id.date);
        monthlyRentPrice = findViewById(R.id.monthlyRentPrice);
        submitBtn = findViewById(R.id.submitBtn);

        // Initialize Validation Style
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        // Add Validation for Bedrooms
        awesomeValidation.addValidation(this, R.id.bedrooms, RegexTemplate.NOT_EMPTY, R.string.required_bedroom_number);
        awesomeValidation.addValidation(this, R.id.date, RegexTemplate.NOT_EMPTY, R.string.required_date);
        awesomeValidation.addValidation(this, R.id.monthlyRentPrice, RegexTemplate.NOT_EMPTY, R.string.required_monthlyRentPrice);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check validations
                boolean isCheckedPropertyType;
                boolean isCheckedFurnitureType;

                if (propertyTypeRadioGroup.getCheckedRadioButtonId() == -1) {
                    StyleableToast.makeText(getApplicationContext(), "Property Type is Required !", R.style.failedToastMessage).show();
                } else {
                    isCheckedPropertyType = true;
                    if (furnitureTypeRadioGroup.getCheckedRadioButtonId() == -1) {
                        StyleableToast.makeText(getApplicationContext(), "Furniture Type is Required !", R.style.failedToastMessage).show();
                    } else {
                        isCheckedFurnitureType = true;
                        if (awesomeValidation.validate() && isCheckedFurnitureType && isCheckedPropertyType) {
                            // If Successfully
                            StyleableToast.makeText(getApplicationContext(), "Submit Data Successfully !", R.style.successToastMessage).show();
                        } else {
                            StyleableToast.makeText(getApplicationContext(), "Submit Data Failed !", R.style.failedToastMessage).show();
                        }
                    }
                }
            }
        });
    }
}