package toolPack.cloudinary.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import net.sf.json.JSONObject;
import toolPack.cloudinary.pojo.constant.CloudinaryConstant;
import toolPack.cloudinary.pojo.result.CloudinaryDeleteResult;
import toolPack.cloudinary.pojo.result.CloudinaryUploadResult;

public class CloudinaryFunction {

	public CloudinaryUploadResult upload(Cloudinary cloudinary, File f) {

		CloudinaryUploadResult result = new CloudinaryUploadResult();

		if (f.length() > CloudinaryConstant.maxSize) {
			return result;
		}

		JSONObject resultJson = null;
		try {
			resultJson = JSONObject.fromObject(cloudinary.uploader().upload(f, ObjectUtils.emptyMap()));
		} catch (IOException e) {
			return result;
		}
		result = buildUploadResult(resultJson);
		return result;
	}
	
	public CloudinaryDeleteResult delete(Cloudinary cloudinary, String publicId) {
		return delete(cloudinary, Arrays.asList(publicId));
	}
	
	/**
	 * 
	 * @param cloudinary
	 * @param publicIds max size 100
	 * {@link CloudinaryConstant} deleteIdListMaxSize
	 * @return
	 */
	public CloudinaryDeleteResult delete(Cloudinary cloudinary, List<String> publicIds) {

		CloudinaryDeleteResult result = new CloudinaryDeleteResult();
		if(publicIds.size() > CloudinaryConstant.deleteIdListMaxSize) {
			return result;
		}

		JSONObject resultJson = null;
		try {
			resultJson = JSONObject.fromObject(cloudinary.api().deleteResources(publicIds, ObjectUtils.emptyMap()));
		} catch (IOException e) {
			return result;
		} catch (Exception e) {
			return result;
		}
		result = buildDeleteResult(resultJson);
		return result;
	}
	
	private CloudinaryUploadResult buildUploadResult(JSONObject j) {
		CloudinaryUploadResult r = new CloudinaryUploadResult();
		r.setSecureUrl(j.getString("secure_url"));
		r.setOriginalFilename(j.getString("original_filename"));
		r.setPublicId(j.getString("public_id"));
		r.setSuccess(true);
		return r;
	}
	
	private CloudinaryDeleteResult buildDeleteResult(JSONObject j) {
		CloudinaryDeleteResult r = new CloudinaryDeleteResult();
		r.setDeleted(j.getJSONObject("deleted"));
		r.setPartial(j.getBoolean("partial"));
		return r;
	}
	
}
