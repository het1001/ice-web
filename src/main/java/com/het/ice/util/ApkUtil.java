package com.het.ice.util;

import java.io.File;

public class ApkUtil {

	private final static String ROOT_PATH = "D://iceApk";

	private static String lastFileName() {
		File path = new File(ROOT_PATH);
		if (!path.exists()) {
			path.mkdir();
			return null;
		}

		String[] files = path.list();

		if (files.length == 0) {
			return "";
		} else if (files.length == 1) {
			return files[0];
		} else {
			int newVersionNum = 0;
			String newFileName = null;

			for (String string : files) {
				int versionNum = Integer.valueOf(getVersion(string));
				if (versionNum > newVersionNum) {
					newVersionNum = versionNum;
					newFileName = string;
				}
			}

			return newFileName;
		}
	}

	public static String getNewVersion() {
		String lastFile = lastFileName();
		if (lastFile != null) {
			return getVersion(lastFile);
		}

		return null;
	}

	private static String getVersion(String fileName) {
		return fileName.substring(6, 11);
	}

	public static File getApk() {
		String fileName = lastFileName();
		return new File(ROOT_PATH + File.separator + fileName);
	}

	public static File getApkByVersion(String version) {
		return new File(ROOT_PATH + File.separator + "iceApp" + version + ".apk");
	}
}
