package loginregister.swoking.fr.loginregister;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import loginregister.swoking.fr.loginregister.adapter.SlidingMenuAdapter;
import loginregister.swoking.fr.loginregister.fragment.Fragment1;
import loginregister.swoking.fr.loginregister.fragment.Fragment2;
import loginregister.swoking.fr.loginregister.fragment.Fragment3;
import loginregister.swoking.fr.loginregister.model.ItemSlideMenu;

public class test extends ActionBarActivity {

    private List<ItemSlideMenu> listSlideing;
    private SlidingMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // init component
        listViewSliding = (ListView)findViewById(R.id.lv_sliding_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listSlideing = new ArrayList<>();
        //Add item for sliding list
        listSlideing.add(new ItemSlideMenu(R.drawable.ic_action_search, "Rechercher"));
        listSlideing.add(new ItemSlideMenu(R.drawable.ic_action_profil, "Profil"));
        listSlideing.add(new ItemSlideMenu(R.drawable.ic_action_setting, "Option"));
        adapter = new SlidingMenuAdapter(this, listSlideing);
        listViewSliding.setAdapter(adapter);

        //Display icon to open / close sliding list
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set title
        setTitle(listSlideing.get(0).getTitle());
        //item selected
        listViewSliding.setItemChecked(0, true);
        //Close menu
        drawerLayout.closeDrawer(listViewSliding);

        //Hande on item click

        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Set title
                setTitle(listSlideing.get(i).getTitle());
                //item selected
                listViewSliding.setItemChecked(i, true);
                //replace fragment
                replaceFragment(i);
                //close menu
                drawerLayout.closeDrawer(listViewSliding);
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_opened, R.string.drawer_closed){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        actionBarDrawerToggle.syncState();
    }

    //Create method replace fragment

    private  void replaceFragment(int pos) {
        Fragment fragment = null;
        switch (pos){
            case 0 :
                fragment = new Fragment1();
                break;
            case 1 :
                fragment = new Fragment2();
                break;
            case 2 :
                fragment = new Fragment3();
                break;
            default:
                fragment = new Fragment1();
                break;
        }

        if(null!=fragment) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_ceontent, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
