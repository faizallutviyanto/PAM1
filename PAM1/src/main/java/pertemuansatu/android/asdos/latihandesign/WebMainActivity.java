package pertemuansatu.android.asdos.latihandesign;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class WebMainActivity extends ActionBarActivity {

    Button btnWeb, btnBrowse;
    ImageView imgReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_main);

        imgReceive = (ImageView) findViewById(R.id.imgReceive);

        Intent receivedIntent = getIntent();
        String action = receivedIntent.getAction();
        String tipe = receivedIntent.getType();

        if(Intent.ACTION_SEND.equals(action) && tipe != null) {
            if (tipe.startsWith("image/")) {
                Uri imageUri = (Uri) receivedIntent.getParcelableExtra(Intent.EXTRA_STREAM);
                if (imageUri != null) {
                    imgReceive.setImageURI(imageUri);
                }
            } else if (tipe.equals("text/plain")) {
                String teksTerima = receivedIntent.getStringExtra(Intent.EXTRA_TEXT);
                Toast.makeText(WebMainActivity.this, teksTerima, Toast.LENGTH_SHORT).show();
            }
        }
        btnBrowse = (Button) findViewById(R.id.btnBrowse);
        btnWeb = (Button) findViewById(R.id.btnWebview);
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.google.com");
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, "fti uksw");
                startActivity(intent);
            }
        });

        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web_main, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            imgReceive.setImageURI(imageUri);
        }
    }
}
