package pertemuansatu.android.asdos.latihandesign;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        imgView = (ImageView) findViewById(R.id.imageView);
        imgView.setOnClickListener(new View.OnClickListener() {
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Toast.makeText(MainActivity.this, "Menu Setting", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_signup) {
            startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            return true;
        }

        if (id == R.id.action_web_activity) {
            startActivity(new Intent(MainActivity.this, WebMainActivity.class));
            return true;
        }

        if (id == R.id.action_share_text) {
            String teks = "coba teks";
            Intent intent = new Intent();
            intent.setAction(intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, teks);
            intent.setType("text/plain");
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_notification) {
            startActivity(new Intent(MainActivity.this, NotificationActivity.class));
        }

        if (id == R.id.action_sms) {
            startActivity(new Intent(MainActivity.this, SMSMainActivity.class));
        }

        if (id == R.id.action_sqlite) {
            startActivity(new Intent(MainActivity.this, CRUDActivity.class));
        }
        if (id == R.id.action_xmlparse) {
            startActivity(new Intent(MainActivity.this, XMLParseActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            imgView.setImageURI(imageUri);
        }
    }
}
