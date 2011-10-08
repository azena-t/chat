package co.uk.kevintimmins.chat.server;

import java.io.IOException;
import java.net.*;

import org.apache.log4j.Logger;

public class PlumServer implements Runnable{
	
	static Logger logger = Logger.getLogger(PlumServer.class);
	Socket plumStone;
	ServerSocket thePlumTree;
	PlumRelay plumRelay;
	int port = 8867;
	boolean flag = true;
	
	public void close(){
		flag = false;
		plumRelay.close();
	}
	
	
	public PlumServer(){
		
	}
	
	public void init(){
		try {
			thePlumTree = new ServerSocket(port);
		} catch (IOException e) {
			logger.error("Cannot instantiate the server on port " + port);
			e.printStackTrace();
			flag=false;
		}
		try {
			thePlumTree.setSoTimeout(100);
		} catch (SocketException e) {
			e.printStackTrace();
			flag=false;
		}
		plumRelay = new PlumRelay();
		plumRelay.init();
		Thread plumRelayThread = new Thread(plumRelay);
		plumRelayThread.start();
	}

	@Override
	public void run() {
		while(flag){
			try{
				Thread.sleep(100);
			}catch(InterruptedException ie){
				
			}
			try {
				plumStone = thePlumTree.accept();
				System.out.println("Plum joined the server");
				plumStone.setKeepAlive(true);
				plumRelay.addSocket(plumStone);
			} catch (IOException e) {
				
			}
		}
	}
}
