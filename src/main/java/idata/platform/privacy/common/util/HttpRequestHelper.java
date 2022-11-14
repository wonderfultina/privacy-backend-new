package idata.platform.privacy.common.util;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class HttpRequestHelper {
    static final String POST = "POST";
    static final String GET = "GET";
    static final int CONN_TIMEOUT = 30000;// ms
    static final int READ_TIMEOUT = 30000;// ms

    /**
     * post 方式发送http请求.
     *
     * @param strUrl
     * @param reqData
     * @return
     */
    public static byte[] doPost(String strUrl, byte[] reqData) {
        return send(strUrl, POST, reqData);
    }


    /**
     * get方式发送http请求.
     *
     * @param strUrl
     * @return
     */
    public static byte[] doGet(String strUrl) {
        return send(strUrl, GET, null);
    }


    public static byte[] send(String strUrl, String method, byte[] reqData) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
            httpcon.setDoOutput(true);
            httpcon.setDoInput(true);
            httpcon.setUseCaches(false);
            httpcon.setInstanceFollowRedirects(true);
            httpcon.setConnectTimeout(CONN_TIMEOUT);
            httpcon.setReadTimeout(READ_TIMEOUT);
            httpcon.setRequestMethod(method);
            httpcon.connect();
            if (method.equalsIgnoreCase(POST)) {
                OutputStream os = httpcon.getOutputStream();
                os.write(reqData);
                os.flush();
                os.close();
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(httpcon.getInputStream(),"utf-8"));
            String inputLine;
            StringBuilder bankXmlBuffer = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                bankXmlBuffer.append(inputLine);
            }
            in.close();
            httpcon.disconnect();
            return bankXmlBuffer.toString().getBytes();
        }catch (Exception ex) {
            log.error(ex.toString(), ex);
            return null;
        }
    }
}