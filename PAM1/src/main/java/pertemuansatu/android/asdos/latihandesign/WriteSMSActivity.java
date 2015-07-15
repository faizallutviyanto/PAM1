package pertemuansatu.android.asdos.latihandesign;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class WriteSMSActivity extends ActionBarActivity implements View.OnClickListener {

    Button btnSend;
    EditText txtNumber;
    EditText txtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_sms);
        btnSend = (Button) findViewById(R.id.btn_send);
        txtNumber = (EditText) findViewById(R.id.write_txt_number);
        txtMessage = (EditText) findViewById(R.id.write_txt_message);
        btnSend.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_write_sm, menu);
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

    private void sendSMS(){
        String phoneNumber = txtNumber.getText().toString();
        String smsMessage = txtMessage.getText().toString();
        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, smsMessage, null, null);
            Toast.makeText(this, "SMS Sent", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        sendSMS();
    }
}
