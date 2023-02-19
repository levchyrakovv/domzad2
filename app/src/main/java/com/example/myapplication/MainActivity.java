package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private float apartmentPrice = 1_000_000;
    private int account = 250_000;
    private float salary = 100_000;
    private int percentFree = 50;
    private float percentBank = 5;
    private float[] monthlyPayments = new float[120]; //10 years

    private float apartmentPriceWithContribution() {
        return  apartmentPrice - account;
    }

    private float mortgageCosts(float amount,int percent) {
        return (amount * percent) / 100;
    }

    private int countMonth(float total, float mortgageCosts, float bankPercent) {
        float percentPerMonth = bankPercent / 12;
        int count = 0;

        while (total > 0) {
            total = (total + (total * percentPerMonth) / 100) - mortgageCosts;

            if (total > mortgageCosts) {
                monthlyPayments[count] = mortgageCosts;
            } else {
                monthlyPayments[count] = total;

            }
            count++;
        }
        return count;
    }
        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            TextView countMonth = findViewById(R.id.countMonth);
            TextView payments = findViewById(R.id.payments);

            int count = countMonth(apartmentPriceWithContribution(), mortgageCosts(salary,percentFree), percentBank);
            countMonth.setText(count);

            String s = ("");

            for (int i = 0; i < count; i++){
                s += monthlyPayments[i] + ";";
            }
            payments.setText(s);
        }
    }