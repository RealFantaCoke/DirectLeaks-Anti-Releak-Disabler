package fr.jmlsoftwares.directleakssucks;

import java.util.Scanner;

import fr.jmlsoftwares.directleakssucks.functions.FileEditor;
import fr.jmlsoftwares.directleakssucks.functions.Firewall;
import fr.jmlsoftwares.directleakssucks.functions.IPTables;

public class Main {
	
	private static String OS = System.getProperty("os.name").toLowerCase();
	public static String hostFile;
	private static String[] lines = {
		"5.189.167.27"
	};
	
	public static void main(String[] args) {
		
		System.out.println("OS: " + OS);
		System.out.println("DirectLeaks Anti-Releak Disabler - v1.1");
		System.out.println("All rights reserved, JML-Software© 2018.");

		int i = 0;
		while (i < 3) {
			System.out.println("");
			i++;
		}
		
		try {
			if (OS.contains("windows")) {
				hostFile = "C:\\Windows\\System32\\drivers\\etc\\hosts";
				FileEditor.SetAttributes("windows", hostFile, 2, false);
			} else if (OS.contains("linux")) {
				hostFile = "/etc/hosts";
				FileEditor.SetAttributes("linux", hostFile, 2, true);
			} else {
				System.out.println("Your OS is probably not compatible with this program for the moment !");
				System.exit(0);
			}
		} catch (Exception e) {
			
		}
		
		Methods();
		
	}
	
	public static void Methods() {
		
		System.out.println("Methods:");
		System.out.println("0 = Host File (Outdated), 1 = New Method (Recommended)");
		System.out.println("Answer:");
        Scanner s = new Scanner(System.in);
        String Method = s.nextLine().replace("Answer:", "");
        s.close();
		
        if (Method.equalsIgnoreCase("0")) {
    		
    		FileEditor.AddLine(hostFile, "\n\n#DirectLeaks Anti-Releak");

    		int i = 0;
    		
    		while (i < lines.length) {
    			if (FileEditor.AddLine(hostFile, "127.0.0.1	" + lines[i]) != false) {
    				
    			} else {
    				System.out.println("Error while disabling the DirectLeaks Anti-Releak...");
    				return;
    			}
    			i++;
    		}
        	
        } else if (Method.equalsIgnoreCase("1")) {
        	
        	if (OS.contains("windows")) {
        		
        		int i = 0;
        		
        		while (i < lines.length) {
        			
        			if (Firewall.AddRule("Windows Media Player " + i, "out", "any", "block", lines[i]) != false) {
        				
        			} else {
        				
        				System.out.println("Error while disabling the DirectLeaks Anti-Releak...");
        				
        				return;
        				
        			}
        			
        			i++;
        			
        		}
        		
        	} else if (OS.contains("linux")) {
        		
        		int i = 0;
        		
        		while (i < lines.length) {
        			
        			if (IPTables.BlockIP(lines[i]) != false) {
        				
        			} else {
        				
        				System.out.println("Error while disabling the DirectLeaks Anti-Releak...");
        				
        				return;
        				
        			}
        			
        			i++;
        			
        		}
        		
        	} else {
        		
        		System.out.println("Your OS is probably not compatible with this program for the moment !");
        		
        	}
        	
        } else {
        	
        	System.out.println("This method dosen't exists.");
        	
        	Methods();
        	
        }

		int i = 0;
		while (i < 3) {
			System.out.println("");
			i++;
		}

		System.out.println("DirectLeaks Anti-Releak Disabled.");
		
	}

}
