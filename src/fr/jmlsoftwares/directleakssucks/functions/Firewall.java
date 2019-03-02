package fr.jmlsoftwares.directleakssucks.functions;

import java.io.IOException;

public class Firewall {
	
	public static boolean AddRule(String name, String dir, String interfacetype, String action, String remoteip) {
		
		try {
			Runtime.getRuntime().exec(
				"netsh advfirewall firewall add rule name=\"" + name + "\" remoteip=" + remoteip + " dir=" + dir + " enable=yes action=" + action
			);
		} catch (IOException e) {
			
			return false;
			
		}
		
		return true;
		
	}

}
