package org.multipoly.utils;

import java.io.*;

public class Resources {

	public static InputStream getResourceAsStream(String resource) {
		String stripped = resource.startsWith("/") ? resource.substring(1) : resource;
		InputStream stream = getResourceAsStream(resource, stripped);
		return stream;
	}

	static InputStream getResourceAsStream(String resource, String stripped) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream stream = null;
		if (classLoader != null) {
			stream = classLoader.getResourceAsStream(stripped);
		}
		return stream;
	}

	public static void closeStream(InputStream inputStream) {
		if (inputStream == null) {
			return;
		}
		try {
			inputStream.close();
		} catch (IOException e) {
			//
		}
	}
	
	public static Reader getResourceAsReader(String resource) {
		String stripped = resource.startsWith("/") ? resource.substring(1) : resource;
		InputStream stream = getResourceAsStream(resource, stripped);
		return new InputStreamReader(stream);
	}
	
	public static Reader getResourceAsReader(File file) throws FileNotFoundException {
		return new InputStreamReader(new FileInputStream(file));
	}	
	
	public static void closeStream(Reader reader) {
		if (reader == null) {
			return;
		}
		try {
			reader.close();
		} catch (IOException e) {
			//
		}
	}
	
	public static InputStream getResourceAsStream(File file) throws FileNotFoundException {
		return new FileInputStream(file);
	}
}
