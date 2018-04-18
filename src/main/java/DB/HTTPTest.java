package DB;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class HTTPTest {

    public List<Item> transform(String query) {
        //JSONObject json = new JSONObject();
        JSONArray a1 = new JSONArray();
        a1.put("title");
        a1.put("content");
        JSONObject j1 = new JSONObject();
        //TODO:
        j1.put("query", query);
        j1.put("fields", a1);
        JSONObject j2 = new JSONObject();
        j2.put("multi_match", j1);
        JSONObject j3 = new JSONObject();
        j3.put("query", j2);
        //System.out.print(j3.toString()+"\n");

        HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead
        List<Item> itemList = new LinkedList<>();
        try {
        	
            //HttpGet httpGet=new HttpGet("http://120.79.216.1:9200/_count");
            //HttpPost request = new HttpPost("http://120.79.216.1:9200/news/sougou/_search");
            HttpPost request = new HttpPost("http://123.57.163.79:9200/_analyze?analyzer=ik&pretty=true&text=我是中国人，我爱中国 ");
            //StringEntity params =new StringEntity("details={\"query\" : { \"match\" : { \"title\" : \"缇庡浗\" }}}");

            //涓嶅姞UTF-8鏌ヤ笉鍑虹粨鏋滐紒
            StringEntity params = new StringEntity(j3.toString(), "UTF-8");
            //request.addHeader("content-type", "application/x-www-form-urlencoded");
            request.addHeader("content-type", "application/json");
            request.addHeader("Accept", "application/json");
            request.addHeader("Accept-Encoding", "gzip");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() == 200) {
                // 寰楀埌httpResponse鐨勫疄浣撴暟鎹�
                HttpEntity httpEntity = response.getEntity();
                String s = EntityUtils.toString(httpEntity, "UTF-8");
                //TODO:
                //System.out.print(s);
                SpareItem outer = new SpareItem();
                itemList= outer.Spare(s);
                
            }
        } catch (Exception ex) {

            //handle exception here

        } finally {
            //Deprecated
            //httpClient.getConnectionManager().shutdown();
        }
		return itemList;
    }
}
