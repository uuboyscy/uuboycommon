package uuboy.scy.common.api;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Project: uuboycommon
 * Package: uuboy.scy.common.api
 * ApiHelper.java
 * Description:
 * User: uuboyscy
 * Created Date: 1/26/22
 * Version: 0.0.1
 */

public class ApiHelper {

    public ApiHelper() {

    }

    public static List<Object> get(String endpoint, String path, Map<String, String> paramters, ApiTransformation apiTrans, Map<String, String> headers) throws Exception {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        List results = null;

        try {
            httpclient = HttpClients.createDefault();
            String url = paramters != null ? String.format("%s/%s?%s", endpoint, path, combineParameters(paramters)) : String.format("%s/%s", endpoint, path);
            HttpGet httpget = new HttpGet(url);
            if (headers != null) {
                Iterator stringIterator = headers.keySet().iterator();

                while(stringIterator.hasNext()) {
                    String header = (String) stringIterator.next();
                    httpget.addHeader(header, (String)headers.get(header));
                }
            }

            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            System.out.println(response.getStatusLine().getStatusCode());
            if (entity != null) {
                results = apiTrans.trans(entity);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            httpclient.close();
            response.close();
        }

        return results;
    }

    public static String combineParameters(Map<String, String> paramters) {
        String[] keys = (String[])paramters.keySet().toArray(new String[paramters.size()]);
        String[] parameterStrs = new String[keys.length];

        for(int i = 0; i < keys.length; ++i) {
            String key = keys[i];
            String parameterStr = String.format("%s=%s", key, paramters.get(key));
            parameterStrs[i] = parameterStr;
        }

        return String.join("&", parameterStrs);
    }

}
