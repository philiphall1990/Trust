package trust.trustmedia.trust2;

import android.nfc.FormatException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.app.*;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

   public void verify() {
       NFC n = new NFC();
       try {
           n.getNFCAndPrepareMessage(this.getBaseContext(), this);
       }
       catch (FormatException e) {
           System.out.print(e.toString());
       }
    }

}




