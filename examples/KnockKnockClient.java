import java.net.*;
import java.io.*;

public class KnockKnockClient {

	public static void main(String[] args) throws UnknownHostException {
		if(args.length != 1) {
			System.err.println("Usage: java EchoClient <port number>");
			System.exit(1);
		}
		
		String hostName = InetAddress.getLocalHost().getHostName();
		int portNumber = Integer.parseInt(args[0]);
		
		try(
			Socket kkSocket = new Socket(hostName, portNumber);
			PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
		){
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			String fromServer;
			String fromUser;
			
			while((fromServer = in.readLine()) != null) {
				System.out.println("Server: " + fromServer);
				if(fromServer.equals("Bye.")) break;
				
				fromUser = stdIn.readLine();
				if(fromUser != null) {
					System.out.println("Client: " + fromUser);
					out.println(fromUser);
				}
			}
		}catch(UnknownHostException e) {
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		}catch(IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + hostName);
			System.exit(1);
		} // end try-catch

	} // end main()
} // end KnockKnockClient Class
