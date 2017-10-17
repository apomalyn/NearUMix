package squiddle.sheshire.apomalyn.qc.ca.nearumix.DAO;

import android.os.StrictMode;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by apomalyn on 03/10/17.
 */

public class BaseDeDonnees {


    private static final String NAMESPACE = "http://nearumix.rockncode.fr/soap/webServicenearumix";
    private static final String URL = "http://nearumix.rockncode.fr/index.php?wsdl";

    public static final String GET_UTILISATEUR = "getutilisateur";


    public /*List<HashMap<String, String>>*/ String envoyerRequete(String methode){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try{
            SoapObject requete = new SoapObject(NAMESPACE, methode);
            requete.addProperty("id", 1);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(requete);

            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(URL);
            androidHttpTransport.call(methode, envelope);
            SoapPrimitive resultString = (SoapPrimitive)envelope.getResponse();

            return resultString.toString(); //this.parseReponse(reponse);
        } catch (Exception e){
            Log.e("envoi de requete echoue", "", e);
        }

        return null;
    }

    private /*List<HashMap<String, String>>*/ String parseReponse(SoapObject reponse){
        SoapObject donneesEncoder = (SoapObject)reponse.getProperty("donnees");
        List<HashMap<String, String>> donnees = new ArrayList<>();

        HashMap<String, String> map;
        for (int i = 0; i < donneesEncoder.getPropertyCount(); i++){
            SoapObject donnee = (SoapObject)donneesEncoder.getProperty(i);
            map = new HashMap<>();
            map.put(donnee.getName(), donnee.toString());
            donnees.add(map);
        }

        //return donnees;

        return reponse.toString();
    }
}
