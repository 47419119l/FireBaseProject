package com.example.sandra.firebaseproject;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements LocationListener {

    Firebase ref;
    Firebase NotasRef;
    Button bt;
    Button ok;
    EditText edit1;
    private double lon;
    private double lat;
    private LocationManager locationManager;

    public MainActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Firebase.setAndroidContext(getContext());
        ref = new Firebase("https://poyecto.firebaseio.com/");
        NotasRef = ref.child("Notas");


        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 5000, 10, this);

        bt = (Button) rootView.findViewById(R.id.alta);
        edit1 = (EditText) rootView.findViewById(R.id.editText);
        ok = (Button) rootView.findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getContext(), Fotis.class);
                startActivity(i);

            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom = edit1.getText().toString();
                Notas nota = new Notas(nom, lat, lon);
                edit1.setText("");
                pujarNota(nota);


            }
        });


        ListView firBaseList = (ListView) rootView.findViewById(R.id.fireBaseList);
        FirebaseListAdapter adapter = new FirebaseListAdapter<Notas>(getActivity(), Notas.class, android.R.layout.two_line_list_item, NotasRef) {
            @Override
            protected void populateView(View v, Notas model, int position) {
                ((TextView) v.findViewById(android.R.id.text1)).setText(model.getNota());
                ((TextView) v.findViewById(android.R.id.text2)).setText("    - Lat : " + String.valueOf(model.getLat()) + "\n    - Long :" + model.getLon());

            }
        };
        firBaseList.setAdapter(adapter);

        return rootView;
    }

    public void pujarNota(Notas notas) {

        Firebase nota = NotasRef.push();
        nota.setValue(notas);

    }

    public void pujarUser(User user2) {

        Firebase user = NotasRef.push();
        user.setValue(user2);

    }

    @Override
    public void onLocationChanged(Location location) {

        String msg = "New Latitude: " + location.getLatitude()
                + "New Longitude: " + location.getLongitude();
        this.lon = location.getLongitude();
        this.lat = location.getLatitude();

    }

    @Override
    public void onProviderDisabled(String provider) {

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
        Toast.makeText(getContext(), "Gps OFF",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {

        Toast.makeText(getContext(), "Gps OK ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }
}
