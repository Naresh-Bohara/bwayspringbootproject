package com.bway.springbootproject.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VerifyRecaptcha {

	public static final String url = "https://www.google.com/recaptcha/api/siteverify";

	public static final String secret = "6LcEW4AtAAAAADn-7J3dk4wt4FUdsgN-1uEA6WMz";

	private final static String USER_AGENT = "Mozilla/5.0";

	public static boolean verify(String gRecaptchaResponse) throws IOException {

		if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
			return false;
		}

		try {

			URL obj = new URL(url);

			HttpsURLConnection con =
					(HttpsURLConnection) obj.openConnection();

			// Add request header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty(
					"Content-Type",
					"application/x-www-form-urlencoded"
			);

			String postParams =
					"secret=" + secret +
					"&response=" + gRecaptchaResponse;

			// Send POST request
			con.setDoOutput(true);

			DataOutputStream wr =
					new DataOutputStream(con.getOutputStream());

			wr.writeBytes(postParams);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();

			System.out.println(
					"\nSending 'POST' request to URL : " + url
			);

			System.out.println(
					"Post parameters : " + postParams
			);

			System.out.println(
					"Response Code : " + responseCode
			);

			BufferedReader in =
					new BufferedReader(
							new InputStreamReader(con.getInputStream())
					);

			String inputLine;

			StringBuffer response =
					new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			// Print result
			System.out.println(response.toString());

			// Parse JSON response
			ObjectMapper mapper = new ObjectMapper();

			JsonNode jsonObject =
					mapper.readTree(response.toString());

			// Return success value
			return jsonObject
					.get("success")
					.asBoolean();

		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}
}