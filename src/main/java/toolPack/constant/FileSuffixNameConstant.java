package toolPack.constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileSuffixNameConstant {

	public static final String JPG = "jpg";
	public static final String JPEG = "jpeg";
	public static final String PNG = "png";
	public static final String BMP = "bmp";
	public static final String TIF = "tif";
	public static final String GIF = "gif";
	public static final String WEBP = "webp";
	public static final String WEBM = "webm";

	public static final String TXT = "txt";
	public static final String DOC = "doc";
	public static final String DOCX = "docx";
	public static final String PPT = "ppt";
	public static final String XLSX = "xlsx";
	public static final String XLS = "xls";
	public static final String CSV = "csv";
	public static final String PDF = "pdf";

	public static final List<String> IMAGE_SUFFIX = new ArrayList<String>();
	public static final List<String> DOCUMENT_SUFFIX = new ArrayList<String>();

	static {
		IMAGE_SUFFIX.addAll(Arrays.asList(JPG, JPEG, PNG, BMP, TIF, GIF, WEBP, WEBM));
		DOCUMENT_SUFFIX.addAll(Arrays.asList(TXT, DOC, DOCX, PPT, XLSX, XLS, CSV, PDF));
	}
	
	public static void main(String[] args) {
		System.out.println(DOCUMENT_SUFFIX);
	}
}
