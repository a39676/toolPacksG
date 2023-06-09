package toolPack.imgbb.pojo.dto;

public class ImgbbUploadResponseDataDTO {

	private String id;
	private String title;
	private String url_viewer;
	private String url;
	private String display_url;
	private Integer width;
	private Integer height;
	private Long size;
	private Long time;
	private Long expiration;
	private ImgbbUploadImgDTO image;
	private ImgbbUploadImgDTO thumb;
	private String delete_url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl_viewer() {
		return url_viewer;
	}

	public void setUrl_viewer(String url_viewer) {
		this.url_viewer = url_viewer;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDisplay_url() {
		return display_url;
	}

	public void setDisplay_url(String display_url) {
		this.display_url = display_url;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Long getExpiration() {
		return expiration;
	}

	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}

	public ImgbbUploadImgDTO getImage() {
		return image;
	}

	public void setImage(ImgbbUploadImgDTO image) {
		this.image = image;
	}

	public ImgbbUploadImgDTO getThumb() {
		return thumb;
	}

	public void setThumb(ImgbbUploadImgDTO thumb) {
		this.thumb = thumb;
	}

	public String getDelete_url() {
		return delete_url;
	}

	public void setDelete_url(String delete_url) {
		this.delete_url = delete_url;
	}

	@Override
	public String toString() {
		return "ImgbbUploadResponseDataDTO [id=" + id + ", title=" + title + ", url_viewer=" + url_viewer + ", url="
				+ url + ", display_url=" + display_url + ", width=" + width + ", height=" + height + ", size=" + size
				+ ", time=" + time + ", expiration=" + expiration + ", image=" + image + ", thumb=" + thumb
				+ ", delete_url=" + delete_url + "]";
	}

}
