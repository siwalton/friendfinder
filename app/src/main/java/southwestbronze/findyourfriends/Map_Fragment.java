package southwestbronze.findyourfriends;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;


import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Map_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Map_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Map_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // private static final String ARG_PARAM1 = "param1";
    //  private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //  private String mParam1;
    //  private String mParam2;

    GoogleMap map;
    LocationManager locationManager;
    LatLng currLoc;

    private OnFragmentInteractionListener mListener;

    public Map_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Map_Fragment newInstance(String param1, String param2) {
        Map_Fragment fragment = new Map_Fragment();
        Bundle args = new Bundle();
        //   args.putString(ARG_PARAM1, param1);
        //    args.putString(ARG_PARAM2, param2);
        //  fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //       mParam1 = getArguments().getString(ARG_PARAM1);
            //      mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View infl =inflater.inflate(R.layout.fragment_map, container, false);

        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment myMapFragment=(SupportMapFragment) fm.findFragmentById(R.id.location_map);


        // here instead of using getMap(), we are using getMapAsync() which returns a callback and shows map only when it gets ready.
        //it automatically checks for null pointer or older play services version, getMap() is deprecated as mentioned in google docs.


        myMapFragment.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googlemap) {
                // TODO Auto-generated method stub
                map=googlemap;
                refreshLoc();
            }
        });



        return infl;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;





            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                AlertDialog ad = new AlertDialog.Builder(context)
                        .create();
                ad.setCancelable(false);
                ad.setTitle("Error");
                ad.setMessage("Please enable location services");
                ad.setButton("Dismiss", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ad.show();
            }
            else {
                double longitude = 0;
                double latitude = 0;
                LocationRequest mLocationRequest = LocationRequest.create();

                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location!=null){
                    longitude = location.getLongitude();
                    latitude = location.getLatitude();
                    String locLat = String.valueOf(latitude)+","+String.valueOf(longitude);
                    currLoc = new LatLng(latitude, longitude);
                    //MarkerOptions mkrOpt = new MarkerOptions();
//                    if(map!=null) {
//                        map.addMarker(mkrOpt.position(currLoc).title("YOU!"));
//                        map.moveCamera(CameraUpdateFactory.newLatLng(currLoc));
//                    }
                }

//                AlertDialog ad = new AlertDialog.Builder(context)
//                        .create();
//                ad.setCancelable(false);
//                ad.setTitle("Error");
//                ad.setMessage("You're at "+latitude+" and "+longitude);
//                ad.setButton("Dismiss", new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//                ad.show();

                //    map.addMarker(new MarkerOptions()
                //              .position(new LatLng(latitude, longitude))
                //              .title("Here you are!!"));
            }
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

//    private MapFragment getMapFragment() {
//        FragmentManager fm = null;
//
//        Log.d(TAG, "sdk: " + Build.VERSION.SDK_INT);
//        Log.d(TAG, "release: " + Build.VERSION.RELEASE);
//
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            Log.d(TAG, "using getFragmentManager");
//            fm = getFragmentManager();
//        } else {
//            Log.d(TAG, "using getChildFragmentManager");
//            fm = getChildFragmentManager();
//        }
//
//        return (MapFragment) fm.findFragmentById(R.id.map);
//    }

    public void onCreate(GoogleMap googleMap) {
        map = googleMap;
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        else {
            double longitude = 0;
            double latitude = 0;
            LocationRequest mLocationRequest = LocationRequest.create();

            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                longitude = location.getLongitude();
                latitude = location.getLatitude();
                String locLat = String.valueOf(latitude) + "," + String.valueOf(longitude);
                currLoc = new LatLng(latitude, longitude);
                MarkerOptions mkrOpt = new MarkerOptions();
                if (map != null) {
                    map.addMarker(mkrOpt.position(currLoc).title("YOU!"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(currLoc));
                }
            }
        }
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void refreshLoc()
    {

        MarkerOptions mkrOpt = new MarkerOptions();
        if(map!=null && currLoc != null) {
            map.addMarker(mkrOpt.position(currLoc).title("YOU!"));
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(currLoc, 11));

        }
    }
}
