package ch.hsr.uint1.whitespace.library.client.android.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

public class PropertyReader {

	private static final String PROPERTY_FILE_NAME = "library-app.properties";

	private static Properties properties;

	private PropertyReader() {

	}

	public static void initProperties(Context context) {
		properties = loadProperties(context);
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	private static Properties loadProperties(Context context) {
		Resources resources = context.getResources();
		AssetManager assetManager = resources.getAssets();
		Properties properties = new Properties();
		try {
			InputStream inputStream = assetManager.open(PROPERTY_FILE_NAME);
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

}
