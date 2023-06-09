package toolPack.imgbb.pojo.dto;

public class ImgbbUploadImgDTO {

	private String filename;
	private String name;
	private String mime;
	private String extension;
	private String url;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "ImgbbUploadImgDTO [filename=" + filename + ", name=" + name + ", mime=" + mime + ", extension="
				+ extension + ", url=" + url + "]";
	}

}
