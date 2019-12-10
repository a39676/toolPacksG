package toolPack.cloudinary.pojo.result;

public class CloudinaryUploadResult {

	private String secureUrl;
	private String originalFilename;
	private String publicId;
	private boolean success = false;

	@Override
	public String toString() {
		return "CloudinaryUploadResult [secureUrl=" + secureUrl + ", originalFilename=" + originalFilename
				+ ", publicId=" + publicId + ", success=" + success + "]";
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getSecureUrl() {
		return secureUrl;
	}

	public void setSecureUrl(String secureUrl) {
		this.secureUrl = secureUrl;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

}
