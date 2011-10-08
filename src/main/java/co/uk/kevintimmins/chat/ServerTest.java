package co.uk.kevintimmins.chat;

import java.io.*;
import java.net.*;

public class ServerTest implements Runnable{

	Socket sock;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new ServerTest()).start();

	}

	public void run(){
		ServerSocket serve = null;
		while(true){
			try {
				serve = new ServerSocket(8888);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				sock = serve.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				System.out.println(in.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
