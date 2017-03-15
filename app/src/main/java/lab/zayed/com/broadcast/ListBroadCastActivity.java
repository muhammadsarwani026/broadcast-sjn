package lab.zayed.com.broadcast;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListBroadCastActivity extends AppCompatActivity {

    //private ProgressDialog pDialog;
    private List<Informasi> informasiList;
    private String urlJsonArray = "http://172.25.87.79:81/jav/";
    private static String TAG = MainActivity.class.getSimpleName();
    private Informasi infor;
    private ProgressBar progressBar;
    private RecyclerView rv;
    private recyclerViewInformasiAlternatif ri;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_broad_cast2);
        progressBar = (ProgressBar) findViewById(R.id.progresBar);
        rv = (RecyclerView) findViewById(R.id.recyclerView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        informasiList = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(this);
        ri = new recyclerViewInformasiAlternatif();

        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(ri);

        makeJsonArrayRequest();
        //loadData();

        /*pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        hideDialog();*/

        Intent intent = getIntent();
        if(intent.getStringExtra("username").equals("user")) {
            fab.setVisibility(View.GONE);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                //startActivity(new Intent(ListBroadCastActivity.this, SendBroadCastActivity.class));
                //informasiList.clear();
                //ri.notifyItemRangeRemoved(0, informasiList.size());
                startActivityForResult(new Intent(getBaseContext().getApplicationContext(),SendBroadCastActivity.class), 1);
            }
        });
    }

    private void makeJsonArrayRequest() {
        //showDialog();
        JsonArrayRequest req = new JsonArrayRequest(urlJsonArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "Response : " + response.toString());
                        try {
                            // Parsing json array response
                            // loop through each json object
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonInformasi = (JSONObject) response.get(i);
                                infor = new Informasi(jsonInformasi.getString("id"), jsonInformasi.getString("judul"), jsonInformasi.getString("isi"), jsonInformasi.getString("jampost"));
                                informasiList.add(infor);
                            }
                            ri.addAll(informasiList);
                            ri.setLoading(false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        //hideDialog();
                        progressBar.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }

    /*private void showDialog() {
        if(!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    private void hideDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }*/

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            //Update List
            Toast.makeText(this, "MASUK RESULT OK", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.VISIBLE);

            makeJsonArrayRequest();
        }

        /*else if (resultCode == RESULT_CANCELED) {
            //Do nothing?
            //Toast.makeText(this, "MASUK RESULT CANCELED", Toast.LENGTH_SHORT).show();
        }*/
    }

    /*private void loadData() {
        ri = new recyclerInformasi(informasiList);
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseContext().getApplicationContext());
        rv.setLayoutManager(mLayoutManager);
        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(ri);

        rv.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        ri.notifyDataSetChanged();
    }*/
}

