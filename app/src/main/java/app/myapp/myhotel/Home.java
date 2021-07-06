package app.myapp.myhotel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.myapp.myhotel.AdapterRecycler.Adapter_Home;
import app.myapp.myhotel.Model.InfoHotel;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.VolleySetting;

public class Home extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_home, container, false);


        RecyclerView recyclerView=view.findViewById(R.id.recycler);


        dataMessage(recyclerView);


        return view;

    }

















    private void dataMessage(RecyclerView r) {

        final String token = SaveToken.getInstanse(getActivity()).getToken().getToken();

        ArrayList<InfoHotel> arrayList1 =new ArrayList<>();




        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_HOTEL_URL, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);

                        InfoHotel infoHotel=new InfoHotel();

                        infoHotel.setId(screen.getInt("id"));
                        infoHotel.setImage1(screen.getString("image1"));
                        infoHotel.setImage2(screen.getString("image2"));
                        infoHotel.setImage3(screen.getString("image3"));
                        infoHotel.setNamehotel(screen.getString("namehotel"));
                        infoHotel.setCountry(screen.getString("country"));
                        infoHotel.setCity(screen.getString("city"));
                        infoHotel.setEvaluation(screen.getString("evaluation"));
                        arrayList1.add(infoHotel);


                    }

                    Adapter_Home adapter_home=new Adapter_Home(arrayList1,getActivity());

                    r.setAdapter(adapter_home);
                    r.setLayoutManager(new LinearLayoutManager(getActivity()));
                    r.setHasFixedSize(true);



                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        }) {
            public Map<String, String> getHeaders() {
                Map<String, String> map = new HashMap<>();
                map.put("Accept", "application/json");
                map.put("Authorization", "Bearer  " + token);
                return map;
            }

        };
        VolleySetting.getInstance(getActivity()).addRequest(jsonObjectRequest);


    }

}