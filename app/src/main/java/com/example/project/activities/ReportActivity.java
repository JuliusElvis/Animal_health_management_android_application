package com.example.project.activities;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ReportActivity extends AppCompatActivity {

    Button getReport;
    TextView tv,tv1,tv2,tv3,tv4,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tv15,tv16,tv17,tv18;
    public String st6 = "";//DATE OF BIRTH
    public String st2= "";//TYPE OF ANIMAL
    //public String tv4= "";
    String st,st3,st4,st5,st7,st8,st9,st10,st11,st12,st13,st14,st15,st16,st17,
            st18,st19,st20,st21,st22,st23,st24,st25,st26,st27,st28,st29,st30,st31,st32,st33,
            st34,st35,st36,st37,st38,st39,st40;
    //final strings
    String sw,sp,sp1,sp2;
    Bitmap bmp,bmpScale;
    int pageWidth=1200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        tv = findViewById(R.id.textView);
        tv1 = findViewById(R.id.textView2);
        tv2 = findViewById(R.id.textView3);
        tv3 = findViewById(R.id.textView4);
        tv4 = findViewById(R.id.textView5);
        tv6 = findViewById(R.id.textView6);
        tv7 = findViewById(R.id.textView7);
        tv8 = findViewById(R.id.textView8);
        tv9 = findViewById(R.id.textView9);
        tv10 = findViewById(R.id.textView10);
        tv11 = findViewById(R.id.textView11);
        tv12 = findViewById(R.id.textView12);
        tv13 = findViewById(R.id.textView13);
        tv14 = findViewById(R.id.textView14);
        tv15 = findViewById(R.id.textView15);
        tv16 = findViewById(R.id.textView16);
        tv17 = findViewById(R.id.textView17);
        tv18 = findViewById(R.id.textView18);
        //searchDoc = findViewById(R.id.btnSearchDoc);
        getReport = findViewById(R.id.button);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.farm);
        bmpScale = Bitmap.createScaledBitmap(bmp,1200,540,false);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        st = getIntent().getExtras().getString("Value");
        String sv = getResources().getString(R.string.cowName);
        sw = sv+st;
        tv.setText(sw);

        st2 = getIntent().getExtras().getString("Value1");
        st3 = getResources().getString(R.string.typeOfAnimal);
        sp = st3 + st2;
        tv1.setText(sp);

        st4 = getIntent().getExtras().getString("Value2");
        st5 = getResources().getString(R.string.gender);
        sp1 = st5+st4;
        tv2.setText(sp1);

        st6 = getIntent().getExtras().getString("Value3");
        st7 = getResources().getString(R.string.DOB);
        sp2 = st7 + st6;
        tv3.setText(sp2);
        process();
        createPdf();

        /*searchDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });*/


    }
    public void process(){
        if (st2.equals("Cow")){
            st8 = getResources().getString(R.string.Deworming1);
            tv4.setText(st8);

            st9 = getResources().getString(R.string.Deworming2);
            tv6.setText(st9);

            st10 = getResources().getString(R.string.calfTag);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            try {
                Date myDate = sdf.parse(st6);
                Calendar c = Calendar.getInstance();
                c.setTime(myDate);
                c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 20);
                Date dt = c.getTime();
                String dt1 = sdf.format(dt);
                st11 = st10 + dt1;
                tv7.setText(st11);
            }catch (ParseException e){
                e.printStackTrace();
            }

            //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            try {
                Date myDate1 = sdf.parse(st6);
                Calendar c = Calendar.getInstance();
                c.setTime(myDate1);
                c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 90);
                Date dt = c.getTime();
                String dt1 = sdf.format(dt);
                st12 = getResources().getString(R.string.IBR);
                st13 = st12 +dt1;
                tv8.setText(st13);
                st14 = getResources().getString(R.string.Theileriosis);
                st15 = st14+dt1;
                tv9.setText(st15);
            }catch (ParseException e){
                e.printStackTrace();
            }
            st16 = getResources().getString(R.string.Disbudding);
            tv10.setText(st16);
            st17 = getResources().getString(R.string.Castrating);
            tv11.setText(st17);
            st18 = getResources().getString(R.string.Weaning);
            tv12.setText(st18);
            try {
                Date myDate1 = sdf.parse(st6);
                Calendar c = Calendar.getInstance();
                c.setTime(myDate1);
                c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 120);
                Date dt = c.getTime();
                String dt1 = sdf.format(dt);
                st19 = getResources().getString(R.string.Anthrax);
                st20 = st19 +dt1;
                tv13.setText(st20);

                st21=getResources().getString(R.string.Brucellosis);
                st22 = st21+dt1;
                tv14.setText(st22);

                st23 = getResources().getString(R.string.footAndMouth);
                st24 = st23 + dt1;
                tv15.setText(st24);

            }catch (ParseException e){
                e.printStackTrace();
            }

            try {
                Date myDate = sdf.parse(st6);
                Calendar c = Calendar.getInstance();
                c.setTime(myDate);
                c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 180);
                Date dt = c.getTime();
                String dt1 = sdf.format(dt);
                st25 = getResources().getString(R.string.blackQuarter);
                st26 = st25 + dt1;
                tv16.setText(st26);
                st27 = getResources().getString(R.string.septicaemia);
                st28 = st27 + dt1;
                tv17.setText(st28);
                st34 = getResources().getString(R.string.rinderPest);
                st35 = st34+dt1;
                tv18.setText(st35);

            }catch (ParseException e){
                e.printStackTrace();
            }

        }else {
            if (st2.equals("Sheep/Goats")){
                st8 = getResources().getString(R.string.Deworming1);
                tv4.setText(st8);

                st9 = getResources().getString(R.string.Deworming2);
                tv6.setText(st9);

                st10 = getResources().getString(R.string.earTagging);
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    Date myDate = sdf.parse(st6);
                    Calendar c = Calendar.getInstance();
                    c.setTime(myDate);
                    c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 20);
                    Date dt = c.getTime();
                    String dt1 = sdf.format(dt);
                    st11 = st10 + dt1;
                    tv7.setText(st11);
                }catch (ParseException e){
                    e.printStackTrace();
                }

                try {
                    Date myDate1 = sdf.parse(st6);
                    Calendar c = Calendar.getInstance();
                    c.setTime(myDate1);
                    c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 90);
                    Date dt = c.getTime();
                    String dt1 = sdf.format(dt);
                    st12 = getResources().getString(R.string.goatPox);
                    st13 = st12 +dt1;
                    tv8.setText(st13);
                    st14 = getResources().getString(R.string.ccpp);
                    st15 = st14+dt1;
                    tv9.setText(st15);
                }catch (ParseException e){
                    e.printStackTrace();
                }
                try {
                    Date myDate1 = sdf.parse(st6);
                    Calendar c = Calendar.getInstance();
                    c.setTime(myDate1);
                    c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 2);
                    Date dt = c.getTime();
                    String dt1 = sdf.format(dt);
                    st30 = getResources().getString(R.string.tetanus);
                    st16 = st30 +dt1;
                    tv10.setText(st16);

                }catch (ParseException e){
                    e.printStackTrace();
                }
                try {
                    Date myDate1 = sdf.parse(st6);
                    Calendar c = Calendar.getInstance();
                    c.setTime(myDate1);
                    c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 120);
                    Date dt = c.getTime();
                    String dt1 = sdf.format(dt);
                    st19 = getResources().getString(R.string.Enterotoxaemia);
                    st20 = st19 +dt1;
                    tv13.setText(st20);

                    st21=getResources().getString(R.string.Brucellosis);
                    st22 = st21+dt1;
                    tv14.setText(st22);

                    st23 = getResources().getString(R.string.footAndMouth);
                    st24 = st23 + dt1;
                    tv15.setText(st24);

                    st33 = getResources().getString(R.string.multiVax);
                    st18 = st33 + dt1;
                    tv12.setText(st18);


                }catch (ParseException e){
                    e.printStackTrace();
                }

                try {
                    Date myDate = sdf.parse(st6);
                    Calendar c = Calendar.getInstance();
                    c.setTime(myDate);
                    c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 180);
                    Date dt = c.getTime();
                    String dt1 = sdf.format(dt);
                    st25 = getResources().getString(R.string.blackQuarter);
                    st26 = st25 + dt1;
                    tv16.setText(st26);
                    st27 = getResources().getString(R.string.septicaemia);
                    st28 = st27 + dt1;
                    tv17.setText(st28);
                    st31 = getResources().getString(R.string.anthrax);
                    st35 = st31 + dt1;
                    tv18.setText(st35);
                    st32 = getResources().getString(R.string.rinderPest);
                    st17 =st32 + dt1;
                    tv11.setText(st17);

                }catch (ParseException e){
                    e.printStackTrace();
                }

            }else {
                if (st2.equals("Poultry")){
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                    try {
                        Date myDate = sdf.parse(st6);
                        Calendar c = Calendar.getInstance();
                        c.setTime(myDate);
                        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 1);
                        Date dt = c.getTime();
                        String dt1 = sdf.format(dt);
                        st10 = getResources().getString(R.string.Mareks);
                        st8 = st10 + dt1;
                        tv4.setText(st8);
                    }catch (ParseException e){
                        e.printStackTrace();
                    }
                    try {
                        Date myDate = sdf.parse(st6);
                        Calendar c = Calendar.getInstance();
                        c.setTime(myDate);
                        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 6);
                        Date dt = c.getTime();
                        String dt1 = sdf.format(dt);
                        st12 = getResources().getString(R.string.NCD);
                        st9 = st12 + dt1;
                        tv6.setText(st9);
                    }catch (ParseException e){
                        e.printStackTrace();
                    }
                    try {
                        Date myDate = sdf.parse(st6);
                        Calendar c = Calendar.getInstance();
                        c.setTime(myDate);
                        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 10);
                        Date dt = c.getTime();
                        String dt1 = sdf.format(dt);
                        st14 = getResources().getString(R.string.Gumboro);
                        st11 = st14 + dt1;
                        tv7.setText(st11);
                    }catch (ParseException e){
                        e.printStackTrace();
                    }
                    try {
                        Date myDate = sdf.parse(st6);
                        Calendar c = Calendar.getInstance();
                        c.setTime(myDate);
                        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 18);
                        Date dt = c.getTime();
                        String dt1 = sdf.format(dt);
                        st19 = getResources().getString(R.string.Gumboro2);
                        st13 = st19 + dt1;
                        tv8.setText(st13);
                        st21 = getResources().getString(R.string.IBD);
                        st15 = st21 + dt1;
                        tv9.setText(st15);
                    }catch (ParseException e){
                        e.printStackTrace();
                    }

                    try {
                        Date myDate = sdf.parse(st6);
                        Calendar c = Calendar.getInstance();
                        c.setTime(myDate);
                        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 21);
                        Date dt = c.getTime();
                        String dt1 = sdf.format(dt);
                        st23 = getResources().getString(R.string.Newcastle);
                        st16 = st23 + dt1;
                        tv10.setText(st16);
                    }catch (ParseException e){
                        e.printStackTrace();
                    }
                    try {
                        Date myDate = sdf.parse(st6);
                        Calendar c = Calendar.getInstance();
                        c.setTime(myDate);
                        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 28);
                        Date dt = c.getTime();
                        String dt1 = sdf.format(dt);
                        st25 = getResources().getString(R.string.NCD);
                        st17 = st25 + dt1;
                        tv11.setText(st17);
                    }catch (ParseException e){
                        e.printStackTrace();
                    }
                    try {
                        Date myDate = sdf.parse(st6);
                        Calendar c = Calendar.getInstance();
                        c.setTime(myDate);
                        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 30);
                        Date dt = c.getTime();
                        String dt1 = sdf.format(dt);
                        st27 = getResources().getString(R.string.bursal);
                        st18 = st27 + dt1;
                        tv12.setText(st18);
                    }catch (ParseException e){
                        e.printStackTrace();
                    }
                    try {
                        Date myDate = sdf.parse(st6);
                        Calendar c = Calendar.getInstance();
                        c.setTime(myDate);
                        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 42);
                        Date dt = c.getTime();
                        String dt1 = sdf.format(dt);
                        st34 = getResources().getString(R.string.fowlPox);
                        st20 = st34 + dt1;
                        tv13.setText(st20);

                        st36 = getResources().getString(R.string.Bronchitis);
                        st22 = st36+dt1;
                        tv14.setText(st22);

                        st37 = getResources().getString(R.string.fowlCholera);
                        st24 = st37 + dt1;
                        tv15.setText(st24);

                    }
                    catch (ParseException e){
                        e.printStackTrace();
                    }
                    try {
                        Date myDate = sdf.parse(st6);
                        Calendar c = Calendar.getInstance();
                        c.setTime(myDate);
                        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 56);
                        Date dt = c.getTime();
                        String dt1 = sdf.format(dt);
                        st38 = getResources().getString(R.string.Newcastle2);
                        st26 = st38 + dt1;
                        tv16.setText(st26);

                        st39 =getResources().getString(R.string.fowlTyphoid);
                        st28 = st39 + dt1;
                        tv17.setText(st28);
                    }catch (ParseException e){
                        e.printStackTrace();
                    }
                    try {
                        Date myDate = sdf.parse(st6);
                        Calendar c = Calendar.getInstance();
                        c.setTime(myDate);
                        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 56);
                        Date dt = c.getTime();
                        String dt1 = sdf.format(dt);
                        st40 = getResources().getString(R.string.Deworm);
                        st35 = st40 + dt1;
                        tv18.setText(st35);

                    }catch (ParseException e){
                        e.printStackTrace();
                    }

                }else {
                    if (st2.equals("Pigs")){
                        st8 = getResources().getString(R.string.afterBirth);
                        tv4.setText(st8);

                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        try {
                            Date myDate = sdf.parse(st6);
                            Calendar c = Calendar.getInstance();
                            c.setTime(myDate);
                            c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 3);
                            Date dt = c.getTime();
                            String dt1 = sdf.format(dt);
                            st10 = getResources().getString(R.string.iron);
                            st9 = st10 + dt1;
                            tv6.setText(st9);
                        }catch (ParseException e){
                            e.printStackTrace();
                        }

                        try {
                            Date myDate = sdf.parse(st6);
                            Calendar c = Calendar.getInstance();
                            c.setTime(myDate);
                            c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 7);
                            Date dt = c.getTime();
                            String dt1 = sdf.format(dt);
                            st12 = getResources().getString(R.string.Castration);
                            st11 = st12 + dt1;
                            tv7.setText(st11);

                            st14 = getResources().getString(R.string.teethClipping);
                            st13 = st14 + dt1;
                            tv8.setText(st13);

                            st19 = getResources().getString(R.string.tailDocking);
                            st15 = st19 + dt1;
                            tv9.setText(st15);
                        }catch (ParseException e){
                            e.printStackTrace();
                        }

                        st16= getResources().getString(R.string.weaning);
                        tv10.setText(st16);

                        try {
                            Date myDate = sdf.parse(st6);
                            Calendar c = Calendar.getInstance();
                            c.setTime(myDate);
                            c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 28);
                            Date dt = c.getTime();
                            String dt1 = sdf.format(dt);
                            st19 = getResources().getString(R.string.prrs);
                            st17 = st19 + dt1;
                            tv11.setText(st17);

                            st21 = getResources().getString(R.string.rhinitis);
                            st18 = st21 + dt1;
                            tv12.setText(st18);
                        }catch (ParseException e){
                            e.printStackTrace();
                        }

                        try {
                            Date myDate = sdf.parse(st6);
                            Calendar c = Calendar.getInstance();
                            c.setTime(myDate);
                            c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 60);
                            Date dt = c.getTime();
                            String dt1 = sdf.format(dt);
                            st23 = getResources().getString(R.string.glassersVaccine);
                            st20 = st23 + dt1;
                            tv13.setText(st20);

                            st25 = getResources().getString(R.string.swineFever);
                            st22 = st25 + dt1;
                            tv14.setText(st22);


                        }catch (ParseException e){
                            e.printStackTrace();
                        }

                        try {
                            Date myDate = sdf.parse(st6);
                            Calendar c = Calendar.getInstance();
                            c.setTime(myDate);
                            c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 90);
                            Date dt = c.getTime();
                            String dt1 = sdf.format(dt);
                            st27 = getResources().getString(R.string.swineFever2);
                            st24 = st27 + dt1;
                            tv15.setText(st24);

                            st34 = getResources().getString(R.string.parvoVirus);
                            st26 = st34 + dt1;
                            tv16.setText(st26);

                            st36 = getResources().getString(R.string.swineInfluenza);
                            st28 = st36 + dt1;
                            tv17.setText(st28);


                        }catch (ParseException e){
                            e.printStackTrace();
                        }

                        try {
                            Date myDate = sdf.parse(st6);
                            Calendar c = Calendar.getInstance();
                            c.setTime(myDate);
                            c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 120);
                            Date dt = c.getTime();
                            String dt1 = sdf.format(dt);
                            st37 = getResources().getString(R.string.footAndMouth);
                            st35 = st37 + dt1;
                            tv18.setText(st35);
                        }catch (ParseException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }


    }
    public void onBackPressed(){
        //Intent a = new Intent(Intent.ACTION_MAIN);
        //a.addCategory(Intent.CATEGORY_HOME);
        //a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //startActivity(a);
        finish();
    }
    public void openNewActivity(){
        Intent intent = new Intent(this, DocDisplayActivity.class);
        startActivity(intent);
    }

    public void createPdf(){
        getReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "report failed", Toast.LENGTH_SHORT).show();
                PdfDocument pdfDocument = new PdfDocument();
                Paint myPaint = new Paint();
                Paint titlePaint = new Paint();

                PdfDocument.PageInfo myPageinfo = new PdfDocument.PageInfo.Builder(1200,2010,1).create();
                PdfDocument.Page myPage1 = pdfDocument.startPage(myPageinfo);
                Canvas canvas = myPage1.getCanvas();

                canvas.drawBitmap(bmpScale,0,0,myPaint);
                titlePaint.setTextAlign(Paint.Align.CENTER);
                titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
                titlePaint.setTextSize(70);
                canvas.drawText("Livestock Manager",pageWidth/2,200,titlePaint);

                titlePaint.setTextAlign(Paint.Align.CENTER);
                titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.ITALIC));
                titlePaint.setTextSize(70);
                canvas.drawText("Animal Report",pageWidth/2,500,titlePaint);

                myPaint.setTextAlign(Paint.Align.LEFT);
                myPaint.setTextSize(35f);
                myPaint.setColor(Color.BLACK);
                canvas.drawText(sw,20,590,myPaint);
                canvas.drawText(sp,20,640,myPaint);
                canvas.drawText(sp1,20,690,myPaint);
                canvas.drawText(sp2,20,740,myPaint);
                canvas.drawText(st8,20,790,myPaint);
                canvas.drawText(st9,20,840,myPaint);
                canvas.drawText(st16,20,890,myPaint);
                canvas.drawText(st17,20,940,myPaint);
                canvas.drawText(st18,20,990,myPaint);
                canvas.drawText(st11,20,1040,myPaint);
                canvas.drawText(st13,20,1090,myPaint);
                canvas.drawText(st15,20,1140,myPaint);
                canvas.drawText(st20,20,1190,myPaint);
                canvas.drawText(st22,20,1240,myPaint);
                canvas.drawText(st24,20,1290,myPaint);
                canvas.drawText(st26,20,1340,myPaint);
                canvas.drawText(st28,20,1390,myPaint);
                canvas.drawText(st35,20,1440,myPaint);

                pdfDocument.finishPage(myPage1);
                File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),"/animalCalendar.pdf");
                //File file = new File(Environment.getExternalStorageDirectory(),"/animalCalendar.pdf");

                try {
                    pdfDocument.writeTo(new FileOutputStream(file));
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                pdfDocument.close();
                Toast.makeText(getApplicationContext(), "success, file saved to documents", Toast.LENGTH_SHORT).show();

            }
        });


    }
}

