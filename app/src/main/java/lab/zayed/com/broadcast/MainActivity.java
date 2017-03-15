package lab.zayed.com.broadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    EditText editTextUsername;
    EditText editTextPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = (EditText) findViewById(R.id.editUserName);
        editTextPassword = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                Intent intent = new Intent(MainActivity.this, ListBroadCastActivity.class);

                if(editTextUsername.getText().toString().trim().equals("mhs") && editTextPassword.getText().toString().trim().equals("12345")) {
                    intent.putExtra("username", editTextUsername.getText().toString().trim());
                    intent.putExtra("password", editTextPassword.getText().toString().trim());
                    startActivity(intent);
                }

                else if (editTextUsername.getText().toString().trim().equals("user") && editTextPassword.getText().toString().trim().equals("biasa")) {
                    intent.putExtra("username", editTextUsername.getText().toString().trim());
                    intent.putExtra("password", editTextPassword.getText().toString().trim());
                    startActivity(intent);
                }

                else {
                    Toast.makeText(this, "GAGAL LOGIN", Toast.LENGTH_SHORT).show();
                }

            break;
            default:
            break;
        }
    }
}
