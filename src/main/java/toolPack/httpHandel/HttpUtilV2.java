package toolPack.httpHandel;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

/**
 * http单元
 *
 */
public class HttpUtilV2 {

	private final String defaultUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36";
	public final String get = "GET";
	public final String post = "POST";
	public final String put = "PUT";
	public final String delete = "DELETE";
	public final String head = "HEAD";
	public final String patch = "PATCH";
	public final String options = "OPTIONS";
	public final String trace = "TRACE";

	public Map<String, String> buildDefaultRequestPropertyMap() {
		Map<String, String> defaultRequestPropertyMap = new HashMap<String, String>();
		defaultRequestPropertyMap.put("User-Agent", defaultUserAgent);
		defaultRequestPropertyMap.put("Content-Type", "application/x-www-form-urlencoded charset=UTF-8");

		return defaultRequestPropertyMap;
	}

	public HttpResponse<String> sendGet(String url, Map<String, String> keyValues,
			Map<String, String> requestPropertyMap) throws URISyntaxException, InterruptedException, IOException {

		URIBuilder urlBuilder = new URIBuilder(url);
		if (keyValues != null && !keyValues.isEmpty()) {
			for (Map.Entry<String, String> entry : keyValues.entrySet()) {
				urlBuilder.addParameter(entry.getKey(), entry.getValue());
			}
		}

		URI uri = urlBuilder.build();

		Builder httpRequestBuilder = HttpRequest.newBuilder().uri(uri).GET().version(HttpClient.Version.HTTP_2);
		if (requestPropertyMap != null && !requestPropertyMap.isEmpty()) {
			for (Map.Entry<String, String> entry : requestPropertyMap.entrySet()) {
				httpRequestBuilder.headers(entry.getKey(), entry.getValue());
			}
		}

		HttpRequest httpRequest = httpRequestBuilder.build();

		return HttpClient.newBuilder().build().send(httpRequest, BodyHandlers.ofString());
	}

	public HttpResponse<String> sendGet(String url, Map<String, String> keyValues)
			throws IOException, URISyntaxException, InterruptedException {
		return sendGet(url, keyValues, null);
	}

	public HttpResponse<String> sendGet(String url) throws IOException, URISyntaxException, InterruptedException {
		return sendGet(url, null);
	}

	public HttpResponse<String> sendPost(String url, String urlParameters, Map<String, String> requestPropertyMap)
			throws IOException, InterruptedException, URISyntaxException {

		URI myurl = new URIBuilder(url).build();

		byte[] postData = null;
		if (StringUtils.isNotBlank(urlParameters)) {
			postData = urlParameters.getBytes(StandardCharsets.UTF_8);
		}
		Builder httpRequestBuilder = HttpRequest.newBuilder().uri(myurl)
				.POST(HttpRequest.BodyPublishers.ofByteArray(postData));

		if (requestPropertyMap != null && !requestPropertyMap.isEmpty()) {
			for (Map.Entry<String, String> entry : requestPropertyMap.entrySet()) {
				httpRequestBuilder.headers(entry.getKey(), entry.getValue());
			}
		}

		HttpRequest httpRequest = httpRequestBuilder.build();

		return HttpClient.newBuilder().build().send(httpRequest, BodyHandlers.ofString());
	}

	public HttpResponse<String> sendPostRestful(String url, String jsonStr)
			throws IOException, InterruptedException, URISyntaxException {
		Map<String, String> requestPropertyMap = buildDefaultRequestPropertyMap();
		requestPropertyMap.put("Content-Type", "application/json; charset=UTF-8");
		requestPropertyMap.put("Data-Type", "json; charset=UTF-8");

		return sendPost(url, jsonStr, requestPropertyMap);
	}

	public HttpResponse<String> sendPost(String url) throws IOException, InterruptedException, URISyntaxException {
		return sendPost(url, null, null);
	}

	public HttpResponse<String> sendPost(String url, String params)
			throws IOException, InterruptedException, URISyntaxException {
		return sendPost(url, params, null);
	}

}
