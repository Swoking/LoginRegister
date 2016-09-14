package loginregister.swoking.fr.loginregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    public int id = 0;
    public String bio = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        id = getIntent().getIntExtra("id", -1);
        bio = getIntent().getStringExtra("bio");

        TextView txtBio = (TextView) findViewById(R.id.txtBio);
        txtBio.setText(bio);

        SlideShowView slideShow = (SlideShowView) findViewById(R.id.slideShow);
        slideShow.start(getIntent().getStringArrayExtra("url"));
    }

}