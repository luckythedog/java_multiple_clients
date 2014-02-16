import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


public class ConnectThread extends Thread {
	private Vector<ClientThread> _clientThreads;
	private int _clientsConnectedCount;
	private ServerSocket _masterSocket;
	public ConnectThread(ServerSocket masterSocket){
		_clientsConnectedCount = 0;
		_clientThreads = new Vector<ClientThread>();
		_masterSocket = masterSocket;
	}
	public void addClient(Socket incomingClient){
		ClientThread newClientThread = new ClientThread(incomingClient);
		newClientThread.start();
		_clientThreads.add(newClientThread);
	}
	public void run(){
		while(Server.getServerStatus()){
			try {
				Socket newClient = _masterSocket.accept();
				System.out.println("New client was accepted!");
				addClient(newClient);

				/*Creates a new client and thread to handle RECV*/

			} catch (IOException e) {
				e.printStackTrace();
			}

			/*Handles removing of threads*/
			System.out.println(_clientThreads.size());
			for(int i=0; i<_clientThreads.size(); i++){
				if(!_clientThreads.get(i).getClientConnected()){
					System.out.println("Client has disconnected!");
					_clientThreads.remove(i);
				}
			}
		}
	}
}
