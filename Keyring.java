package trust.trustmedia.trust2;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Pair;

import java.util.ArrayList;
/**
 * Created by phil on 26/11/16.
 */

public class Keyring {

    private ArrayList<Pair<String, String>> keyring;
    private String name = "Me";
    private String privateKey;
    private String publicKey;

    public Keyring() {
        Pair<String, String> temp = this.generateKeys();
        this.privateKey = temp.first;
        this.publicKey = temp.second;
        this.keyring = new ArrayList<>();
        this.keyring.add(new Pair(this.name, this.publicKey));
    }

    public Keyring(Pair<String, String> personalKeyPair) {
        this.keyring = new ArrayList<Pair<String, String>>();
        this.keyring.add(personalKeyPair);
    }

    public Keyring(String name, String publickey) {
        this.keyring = new ArrayList<Pair<String, String>>();
        this.publicKey = publickey;
        this.name = name;
        Pair<String, String> tempPair = new Pair<>(this.name, this.publicKey);
        this.keyring.add(tempPair);
    }

    private Pair<String, String> generateKeys() {
        RSA rsa = new RSA();
        return rsa.generateKeys();


    }

    public void addKeyPair(Pair<String, String> kp) {
        this.keyring.add(kp);
    }

    public void addKeyPairAsStrings(String name, String publicKey) {
        //   Pair<String, String> duplicate = this.keyring.stream()
        //           .filter(kp -> kp.second.equals(publicKey))
        //           .collect(Collectors.toList()).get(0);
        //  if (duplicate != null) {
        //     System.out.println(String.format("Key duplicate! User {0} is trusted and exists as {1} in your keyring.", name, duplicate.first));
        // } else {

        this.keyring.add(new Pair<String, String>(name, publicKey));
    }


    public void deleteKeyPairByKey(String key) {
        this.keyring.removeIf(p -> p.second.equals(key));
    }

    public void deleteKeyPairByName(String name) {
         this.keyring.removeIf(kp -> kp.first.equals(name));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void importKeyRingFromTrusted(ArrayList<Pair<String, String>> keyring) {
        if (this.keyring.stream().filter(keyring::contains).toArray().length > 0) {
        this.keyring.addAll(keyring);
    }
}
