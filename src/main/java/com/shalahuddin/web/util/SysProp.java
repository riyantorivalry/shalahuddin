/**
 *
 */
package com.shalahuddin.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;

public class SysProp implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 4088397439715839568L;

	public SysProp() {
	}

	private static String propName = IConstants.PROPERTIES_FILE_NAME;
	static Properties props;

	public static String getProperty(String key) {
		return props.getProperty(key);
	}

	public static void reload() {
		props = loadData();
	}

	public static String getProperty(String key, String defaults) {
		return props.getProperty(key, defaults);
	}

	static {
		props = loadData();
	}

	private static Properties loadData() {
		Properties prop = null;
		try {
			String strClassPath = System.getProperty("java.class.path");
			String strPath = "";
			StringTokenizer st = new StringTokenizer(strClassPath,
					File.pathSeparator);
			File propDir = null;
			File propFile = null;
			label0:
				do {
					if (!st.hasMoreTokens()) {
						break;
					}
					strPath = st.nextToken();
					try {
						propDir = new File(strPath);
						if (!propDir.exists() || !propDir.isDirectory()) {
							continue;
						}
						String arrFieldName[] = propDir.list();
						int i = 0;
						do {
							if (i >= arrFieldName.length) {
								continue label0;
							}
							if (arrFieldName[i].equalsIgnoreCase(propName)) {
								propFile = new File(propDir.getPath(),
										arrFieldName[i]);
								if (propFile.isFile()) {
									break;
								}
							}
							i++;
						} while (true);
						break;
					} catch (Exception e1) {
						propFile = null;
					}
				} while (true);
			propDir = null;
			if (propFile == null) {
				throw new Exception("File " + propName +
						" was not found. You need to copy file " +
						propName + " to system config path and add the config path to the classpath in CheckIPSATPMailAddress.bat. eg. set CLASSPATH=%APPLICATION%/config/;");
			}
			prop = new Properties();
			FileInputStream in = new FileInputStream(propFile);
			prop.load(in);
			in.close();
			Enumeration enums = prop.keys();
			while (enums.hasMoreElements()) {
				String key = (String) enums.nextElement();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
}
