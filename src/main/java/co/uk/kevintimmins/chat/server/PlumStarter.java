package co.uk.kevintimmins.chat.server;

public class PlumStarter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final PlumServer plumServe = new PlumServer();
		plumServe.init();
		Runtime.getRuntime().addShutdownHook(
				new Thread(){
					@Override
					public void run(){
						plumServe.close();
					}
				}
		);
		Thread plumService = new Thread(plumServe);
		plumService.start();
	}

}
