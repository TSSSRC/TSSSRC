package src.scoringsystem.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import src.scoringsystem.network.packages.*;

public class Client
{
	private int runnableState = -1;
	private String username;
	private String ip;
	private int port;
	private Socket socket;
	private DataPackage ourData;
	private DataPackageType type;
	private ArrayList<DataPackage> recievedData = new ArrayList<DataPackage>();

	public Client(String ipA, int p, String name, DataPackageType t){
		type = t;
		switch(type){//JudgeScore, Login, JudgeReset, DiffJudge, TOFJudge,
		case JudgeScore: ourData = new JudgeScorePackage();
			break;
		case Login: ourData = new LoginPackage();
			break;
		case JudgeReset: ourData = new JudgeResetPackage();
			break;
		case DiffJudge: ourData = new DiffJudgePackage();
			break; 
		case TOFJudge: ourData = new TOFJudgePackage();
			break;
		}
		try
		{						
			ip = ipA;
			port = p;			
			socket = new Socket(ip, port);			
			username = name;
			
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(type);
			
			oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(username);
			
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			String response = (String) ois.readObject();
			
			if (response.equals("This user is already connected"))
			{
				runnableState = 0;
			}
			else if (response.equals("Unsucsesful login"))
			{
				runnableState = 1;
			}
			else
			{
				runnableState = 2;
				
			}
			
			new Thread(send).start();
			new Thread(receive).start();
			
		}catch (Exception ex){
			System.out.println("Error: " + ex);
			runnableState = 3;
		}
	}
	public int state = 0;
	/**
     *
     */
	public boolean connected = true;
	Runnable send = new Runnable()
	{
		
		@Override
		public void run()
		{
			ObjectOutputStream oos;
			while (connected)
			{
				if (socket != null)
				{
					try
					{
						
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(state);
						oos = new ObjectOutputStream(socket.getOutputStream());
						oos.writeObject(type);
						if(ourData.getBaseDataObject("Username")==null){
							ourData.addBaseObject("Username", username);
						}
						
						
						if (ourData != null)
						{
							oos = new ObjectOutputStream(socket.getOutputStream());							
							oos.writeObject(ourData);
						}
						else
						{
							break;
						}
						if (state == 1)
						{
							connected = false;
							socket = null;
							System.exit(0);
							
						}
					}
					catch (Exception ex)
					{
						System.out.println("Error: " + ex);
					}
				}
				else
				{
					break;
				}
			}
		}
	};
	Runnable receive = new Runnable()
	{
		
		@SuppressWarnings({ "unchecked", "null" })
        @Override
		public void run()
		{
			
			ObjectInputStream ois;
			
			while (connected)
			{
				try
				{
					ois = new ObjectInputStream(socket.getInputStream());
					int receiveState = (Integer) ois.readObject();
					
					if (receiveState == 1)
					{
						connected = false;
						socket = null;
						System.exit(0);
						
					}
					else if (receiveState == 2)
					{
						connected = false;
						socket = null;
						System.exit(0);
					}
					
					ois = new ObjectInputStream(socket.getInputStream());
					Object obIn = ois.readObject();
					ArrayList<DataPackage> listData = null;
					if (obIn instanceof ArrayList<?>)
					{
						listData = (ArrayList<DataPackage>) obIn;
					}else{
					
						for (int i = 0; i < listData.size(); i++)
						{
							DataPackage dp = listData.get(i);
							if (listData.size() != recievedData.size())
							{
								if (listData.size() > recievedData.size())
								{
									recievedData.add(dp);
								}
								if (listData.size() < recievedData.size())
								{
									recievedData.remove(0);
								}
							}
							else
							{
								recievedData.set(i, dp);
							}
						}
					}
				}
				catch (Exception ex)
				{
				}
			}
		}
	};
	
	
	
	public int getRunnableState()
	{
		return runnableState;
	}
	public void close()
	{
		try
		{
			socket.close();
		}
		catch (IOException ex)
		{
			System.out.println("Couldnt close socket: "+ex);
		}
	}
	public ArrayList<DataPackage> getData()
	{	
		return recievedData;
	}
}
