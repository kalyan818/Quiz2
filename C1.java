package com.example.kalya.quiz;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class C1 extends AppCompatActivity {
    private ImageView imageView;
    private int currentPage = 0;
    private Button next, previous;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        //if user clicks next button then increment value of the pages and change from current page to next  page
        next = (Button)findViewById(R.id.next);
        previous = (Button)findViewById(R.id.previous);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage++;
                render();
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPage--;
                render();
            }
        });
        render();
    }
    private void render(){
        try {
            imageView = (ImageView)findViewById(R.id.image);
            int REQ_WIDTH = imageView.getWidth();
            int REQ_HEIGHT = imageView.getHeight();
            Bitmap bitmap = Bitmap.createBitmap(REQ_WIDTH,REQ_HEIGHT, Bitmap.Config.ARGB_4444);
            File file = new File("/sdcard/UCDownloads/cprogramming_tutorial.pdf");
            PdfRenderer renderer = new PdfRenderer(ParcelFileDescriptor.open(file,ParcelFileDescriptor.MODE_READ_ONLY));

            if (currentPage < 0){
                currentPage = 1;
            }else if(currentPage > renderer.getPageCount()){
                currentPage = renderer.getPageCount() - 1;
            }

            Matrix m = imageView.getImageMatrix();
            Rect rect = new Rect(0,0,REQ_WIDTH,REQ_HEIGHT);
            renderer.openPage(currentPage).render(bitmap,rect,m,PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            imageView.setImageMatrix(m);
            imageView.setImageBitmap(bitmap);
            imageView.invalidate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

