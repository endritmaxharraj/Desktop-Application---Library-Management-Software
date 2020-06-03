package main.utils;

import java.util.Properties;

public class AppConfig {
	private Properties props;

	private AppConfig() {
		try {
			props = new Properties();
			props.load(getClass().getResourceAsStream("../resources/config.properties"));
		} catch (Exception e) {
		}
	}

	private static AppConfig instance;

	public static AppConfig get() {
		if (instance == null) {
			instance = new AppConfig();
		}
		return instance;
	}

	public String getAppName() {
		return props.getProperty("appname", "FIEK BIBLOTEKA INC.");
	}

	public String getVersion() {
		return props.getProperty("version", "FIEK BIBLOTEKA INC.");
	}

	public String getReleased() {
		return props.getProperty("released", "FIEK BIBLOTEKA INC.");
	}

	public String getConnectionString() {
		return props.getProperty("connectionString");
	}

	public String getDriverType() {
		return props.getProperty("driverType");
	}
}
