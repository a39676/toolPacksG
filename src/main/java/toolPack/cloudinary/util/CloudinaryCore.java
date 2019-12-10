package toolPack.cloudinary.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import toolPack.cloudinary.pojo.constant.CloudinaryConstant;

public class CloudinaryCore {

	public Cloudinary buildCloudinary(String userName, String apiKey, String apiSecret) {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				CloudinaryConstant.cloudName, userName, 
				CloudinaryConstant.apiKey, apiKey, 
				CloudinaryConstant.apiSecret, apiSecret));
		return cloudinary;
	}
	
}
