package httpHandel;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * http单元
 *
 */
public class HttpUtil {

	// private static final String USER_AGENT = "Mozilla/5.0";
	private static final String USER_AGENT = "Fiddler";

	

	// HTTP GET request
	public String sendGet(String url, HashMap<String, String> keyValues) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		if (keyValues != null && keyValues.size() > 0) {
			for (Map.Entry<String, String> entry : keyValues.entrySet()) {
				con.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}

		// int responseCode = con.getResponseCode();
		// System.out.println("\nSending 'GET' request to URL : " + url);
		// System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		response.append(con.getResponseMessage());
//		response.append(con.getHeaderFields());
		in.close();

		// print result
		return response.toString();
	}

	public String sendGet(String url) throws Exception {
		return sendGet(url, null);
	}

	// HTTP POST request
	public String sendPost(String url, String urlParameters) throws Exception {
//		String urlParameters = "{\"version\":\"2\", \"platform\":\"1\", \"status\":\"0\", \"des\":\"\"}";
		HttpURLConnection con;
		StringBuilder content;

        byte[] postData = null;
        if(urlParameters != null && urlParameters.trim().length() >0) {
        	postData = urlParameters.getBytes(StandardCharsets.UTF_8);
        } 
        
        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            
            if(postData != null) {
            	DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            	wr.write(postData);
            	wr.flush();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            content = new StringBuilder();

            while ((line = in.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }


        } finally {
            
//            con.disconnect();
        }
		return content.toString();
		
	}

	public String sendPost(String url) throws Exception {
		return sendPost(url, null);
	}
	
	
	public static void main(String[] args) throws Exception {

		HttpUtil http = new HttpUtil();
		String host = "http://localhost:8080/";
		String url = "operation/task/getTaskList.action";
		// String result = http.sendGet(HOST + url, requestHeaderKeys);
		String result = http.sendPost(host + url);
		System.out.println(result);

		// System.out.println("\nTesting 2 - Send Http POST request");
		// http.sendPost();

	}
}
