package toolPack.imgbb.pojo.dto;

public class ImgbbUploadResponseDTO {

	private ImgbbUploadResponseDataDTO data;
	private Boolean success;
	private Integer status;

	public ImgbbUploadResponseDataDTO getData() {
		return data;
	}

	public void setData(ImgbbUploadResponseDataDTO data) {
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ImgbbUploadResponseDTO [data=" + data + ", success=" + success + ", status=" + status + "]";
	}

}
