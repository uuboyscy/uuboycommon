package uuboy.scy.common.api;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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

    /**
     * GET request an API with List Object returned.
     * @param endpoint https://httpbin.org
     * @param path /api/something
     * @param paramters Parameters followed behind "?" symbol. Put the keys and values with HashMap
     * @param apiTrans Object format for specific response Json
     * @param headers Headers put with HashMap
     * @return List of object which can be determined by implementing Transformation interface
     * @throws Exception
     */
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
                    httpget.addHeader(header, (String) headers.get(header));
                }
            }

            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
//            System.out.println(response.getStatusLine().getStatusCode());
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

    /**
     *
     * @param endpoint
     * @param path
     * @param paramters
     * @param apiTrans
     * @param headers
     * @return
     * @throws Exception
     */
    public static Object getObj(String endpoint, String path, Map<String, String> paramters, ApiTransformation apiTrans, Map<String, String> headers) throws Exception {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        Object result = null;

        try {
            httpclient = HttpClients.createDefault();
            String url = paramters != null ? String.format("%s/%s?%s", endpoint, path, combineParameters(paramters)) : String.format("%s/%s", endpoint, path);
            HttpGet httpget = new HttpGet(url);
            if (headers != null) {
                Iterator stringIterator = headers.keySet().iterator();

                while(stringIterator.hasNext()) {
                    String header = (String) stringIterator.next();
                    httpget.addHeader(header, (String) headers.get(header));
                }
            }

            response = httpclient.execute(httpget);
//            System.out.println(response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = apiTrans.transToObj(entity);
//                System.out.println("Response content: " + result);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            httpclient.close();
            response.close();
        }

        return result;
    }

    /**
     *
     * @param endpoint
     * @param path
     * @param parameters
     * @param headers
     * @return
     * @throws IOException
     */
    public static Integer post(String endpoint, String path, Map<String, String> parameters, Map<String, String> headers) throws IOException {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        Integer statusCode = null;

        try {
            httpclient = HttpClients.createDefault();
            String url = String.format("%s/%s", endpoint, path);
            HttpPost httpPost = new HttpPost(url);
            ArrayList<BasicNameValuePair> params = new ArrayList();
            Iterator stringIterator = parameters.keySet().iterator();

            String header;
            while(stringIterator.hasNext()) {
                header = (String) stringIterator.next();
                params.add(new BasicNameValuePair(header, (String) parameters.get(header)));
            }

            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            if (headers != null) {
                stringIterator = headers.keySet().iterator();

                while(stringIterator.hasNext()) {
                    header = (String) stringIterator.next();
                    httpPost.addHeader(header, (String) headers.get(header));
                }
            }

            response = httpclient.execute(httpPost);
            statusCode = response.getStatusLine().getStatusCode();
//            System.out.println(statusCode);
            return statusCode;
        } catch (Exception e) {
            throw e;
        } finally {
            httpclient.close();
            response.close();
        }
    }

    /**
     *
     * @param endpoint
     * @param path
     * @param updateObj
     * @param headers
     * @return
     * @throws Exception
     */
    public static Integer put(String endpoint, String path, Object updateObj, Map<String, String> headers) throws Exception {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        Integer statusCode = null;

        try {
            httpclient = HttpClients.createDefault();
            String url = String.format("%s/%s", endpoint, path);
            HttpPut httpPut = new HttpPut(url);
            ObjectMapper om = new ObjectMapper();
            System.out.println(om.writeValueAsString(updateObj));
            httpPut.setEntity(new StringEntity(om.writeValueAsString(updateObj), "UTF-8"));
            httpPut.addHeader("Content-type", "application/json");
            httpPut.addHeader("Accept", "application/json");
            if (headers != null) {
                Iterator stringIterator = headers.keySet().iterator();

                while(stringIterator.hasNext()) {
                    String header = (String) stringIterator.next();
                    httpPut.addHeader(header, (String) headers.get(header));
                }
            }

            response = httpclient.execute(httpPut);
            statusCode = response.getStatusLine().getStatusCode();
            System.out.println(response.getStatusLine().getReasonPhrase());
        } catch (Exception e) {
            throw e;
        } finally {
            httpclient.close();
            response.close();
        }

        return statusCode;
    }

    /**
     *
     * @param paramters
     * @return
     */
    public static String combineParameters(Map<String, String> paramters) {
        String[] keys = (String[]) paramters.keySet().toArray(new String[paramters.size()]);
        String[] parameterStrs = new String[keys.length];

        for(int i = 0; i < keys.length; ++i) {
            String key = keys[i];
            String parameterStr = String.format("%s=%s", key, paramters.get(key));
            parameterStrs[i] = parameterStr;
        }

        return String.join("&", parameterStrs);
    }

    public static void main(String[] args) {

        String endpoint = "https://httpbin.org/";
        String pathGet = "get";
        String pathPost = "post";

        Map<String, String> headers = new HashMap<>();
        headers.put("testHeader", "testvalue");

        Map<String, String> postData = new HashMap<>();
        postData.put("testKey1", "value1");
        postData.put("testKey2", "value2");

        try {
            ApiHelper.getObj(
                    endpoint,
                    pathGet,
                    null,
                    new ApiTransformation(){
                        @Override
                        public List<Object> trans(HttpEntity httpEntity) {
                            return null;
                        }

                        @Override
                        public Object transToObj(HttpEntity httpEntity) {
                            String line;
                            StringBuilder sb = new StringBuilder();
                            try {
                                BufferedReader in = new BufferedReader(new InputStreamReader(httpEntity.getContent()));
                                while ((line = in.readLine()) != null) sb.append(line);
                                System.out.println(sb);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }
                    },
                    headers
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ApiHelper.post(
                    endpoint,
                    pathPost,
                    postData,
                    headers
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
