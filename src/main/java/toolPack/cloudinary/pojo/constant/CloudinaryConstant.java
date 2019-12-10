package toolPack.cloudinary.pojo.constant;

public class CloudinaryConstant {
	
	public static final String cloudName = "cloud_name";
	public static final String apiKey = "api_key";
	public static final String apiSecret = "api_secret";
	
	public static final String cloudinaryNameStoreKey = "cloudinaryName"; 
	public static final String cloudinaryApiKeyStoreKey = "cloudinaryApiKey";
	public static final String cloudinaryApiSecretStoreKey = "cloudinaryApiSecret";
	
	public static final long maxSize = 10485760L;
	/**
	 * 2019-11-29
	 * 批量删除接口, 每次最多容纳100个id
	 */
	public static final int deleteIdListMaxSize = 100;

}
