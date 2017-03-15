package lab.zayed.com.broadcast;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SendBroadCastActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextJudul;
    EditText editTextISI;
    Button buttonSendMessage;
    String LINK_SEND_BROADCAST = "http://172.25.87.79:81/jav/insert.php";
    ProgressDialog pDialog;
    String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_broad_cast);

        editTextJudul = (EditText) findViewById(R.id.editJudul);
        editTextISI = (EditText) findViewById(R.id.editIsiBroadCast);
        buttonSendMessage = (Button) findViewById(R.id.btnBroadCast);
        buttonSendMessage.setOnClickListener(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(true);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnBroadCast:
                Toast.makeText(this, "TELAH DI-BROADCAST", Toast.LENGTH_SHORT).show();
                showDialog();
                StringRequest strSendBroadCast = new StringRequest(Request.Method.POST, LINK_SEND_BROADCAST, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Response: " + new StringBuilder(response));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error: " + error.getMessage());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        // Posting parameters to login url
                        Calendar c = Calendar.getInstance();
                        Map<String, String> params = new HashMap<>();
                        params.put("judul", editTextJudul.getText().toString());
                        params.put("isi", editTextISI.getText().toString());
                        params.put("jampost",new StringBuilder(c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND)).toString());
                        return params;
                    }
                };
                AppController.getInstance().addToRequestQueue(strSendBroadCast);
                hideDialog();
                Intent returnIntent = new Intent();
                setResult(RESULT_OK, returnIntent);
                finish();
            break;

            default:

            break;
        }
    }

    private void showDialog() {
        if (!pDialog.isShowing()) pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing()) pDialog.dismiss();
    }
}
