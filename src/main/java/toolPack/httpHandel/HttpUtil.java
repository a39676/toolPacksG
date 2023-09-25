package toolPack.httpHandel;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

/**
 * http单元
 *
 */
public class HttpUtil {

	// private static final String USER_AGENT = "Mozilla/5.0";
//	private static final String USER_AGENT = "Fiddler";
	private final String defaultUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36";
	public final String get = "GET";
	public final String post = "POST";
	public final String put = "PUT";
	public final String delete = "DELETE";
	public final String head = "HEAD";
	public final String patch = "PATCH";
	public final String options = "OPTIONS";
	public final String trace = "TRACE";

	public Map<String, String> builddefaultRequestPropertyMap() {
		Map<String, String> defaultRequestPropertyMap = new HashMap<String, String>();
		defaultRequestPropertyMap.put("User-Agent", defaultUserAgent);
		defaultRequestPropertyMap.put("Content-Type", "application/x-www-form-urlencoded charset=UTF-8");

		return defaultRequestPropertyMap;
	}

	public String sendGet(String url, Map<String, String> keyValues, Map<String, String> requestPropertyMap)
			throws IOException {

		if (keyValues != null && keyValues.size() > 0) {
			url = url + "?";
			boolean flag = true;
			for (Map.Entry<String, String> entry : keyValues.entrySet()) {
				if (flag) {
					url = url + entry.getKey() + "=" + entry.getValue();
					flag = false;
				} else {
					url = url + "&" + entry.getKey() + "=" + entry.getValue();
				}
			}
		}

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod(get);

		if (requestPropertyMap == null) {
			requestPropertyMap = builddefaultRequestPropertyMap();
		}

		for (Entry<String, String> entry : requestPropertyMap.entrySet()) {
			con.setRequestProperty(entry.getKey(), entry.getValue());
		}

		// int responseCode = con.getResponseCode();
		// System.out.println("\nSending 'GET' request to URL : " + url);
		// System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
//		response.append(con.getResponseMessage());
//		response.append(con.getHeaderFields());
		in.close();

		// print result
		return response.toString();
	}

	public String sendGet(String url, Map<String, String> keyValues) throws IOException {
		return sendGet(url, keyValues, null);
	}

	public InputStream sendRequestGetInputStreamReader(String httpMethod, String userAgent, String url,
			Map<String, String> keyValues) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		if (StringUtils.isBlank(httpMethod)) {
			httpMethod = get;
		}
		con.setRequestMethod(httpMethod);

		if (StringUtils.isBlank(userAgent)) {
			con.setRequestProperty("User-Agent", defaultUserAgent);
		} else {
			con.setRequestProperty("User-Agent", userAgent);
		}

		if (keyValues != null && keyValues.size() > 0) {
			for (Map.Entry<String, String> entry : keyValues.entrySet()) {
				con.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}

		return con.getInputStream();
	}

	public String sendRequest(String httpMethod, String userAgent, String url, Map<String, String> keyValues)
			throws IOException {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod(httpMethod);

		if (StringUtils.isBlank(userAgent)) {
			con.setRequestProperty("User-Agent", defaultUserAgent);
		} else {
			con.setRequestProperty("User-Agent", userAgent);
		}

		if (keyValues != null && keyValues.size() > 0) {
			for (Map.Entry<String, String> entry : keyValues.entrySet()) {
				con.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		con.disconnect();

		return response.toString();

	}

	public String sendGet(String url) throws IOException {
		return sendGet(url, null);
	}

	public String sendPost(String url, String urlParameters, Map<String, String> requestPropertyMap)
			throws IOException {
//		String urlParameters = "{\"version\":\"2\", \"platform\":\"1\", \"status\":\"0\", \"des\":\"\"}";
		HttpURLConnection con = null;
		StringBuilder response = new StringBuilder();

		byte[] postData = null;
		if (StringUtils.isNotBlank(urlParameters)) {
			postData = urlParameters.getBytes(StandardCharsets.UTF_8);
		}

		try {

			URL myurl = new URL(url);
			con = (HttpURLConnection) myurl.openConnection();

			con.setDoOutput(true);
			con.setRequestMethod(post);

			if (requestPropertyMap == null) {
				requestPropertyMap = builddefaultRequestPropertyMap();
			}

			for (Entry<String, String> entry : requestPropertyMap.entrySet()) {
				con.setRequestProperty(entry.getKey(), entry.getValue());
			}

			if (postData != null) {
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.write(postData);
				wr.flush();
			}

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line;

			while ((line = in.readLine()) != null) {
				response.append(line);
				response.append(System.lineSeparator());
			}

		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		return response.toString();

	}

	public String sendPostRestful(String url, String jsonStr) throws IOException {
		Map<String, String> requestPropertyMap = builddefaultRequestPropertyMap();
		requestPropertyMap.put("Content-Type", "application/json; charset=UTF-8");
		requestPropertyMap.put("Data-Type", "json; charset=UTF-8");

		return sendPost(url, jsonStr, requestPropertyMap);
	}

	public String sendPost(String url) throws Exception {
		return sendPost(url, null, null);
	}

	public String sendPost(String url, String params) throws Exception {
		return sendPost(url, params, null);
	}

	public void httpPostUploadFileDemo() throws MalformedURLException, IOException {

		String url = "http://example.com/upload";
		String charset = StandardCharsets.UTF_8.displayName();
		String param = "value";
		File textFile = new File("/path/to/file.txt");
		File binaryFile = new File("/path/to/file.bin");
		String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
		String CRLF = "\r\n"; // Line separator required by multipart/form-data.

		URLConnection connection = new URL(url).openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

		try (OutputStream output = connection.getOutputStream();
				PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);) {
			// Send normal param.
			writer.append("--" + boundary).append(CRLF);
			writer.append("Content-Disposition: form-data; name=\"param\"").append(CRLF);
			writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
			writer.append(CRLF).append(param).append(CRLF).flush();

			// Send text file.
			writer.append("--" + boundary).append(CRLF);
			writer.append("Content-Disposition: form-data; name=\"textFile\"; filename=\"" + textFile.getName() + "\"")
					.append(CRLF);
			writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF); // Text file itself must be
																						// saved in this charset!
			writer.append(CRLF).flush();
			Files.copy(textFile.toPath(), output);
			output.flush(); // Important before continuing with writer!
			writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.

			// Send binary file.
			writer.append("--" + boundary).append(CRLF);
			writer.append(
					"Content-Disposition: form-data; name=\"binaryFile\"; filename=\"" + binaryFile.getName() + "\"")
					.append(CRLF);
			writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(binaryFile.getName())).append(CRLF);
			writer.append("Content-Transfer-Encoding: binary").append(CRLF);
			writer.append(CRLF).flush();
			Files.copy(binaryFile.toPath(), output);
			output.flush(); // Important before continuing with writer!
			writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.

			// End of multipart/form-data.
			writer.append("--" + boundary + "--").append(CRLF).flush();
		}

		// Request is lazily fired whenever you need to obtain information about
		// response.
		int responseCode = ((HttpURLConnection) connection).getResponseCode();
		System.out.println(responseCode); // Should be 200

	}
}
