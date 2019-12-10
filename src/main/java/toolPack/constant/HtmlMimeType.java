package toolPack.constant;

public enum HtmlMimeType {
	
	aac("aac", "audio/aac"),
	abw("abw", "application/x-abiword"),
	arc("arc", "application/x-freearc"),
	avi("avi", "video/x-msvideo"),
	azw("azw", "application/vnd.amazon.ebook"),
	bin("bin", "application/octet-stream"),
	bmp("bmp", "image/bmp"),
	bz("bz", "application/x-bzip"),
	bz2("bz2", "application/x-bzip2"),
	csh("csh", "application/x-csh"),
	css("css", "text/css"),
	csv("csv", "text/csv"),
	doc("doc", "application/msword"),
	docx("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
	eot("eot", "application/vnd.ms-fontobject"),
	epub("epub", "application/epub+zip"),
	gz("gz", "application/gzip"),
	gif("gif", "image/gif"),
	htm("htm", "text/html"),
	html("html", "text/html"),
	ico("ico", "image/vnd.microsoft.icon"),
	ics("ics", "text/calendar"),
	jar("jar", "application/java-archive"),
	jpe("jpe", "image/jpegg"),
	jpg("jpg", "image/jpeg"),
	js("js", "text/javascript"),
	json("json", "application/json"),
	jsonld("jsonld", "application/ld+json"),
	mid("mid", "audio/midi audio/x-midi"),
	midi("midi", "audio/midi audio/x-midi"),
	mjs("mjs", "text/javascript"),
	mp3("mp3", "audio/mpeg"),
	mpeg("mpeg", "video/mpeg"),
	mpkg("mpkg", "application/vnd.apple.installer+xml"),
	odp("odp", "application/vnd.oasis.opendocument.presentation"),
	ods("ods", "application/vnd.oasis.opendocument.spreadsheet"),
	odt("odt", "application/vnd.oasis.opendocument.text"),
	oga("oga", "audio/ogg"),
	ogv("ogv", "video/ogg"),
	ogx("ogx", "application/ogg"),
	otf("otf", "font/otf"),
	png("png", "image/png"),
	pdf("pdf", "application/pdf"),
	php("php", "appliction/php"),
	ppt("ppt", "application/vnd.ms-powerpoint"),
	pptx("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"),
	rar("rar", "application/x-rar-compressed"),
	rtf("rtf", "application/rtf"),
	sh("sh", "application/x-sh"),
	svg("svg", "image/svg+xml"),
	swf("swf", "application/x-shockwave-flash"),
	tar("tar", "application/x-tar"),
	tif("tif", "image/tiff"),
	tiff("tiff", "image/tiff"),
	ts("ts", "video/mp2t"),
	ttf("ttf", "font/ttf"),
	txt("txt", "text/plain"),
	vsd("vsd", "application/vnd.visio"),
	wav("wav", "audio/wav"),
	weba("weba", "audio/webm"),
	webm("webm", "video/webm"),
	webp("webp", "image/webp"),
	woff("woff", "font/woff"),
	woff2("woff2", "font/woff2"),
	xhtml("xhtml", "application/xhtml+xml"),
	xls("xls", "application/vnd.ms-excel"),
	xlsx("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
	xml("xml", "application/xml if not readable from casual users (RFC 3023, section 3)"),
	xul("xul", "application/vnd.mozilla.xul+xml"),
	zip("zip", "application/zip"),
	g3gp("3gp", "video/3gpp"),
	g3g2("3g2", "video/3gpp2"),
	z7z("7z", "application/x-7z-compressed"),
	;
	
	private String name;
	private String code;
	
	HtmlMimeType(String name, String code) {
		this.name = name;
		this.code = code;
	}
	
	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}
	
	public static HtmlMimeType getType(String typeName) {
		for(HtmlMimeType t : HtmlMimeType.values()) {
			if(t.getName().equals(typeName)) {
				return t;
			}
		}
		return null;
	}
	
//	public static HtmlMimeType getType(Long code) {
//		for(HtmlMimeType t : HtmlMimeType.values()) {
//			if(t.getCode().equals(code)) {
//				return t;
//			}
//		}
//		return null;
//	}
	
}
