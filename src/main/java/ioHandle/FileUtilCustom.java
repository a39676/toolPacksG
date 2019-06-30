package ioHandle;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.zeroturnaround.zip.ZipUtil;

public class FileUtilCustom {

	public String getStringFromFile(String filePath, String encodeType) {
		Path path = Paths.get(filePath);
		StringBuffer result = new StringBuffer();
		try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName(encodeType))) {

			String currentLine = null;
			while ((currentLine = reader.readLine()) != null) {// while there is content on the current line
				result.append(currentLine);
			}
		} catch (IOException ex) {
			ex.printStackTrace(); // handle an exception here
		}
		return result.toString();
	}

	public String getStringFromFile(String filePath) {
		return getStringFromFile(filePath, StandardCharsets.UTF_8.displayName());
	}

	public byte[] getByteFromFile(String path) throws Exception {
		byte[] b = null;
		File file = new File(path);

		FileInputStream fis = null;
		ByteArrayOutputStream ops = null;
		try {

			if (!file.exists()) {
				System.out.println("文件不存在！");
			}
			if (file.isDirectory()) {
				System.out.println("不能上传目录！");
			}

			byte[] temp = new byte[2048];

			fis = new FileInputStream(file);
			ops = new ByteArrayOutputStream(2048);

			int n;
			while ((n = fis.read(temp)) != -1) {
				ops.write(temp, 0, n);
			}
			b = ops.toByteArray();
		} catch (Exception e) {
			throw new Exception();
		} finally {
			if (ops != null) {
				ops.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
		return b;
	}

	/**
	 * 写入文件(先覆盖文件原有内容)
	 * 
	 * @param byteArray
	 * @param filePath
	 */
	public void byteToFile(byte[] byteArray, String filePath) {
		byteToFile(byteArray, filePath, false);
	}

	/**
	 * 写入文件(在末尾追加)
	 * 
	 * @param byteArray
	 * @param filePath
	 */
	public void byteToFileAppendAtEnd(byte[] byteArray, String filePath) {
		byteToFile(byteArray, filePath, true);
	}

	/**
	 * 写入文件
	 * 
	 * @param byteArray
	 * @param filePath
	 * @param appendFlag 是否在末尾追加
	 */
	public void byteToFile(byte[] byteArray, String filePath, boolean appendFlag) {
		if (byteArray == null || byteArray.length <= 0) {
			return;
		}

		File file = new File(filePath);
		File paraentFolder = file.getParentFile();

		if (!paraentFolder.exists()) {
			paraentFolder.mkdirs();
		}

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}

		if (!file.isFile()) {
			return;
		}

		try {
			FileUtils.writeByteArrayToFile(file, byteArray, appendFlag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fileRecode(String inputPath, String outputPath, String inputCode, String outputCode) throws Exception {
		File inputFile = new File(inputPath);
		File outputFile = new File(outputPath);

		FileInputStream fis = null;
		ByteArrayOutputStream ops = null;

		try {

			if (!inputFile.exists()) {
				System.out.println("文件不存在！");
				return;
			}
			if (inputFile.isDirectory()) {
				System.out.println("不能指定一个目录！");
				return;
			}
			if (!outputFile.exists()) {
				outputFile.createNewFile();
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputPath), inputCode));
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputFile, true), outputCode);

			String line = br.readLine();
			while (line != null) {
				if (line != null && line.length() > 0) {
					writer.write(line);
				}
				line = br.readLine();
			}
			br.close();
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ops != null) {
				ops.close();
			}
			if (fis != null) {
				fis.close();
			}
		}

	}

	public Properties getPropertiesFromFile(String filePath) {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(filePath);

			prop.load(input);

			return prop;

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	public boolean setPropertiesToFile(String filePath, HashMap<String, String> propertiesMap) {

		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream(filePath);

			for (Map.Entry<String, String> entry : propertiesMap.entrySet()) {
				prop.setProperty(entry.getKey(), entry.getValue());
			}

			prop.store(output, null);

			return true;
		} catch (Exception io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		return false;
	}

	public void createFolder(String targetPath, List<String> newFolderNames) {
		File tmpFile;

		for (String folderName : newFolderNames) {
			tmpFile = new File(targetPath + folderName);
			tmpFile.mkdir();
		}
	}

	public void createFiles(String targetPath, List<String> newFileNames) {
		File tmpFile;

		for (String fileName : newFileNames) {
			tmpFile = new File(targetPath + fileName);
			try {
				tmpFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public byte[] encodeFileToBase64(String filePath) throws IOException {

		File file = new File(filePath);
		byte[] bytes = loadFile(file);
		byte[] encoded = Base64.getEncoder().encode(bytes);

		return encoded;
	}

	@SuppressWarnings("resource")
	public byte[] loadFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		long length = file.length();
		if (length > Integer.MAX_VALUE) {
			// File is too large
		}
		byte[] bytes = new byte[(int) length];

		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		if (offset < bytes.length) {
			throw new IOException("Could not completely read file " + file.getName());
		}

		is.close();
		return bytes;
	}

	public void filesToZip(String outputZipPath, List<String> filePaths) throws IOException {
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(outputZipPath);
			zos = new ZipOutputStream(fos);

			for (String filePath : filePaths) {
				addToZipFile(filePath, zos);
			}

			zos.close();
			fos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
			if (zos != null) {
				zos.close();
			}
		}
	}

	public void fileToZip(String outputZipPath, String filePath) throws IOException {
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(outputZipPath);
			zos = new ZipOutputStream(fos);

			addToZipFile(filePath, zos);

			zos.close();
			fos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
			if (zos != null) {
				zos.close();
			}
		}
	}

	public void folderToZip(String outputZipPath, String filePath) throws IOException {
		ZipUtil.pack(new File(filePath), new File(outputZipPath));
	}

	private void addToZipFile(String filePath, ZipOutputStream zos) throws IOException {

		FileInputStream fis = null;
		try {
			File file = new File(filePath);
			fis = new FileInputStream(file);
			ZipEntry zipEntry = new ZipEntry(file.getName());
			zos.putNextEntry(zipEntry);

			byte[] bytes = new byte[1024];
			int length;
			while ((length = fis.read(bytes)) >= 0) {
				zos.write(bytes, 0, length);
			}
			zos.closeEntry();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			zos.closeEntry();
			fis.close();
		}

	}

}
