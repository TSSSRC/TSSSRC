package src.scoringsystem.client;

import src.scoringsystem.network.packages.DataPackageType;

public class TestClient {
	public static void main (String [] args){
		Client node = new Client("192.168.1.110", 2406, "Fabio", DataPackageType.Login);
		//System.out.println("Recieved data: "+node.getData().get(0).getBaseDataObject("Username"));
	}

}
