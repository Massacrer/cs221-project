package uk.ac.aber.cs221.upload;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Cai Jones on 23/01/2015.
 */
public class Uploader extends AsyncTask<JSONObject, JSONObject, JSONObject> {

    @Override
    protected JSONObject doInBackground(JSONObject... json) {
        JSONObject j = json[0];
        HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 100000);
        String url = "http://users.aber.ac.uk/crj10/cs221-project/Website/php/appget.php";
        JSONObject jResponse = null;
        HttpPost post = new HttpPost(url);
        try {
            StringEntity se = new StringEntity("json="+j.toString());
            post.addHeader("content-type", "application/x-www-form-urlencoded");
            post.setEntity(se);

            HttpResponse response;
            response = client.execute(post);
            String serverResponse = org.apache.http.util.EntityUtils.toString(response.getEntity());

            jResponse=new JSONObject(serverResponse);
            Log.i("Server Response:", jResponse.getString("msg"));
        } catch (Exception e) { e.printStackTrace();}

        return jResponse;
    }
}
