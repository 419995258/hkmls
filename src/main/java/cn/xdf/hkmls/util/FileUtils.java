package cn.xdf.hkmls.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 文件 工具类
 *
 * @author cuirongkui@xdf.cn
 * @date 2020.12.21
 */
public class FileUtils {
	private static final int BUFFER_SIZE = 1024;

	/**
	 * 删除指定文件
	 *
	 * @param filePath 文件全路径
	 */
	public static void deleteFile(String filePath) {
		try {
			File file = new File(filePath);
			if(file.exists()) {
				file.delete();
			}
		} catch (Exception e) {
			// do nothing
		}
	}

	/**
	 * 读取文件 (字符)
	 *
	 * @param filePath 文件路径
	 * @return 文件内容 (字符)
	 * @throws IOException 异常信息
	 */
	public static String readToStringByFilePath(String filePath)throws IOException {
		StringBuilder data = new StringBuilder();
		Reader reader  =  null;
		try {
//			File file = new File(filePath);
			File file = ResourceUtils.getFile("classpath:"+filePath);
			reader = new FileReader(file);
			char [] c = new char [1024];
			int len = 0;
			while ((len = reader.read(c)) != -1){
				data.append(String.copyValueOf(c,0,len));
			}
		} finally {
			close(reader);
		}
		return data.toString();
	}

	/**
	 * 读取文件（字节数组）
	 *
	 * @param filePath 文件路径
	 * @return 文件内容 (字节数组)
	 * @throws IOException 异常信息
	 */
	public static byte[] readToBytesByFilePath(String filePath)throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		FileInputStream reader  =  null;
		try {
			File file = new File(filePath);
			reader = new FileInputStream(file);
			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = reader.read(buffer)) != -1){
				output.write(buffer, 0, len);
			}
		} finally {
			close(reader);
		}
		return output.toByteArray();
	}

	public static void close(Closeable closeable) {
		if (null != closeable){
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void flush(Flushable flushable) {
		if (null != flushable){
			try {
				flushable.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
