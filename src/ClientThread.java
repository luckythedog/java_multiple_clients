import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class ClientThread extends Thread{
	private Socket _clientSocket;
	private boolean _clientConnected;
	public ClientThread(Socket clientSocket) {
		_clientSocket = clientSocket;
		_clientConnected = true;
	}
	public boolean getClientConnected(){
		return _clientConnected;
	}
	public void setToOffline(){
		_clientConnected = false;
	}
	public void run(){
		while(_clientConnected){ /*Read from here*/
			BufferedReader bufferReaderSocket = null;
			String readInput;
			try {
				bufferReaderSocket = new BufferedReader(new InputStreamReader(_clientSocket.getInputStream()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*TRY AND CATCH, WHY?!!?!?!?!?!*/
			try {
				if((readInput = bufferReaderSocket.readLine()) != null){
					System.out.println(_clientSocket + " says " + readInput);
				}else{
					setToOffline();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
