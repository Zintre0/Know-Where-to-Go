/**
Copyright 2014 Fabian Ramirez Barrios
This file is part of GeoComm.

GeoComm is free software: you can redistribute it and/or modify 
it under the terms of the GNU General Public License as published by 
the Free Software Foundation, either version 3 of the License, or 
(at your option) any later version.

GeoComm is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of 
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
GNU General Public License for more details.

You should have received a copy of the GNU General Public License 
along with GeoComm.  If not, see <http://www.gnu.org/licenses/>.

**/
package cl.framirez.knowwheretogo;

import java.util.ArrayList;
import java.util.List;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.MinimapOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

//import cl.fabian.mapview.MainActivity;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
 
public class KnowWhereToGoMapViewActivity extends Activity {
	
	
	private final String TAGNAME= "MapViewMainActivity";
	private int idroute = 0;
	
//	private MyItemizedOverlay myItemizedOverlay = null;
	private IMapController myMapController;
	private LocationManager locationManager;
	private MapView mapView;
//    List<GeoCommPoint> points;
    ArrayList<OverlayItem> anotherOverlayItemArray;
    
    
    //OSM
    private MyLocationNewOverlay mLocationOverlay;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_map_view_main);
        
        Intent intent = getIntent();
        if ( intent.getExtras() != null ) {
        	//Getting id of route;
        	this.idroute = intent.getIntExtra("IDROUTE",0);
        	
        }
        if (this.idroute == 0) {
        	Toast.makeText(this,
        			getResources().getString(R.string.mssg_notrouteid)
        			,Toast.LENGTH_LONG).show();
        }
        else {
        	this.getPointsOfRoute(this.idroute);
        }
	    
	    //LOCATION MANAGER
	    this.locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    Location LastLocation = this.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	    
	    //MAP VIEW CONF
	    mapView = (MapView) findViewById(R.id.openmapview);
	    mapView.setMultiTouchControls(true);
	    myMapController = mapView.getController();
	    
        mapView.setBuiltInZoomControls(true);
        myMapController = mapView.getController();
        myMapController.setZoom(16);
        //Set Center
        //myMapController.setCenter(new GeoPoint(LastLocation.getLatitude(),
        //		LastLocation.getLongitude()));
        
         
        //OSM
        this.mLocationOverlay = new MyLocationNewOverlay(this, new GpsMyLocationProvider(this),
        		mapView);
        mapView.getOverlays().add(this.mLocationOverlay);
        
        mLocationOverlay.enableMyLocation();
        mLocationOverlay.enableFollowLocation();
    }

    
    public void getPointsOfRoute(int idroute){
    	//Get Points of Server
    	points = new ArrayList<GeoCommPoint>();
    	GeoCommGlobalClass gc=new GeoCommGlobalClass();
    	//String host = "http://172.16.51.94/~laost/Symfony/web/app_dev.php/getPointsOfRoute/";
    	//String host = "http://"+gc.getIp()+"/~zintre/getPoints.php";
    	String host = gc.getHostPoints();
  	  	//Log.i(TAGNAME, "entropoints: "+idroute);
  	  	GeoCommGetPointsAsyncTask getpoints = new GeoCommGetPointsAsyncTask(this,host,idroute,points);
  	  	getpoints.execute();
    	//Create overlay with points
    }
    
    public void drawPoints(){
    	Log.i(TAGNAME, "ALGOWENON");
    	//anotherOverlayItemArray = new ArrayList<OverlayItem>();
    	Drawable marker=getResources().getDrawable(R.drawable.mark_location);
        
        int markerWidth = marker.getIntrinsicWidth();
        int markerHeight = marker.getIntrinsicHeight();
        marker.setBounds(0, markerHeight, markerWidth, 0);
        ResourceProxy resourceProxy = new DefaultResourceProxyImpl(getApplicationContext());
        
        myItemizedOverlay = new MyItemizedOverlay(marker, resourceProxy);
        
        ArrayList<OverlayItem> anotherOverlayItemArray;
        anotherOverlayItemArray = new ArrayList<OverlayItem>();
        
        //anotherOverlayItemArray.add(new OverlayItem("0, 0", "0, 0", new GeoPoint(0, 0)));
		//anotherOverlayItemArray.add(new OverlayItem("US", "US", new GeoPoint(38.883333, -77.016667)));
    	
    	for (int i =0;i<this.points.size();i++){
    		GeoPoint myPoint = new GeoPoint(this.points.get(i).getLatitude(),this.points.get(i).getLongitude());
    		anotherOverlayItemArray.add(new OverlayItem(this.points.get(i).getName(), this.points.get(i).getDescription(), myPoint));
    		//myItemizedOverlay.addItem(new OverlayItem(this.points.get(i).getName(), Integer.toString(this.points.get(i).getId()), myPoint));
            //myItemizedOverlay.addItem(myPoint,this.points.get(i).getName(), Integer.toString(this.points.get(i).getId()));
            //myItemizedOverlay.onItemLongPress(arg0, );
            //anotherOverlayItemArray.add(new OverlayItem(points.get(i).getName(), Integer.toString(points.get(i).getId()), myPoint));
    	}
    	ItemizedIconOverlay.OnItemGestureListener<OverlayItem> myOnItemGestureListener = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {

            @Override
            public boolean onItemLongPress(int arg0, OverlayItem arg1) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                AlertDialog.Builder builder = new AlertDialog.Builder(KnowWhereToGoMapViewActivity.this);
                //LayoutInflater inflater = KnowWhereToGoMapViewActivity.this.getLayoutInflater();

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                //builder.setView(inflater.inflate(R.layout.dialog_in_points, null));
                builder.setIcon(R.drawable.ic_launcher);

                
                builder.setPositiveButton(getResources().getString(R.string.deletePoints_ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                });
                builder.setMessage(item.getSnippet());
                builder.setTitle(item.getTitle());
                AlertDialog dialog = builder.create();
                dialog.show();



                return true;
            }

        };
        ItemizedOverlayWithFocus<OverlayItem> anotherItemizedIconOverlay = new ItemizedOverlayWithFocus<OverlayItem>(this, anotherOverlayItemArray, myOnItemGestureListener);
        //anotherItemizedIconOverlay.setFocusItemsOnTap(true);
        mapView.getOverlays().add(anotherItemizedIconOverlay);
        
        MinimapOverlay miniMapOverlay = new MinimapOverlay(this, mapView.getTileRequestCompleteHandler());
        miniMapOverlay.setZoomDifference(5);
        miniMapOverlay.setHeight(200);
        miniMapOverlay.setWidth(200);
        mapView.getOverlays().add(miniMapOverlay);
    	
    	//this.mapView.getOverlays().add(myItemizedOverlay);
    }
   
    
		@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.map_view_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
			if (id == R.id.status) {
				return true;
			}
		return super.onOptionsItemSelected(item);
	}


	@Override
    protected void onResume(){
		super.onResume();
		Log.i(TAGNAME,"onResume");
		this.mLocationOverlay.enableMyLocation();
		this.mLocationOverlay.enableFollowLocation();
		
	}
	@Override
    protected void onPause(){
		super.onPause();
		Log.i(TAGNAME,"onPause");
		this.mLocationOverlay.disableMyLocation();
		this.mLocationOverlay.disableFollowLocation();
	}
	@Override
    protected void onStop(){
		super.onStop();
		Log.i(TAGNAME,"onStop");
		this.mLocationOverlay.disableMyLocation();
		this.mLocationOverlay.disableFollowLocation();
		
	}
	@Override
    protected void onDestroy(){
		super.onDestroy();
		Log.i(TAGNAME,"onDestroy");
		this.mLocationOverlay.disableMyLocation();
		this.mLocationOverlay.disableFollowLocation();
		
		this.setResult(RESULT_OK);
	}
	
}
