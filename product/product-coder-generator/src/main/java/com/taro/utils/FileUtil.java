package com.taro.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	public static void writerFile(String filePath, String content) {
		File file = new File(filePath);
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try(FileWriter fileWriter = new FileWriter(file);) {
			fileWriter.write(content);
			fileWriter.flush();
		} catch (IOException e) {
		}
	}
}
