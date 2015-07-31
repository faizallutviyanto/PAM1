package com.ngampus.cafetaria;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
    private EditText nama_pemesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View getStartedButton = findViewById(R.id.button_ayo_pesan);
        getStartedButton.setOnClickListener(this);

        nama_pemesan = (EditText) findViewById(R.id.nama_pemesan);
        final int oldGrav = nama_pemesan.getGravity();
        nama_pemesan.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (nama_pemesan.getText().toString().equals("")) {
                    nama_pemesan.setGravity(oldGrav);
                } else {
                    nama_pemesan.setGravity(Gravity.CENTER);
                }
                return false;
            }
        });
    }

    public String getNama() {
        return nama_pemesan.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_ayo_pesan:
                if (getNama().equals("") || getNama() == null) {
                    new AlertDialog.Builder(this).setTitle(R.string.informasi_tak_lengkap).setMessage(R.string.harus_diisi).setCancelable(false)
                            .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                } else {
                    Intent i = new Intent(this, Pemesanan.class);
                    startActivity(i);
                }
        }
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
                new AlertDialog.Builder(this).setTitle(R.string.help_title).setMessage(R.string.isi_nomor_meja).setCancelable(false)
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                return true;
            case R.id.exit:
                new AlertDialog.Builder(this).setTitle(R.string.exit).setMessage("Apakah Anda Akan Keluar?").setCancelable(true)
                        .setPositiveButton("YA", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.exit(0);
                            }
                        }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {

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
