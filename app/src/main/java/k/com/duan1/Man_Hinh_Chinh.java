package k.com.duan1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import k.com.duan1.Adapter.CustomAdapter;

public class Man_Hinh_Chinh extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageButton imgbtnLogin, imgbtnSearch;
    GridView simpleGrid;
    int logos[] = {R.drawable.thanhpho2,R.drawable.sacmau,
            R.drawable.thiennhien, R.drawable.tinhyeu,
            R.drawable.dongvat, R.drawable.tainhieunhat2,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man__hinh__chinh);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        imgbtnLogin = (ImageButton) findViewById(R.id.imgbtnLogin);
        imgbtnSearch = (ImageButton) findViewById(R.id.imgbtnSearch);
        simpleGrid = (GridView) findViewById(R.id.simpleGridView); // init GridView

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), logos);
        simpleGrid.setAdapter(customAdapter);
        simpleGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent = new Intent(Man_Hinh_Chinh.this, ActivitySecond.class);
                intent.putExtra("image", logos[arg2]);
                startActivity(intent);
            }
        });
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        imgbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intentLogin = new Intent(Man_Hinh_Chinh.this, Login.class);
                startActivity(intentLogin);
            }
        });
        imgbtnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intentSearch = new Intent(Man_Hinh_Chinh.this, Search.class);
                startActivity(intentSearch);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_man__hinh__chinh_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dongvat) {

        } else if (id == R.id.nav_sacmau) {

        } else if (id == R.id.nav_tainhieunhat) {

        } else if (id == R.id.nav_thiennhien) {

        } else if (id == R.id.nav_tinhyeu) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void perform_action(View v) {
        TextView tv = (TextView) findViewById(R.id.tvThuVien);
        //alter text of textview widget
        tv.setText("Thư Viện");
        //assign the textview forecolor
        tv.setTextColor(Color.GREEN);
    }

    public void Moi_Nhat(View view) {
        TextView tv = (TextView) findViewById(R.id.tvMoiNhat);
        //alter text of textview widget
        tv.setText("Mới Nhất");
        //assign the textview forecolor
        tv.setTextColor(Color.GREEN);
    }

    public void Anh_Cua_Toi(View view) {
        TextView tv = (TextView) findViewById(R.id.tvAnhCuaToi);
        Intent intentAnhCuaToi = new Intent(Man_Hinh_Chinh.this, Anhcuatoi_activity.class);
        startActivity(intentAnhCuaToi);
    }

    public void Anh_Noi_Bat(View view) {
        final TextView tv = (TextView) findViewById(R.id.tvAnh_Noi_Bat);
        tv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Man_Hinh_Chinh.this, tv);
                popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(Man_Hinh_Chinh.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();
            }
        });
    }

}
