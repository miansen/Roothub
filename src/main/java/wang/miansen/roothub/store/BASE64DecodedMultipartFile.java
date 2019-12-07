package wang.miansen.roothub.store;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p></p>
 * @author: miansen.wang
 * @date: 2019-04-24
 */
public class BASE64DecodedMultipartFile implements MultipartFile {

	/**
	 * 文件内容数组
	 */
	private final byte[] content;
	
	/**
	 * 头部信息，如: data:image/png
	 */
	private final String header;
	
	/**
	 * 文件类型，如: image
	 */
	private final String contentType;
	
	/**
	 * 后缀名，如: png
	 */
	private final String suffixName;
	
	/**
	 * 文件的原名，这里是随机生成的
	 */
	private final String originalFilename;
	
	public BASE64DecodedMultipartFile(byte[] content, String header) {
		this.content = content;
		this.header = header;
		this.contentType = header.split("/")[0].split(":")[1];
		this.suffixName = header.split("/")[1];
		this.originalFilename = System.currentTimeMillis() + (int)Math.random() * 10000 + "." + this.suffixName;
	}

	/**
	 *  获取表单中文件组件的名字
	 */
	@Override
	public String getName() {
		return null;
	}

	/**
	 * 获取上传文件的原名
	 */
	@Override
	public String getOriginalFilename() {
		return this.originalFilename;
	}

	
	@Override
	public String getContentType() {
		return this.contentType;
	}

	
	@Override
	public boolean isEmpty() {
		return (this.content == null || this.content.length == 0);
	}

	
	@Override
	public long getSize() {
		return this.content.length;
	}

	
	@Override
	public byte[] getBytes() throws IOException {
		return this.content;
	}

	
	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(this.content);
	}

	
	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		new FileOutputStream(dest).write(this.content);
	}

}
