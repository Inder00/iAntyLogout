package pl.inder00.api.connection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPContent {
	
	private String url;
	private String data;
	
	public HTTPContent(String url) {
		this.setURL(url);
	}
	public void connect(){
		if(url == null) return;
		InputStreamReader data;
		URL url;
		try {
			url = new URL(this.getURL());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			data = new InputStreamReader((InputStream) conn.getContent());
			BufferedReader buffer = new BufferedReader(data);
			this.setData(buffer.readLine());
		} catch (Exception e) {
			System.err.print("[API] Error: "+this.getClass().getName());
			e.printStackTrace();
		}
	}
	public String read() {
		return data;
	}
	
	

	public String getURL() {
		return url;
	}

	public void setURL(String url) {
		this.url = url;
	}
	public void setData(String data) {
		this.data = data;
	}

}
