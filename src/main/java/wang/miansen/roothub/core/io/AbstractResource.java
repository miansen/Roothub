package wang.miansen.roothub.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import wang.miansen.roothub.core.exception.NestedIOException;

/**
 * 抽象的资源描述，实现了一些通用的方法。
 * @author miansen.wang
 * @date 2019-11-22
 */
public abstract class AbstractResource implements Resource {

	@Override
	public boolean exists() {
		try {
			return getFile().exists();
		} catch (IOException e) {
			try {
				getInputStream().close();
				return true;
			} catch (IOException e1) {
				return false;
			}
		}
	}

	@Override
	public URL getURL() throws IOException {
		throw new FileNotFoundException(getDescription() + " cannot be resolved to URL");
	}

	@Override
	public URI getURI() throws IOException {
		URL url = getURL();
		try {
			return url.toURI();
		} catch (URISyntaxException e) {
			throw new NestedIOException("Invalid URI [" + url + "]", e);
		}
	}

	@Override
	public File getFile() throws IOException {
		URL url = getURL();
		if (!"file".equals(url.getProtocol())) {
			throw new FileNotFoundException(getDescription() + " cannot be resolved to absolute file path " + "because it does not reside in the file system: " + url);
		} else {
			try {
				return new File(url.toURI().getSchemeSpecificPart());
			} catch (URISyntaxException var3) {
				return new File(url.getFile());
			}
		}
	}

	public File getFile(URI uri) throws IOException {
		if (!"file".equals(uri.getScheme())) {
			throw new FileNotFoundException(getDescription() + " cannot be resolved to absolute file path " + "because it does not reside in the file system: " + uri);
		} else {
			return new File(uri.getSchemeSpecificPart());
		}
	}

	@Override
	public long contentLength() throws IOException {
		InputStream is = getInputStream();
		try {
			long size = 0;
			byte[] buf = new byte[256];
			int read;
			while ((read = is.read(buf)) != -1) {
				size += read;
			}
			return size;
		} finally {
			is.close();
		}
	}

	@Override
	public long lastModified() throws IOException {
		return getFile().lastModified();
	}

	@Override
	public String getFilename() {
		return null;
	}

}
