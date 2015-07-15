package pertemuansatu.android.asdos.latihandesign;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import customtools.CustomArrayAdapter;
import customtools.DataMahasiswa;
import customtools.MySQLiteHelper;


public class CRUDActivity extends ActionBarActivity implements View.OnClickListener {

    Button btnAdd;
    Button btnDelete;
    ListView listData;
    MySQLiteHelper util;
    CustomArrayAdapter adapter;
    ArrayList<DataMahasiswa> listMahasiswa = new ArrayList<DataMahasiswa>();
    EditText txtNim;
    EditText txtNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        listData = (ListView) findViewById(R.id.listViewData);
        txtNim = (EditText) findViewById(R.id.txtNimCRUD);
        txtNama = (EditText) findViewById(R.id.txtNamaCRUD);
        util = new MySQLiteHelper(getApplicationContext());
        listMahasiswa = util.getAllDataMahasiswa();
        adapter = new CustomArrayAdapter(this, listMahasiswa);
        listData.setAdapter(adapter);
        btnAdd.setOnClickListener(this);
        registerForContextMenu(listData);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_crud, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.action_delete_data:
                Toast.makeText(getApplicationContext(), "nim : "+ listMahasiswa.get((int)info.id).getNim(), Toast.LENGTH_SHORT).show();
                util.deleteMahasiswa(listMahasiswa.get((int)info.id).getNim());
                refreshListView();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnAdd) {
            String nim = txtNim.getText().toString().trim();
            String nama = txtNama.getText().toString().trim();
            if (!nim.isEmpty() || !nama.isEmpty()) {
                util.addMahasiswa(new DataMahasiswa(nim, nama));
                refreshListView();
            }
            txtNim.setText("");
            txtNama.setText("");
        }
    }

    public void refreshListView() {
        listMahasiswa.clear();
        listMahasiswa = new MySQLiteHelper(getApplicationContext()).getAllDataMahasiswa();
        adapter.clear();
        adapter.addAll(listMahasiswa);
        adapter.notifyDataSetChanged();
    }

}
