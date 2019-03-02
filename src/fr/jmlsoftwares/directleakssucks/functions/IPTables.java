package fr.jmlsoftwares.directleakssucks.functions;

import java.io.IOException;

public class IPTables {
	
	public static boolean BlockIP(String remoteip) {

		try {
			Runtime.getRuntime().exec(
				"iptables -A block_outgoing -j DROP -d " + remoteip
			);
		} catch (IOException e) {
			
			return false;
			
		}
		
		return true;
		
	}

}
