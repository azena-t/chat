package co.uk.kevintimmins.chat;

import java.io.*;
import java.net.*;

public class ClientTest implements Runnable {
	
	public static void main(String [] a){
		new Thread(new ClientTest()).start();
	}
	
	@Override
	public void run(){
		Socket sock = new Socket();
		try {
			sock.setKeepAlive(true);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		try {
			sock.connect(new InetSocketAddress("localhost",8888));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input = sysin.readLine();
			BufferedWriter write = new BufferedWriter( new OutputStreamWriter(sock.getOutputStream()));
			write.write(input);
			write.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
