package com.example.multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    database ordersDB ;
    Button btnAddData, btnViewData;
    EditText editText1, editText2, editText3;

    public static final String MSG = "com.example.multiscreen.ORDER";

    // We will handle the click on the button here.
    public void placeOrder(View view){
        // Build an Intent to open another activity
        Intent intent = new Intent(this, OrderActivity.class);
        EditText editText1 = findViewById(R.id.editText1);
        EditText editText2 = findViewById(R.id.editText2);
        EditText editText3 = findViewById(R.id.editText3);
        String message = "Your order for " + editText1.getText().toString() + ", "
                         + editText2.getText().toString() + " & "
                         + editText3.getText().toString()
                         + " has been successfully placed. Cheers...!!!";
        intent.putExtra(MSG, message);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ordersDB = new database(this);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        btnAddData = (Button) findViewById(R.id.btnAddData);
        btnViewData = (Button) findViewById(R.id.btnViewData);

        //AddData();
    }

    public void AddData(View view){
//        ordersDB = new database(this);
//
//        editText1 = (EditText) findViewById(R.id.editText1);
//        editText2 = (EditText) findViewById(R.id.editText2);
//        editText3 = (EditText) findViewById(R.id.editText3);
//        btnAddData = (Button) findViewById(R.id.btnAddData);
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String order1 = editText1.getText().toString();
                String order2 = editText2.getText().toString();
                String order3 = editText3.getText().toString();

                boolean insertData = ordersDB.addData(order1, order2, order3);
                if(insertData == true)
                    Toast.makeText(MainActivity.this, "Orders added to the database!!", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Something went wrong!!", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void ViewData(View view){
//        ordersDB = new database(this);
//
//        editText1 = (EditText) findViewById(R.id.editText1);
//        editText2 = (EditText) findViewById(R.id.editText2);
//        editText3 = (EditText) findViewById(R.id.editText3);
//        btnViewData = (Button) findViewById(R.id.btnViewData);
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor data = ordersDB.showData();
                if(data.getCount() == 0){
                    // Message
                    display("Error!!", "No data found.");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(data.moveToNext()){
                    buffer.append("\nID: " + data.getString(0) + "\n");
                    buffer.append("ORDER1: " + data.getString(1) + "\n");
                    buffer.append("ORDER2: " + data.getString(2) + "\n");
                    buffer.append("ORDER3: " + data.getString(3) + "\n");

                    // display message
                    display("All Stored Data:", buffer.toString());
                }
            }
        });

    }

    public void display(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}