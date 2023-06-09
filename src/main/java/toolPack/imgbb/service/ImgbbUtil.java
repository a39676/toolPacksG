package toolPack.imgbb.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import toolPack.imgbb.pojo.dto.ImgbbUploadResponseDTO;

public class ImgbbUtil {

	public ImgbbUploadResponseDTO uploadImg(String apiKey, String filename, String imgInBase64Str,
			boolean saveForLongTime) {
		ImgbbUploadResponseDTO r = new ImgbbUploadResponseDTO();
		String defaultUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36";

		String mainUrl = "https://api.imgbb.com/1/upload?key=%s";

		String url = String.format(mainUrl, apiKey);
		if (saveForLongTime) {
			url = url + "&expiration=15551999";
		} else {
			url = url + "&expiration=1209600";
		}

		Map<String, String> dataMap = new HashMap<>();
		dataMap.put("image", imgInBase64Str);
		if (StringUtils.isNotBlank(filename)) {
			dataMap.put("name", filename);
		}

		Response response = null;
		try {
			response = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true).method(Method.POST)
					.data(dataMap).timeout(20000).userAgent(defaultUserAgent).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}

		r = buildObjFromJsonCustomization(response.body(), ImgbbUploadResponseDTO.class);

		return r;
	}

	protected <T> T buildObjFromJsonCustomization(String jsonStr, Class<T> clazz) {
		try {
			Gson gson = new GsonBuilder().create();

			return gson.fromJson(jsonStr, clazz);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
