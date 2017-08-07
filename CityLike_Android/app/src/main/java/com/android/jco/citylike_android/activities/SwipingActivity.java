package com.android.jco.citylike_android.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.jco.citylike_android.R;
import com.android.jco.citylike_android.models.TestModel;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SwipingActivity extends AppCompatActivity {


    @BindView(R.id.frame) SwipeFlingAdapterView flingContainer;

    ArrayList<TestModel> list;
    private ArrayAdapter<String> mAdapter;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

           list = new ArrayList<TestModel>();
           setContentView(R.layout.activity_swiping);
           SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);
           final ImageView currentImageView = (ImageView)findViewById(R.id.swiping_activity_image);
           list.add(new TestModel(,"PHP"));
           list.add(new TestModel(,"STUFF"));

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
                   list.remove(0);
                   mAdapter.notifyDataSetChanged();
               }

               @Override
               public void onRightCardExit(Object o) {
                   list.remove(0);
                   mAdapter.notifyDataSetChanged();     }




               @Override
               public void onAdapterAboutToEmpty(int i) {

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
