package fr.jmlsoftwares.directleakssucks.functions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileEditor {
	
	private static final String newLine = System.getProperty("line.separator");

	public synchronized static boolean AddLine(String input, String line)  {
	    PrintWriter printWriter = null;
	    File file = new File(input);
	    try {
	        if (!file.exists()) file.createNewFile();
	        printWriter = new PrintWriter(new FileOutputStream(input, true));
	        printWriter.write(line + newLine);
	        return true;
	    } catch (IOException e) {
	        return false;
	    } finally {
	        if (printWriter != null) {
	            printWriter.flush();
	            printWriter.close();
	        }
	    }
	}
	
	public static boolean SetAttributes(String os, String filePath, int attribute, boolean value) {
		try {
			switch (os) {
			case "windows":
				Path windowsFile = Paths.get(filePath);
				if (attribute <= 3) {
					String[] Attributes = {
						"dos:archive",
						"dos:hidden",
						"dos:readonly",
						"dos:system"
					};
					Files.setAttribute(windowsFile, Attributes[attribute], value, LinkOption.NOFOLLOW_LINKS);
				} else {
					return false;
				}
				break;
			case "linux":
				File linuxFile = new File(filePath);
				if (attribute <= 2) {
					switch (attribute) {
					case 0:
						linuxFile.setExecutable(value);
						break;
					case 1:
						linuxFile.setReadable(value);
						break;
					case 2:
						linuxFile.setWritable(value);
						break;
					}
				}
				break;
			}
			return true;
		} catch (Exception e) {
			String privileges = null;
			if (os.contains("windows")) {
				privileges = "admin";
			} else if (os.contains("linux")) {
				privileges = "root";
			}
			System.out.println("This program needs " + privileges + " privileges !");
			System.exit(0);
			return false;
		}
	}
	
}
