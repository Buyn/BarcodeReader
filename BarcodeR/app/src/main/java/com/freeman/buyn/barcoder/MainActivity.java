package com.freeman.buyn.barcoder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends AppCompatActivity {
    private int counterHello = 0;
    //text labels from view
    private TextView counterHelloLabel;
    private TextView textSayLogLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("HelloActivity.onCreate");
        counterHelloLabel = (TextView)findViewById(R.id.helloCounter);
        textSayLogLabel = (TextView)findViewById(R.id.sayTextView);
    }

    /**
     * Handle press "Saybye" button
     * add goodbye text to "saylogLabel"
     * @param view
     */
    public void handleSearch(View view){
        System.out.println("click Search");

        textSayLogLabel.append("\n I don`t know what is it!");
    }
    /**
     * Handle press "Hello" button
     * add Hello text to "saylogLabel" and increase counter label
     * @param view
     */
    public void handleScanButton(View view){
        IntentIntegrator integratorScaner = new IntentIntegrator(this);
        integratorScaner.initiateScan();
        //textSayLogLabel.append("\n Hello World");
        counterHelloLabel.setText(Integer.toString(++counterHello));
        System.out.println("click ScanButton");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (intentResult == null) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        textSayLogLabel.setText("\n"+intentResult.toString()+"\n");


    }
}
