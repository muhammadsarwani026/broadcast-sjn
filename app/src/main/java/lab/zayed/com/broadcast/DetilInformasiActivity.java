package lab.zayed.com.broadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetilInformasiActivity extends AppCompatActivity {

    TextView textJUDUL , textISI ,textJAMPOST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detil_informasi);

        textJUDUL = (TextView) findViewById(R.id.textJUDUL);
        textISI = (TextView) findViewById(R.id.textISI);
        textJAMPOST = (TextView) findViewById(R.id.textJAMPOST);

        Intent in = getIntent();
        textJUDUL.setText(in.getStringExtra("judul"));
        textISI.setText(in.getStringExtra("isi"));
        textJAMPOST.setText(in.getStringExtra("jampost"));
    }
}
