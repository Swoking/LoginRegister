package loginregister.swoking.fr.loginregister;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");

        final EditText etUsername   = (EditText) findViewById(R.id.login_etUsername);
        final EditText etPassword   = (EditText) findViewById(R.id.login_etPassword);
        final Button bLogin         = (Button)   findViewById(R.id.login_bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.login_tvRegisterHere);

        Button btnMenu = (Button)findViewById(R.id.btn_menu);
        btnMenu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, test.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success){
                                String name     = jsonResponse.getString("name");
                                String bio     = jsonResponse.getString("bio");
                                //String username = jsonResponse.getString("username");
                                int    age      = jsonResponse.getInt("age");
                                int    id       = jsonResponse.getInt("id");
                                JSONArray urlsJSON       = jsonResponse.getJSONArray("url");
                                String[] arr = new String[urlsJSON.length()];
                                for(int i=0; i<urlsJSON.length(); i++) {
                                    arr[i]=urlsJSON.optString(i);
                                }

                                Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
                                intent.putExtra("name", name);
                                intent.putExtra("username", username);
                                intent.putExtra("age", age);
                                intent.putExtra("id", id);
                                intent.putExtra("url", arr);
                                intent.putExtra("bio", bio);

                                LoginActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                       .setNegativeButton("Retry", null)
                                       .create()
                                       .show();
                            }
                        } catch (JSONException e) {
                            Log.e("LoginResponseERROR", "Login response are not JSON format.");
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}
