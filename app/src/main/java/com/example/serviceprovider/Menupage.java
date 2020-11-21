package com.example.serviceprovider;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

public class Menupage extends AppCompatActivity {

    GridLayout mainGridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menupage);

        mainGridLayout=(GridLayout)findViewById(R.id.mainGridLayout);
        setSingleEvent(mainGridLayout);
    }

    private void setSingleEvent(GridLayout mainGridLayout) {


        for(int i=0 ; i<mainGridLayout.getChildCount();i++)
        {
            CardView cardView = (CardView)mainGridLayout.getChildAt(i);
            final int finalI = i;

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(finalI==1)
                    {
                        Intent intent = new Intent(Menupage.this , Plumber.class);
                        startActivity(intent);
                    }

                    else if (finalI==0){
                        Intent intent = new Intent(Menupage.this , Carpenter.class);
                        startActivity(intent);
                    }


                    else if (finalI==2){
                        Intent intent = new Intent(Menupage.this , Housemaid.class);
                        startActivity(intent);
                    }

                    else if (finalI==3){
                        Intent intent = new Intent(Menupage.this , Electrician.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}