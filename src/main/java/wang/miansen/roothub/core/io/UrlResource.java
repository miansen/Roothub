package wang.miansen.roothub.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;


/**
 * @Author: miansen.wang
 * @Date: 2019/11/23 15:30
 */
public class UrlResource extends AbstractResource {

    private final URI uri;

    private final URL url;

    public UrlResource(URI uri) throws MalformedURLException {
        this.uri = uri;
        this.url = uri.toURL();
    }

    public UrlResource(URL url) {
        this.uri = null;
        this.url = url;
    }

    public UrlResource(String path) throws MalformedURLException {
        this.uri = null;
        this.url = new URL(path);
    }

    @Override
    public URL getURL() throws IOException {
        return this.url;
    }

    @Override
    public URI getURI() throws IOException {
        return this.uri != null ? this.uri : super.getURI();
    }

    @Override
    public File getFile() throws IOException {
        return this.uri != null ? super.getFile(this.uri) : super.getFile();
    }

    @Override
    public String getFilename() {
        return (new File(this.url.getFile())).getName();
    }

    @Override
    public String getDescription() {
        return "URL [" + this.url + "]";
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        con.setUseCaches(con.getClass().getSimpleName().startsWith("JNLP"));
        try {
            return con.getInputStream();
        } catch (IOException e) {
            if (con instanceof HttpURLConnection) {
                ((HttpURLConnection) con).disconnect();
            }
            throw e;
        }
    }
}
