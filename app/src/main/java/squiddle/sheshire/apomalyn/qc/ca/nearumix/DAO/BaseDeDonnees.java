package squiddle.sheshire.apomalyn.qc.ca.nearumix.DAO;


import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by apomalyn on 03/10/17.
 */

public class BaseDeDonnees {


    private static final String URL = "http://nearumix.rockncode.fr/";

    public static final String GET_UTILISATEUR = "getUtilisateur";

    static final String TAG = "BaseDeDonnees";

    public HashMap<String, String> envoyerRequete(String methode, HashMap<String, String> parametres) {
        try{
            String params = URLEncoder.encode("commande", "UTF-8") + "=" + URLEncoder.encode(methode, "UTF-8");

            for(Map.Entry<String, String> entry : parametres.entrySet()) {
                String clef = entry.getKey();
                String valeur = entry.getValue();
                params += "&" + URLEncoder.encode(clef, "UTF-8") + "=" + URLEncoder.encode(valeur, "UTF-8");
            }

            String test = new PostClass(URL, params).execute().get();
            boolean xd = true;
        }catch (Exception e){
            Log.e(TAG, "", e);
        }
        return new HashMap<String, String>();
    }

    private class PostClass extends AsyncTask<Void, Void, String> {
        String url;
        String parametres;

        public PostClass(String url, String parametres){
            this.url = url;
            this.parametres = parametres;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(this.url);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();

                connection.setRequestMethod("POST");
                connection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
                connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
                connection.setDoOutput(true);
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(this.parametres);
                dStream.flush();
                dStream.close();

                DocumentBuilderFactory factory
                        = DocumentBuilderFactory.newInstance();
                DocumentBuilder parser = factory.newDocumentBuilder();

                DOMSource domSource = new DOMSource(parser.parse(connection.getInputStream()));
                StringWriter writer = new StringWriter();
                StreamResult result = new StreamResult(writer);
                TransformerFactory tf = TransformerFactory.newInstance();
                Transformer transformer = tf.newTransformer();
                transformer.transform(domSource, result);

                return writer.toString();

            } catch (Exception e) {
                Log.e(TAG, "", e);
            }

            return "";
        }
    }

    /*private HashMap<String, String> parseReponse(String reponse) {
        SoapObject donneesEncoder = (SoapObject) reponse.getProperty("donnees");

        HashMap<String, String> donnees = new HashMap<>();
        for (int i = 0; i < donneesEncoder.getPropertyCount(); i++) {
            SoapObject donnee = (SoapObject) donneesEncoder.getProperty(i);
            donnees.put(donnee.getName(), donnee.toString());
        }

        return donnees;
    }*/

}
