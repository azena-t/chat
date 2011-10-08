package co.uk.kevintimmins.chat.client;

//import java.io.IOException;

import java.io.IOException;

import co.uk.kevintimmins.chat.PlumSocket;

public class PlumReader implements Runnable {

	boolean flag = true;
	PlumSocket plum;
	String input;
	
	public void close(){
		flag = false;
	}
	public PlumReader(PlumSocket plum){
		this.plum = plum;
	}
	
	public void init(){
		
	}
	
	@Override
	public void run(){
		while(flag){
			try{
				Thread.sleep(100);
			}catch(InterruptedException ie){
				
			}
			try {
				if(plum.ready()){
					input = plum.read();
					System.out.println(input);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
