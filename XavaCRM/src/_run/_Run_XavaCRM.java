package _run;

import org.openxava.util.*;

/**
 * Execute this class to start the application.
 *
 * With Eclipse: Right mouse button > Run As > Java Application
 */

public class _Run_XavaCRM {

	public static void main(String[] args) throws Exception {
		// DBServer.start("XavaCRMDB"); // To use your own database comment this line and configure web/META-INF/context.xml
		AppServer.run("XavaCRM"); // Use AppServer.run("") to run in root context
	}

}
