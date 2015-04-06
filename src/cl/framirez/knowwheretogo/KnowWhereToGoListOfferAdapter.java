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

import java.util.List;

import cl.framirez.knowwheretogo.entity.KnowWhereToGoOffer;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class KnowWhereToGoListOfferAdapter extends BaseAdapter {
	  private Context mcontext;
	  private List<KnowWhereToGoOffer> values;
	  

	  //public KnowWhereToGoListOfferAdapter(Context context, List<LyricsSongLyric> lyrics) {
	  public KnowWhereToGoListOfferAdapter(Context context, List<KnowWhereToGoOffer> lyrics) {
		  	super();
		    this.mcontext = context;
		    this.values = lyrics;
	  }
	  //@Override
	  public View getView(int position, View rowView, ViewGroup parent) {
		  //gat a information about the user for show a image		  
		  LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		  rowView = inflater.inflate(R.layout.item_offer,null);
		  TextView textName = (TextView)rowView.findViewById(R.id.textViewName);
		  TextView textOwner = (TextView) rowView.findViewById(R.id.textView_author);
		  TextView textTime = (TextView) rowView.findViewById(R.id.textView_time);
		 // TextView textCredentials = (TextView)rowView.findViewById(R.id.textView_points);
		  ImageView imageOwner = (ImageView)rowView.findViewById(R.id.image_owner);
		  
		  
		  textName.setText(values.get(position).getOffer_name());
		  textOwner.setText(values.get(position).getDescription().substring(0, 10)+"...");
		  textTime.setText(values.get(position).getTime_hh()+":"+values.get(position).getTime_mm()+" hrs");
		  
	    return rowView;
	  }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return values.size();
	}
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
}

