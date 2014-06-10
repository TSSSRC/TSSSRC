package src.scoringsystem.database;


import java.util.ArrayList;
import java.sql.*;

public class MySQL {
	private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "nbuser";
    private static String url = "jdbc:mysql://localhost:3306/";
    private static Connection connection;
    private static PreparedStatement statement;
    private static  ResultSet resultSet;
    private static String query;
    public static void main(String[] args){
    	if(updateValue("tblUsers", "Name,Surname","1", "Fred,Smith")){
    		
    	}
    }
    
	
	static boolean insertValue(String table, String column, String value){
		url+=table;	
		try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection Successful");
            String columns[] = column.split(",");
    		String values[] =  value.split(",");
    		int size = columns.length;
    		query = "INSERT INTO " + table + " (`";
    		for(int i=0; i<size-1; i++){    			
    			query += columns[i] + "`, `"; 
    		} 
    		query += columns[size-1] + "`) VALUES (`";
    		
    		for(int i=0; i<size-1; i++){    			
    			query += values[i] + "`, `"; 
    		}
    		query += values[size-1] + "`);";
    		//System.out.println(query);
    		statement = connection.prepareCall(query);
            statement.executeUpdate();
            statement.close();
    		
    		return true;
        } catch (SQLException ex) {
            System.out.println("Class not found: " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Connection error: " + ex);
        }
		return false;  
	}
	static boolean updateValue(String table, String column, String index, String newValue){
		url+=table;	
		try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection Successful");
            String columns[] = column.split(",");    		
    		String newValues[] =  newValue.split(",");
    		query = "UPDATE " + table + " SET ";
			for(int j=0; j<newValues.length-1; j++){
				query += columns[j] + " = " + newValues[j]+", ";
				
			}
			query += columns[newValues.length-1] + " = " + newValues[newValues.length-1];
			query+= " WHERE Index = " + index;
    		
    		System.out.println(query);
    		statement = connection.prepareCall(query);
            statement.executeUpdate();
            statement.close();
    		return true;
        } catch (SQLException ex) {
            System.out.println("Class not found: " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Connection error: " + ex);
        }
		return false;
	}
	static boolean deleteValue(String table, String index){
		url+=table;	
		try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection Successful");
            query = "DELETE FROM "+ table+ " WHERE Index = " +index;
            statement = connection.prepareCall(query);
            statement.executeUpdate();
            statement.close();   		  		
    		
    		return true;
        } catch (SQLException ex) {
            System.out.println("Class not found: " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Connection error: " + ex);
        }
		return false;
	}
	static ArrayList<Object> retrieve(String table, String q)throws SQLException{
		url+=table;	
		ArrayList<Object> temp = null;
		try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection Successful");
            query = q;
            statement = connection.prepareCall(query);
            resultSet = statement.executeQuery();
            
            temp = new ArrayList<Object>();
            int i = 0;
            try {
                while ( resultSet.next()) {
                    temp.add(resultSet.getString(i));
                    i++;
                }
            } catch (SQLException ex) {
                System.out.println("Error: ex");            }
               		
    		
    		return temp;
        } catch (SQLException ex) {
            System.out.println("Class not found: " + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Connection error: " + ex);
        }
		return null;
	}

}
