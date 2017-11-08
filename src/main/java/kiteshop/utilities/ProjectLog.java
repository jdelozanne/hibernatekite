package kiteshop.utilities;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ProjectLog {


	/* 
	 * De 2 waarden hieronder kunnen naar believen worden aangepast door de programmeur
	 * voor test doeleinden SP SP sp
	 */ 
	static Level consolePrintLevel = Level.SEVERE;
	static Level filePrintLevel  = Level.FINEST;



	public static Logger getLogger(){

		System.out.println("lalala");
		Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);  // zodat hij in het hele project gebruikt kan worden
		LogManager.getLogManager().reset();

		logr.setLevel(Level.ALL);

		ConsoleHandler ch = new ConsoleHandler();
		ch.setLevel(consolePrintLevel);  
		logr.addHandler(ch);

		/*
		try {
			//src/main/java/kiteshop/test/ProjectLog.log
			/*FileHandler fh = new FileHandler("src/main/java/kiteshop/test/ProjectLog.log", true);
			fh.setLevel(filePrintLevel);
			fh.setFormatter(new SimpleFormatter());

			logr.addHandler(fh);


			sout
		} catch (IOException e){
			logr.log(Level.SEVERE, "File logger not working", e);
		}
		 */
		return logr;
	}
	/*
	 * 
	 * Printing to console is de ROOTLOGGER, daar hebben we geen invloed op
	 * severe
	 * warning
	 * info
	 * config
	 * fine
	 * finer
	 * finest
	 */

}

