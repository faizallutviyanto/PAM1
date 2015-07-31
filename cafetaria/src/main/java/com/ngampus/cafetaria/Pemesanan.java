package com.ngampus.cafetaria;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import static android.provider.BaseColumns._ID;
import static com.ngampus.cafetaria.Constants.PILIHAN;
import static com.ngampus.cafetaria.Constants.SPESIAL;
import static com.ngampus.cafetaria.Constants.TABLE_NAME;
import static com.ngampus.cafetaria.Constants.UKURAN;

/**
 * Created by Faizal Lutviyanto on 7/30/2015.
 */
@SuppressWarnings("deprecation")
public class Pemesanan extends ListActivity implements OnClickListener {
    private DataMinuman data;
    private boolean hasShown = false;
    private TextView totalText;
    private Cursor cursorEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);
        getListView().setChoiceMode(1);
        getListView().setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                cursorEdit = (Cursor) getListView().getItemAtPosition(arg2);
                editMinuman();
            }

        });

        data = new DataMinuman(this);
        try {

        } finally {
            data.close();
        }

        //set button onclicklistener
        View getButtonPesanBaru = findViewById(R.id.button_pesan_baru);
        getButtonPesanBaru.setOnClickListener(this);
        View getButtonKeluar = findViewById(R.id.button_keluar);
        getButtonKeluar.setOnClickListener(this);
        totalText = (TextView) findViewById(R.id.total);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getListView().getCount() == 0)
            totalText.setText("Total: RP. 0.00");
        else
            totalText.setText("Total: Rp. " + (getListView().getCount() * 9.99));
    }

    private void editMinuman() {
        int currentPizza = cursorEdit.getInt(0);
        Bundle bundle = new Bundle();
        bundle.putInt(_ID, currentPizza);

        Intent intent = new Intent(Pemesanan.this, MinumanBaru.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_pesan_baru:
                if (!hasShown) {
                    openHowToDialog();
                    hasShown = true;
                } else {
                    openSizeSelectionDialog();
                }
                break;
            case R.id.button_keluar:
                if (getListView().getCount() != 0)
                    dialogKeluar();
                else {
                    new AlertDialog.Builder(this).setTitle("Info").setMessage("Anda Harus Memesan, Sebelum Keluar.").setCancelable(false)
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    openSizeSelectionDialog();
                                }
                            }).show();
                }
                break;

        }
    }

    private void openHowToDialog() {
        new AlertDialog.Builder(this).setTitle(R.string.cara_pesan).setMessage(R.string.cara_pesan_teks).setCancelable(false)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openSizeSelectionDialog();
                    }
                }).show();
    }

    private void openSizeSelectionDialog() {
        new AlertDialog.Builder(this).setTitle(R.string.ukuran_kopi).setItems(R.array.ukuran, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String ukuran;
                if (which == 0)
                    ukuran = "gelas_kecil_label";
                else
                    ukuran = "gelas_besar_label";
                openPilihanSelectionDialog(ukuran);
            }
        }).show();
    }

    protected void openPilihanSelectionDialog(final String ukuran) {
        new AlertDialog.Builder(this).setTitle(R.string.pilihan_selection).setItems(R.array.pilihan, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String pilihan;
                if (which == 0)
                    pilihan = "hangat";
                else
                    pilihan = "dingin";
                startKopiCreation();
            }
        }).show();
    }

    protected void startKopiCreation() {
        Intent intent = new Intent(Pemesanan.this, MinumanBaru.class);
        startActivity(intent);
    }

    private void dialogKeluar() {
        new AlertDialog.Builder(this).setTitle(R.string.checkout_title).setMessage(R.string.checkout_text).setCancelable(false)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.getWritableDatabase().delete(TABLE_NAME, null, null);
                        finish();
                    }
                }).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                return true;
            case R.id.help:
                new AlertDialog.Builder(this).setTitle(R.string.help_title).setMessage(R.string.edit_order_help).setCancelable(false)
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                return true;
            case R.id.exit:
                new AlertDialog.Builder(this).setTitle(R.string.exit).setMessage("Are you sure you want to exit?").setCancelable(true)
                        .setNeutralButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.exit(0);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                return true;
        }
        return false;
    }
}
