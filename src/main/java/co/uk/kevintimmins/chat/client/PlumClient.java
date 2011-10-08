package co.uk.kevintimmins.chat.client;

import java.io.*;
import java.net.*;

import co.uk.kevintimmins.chat.PlumSocket;

public class PlumClient implements Runnable{

	Socket plumStone;
	PlumSocket plum;
	BufferedReader in;
	boolean flag = true;
	String input;
	PlumReader reader;

	public void close() throws IOException{
		flag=false;
		in.close();
		plum.leave();
		reader.close();
	}

	public PlumClient(){

	}

	public void init() throws UnknownHostException, IOException{
		plumStone = new Socket();
		plumStone.setKeepAlive(true);
		plumStone.connect(new InetSocketAddress("localhost",8867));
		plum = new PlumSocket(plumStone);
		plum.init();
		reader = new PlumReader(plum);
		Thread plumExecutor = new Thread(reader);
		plumExecutor.start();
		in = new BufferedReader(new InputStreamReader(System.in));
	}

	@Override
	public void run(){
		while(flag){
			try{
				Thread.sleep(100);
			}catch(InterruptedException ioe){
				ioe.printStackTrace();
			}
			try {
				input = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(input.equalsIgnoreCase("clientquitxx")){
				try {
					close();
				} catch (IOException e) {
				}
			} else {
				try {
					plum.post(input);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
