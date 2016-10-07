package com.example.hangzhao.generatorqr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    private Button generateButton;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;
        editText = (EditText) this.findViewById(R.id.userInput_EditText);
        generateButton = (Button) this.findViewById(R.id.generateQR_Button);
        generateButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void  onClick ( View v ) {
                String text2QR = editText.getText().toString();
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(text2QR, BarcodeFormat.QR_CODE, 200, 200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitMap = barcodeEncoder.createBitmap( bitMatrix );
                    Intent intent = new Intent( MainActivity.this, QrActivity.class );
                    intent.putExtra( "pic", bitMap );
                    context.startActivity( intent );
                } catch ( WriterException e ) { e.printStackTrace(); }
            }
        });
    }
}
