import java.net.*;
import java.io.*;
import java.net.ServerSocket;

public class Server {
	static private boolean _serverOnline; /*Boolean for server online*/
	static ServerSocket _serverSocket;
	static short _portNumber;
	static public void setServerOnline(boolean newStatus){
		_serverOnline = newStatus;
		
	}
	static public boolean getServerStatus(){
		return _serverOnline;
		
	}
	public static void main(String[] args) throws IOException{
		setServerOnline(false); /*Start off offline*/
		_portNumber = 6665; /*Default port number*/
		try{
		_serverSocket = new ServerSocket(_portNumber, 0);
		}catch (FileNotFoundException e){
			System.err.println("FileNotFoundException: " + e.getMessage());
		}catch (IOException e){
			System.err.println("Caught IOException: " + e.getMessage());
		}
		setServerOnline(true);
		
		ConnectThread connectThread = new ConnectThread(_serverSocket);
		connectThread.run();
		while(true){}
	}
}
