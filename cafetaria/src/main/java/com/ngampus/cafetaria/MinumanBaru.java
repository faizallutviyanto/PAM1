package com.ngampus.cafetaria;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static android.view.View.OnClickListener;
import static com.ngampus.cafetaria.Constants.SPESIAL;
import static com.ngampus.cafetaria.Constants.TABLE_NAME;
import static com.ngampus.cafetaria.Constants.UKURAN;

/**
 * Created by Faizal Lutviyanto on 7/30/2015.
 */
@SuppressWarnings("deprecation")
public class MinumanBaru extends Activity implements OnClickListener {

    private RadioButton spesialRadio;
    private TextView spesialText;
    private ArrayList<String> spesialList = new ArrayList<String>();
    private ArrayList<String> toppingsList = new ArrayList<String>();
    private DataMinuman data;
    int id = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baru);
        Gallery gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(this));
        View getAddToCartButton = findViewById(R.id.tambah_pesanan);
        getAddToCartButton.setOnClickListener(this);
        View getCancelButton = findViewById(R.id.hapus_button);
        getCancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
