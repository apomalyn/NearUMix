package squiddle.sheshire.apomalyn.qc.ca.nearumix.DAO;


import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by apomalyn on 03/10/17.
 */

public class BaseDeDonnees {


    private static final String URL = "http://nearumix.rockncode.fr/";

    /**
     * Liste des commandes
     */
    public static final String GET_UTILISATEUR = "getUtilisateur";


    /**
     * Liste des erreurs
     */
    public static final int NON_TROUVE = 0;
    public static final int PAS_DE_PARAMETRES = 1;

    private static final String TAG = "BaseDeDonnees";

    public HashMap<String, String> envoyerRequete(String methode, HashMap<String, String> parametres) {
        try{
            String params = URLEncoder.encode("commande", "UTF-8") + "=" + URLEncoder.encode(methode, "UTF-8");

            for(Map.Entry<String, String> entry : parametres.entrySet()) {
                String clef = entry.getKey();
                String valeur = entry.getValue();
                params += "&" + URLEncoder.encode(clef, "UTF-8") + "=" + URLEncoder.encode(valeur, "UTF-8");
            }

            return new PostClass(URL, params).execute().get();
        }catch (Exception e){
            Log.e(TAG, "", e);
        }
        return new HashMap<String, String>();
    }

    private class PostClass extends AsyncTask<Void, Void, HashMap<String, String>> {
        String url;
        String parametres;

        public PostClass(String url, String parametres){
            this.url = url;
            this.parametres = parametres;
        }

        @Override
        protected HashMap<String, String> doInBackground(Void... params) {

            HashMap<String, String> liste = new HashMap<>();

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

                Document xmlResultat = parser.parse(connection.getInputStream());

                NodeList nodes = xmlResultat.getElementsByTagName("donnees").item(0).getChildNodes();
                System.out.println(nodes.getLength());


                for (int i = 0; i < nodes.getLength(); i++) {
                    Element element = (Element) nodes.item(i);
                    String name = element.getTextContent();

                    liste.put(element.getTagName(), name);
                }
            } catch (Exception e) {
                Log.e(TAG, "", e);
            }

            return liste;
        }

        @Override
        protected void onPostExecute(HashMap<String, String> s) {
            super.onPostExecute(s);
        }
    }
}
