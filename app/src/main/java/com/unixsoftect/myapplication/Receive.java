package com.unixsoftect.myapplication;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Receive extends AppCompatActivity {
    GridLayout gridLayout;
    List<View> btnview = new ArrayList<>();
    List<Integer> visitedbtn = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        String num = getIntent().getStringExtra("number");
        Log.e("lognumber", "" + num);
        gridLayout = findViewById(R.id.gridlayout);

        if (num.equals(""))
            return;
        //LayoutParams. This set of layout parameters defaults the width and the height of the children to ViewGroup.
        // This set of layout parameters enforces the width of each child to be ViewGroup.
        for (int i = 0; i < Integer.parseInt(num); i++) {
            Button b = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            b.setPadding(30,30,30,30);
            params.setMargins(15, 15, 15, 15);
            b.setLayoutParams(params);
            setBackgroundTint(b, Color.DKGRAY);
            b.setClickable(false);
            btnview.add(b);
            gridLayout.addView(b);
        }
        RandomRedBtn();
    }

    public void RandomRedBtn() {
        List<View> btnv = btnview;
        try{
            Random r = new Random();
            int nu = r.nextInt((btnv.size() - 1) + 1);

            if (visitedbtn != null && !visitedbtn.contains(nu)) {
                View v = btnv.get(nu);
                Button b = (Button)v;
                setBackgroundTint(b, Color.RED);
                b.setClickable(true);
                b.setOnClickListener(listener);

            } else {
                RandomRedBtn();
            }
            if (visitedbtn != null) {
                visitedbtn.add(nu);
            }

        }
        catch (Exception e){
            Log.e("TAG", "RandomRedBtn: "+e.getMessage() );
        }


    }

//View.OnClickListener list = new View.OnClickListener() {
//    @Override
//    public void onClick(View view) {
//
//    }
//}

    View.OnClickListener listener = view -> {
        Button b = (Button) view;
        setBackgroundTint(b, Color.BLUE);
        b.setClickable(false);
        b.setOnClickListener(null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            visitedbtn = visitedbtn.stream().distinct().collect(Collectors.toList());
        }
       // Log.e("TAG", ": "+visitedbtn.size()+" "+btnview.size() );
        if (visitedbtn.size() != btnview.size() )
            RandomRedBtn();
    };

    public void setBackgroundTint(View v, int color) {
        Drawable d = v.getBackground();
        d = DrawableCompat.wrap(d);
        DrawableCompat.setTint(d, color);
        v.setBackground(d);
        //v.setBackgroundColor(color);
    }
}
////@android:color/darker_gray