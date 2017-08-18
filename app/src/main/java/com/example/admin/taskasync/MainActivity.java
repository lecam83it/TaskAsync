package com.example.admin.taskasync;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.asynctask.DownloadTask;

public class MainActivity extends AppCompatActivity {

    private EditText edtImg;
    private EditText edtText;
    private Button btnUpdate;
    private TextView txtText;
    private ImageView imgAva;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imgLink = edtImg.getText().toString().trim();
                String txtLink = edtText.getText().toString().trim();
                if(imgLink.equals("")){
                    Toast.makeText(MainActivity.this,"You can enter URL of Image",Toast.LENGTH_SHORT).show();
                }else if(txtLink.equals("")){
                    Toast.makeText(MainActivity.this,"You can enter URL of Text",Toast.LENGTH_SHORT).show();
                }else{
                    DownloadTask task = new DownloadTask(MainActivity.this, txtText, imgAva);
                    task.execute(imgLink, txtLink);
                }
            }
        });
    }

    private void addControls() {
        edtImg = (EditText) findViewById(R.id.edtImg);
        edtText = (EditText) findViewById(R.id.edtText);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        txtText = (TextView) findViewById(R.id.txtText);
        imgAva = (ImageView) findViewById(R.id.imgAva);
    }
}
