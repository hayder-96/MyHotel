package app.myapp.myhotel;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import app.myapp.myhotel.AdapterRecycler.Adapter_ResUser;
import app.myapp.myhotel.Model.InfoHotel;
import app.myapp.myhotel.Model.InfoUser;
import app.myapp.myhotel.ServerApi.LinkServer;
import app.myapp.myhotel.Shaerd.SaveToken;
import app.myapp.myhotel.Shaerd.VolleySetting;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Request_User#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Request_User extends Fragment {


    Adapter_ResUser adapter_resUser;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Request_User() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Request_User.
     */
    // TODO: Rename and change types and number of parameters
    public static Request_User newInstance(String param1, String param2) {
        Request_User fragment = new Request_User();
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
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_request__user, container, false);

        RecyclerView recyclerView=view.findViewById(R.id.rec_req);

//        ArrayList<InfoUser> arrayList =dataMessage();
//
//        adapter_resUser=new Adapter_ResUser(arrayList,getActivity());
//        recyclerView.setAdapter(adapter_resUser);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setHasFixedSize(true);

        //Toast.makeText(getActivity(),arrayList.size()+"a",Toast.LENGTH_SHORT).show();

        dataMessage(recyclerView);
        return view;
    }














    private void dataMessage(RecyclerView recyclerView) {

        final String token = SaveToken.getInstanse(getActivity()).getToken().getToken();
          int id=Integer.parseInt(SaveToken.getInstanse(getActivity()).getToken().getId());

        ArrayList<InfoUser> arrayList1 =new ArrayList<>();


        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, LinkServer.GET_USERS_URL+id, null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {

                try {


                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject screen = jsonArray.getJSONObject(i);



                        InfoUser infoUser=new InfoUser();
                        infoUser.setId(screen.getInt("id"));
                        infoUser.setName(screen.getString("name"));
                        infoUser.setPhone(screen.getString("phone"));
                        infoUser.setEmail(screen.getString("email"));
                        infoUser.setCountry(screen.getString("country"));
                        infoUser.setNameroom(screen.getString("nameroom"));
                        infoUser.setTyperoom(screen.getString("typeroom"));
                        infoUser.setNumbed(screen.getString("numbed"));
                        infoUser.setTypebed(screen.getString("priceroom"));
                        infoUser.setPriceroom(screen.getString("typebed"));
                        infoUser.setAccess(screen.getString("access"));
                        infoUser.setLeaving(screen.getString("leaving"));
                        infoUser.setNumguest(screen.getString("numguest"));
                        infoUser.setTypetrip(screen.getString("typetrip"));
                        infoUser.setRoom_id(screen.getString("room_id"));
                         infoUser.setNamehotel(screen.getString("namehotel"));
                        infoUser.setUser_id(screen.getString("user_id"));



                        arrayList1.add(infoUser);
                    }

                    Adapter_ResUser adapter_resUser=new Adapter_ResUser(arrayList1,getActivity());
                    recyclerView.setAdapter(adapter_resUser);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setHasFixedSize(true);
                    adapter_resUser.notifyDataSetChanged();





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