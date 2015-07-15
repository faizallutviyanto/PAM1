package pertemuansatu.android.asdos.latihandesign;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import customtools.DataMahasiswa;


public class XMLParseActivity extends ActionBarActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmlparse);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        XmlPullParserFactory pullParserFactory;
        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();
            InputStream in_s = getApplicationContext().getAssets().open("datamahasiswa.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);

            parser.setInput(in_s, null);

            parseXML(parser);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseXML(XmlPullParser parser) throws XmlPullParserException, IOException {
        ArrayList<DataMahasiswa> listMahasiswa = null;
        int eventType = parser.getEventType();
        DataMahasiswa dataMahasiswa = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String name = null;
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    listMahasiswa = new ArrayList();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if (name.equals("mahasiswa")) {
                        dataMahasiswa = new DataMahasiswa();
                    } else if (dataMahasiswa != null) {
                        if (name.equals("nim")) {
                            dataMahasiswa.setNim(parser.nextText());
                        } else if (name.equals("nama")) {
                            dataMahasiswa.setNama(parser.nextText());
                        } else if (name.equals("fakultas")) {
                            dataMahasiswa.setFakultas(parser.nextText());
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if (name.equalsIgnoreCase("mahasiswa") && dataMahasiswa != null) {
                        listMahasiswa.add(dataMahasiswa);
                    }
            }
            eventType = parser.next();
        }
        Toast.makeText(getApplicationContext(), "size list : " + listMahasiswa.size(), Toast.LENGTH_LONG).show();
        showData(listMahasiswa);
    }

    private void showData(ArrayList<DataMahasiswa> listMahasiswa) {
        String content = "";
        for(DataMahasiswa mahasiswa : listMahasiswa){
            content = content + "nim :" + mahasiswa.getNim() + "\n";
            content = content + "nama :" + mahasiswa.getNama() + "\n";
            content = content + "fakultas :" + mahasiswa.getFakultas() + "\n\n";
        }
        TextView display = (TextView) findViewById(R.id.txt_view_xmlparse);
        display.setText(content);
    }

}
