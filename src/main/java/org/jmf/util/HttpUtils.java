package org.jmf.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Utility class.
 * 
 * @author jose
 *
 */
public final class HttpUtils {

    private HttpUtils() {
    }

    /**
     * Return base url (protocol://host:port).
     * 
     * @param sUrl
     *            String url
     * @return String formed by protocol ://host:port
     * @throws MalformedURLException
     */
    public static String getBaseUrl(String sUrl) {        
       
            return sUrl == null || "".equals(sUrl) ? null : (sUrl.split("/")[0] + "//" + sUrl.split("/")[2]);
         
    }

    /**
     * Return map of body ("body" key) and headers.
     * 
     * @param url
     *            Url
     * @param headers
     *            HTTP request headers
     * @param params
     *            HTTP Post parameters
     * @return Map: [body: HTML body, header1 : header1 value, etc...]
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     */
    public static Map<String, String> httpRequest(String url, Map<String, String> headermap, Map<String, String> params)
            throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {

        Map<String, String> responsemap = new ConcurrentHashMap<>();
        
        CloseableHttpClient httpclient = createHttpClient(url);

        // Launch HTTP request
        HttpResponse response = httpclient.execute(getRequest(params, url, headermap));
       
        // Save headers in response
        for (Header header : response.getAllHeaders()) {
            responsemap.put(header.getName(), header.getValue());
        }
        // Save body in response
        responsemap.put("body", EntityUtils.toString(response.getEntity(), "UTF-8"));
        
        httpclient.close();

        return responsemap;
    }

    /**
     * Create HttpRequestBase  object.
     * 
     * @param params Map of params
     * @param url Remote url
     * @param headermap Map of headers
     * @return HttpRequestBase 
     * @throws UnsupportedEncodingException
     */
    private static  HttpRequestBase getRequest(Map<String, String> params, String url, Map<String, String> headermap) 
            throws UnsupportedEncodingException {

        HttpRequestBase request;

        if (params == null) { // Do GET call
            request = new HttpGet(url);
        } else { // Do POST call
            request = new HttpPost(url);
            // Add POST parameters to request
            final ArrayList<NameValuePair> postParameters = new ArrayList<>();
            for (final Entry<String, String> param : params.entrySet()) {
                postParameters.add(new BasicNameValuePair(param.getKey(), param.getValue()));
            }
            ((HttpPost) request).setEntity(new UrlEncodedFormEntity(postParameters));  
        }

        // Add headers to request
        if (headermap != null) {
            for (final Entry<String, String> entry : headermap.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }

        return request;
    }

    /**
     * 
     * @param url Remote url
     * @return CloseableHttpClient
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     */
    private static CloseableHttpClient createHttpClient(String url)
            throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        if (url.toLowerCase(Locale.ENGLISH).startsWith("https")) {
            // Accept ALL
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @ Override
                public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                    return true;
                }
            }).build();
            return HttpClients.custom().setSslcontext(sslContext).setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .build();
        } else { // HTTP request
            return HttpClients.createDefault();
        }
    }

}
