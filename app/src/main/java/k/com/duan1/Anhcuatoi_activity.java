package k.com.duan1;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import k.com.duan1.Adapter.AnhcuatoiAdapter;
import k.com.duan1.AnhCuaToi.AnhCuaToi;
import k.com.duan1.Database.Database;

public class Anhcuatoi_activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button btnAdd, btnCanel;
    private EditText edtTen, edtMoTa;
    private ImageButton ibtnCamera, ibtnFolder;
    private ImageView imgHinh;
    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FOLDER = 456;
    public static Database database;
    ListView lvAncuatoi;
    ArrayList<AnhCuaToi> arrayAnhCuaToi;
    AnhcuatoiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anhcuatoi_activity);
        lvAncuatoi = (ListView) findViewById(R.id.lvAnhCuaToi);
        arrayAnhCuaToi = new ArrayList<>();
        adapter = new AnhcuatoiAdapter(this,R.layout.dong_anhcuatoi,arrayAnhCuaToi);
        lvAncuatoi.setAdapter(adapter);

        database = new Database(this, "QuanLy.sqlite", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS AnhCuaToi(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(150), MoTa VARCHAR(250), HinhAnh BLOB)");

//        get data
        Cursor cursor = database.GetData("SELECT * FROM AnhCuaToi");
        while (cursor.moveToNext()){
            arrayAnhCuaToi.add(new AnhCuaToi(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getBlob(3)
            ));
        }
        adapter.notifyDataSetChanged();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        AnhXa();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                chuyen data imagesview qua byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                byte[] hinhAnh = byteArray.toByteArray();
                Anhcuatoi_activity.database.INSERT_AnhCuaToi(
                        edtTen.getText().toString().trim(),
                        edtMoTa.getText().toString().trim(),
                        hinhAnh
                );
                Toast.makeText(Anhcuatoi_activity.this, "Đã Thêm", Toast.LENGTH_SHORT).show();
            }
        });
        ibtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });

        ibtnFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });
    }

    private void AnhXa() {
        btnAdd = (Button) findViewById(R.id.btnAdd);

        edtMoTa = (EditText) findViewById(R.id.edMoTa);
        edtTen = (EditText) findViewById(R.id.edTen);
        imgHinh = (ImageView) findViewById(R.id.imgHinh);
        ibtnCamera = (ImageButton) findViewById(R.id.ibtnCamera);
        ibtnFolder = (ImageButton) findViewById(R.id.ibtnFoder);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);
        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgHinh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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
}
