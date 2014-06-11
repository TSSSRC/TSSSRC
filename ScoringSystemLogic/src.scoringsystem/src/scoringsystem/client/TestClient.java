package src.scoringsystem.client;

import src.scoringsystem.network.packages.DataPackageType;

public class TestClient {
	public static void main (String [] args){
		@SuppressWarnings("unused")
        Client node = new Client("192.168.7.16", 2406, "Fabio", DataPackageType.Login);
		//System.out.println("Recieved data: "+node.getData().get(0).getBaseDataObject("Username"));
	}

}
