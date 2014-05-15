package dk.dtu.sdn.project.curl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import dk.dtu.sdn.project.utils.Constants;

public class SendCURL {

	private URL gURL;

	public SendCURL(String url) throws MalformedURLException, IOException {
		this.gURL = new URL(url);
	}

	public boolean sendJSON(String inputJSON, boolean isTypeDelete) throws IOException {
		HttpURLConnection httpConnection = (HttpURLConnection) (gURL.openConnection());
		httpConnection.setDoOutput(true);
		httpConnection.setRequestProperty("Content-Type", "application/json");
		httpConnection.setRequestProperty("Accept", "application/json");
		httpConnection.setRequestMethod("POST");
		if (isTypeDelete) {
			httpConnection.setRequestProperty("X-HTTP-Method-Override", "DELETE");
		}
		httpConnection.connect();
		OutputStream os = httpConnection.getOutputStream();
		os.write(inputJSON.getBytes("UTF-8"));
		os.flush();
		os.close();
		boolean toReturn = httpConnection.getResponseMessage().equalsIgnoreCase(Constants.ResponseSuccess);
		httpConnection.disconnect();
		return toReturn;
	}
}
