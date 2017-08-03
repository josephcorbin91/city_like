package com.android.jco.citylike_android.activities.Activities;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.jco.citylike_android.R;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SwipingActivity extends AppCompatActivity {


    @BindView(R.id.frame) SwipeFlingAdapterView flingContainer;

    ArrayList<String> list;
    private ArrayAdapter<String> mAdapter;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

           list = new ArrayList<String>();
           setContentView(R.layout.activity_swiping);
           SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
           list.add("PHP");
           list.add("STUFF");
           list.add("STUFF1");
           list.add("STUFF2");
           list.add("STUFF3");
           mAdapter = new ArrayAdapter<String>(this, R.layout.item, R.id.helloText, list );

           flingContainer.setAdapter(mAdapter);
           flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
               @Override
               public void removeFirstObjectInAdapter() {
                   list.remove(0);
                   mAdapter.notifyDataSetChanged();
               }

               @Override
               public void onLeftCardExit(Object o) {

                   Toast.makeText(SwipingActivity.this, "Left", Toast.LENGTH_SHORT).show();
               }

               @Override
               public void onRightCardExit(Object o) {

                   Toast.makeText(SwipingActivity.this, "Right!", Toast.LENGTH_SHORT).show();
               }



               @Override
               public void onAdapterAboutToEmpty(int i) {

                   list.add("XML");
                   mAdapter.notifyDataSetChanged();
               }

               @Override
               public void onScroll(float v) {

               }
           });

           flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
               @Override
               public void onItemClicked(int i, Object o) {
                   Toast.makeText(SwipingActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
               }
           });
           ButterKnife.bind(this);

       }

}
