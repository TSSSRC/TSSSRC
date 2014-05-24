package src.scoringsystem.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import src.scoringsystem.network.packages.*;

public class Server {
	public static int port = 2406;
	public static String ip = "";
	public static String ipAddress;
	public static InetSocketAddress insa;
	public static ServerSocket server;
	public static ArrayList<Socket> socketList = new ArrayList<Socket>();
	public static HashMap<Socket, Integer> socketState = new HashMap<Socket, Integer>();
	public static ArrayList<Integer> clientStateList = new ArrayList<Integer>();
	public static ArrayList<DataPackage> dataPackageList = new ArrayList<DataPackage>();

	public static boolean checkLogin(String username) {
		return true;
	}

	public static void disconnectClient(int index) {
		try {
			socketState.remove(index);
			clientStateList.remove(index);
			dataPackageList.remove(index);
			socketList.remove(index);

		} catch (Exception ex) {
		}
	}

	private static Runnable accept = new Runnable() {

		@Override
		public void run() {
			new Thread(send).start();
			new Thread(receive).start();
			while (true) {
				try {
					String username;
					
					DataPackageType type;		
					Socket socket = server.accept();				
					ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
					type =(DataPackageType) ois.readObject();
					
					ois = new ObjectInputStream(socket.getInputStream());
					username = (String) ois.readObject();
					// String str = (String) ois.readObject();//Use whenever
					// need to get input;

					boolean accepted = true;
					for (int i = 0; i < dataPackageList.size(); i++) {
						if (((String) dataPackageList.get(i).getBaseDataObject("username")).toLowerCase().equals(username.toLowerCase())) {
							// If the user is already connected
							accepted = false;
							break;
						}

					}
					ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
					if (checkLogin(username)) {// checks in database if the
												// login was succesful
						if (accepted) {

							oos.writeObject("Welcome to this server...");
							System.out.println(username + " - "	+ socket.getInetAddress().getHostAddress()+ " - "+ socket.getInetAddress().getHostName());
							// socketState.put(socket, 0);							clientStateList.add(0);
							
							System.out.println(type);
							switch (type) {// JudgeScore, Login, JudgeReset,
											// DiffJudge, TOFJudge,
							case JudgeScore:
								dataPackageList.add(new JudgeScorePackage());
								break;
							case Login:
								dataPackageList.add(new LoginPackage());
								break;
							case JudgeReset:
								dataPackageList.add(new JudgeResetPackage());
								break;
							case DiffJudge:
								dataPackageList.add(new DiffJudgePackage());
								break;
							case TOFJudge:
								dataPackageList.add(new TOFJudgePackage());
								break;
							default:
								System.out.println("NUll type");
								break;
							}
							socketList.add(socket);							
						} else {
							oos.writeObject("This user is already connected");
						}
					} else {
						oos.writeObject("Unsucsesful login");
						accepted = false;
					}
				} catch (Exception ex) {
					System.out.println("Error1: " + ex);
				}
			}

		}
	};
	private static Runnable send = new Runnable() {

		@Override
		public void run() {
			ObjectOutputStream oos;

			while (true) {
				for (int i = 0; i < socketList.size(); i++) {
					try {
						oos = new ObjectOutputStream(socketList.get(i)
								.getOutputStream());
						// int clientState = socketState.get(i).intValue();
						int clientState = clientStateList.get(i);

						oos.writeObject(clientState);// Sends the state of the
														// client

						oos = new ObjectOutputStream(socketList.get(i)
								.getOutputStream());
						oos.writeObject(dataPackageList);

						if (clientState == 1) {// Kicked
							disconnectClient(i);
							i--;
							System.out.println("Kicked User");

						} else if (clientState == 2) {// Server Disconnected
							disconnectClient(i);
							i--;
							System.out.println("Server disconnected");
						}

					} catch (Exception ex) {
						System.out.println("Error2:" + ex);
					}
				}

			}

		}
	};
	private static Runnable receive = new Runnable() {

		@Override
		public void run() {
			String oldData = "";
			ObjectInputStream ois;
			while (true) {
				for (int i = 0; i < socketList.size(); i++) {

					try {
						ois = new ObjectInputStream(socketList.get(i).getInputStream());
						int receiveState = (Integer) ois.readObject();
						ois = new ObjectInputStream(socketList.get(i).getInputStream());
						DataPackageType dpType = (DataPackageType) ois.readObject();
						
						ois = new ObjectInputStream(socketList.get(i).getInputStream());
						DataPackage dp=null;
						switch(dpType){//JudgeScore, Login, JudgeReset, DiffJudge, TOFJudge,
						case JudgeScore:dp = (JudgeScorePackage) ois.readObject();
							break;
						case Login:dp =  (LoginPackage) ois.readObject();
							break;
						case JudgeReset:dp =  (JudgeResetPackage) ois.readObject();
							break;
						case DiffJudge: dp = (DiffJudgePackage) ois.readObject();
							break; 
						case TOFJudge: dp = (TOFJudgePackage) ois.readObject();
							break;
						}						

						if (!(oldData.equals(dp.getID() + ""))) {
							oldData = "" + dp.getID();
						}
						dataPackageList.set(i, dp);
						if (receiveState == 1) {// client dc'ed by user
							disconnectClient(i);
							i--;
						}

					} catch (Exception ex) {// Client dc'ed
						disconnectClient(i);
						i--;

					}

				}
			}

		}
	};

	public static void main(String[] args) {
		try {
			ip = InetAddress.getLocalHost().getHostAddress() + ":" + port;
			ipAddress = InetAddress.getLocalHost().getHostAddress();
			server = new ServerSocket(port, 0, InetAddress.getLocalHost());
			new Thread(accept).start();
			System.out.println("Server Running:");
			 System.out.println(InetAddress.getLocalHost());
		} catch (IOException ex) {
			System.out.println("System could not find ip address: " + ex);
		}
	}
}
