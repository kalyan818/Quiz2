package com.example.kalya.quiz;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout relativeLayout,relativeLayout1,relativeLayout2,relativeLayout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout)findViewById(R.id.CLanguage);
        relativeLayout1 = (RelativeLayout)findViewById(R.id.CPlusplus);
        relativeLayout2 = (RelativeLayout)findViewById(R.id.JAVA);
        relativeLayout3 = (RelativeLayout)findViewById(R.id.QuizeBox);
        relativeLayout.setOnClickListener(this);
        relativeLayout1.setOnClickListener(this);
        relativeLayout2.setOnClickListener(this);
        relativeLayout3.setOnClickListener(this);
        //if your using this app on more than android version 6.0 then we need to allow permission for this app it starts from here
        if(isReadStorageAllowed()){
            return;
        }
        requestStoragePermission();
    }
    private boolean isReadStorageAllowed() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;
        return false;
    }
    private void requestStoragePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},23);
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if(requestCode == 23){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public void onClick(View view) {
        if (view == relativeLayout){
            //if user clicks on this realtive layout then new intent(layout changes to bellow decleared class) and same for all intent
            Intent intent = new Intent(this,InterviewQuestions.class);
            startActivity(intent);
        }
        if (view == relativeLayout1){
            Intent intent = new Intent(this,C1.class);
            startActivity(intent);
        }
        if (view == relativeLayout2){
            Intent intent = new Intent(this,Java.class);
            startActivity(intent);
        }
        if (view == relativeLayout3){
            Intent intent = new Intent(this,Quize.class);
            startActivity(intent);
            finish();
        }
    }
}
