package io.github.elementaldevelopers.antiscam.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang3.NotImplementedException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class APIGrabber {
	private static JsonParser parser = new JsonParser();
	public static String getData() {
		try {
			URL url = new URL("https://api.github.com/repos/skyblockz/pricecheckbot/contents/scammer.json");
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("Accept", "application/vnd.github.3.raw");
			InputStream response = conn.getInputStream();
			BufferedReader in = new BufferedReader(
                    new InputStreamReader(response)
            );
			String inputLine;
			String outputline = "";
			while ((inputLine = in.readLine()) != null) 
				outputline += inputLine;
			return outputline;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "Error";
		} catch (IOException e) {
			System.out.println("Error in downloding stuff :(");
			return "Error";
		}
		
	}
	public static String getUUID(String IGN) {
		try {
			URL url = new URL("https://api.mojang.com/users/profiles/minecraft/<username>".replace("<username>", IGN));
			URLConnection conn = url.openConnection();
			int code = ((HttpURLConnection) conn).getResponseCode();
			if (code == 204) {
				return "IGN Not Valid";
			}
			InputStream response = conn.getInputStream();
			BufferedReader in = new BufferedReader(
                    new InputStreamReader(response)
            );
			String inputLine;
			String outputline = "";
			while ((inputLine = in.readLine()) != null) 
				outputline += inputLine;
			JsonObject json = parser.parse(outputline).getAsJsonObject();
			String uuid = json.get("uuid").getAsString();
			return uuid;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "Error happened";
		} catch (IOException e) {
			System.out.println("Error in downloding stuff :(");
			e.printStackTrace();
			return "Error happened";
		}
	}
}
