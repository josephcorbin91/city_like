package com.android.jco.citylike_android.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.design.widget.FloatingActionButton;
import android.support.v13.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.jco.citylike_android.R;
import com.android.jco.citylike_android.models.SeattleBuildingPermit;
import com.android.jco.citylike_android.services.ApiIntentService;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Retrofit;

public class SwipingActivity extends AppCompatActivity {

    public static final int PERMISSION_REQUEST_TO_ACCESS_LOCATION = 32;

    public static SeattleBuildingPermitAdapter myAppAdapter;
    public static ViewHolder viewHolder;
    private ArrayList<SeattleBuildingPermit> array;
    private SwipeFlingAdapterView flingContainer;
    private FusedLocationProviderClient mFusedLocationClient;
    private ApiIntentService apiIntentService;

    private Toolbar toolbar;
    private MaterialMenuView materialMenuView;
    private int materialButtonState;
    private DrawerLayout drawerLayout;
    private boolean direction;
    private int actionBarMenuState;
    private static Retrofit retrofit=null;
    private FloatingActionButton swipe_card_map_button;
    public static final String BASE_URL = "https://citylike1.herokuapp.com/";


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.swipe_menu_city_hall_action:
                Toast.makeText(getApplicationContext(), "Contact City Official", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.swipe_menu_map_action:
                Toast.makeText(getApplicationContext(), "Contact City Official", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;






        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.swipe_menu,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkLocationPermission();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swiping_activity);
        LitePal.initialize(this);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Toolbar toolbarBottom =(Toolbar)findViewById(R.id.toolbar_bottom);
        toolbarBottom.inflateMenu(R.menu.swipe_menu_bottom);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        try {



        }
        catch (Exception ex){
            System.out.println("Exiting start of service.");
            ex.printStackTrace();

        }
        initiateToolBar();
        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        array = new ArrayList<>();

        List<SeattleBuildingPermit> list = DataSupport.findAll(SeattleBuildingPermit.class);

        for(int i =0 ; i< 15; i++)
            array.add(list.get(i));

        /*
        array.add(new Data("https://www10.aeccafe.com/blogs/arch-showcase/files/2012/05/LAUS_UNStudio_Aerial.jpg", "Hi I am Katrina Kaif. Wanna chat with me ?. \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        array.add(new Data("https://www.theawl.com/2015/06/future-new-york-just-a-bunch-of-boxes-stacked-up-wherever/", "Hi I am Emma Watson. Wanna chat with me ? \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        array.add(new Data("http://1.bp.blogspot.com/-b-EhAkhIetY/UPp98bZHuvI/AAAAAAAAJnk/cweTZfptRP8/s1600/shanghai+Bund.jpg", "Hi I am Scarlett Johansson. Wanna chat with me ? \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        array.add(new Data("http://www.futureofeverything.io/wp-content/uploads/2017/03/beijing-electric-taxi-990x557.jpeg", "Hi I am Priyanka Chopra. Wanna chat with me ? \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        array.add(new Data("http://www.bridge-chailley.fr/wp-content/uploads/2013/04/D%C3%A9fense-quartier.jpg", "Hi I am Deepika Padukone. Wanna chat with me ? \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        array.add(new Data("https://media.timeout.com/images/102531934/image.jpg", "Hi I am Anjelina Jolie. Wanna chat with me ? \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
        array.add(new Data("http://www.upside-down.ca/cherry-oxford.jpg", "Hi I am Aishwarya Rai. Wanna chat with me ? \n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."));
*/
        myAppAdapter = new SeattleBuildingPermitAdapter(array, SwipingActivity.this);
        flingContainer.setAdapter(myAppAdapter);

        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                array.remove(0);
                myAppAdapter.notifyDataSetChanged();
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
            }

            @Override
            public void onRightCardExit(Object dataObject) {

                array.remove(0);
                myAppAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {

                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.background).setAlpha(0);

                myAppAdapter.notifyDataSetChanged();
            }
        });

    }


    public void initiateToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final MaterialMenuDrawable materialMenu = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        toolbar.setNavigationIcon(materialMenu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // actionBarMenuState = generateState(actionBarMenuState);
                //getMaterialMenu(toolbar).animateIconState(intToState(actionBarMenuState));
            }
        });


        drawerLayout = ((DrawerLayout) findViewById(R.id.drawer_layout));
        drawerLayout.setScrimColor(Color.parseColor("#66000000"));
        drawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                getMaterialMenu(toolbar).setTransformationOffset(
                        MaterialMenuDrawable.AnimationState.BURGER_ARROW,
                        direction ? 2 - slideOffset : slideOffset
                );
            }

            @Override
            public void onDrawerOpened(android.view.View drawerView) {
                direction = true;
            }

            @Override
            public void onDrawerClosed(android.view.View drawerView) {
                direction = false;
            }
        });


    }

    public static class ViewHolder {
        public static FrameLayout background;
        public TextView DataText;
        public ImageView cardImage;
        public FloatingActionButton swipe_card_map_button;


    }

    public class SeattleBuildingPermitAdapter extends BaseAdapter {


        public List<SeattleBuildingPermit> seattleBuildingPermits;
        public Context context;

        private SeattleBuildingPermitAdapter(List<SeattleBuildingPermit> seattleBuildingPermits, Context context) {
            this.seattleBuildingPermits = seattleBuildingPermits;
            this.context = context;
        }

        @Override
        public int getCount() {
            return seattleBuildingPermits.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;


            if (rowView == null) {

                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.item, parent, false);
                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.DataText = (TextView) rowView.findViewById(R.id.bookText);
                viewHolder.background = (FrameLayout) rowView.findViewById(R.id.background);
                viewHolder.cardImage = (ImageView) rowView.findViewById(R.id.cardImage);
                viewHolder.cardImage.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        viewDetail();
                        return true;
                    }
                });
                viewHolder.swipe_card_map_button = (FloatingActionButton) rowView.findViewById(R.id.swipe_card_map_button);
                viewHolder.swipe_card_map_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<Location> locationArrayList = new ArrayList<Location>();
                        for(int i=0; i <seattleBuildingPermits.size();i++) {
                            Location targetLocation = new Location("Building Location");
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("SeattleBuildingPermit", seattleBuildingPermits.get(i));
                            targetLocation.setExtras(bundle);
                            targetLocation.setLongitude(seattleBuildingPermits.get(i).getLongitude());
                            targetLocation.setLatitude(seattleBuildingPermits.get(i).getLatitude());
                            locationArrayList.add(targetLocation);
                            System.out.println("Locations : "  +targetLocation.toString());
                        }
                        startMapActivity(locationArrayList.get(position),locationArrayList);
                    }
                });

                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.DataText.setText(seattleBuildingPermits.get(position).getDescription() + "");

            Glide.with(SwipingActivity.this).load(seattleBuildingPermits.get(position).getImagePath()).into(viewHolder.cardImage);

            return rowView;
        }
    }

    public void viewDetail(){
        Intent intent = new Intent(this, DetailActivity.class);

        startActivity(intent);
    }

    public void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED ) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSION_REQUEST_TO_ACCESS_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    public void startMapActivity(Location buildingLocation, ArrayList<Location> locationList){

        final Location mLocation = buildingLocation;
        final ArrayList<Location> mLocationList = locationList;

        System.out.println("locationList");
        for(Location location : locationList)
            System.out.println(location.getExtras().getSerializable("SeattleBuildingPermit"));
        mFusedLocationClient.getLastLocation().

                addOnSuccessListener(this,new OnSuccessListener<Location>() {
                    @Override
                            public void onSuccess (Location location){
                                if (location != null) {
                            Intent intent = new Intent(SwipingActivity.this, MapsMarkerActivity.class);
                            intent.putExtra("CurrentLocation",location);
                            intent.putExtra("allLocations",mLocationList);



                            intent.putExtra("buildingPermitLocation",mLocation);


                            startActivity(intent);

                            Toast.makeText(SwipingActivity.this, String.valueOf(location.getLatitude()), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_TO_ACCESS_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.


                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }




    private void refreshDrawerState() {
        this.direction = drawerLayout.isDrawerOpen(GravityCompat.START);
    }

      private static MaterialMenuDrawable getMaterialMenu(Toolbar toolbar) {
        return (MaterialMenuDrawable) toolbar.getNavigationIcon();
}

    private static int generateState(int previous) {
        int generated = new Random().nextInt(4);
        return generated != previous ? generated : generateState(previous);
    }

}
