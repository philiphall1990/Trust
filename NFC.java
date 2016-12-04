package trust.trustmedia.trust2;

import android.app.Activity;
import android.content.Context;
import android.nfc.*;
import android.telephony.TelephonyManager;
import java.io.UnsupportedEncodingException;


public class NFC {

    public void getNFCAndPrepareMessage(Context context, Activity activity) throws FormatException {
        NfcManager manager = (NfcManager) context.getSystemService(Context.NFC_SERVICE);
        NfcAdapter adapter = manager.getDefaultAdapter();
        String ids = packageContent(context, "NewKey");
        NdefMessage formattedMessage = this.createNdefMessage(ids);
        adapter.setNdefPushMessage(formattedMessage, activity);
    }

    public NdefMessage createNdefMessage(String content) {
        try {
            return new NdefMessage(new NdefRecord[]{NdefRecord.createExternal("Trust", "KeyRing", content.getBytes("UTF-8"))});
        } catch (UnsupportedEncodingException e) {
            System.out.println("Couldn't create NdefMessage. Error: " + e.toString());
            return null;
            }
        }


        public String packageContent(Context c, String privateKey) {
            TelephonyManager m = (TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);
            String id = m.getDeviceId();
            RSA r = new RSA();
            return r.encrypt(id).concat("|" + id);


    }

}
