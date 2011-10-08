package co.uk.kevintimmins.chat.client;

import java.io.IOException;
import java.net.UnknownHostException;

public class PlumClientInit {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		PlumClient plum = new PlumClient();
		plum.init();
		Thread plumStarter = new Thread(plum);
		plumStarter.start();
	}
}
