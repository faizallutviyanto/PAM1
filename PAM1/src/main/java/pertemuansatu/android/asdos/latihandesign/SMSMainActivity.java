package pertemuansatu.android.asdos.latihandesign;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SMSMainActivity extends ActionBarActivity implements View.OnClickListener{

    Button btnRead;
    Button btnWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsmain);
        btnRead = (Button) findViewById(R.id.btn_read);
        btnWrite = (Button) findViewById(R.id.btn_write);
        btnRead.setOnClickListener(this);
        btnWrite.setOnClickListener(this);

        Button startBtn = (Button) findViewById(R.id.btn_call);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                makeCall();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_smsmain, menu);
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

    protected void makeCall() {
        Log.i("Make call", "");

        EditText txtCallNumber;
        txtCallNumber = (EditText) findViewById(R.id.txt_call_number);
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:" + txtCallNumber.getText().toString()));

        try {
            startActivity(phoneIntent);
            finish();
            Log.i("Finished making a call...", "");

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        if(v == btnRead){
            startActivity(new Intent(getApplicationContext(), ReadSMSActivity.class));
        }
        if(v == btnWrite){
            startActivity(new Intent(getApplicationContext(), WriteSMSActivity.class));
        }
    }
}
