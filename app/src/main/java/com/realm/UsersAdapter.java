package com.realm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UsersAdapter extends ArrayAdapter<UserDetails> {
    public UsersAdapter(Context context, ArrayList<UserDetails> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final UserDetails user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.text1);
        final ImageView imgAvatar = (ImageView) convertView.findViewById(R.id.avatar);
        // Populate the data into the template view using the data object
        tvName.setText(user.username);

        String urll = "https://realm-dab25.firebaseio.com/users.json";
        StringRequest request = new StringRequest(Request.Method.GET, urll, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                if(!s.equals("null")){
                    try {
                        String userAvatar = user.username;
                        JSONObject obj = new JSONObject(s);

                        String imgUrl = obj.getJSONObject(userAvatar).getString("imgURL");

                        Picasso.with(getContext()).load(imgUrl).into(imgAvatar);

                        String eboy = imgUrl;

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(getContext());
        rQueue.add(request);

        // Return the completed view to render on screen
        return convertView;
    }
}