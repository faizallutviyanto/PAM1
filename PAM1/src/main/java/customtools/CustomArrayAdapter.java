package customtools;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import pertemuansatu.android.asdos.latihandesign.R;
import java.util.ArrayList;

/**
 * Created by ADMIN on 7/7/2015.
 */
public class CustomArrayAdapter extends ArrayAdapter<DataMahasiswa> {

    public Context context;
    public Activity activity;
    public ArrayList<DataMahasiswa> listData;

    public CustomArrayAdapter(Context context, ArrayList<DataMahasiswa> listValue) {
        super(context, R.layout.item_listview, listValue);
        this.context = context;
        this.listData = listValue;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_listview, parent, false);
        TextView nim = (TextView) rowView.findViewById(R.id.txtNim);
        TextView nama = (TextView) rowView.findViewById(R.id.txtNama);
        DataMahasiswa data = listData.get(position);
        nim.setText(data.getNim());
        nama.setText(data.getNama());
        Log.d("Konten", data.getNim() + " : " + data.getNama());
        return rowView;
    }
}
