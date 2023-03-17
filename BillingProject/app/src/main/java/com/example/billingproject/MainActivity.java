package com.example.billingproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Billing Project";
    private static final String KEY_INDEX = "index";
    public int currentIndex = 0;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Billing[] billings = new Billing[]{
                new Billing(105, "Johnston Jane", "Chair", 99.99, 2),
                new Billing(108, "Fikhali Samuel", "Table", 139.99, 1),
                new Billing(113, "Samson Amina", "KeyUSB", 14.99, 2)
        };
        //Retrieve current index if the savedInstanceState is not null
        if(savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_INDEX);
        }

        //Set text info in the text view while input button clicked
        (findViewById(R.id.input_billing_button)).setOnClickListener(v ->{
            String clientId = ((EditText)findViewById(R.id.client_id_edit_text)).getText().toString();
            String clientName = ((EditText)findViewById(R.id.client_name_edit_text)).getText().toString();
            String prdName = ((EditText)findViewById(R.id.product_name_edit_text)).getText().toString();
            String prdPrice = ((EditText)findViewById(R.id.product_price_edit_text)).getText().toString();
            String prdQty = ((EditText)findViewById(R.id.product_quantity_edit_text)).getText().toString();
            Billing billing = new Billing(Integer.parseInt(clientId) , clientName, prdName, Double.parseDouble(prdPrice), Integer.parseInt(prdQty));
            String billInfo = String.format("Client: %s, %s,\nProduct: %s is %.2f$", clientId, clientName, prdName, billing.CalculateBilling());
            ((TextView)findViewById(R.id.client_text_view)).setText(billInfo);
            Toast.makeText(MainActivity.this, billInfo, Toast.LENGTH_SHORT).show();
        });
        //Set text info in the text view while record button clicked
        (findViewById(R.id.record_billing_button)).setOnClickListener(v -> {
            ((TextView)findViewById(R.id.client_text_view)).setText(
                    String.format("Client: %s, %s,\nProduct: %s is %.2f$", billings[currentIndex].getClient_id(),billings[currentIndex].getClient_name(),
                            billings[currentIndex].getProduct_Name(), billings[currentIndex].CalculateBilling()));
            Toast.makeText(MainActivity.this, String.format("Client: %s, %s,\nProduct: %s is %.2f$", billings[currentIndex].getClient_id(),billings[currentIndex].getClient_name(),
                    billings[currentIndex].getProduct_Name(), billings[currentIndex].CalculateBilling()), Toast.LENGTH_SHORT).show();
        });
        //Set text info in the text view while Prev button clicked
        (findViewById(R.id.prev_button)).setOnClickListener( v -> {
            currentIndex = (currentIndex - 1 + billings.length) % billings.length;
            ((TextView)findViewById(R.id.client_text_view)).setText(
                    String.format("Client: %s, %s,\nProduct: %s is %.2f$", billings[currentIndex].getClient_id(),billings[currentIndex].getClient_name(),
                            billings[currentIndex].getProduct_Name(), ((billings[currentIndex].getPrd_Price()) * (billings[currentIndex].getPrd_Qty()))));
            Toast.makeText(MainActivity.this, String.format("Client: %s, %s,\nProduct: %s is %.2f$", billings[currentIndex].getClient_id(),billings[currentIndex].getClient_name(),
                    billings[currentIndex].getProduct_Name(), ((billings[currentIndex].getPrd_Price()) * (billings[currentIndex].getPrd_Qty()))), Toast.LENGTH_SHORT).show();

        });

        //Set text info in the text view while Next button clicked
        (findViewById(R.id.next_button)).setOnClickListener( v -> {
            currentIndex = (currentIndex + 1) % billings.length;
            ((TextView)findViewById(R.id.client_text_view)).setText(
            String.format("Client: %s, %s,\nProduct: %s is %.2f$", billings[currentIndex].getClient_id(),billings[currentIndex].getClient_name(),
                    billings[currentIndex].getProduct_Name(), ((billings[currentIndex].getPrd_Price()) * (billings[currentIndex].getPrd_Qty()))));
            Toast.makeText(MainActivity.this, String.format("Client: %s, %s,\nProduct: %s is %.2f$", billings[currentIndex].getClient_id(),billings[currentIndex].getClient_name(),
                    billings[currentIndex].getProduct_Name(), ((billings[currentIndex].getPrd_Price()) * (billings[currentIndex].getPrd_Qty()))), Toast.LENGTH_SHORT).show();
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, " onStart() method called!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, " onPause() method called!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, " onResume() method called!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, " onStop() method called!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, " onDestroy() method called!");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Log.d("Student Grade Project", "Stored current index: " + currentIndex);
        saveInstanceState.putInt(KEY_INDEX, currentIndex);
    }
}