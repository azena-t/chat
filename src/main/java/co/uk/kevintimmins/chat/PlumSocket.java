package co.uk.kevintimmins.chat;

import java.io.*;
import java.net.*;

public class PlumSocket {
	
	Socket plum;
	PrintWriter out;
	BufferedReader in;
	String input;
	
	public PlumSocket(Socket plum){
		this.plum = plum;
	}
	
	public void init() throws IOException{
		out = new PrintWriter(plum.getOutputStream());
		in = new BufferedReader(new InputStreamReader(plum.getInputStream()));		
	}
	
	public void post(String message) throws IOException{
		message = message + "\n";
		out.write(message);
		out.flush();
	}
	
	public boolean isConnected(){
		return !plum.isClosed();
	}
	
	public boolean ready() throws IOException{
		return in.ready();
	}
	
	public String read() throws IOException{
		if(ready()){
			input = in.readLine();
			if(input != null)
				return input;
		}
		return null;
	}
	
	public void leave() throws IOException{
		out.close();
		in.close();
		plum.close();
	}
}
