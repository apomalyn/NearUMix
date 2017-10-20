package squiddle.sheshire.apomalyn.qc.ca.nearumix.DAO;


import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.io.DataOutputStream;

import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by apomalyn on 03/10/17.
 */

public class BaseDeDonnees {


    private static final String URL = "http://nearumix.rockncode.fr/";

    /**
     * Liste des commandes
     */
    public static final String GET_UTILISATEUR = "getUtilisateur";
    public static final String MODIFIER_UTILISATEUR = "modifierUtilisateur";
    public static final String AJOUTER_UTILISATEUR = "ajouterUtilisateur";
    public static final String AJOUTER_AMIS = "ajouterAmis";
    public static final String SUPPRIMER_AMIS = "supprimerAmis";

    public static final String GET_POINT_INFLUENCE = "getPointInfluence";
    public static final String GET_POINTS_INFLUENCE = "getPointsInfluence";
    public static final String VOTER_MUSIQUE = "voterMusique";
    public static final String GET_MUSIQUE = "getMusique";

    /**
     * Liste des erreurs
     */
    public static final int NON_TROUVE = 0;
    public static final int PAS_DE_PARAMETRES = 1;
    public static final int ECHEC = 2;


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
        return null;
    }

    public HashMap<String, String> envoyerRequete(String methode) {
        return envoyerRequete(methode, new HashMap<String, String>());
    }

    public HashMap<String, String> convertirXMLenHashMap(Document xml, String tagEntree){
        HashMap<String, String> liste = new HashMap<>();
        if(xml == null) return null;
        NodeList nodes = xml.getElementsByTagName(tagEntree).item(0).getChildNodes();

        String donnees;

        String xmlstring;


        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);
            if (element.getChildNodes().getLength() > 0 && element.getChildNodes().item(0).getChildNodes().getLength() > 0) {
                donnees = innerXml(nodes.item(i));
            }else{
                donnees = element.getTextContent();
            }

            liste.put(element.getTagName(), donnees);
        }

        int i = liste.size();
        return liste;
    }

    public HashMap<String, String> convertirXMLenHashMap(Document xml){
        return convertirXMLenHashMap(xml, "donnees");
    }


    public HashMap<String, String> convertirXMLenHashMap(String xml, String tagEntree){
        Document doc = null;
        if(xml.equals(""))  return null;
        try{
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            doc = db.parse(is);
        }catch(Exception e){
            Log.e(TAG, "", e);
        }

        return convertirXMLenHashMap(doc, tagEntree);
    }

    private String innerXml(Node node) {
        StringWriter sw = new StringWriter();
        try {
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            t.transform(new DOMSource(node), new StreamResult(sw));
        } catch (TransformerException te) {
            System.out.println("nodeToString Transformer Exception");
        }
        return sw.toString();
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

//                BufferedInputStream is = new BufferedInputStream(connection.getInputStream());
//                BufferedReader br = new BufferedReader(new InputStreamReader(is));
//                String inputLine = "";
//                StringBuffer sb = new StringBuffer();
//                while ((inputLine = br.readLine()) != null) {
//                    sb.append(inputLine);
//                }
//                String result = sb.toString();

                Document xmlResultat = parser.parse(connection.getInputStream());

                liste = convertirXMLenHashMap(xmlResultat);
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
