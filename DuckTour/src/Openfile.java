import java.io.*;
import java.util.*;

public class Openfile {
	
	public static ArrayList<String> read (String filename){
		File iFile = new File(filename);
		String filedata;
		ArrayList<String> filetxt = new ArrayList<String>();
		int i=0;
		try {
		BufferedReader iFileReader = new BufferedReader(new FileReader(iFile)); 
		
		while ((filedata = iFileReader.readLine()) != null){ 
				filetxt.add(filedata);
				i++;
			}
		} catch (Exception e) {
				System.out.println("File not found");
			}
		return filetxt;
	}
	
	public static void writeAL (String filename, ArrayList<String> filedata){
		File oFile = new File(filename);
		try {
			PrintWriter writer = new PrintWriter(oFile, "UTF-8");
			Iterator i = filedata.iterator();
			while (i.hasNext()) {
				writer.println(i.next());
		}
		writer.close();
		} catch (Exception e) {
				System.out.println("File not found");
			}
		System.out.println(filename + " has been updated successfully");
	}
	
	public static void writeString (String filename, String data) {
		File file = new File(filename);
		
		try{
			PrintWriter out = new PrintWriter(new FileWriter(file, true));
		    out.append(data+"\n");
		    out.close();
		    } catch(IOException e){
		    	System.out.println("File not found");
		    }
	}
	
}
