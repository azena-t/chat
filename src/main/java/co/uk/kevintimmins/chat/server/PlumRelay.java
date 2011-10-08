package co.uk.kevintimmins.chat.server;

import java.io.IOException;
import java.net.Socket;
import java.util.*;

import org.apache.log4j.Logger;

import co.uk.kevintimmins.chat.PlumSocket;

public class PlumRelay implements Runnable{

	static Logger logger = Logger.getLogger(PlumRelay.class);
	boolean flag = true;
	PlumSocket plumStone;
	List<PlumSocket> plums;
	String input;

	public void close(){
		for(PlumSocket plum : plums){
			try {
				plum.leave();
			} catch (IOException e) {
			}
		}
		flag=false;
	}

	public PlumRelay(){

	}

	public void init(){
		plums = new ArrayList<PlumSocket>();
	}

	synchronized void addSocket(Socket socket) throws IOException{
		plumStone = new PlumSocket(socket);
		plumStone.init();
		plums.add(plumStone);
		plumStone=null;
		this.notifyAll();
	}

	@Override
	public void run() {
		while(flag){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for( PlumSocket plum : plums ){
				Thread.yield();
				try {
					if(plum.ready()){
						input = plum.read();
						System.out.println(input);
						outputMessage(input);
					}	
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	void outputMessage(String output){
		for( PlumSocket plum : plums ){
			if(plum.isConnected()){
				try {
					plum.post(output);
				} catch (IOException e) {
					System.out.println("Plum posting failed");
				}
			} else {
				plums.remove(plum);
			}
		}
	}
}
