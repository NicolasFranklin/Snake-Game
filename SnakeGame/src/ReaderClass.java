import java.io.*;
import java.util.*;

public class ReaderClass {
	
	
	//Cannot be a main here
    public static void main(String[] args) throws NumberFormatException, IOException {
    	
    	//System Reader
    	BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
    	
    	//Initialize
    	int choice;
    	File file = new File("ReadMe");
    	
    	
    	
    	
    	
    	//Inf loop where you choose what to do, and loop back after until 0 is pressed
		do {
			
	    	System.out.print("Press 1 to read and 2 to write, 0 to exit");
	    	choice = Integer.parseInt(br.readLine()); //Reading the line
	    	
	    	System.out.println("you chose "+choice);
	    	
	    	if(choice==1)
	    		read(file);
	    	
	    	else if(choice==2)
	    		write(br,file); //Pass 'br' to allow the 'write' method to read input from user (and write that input to the file)
	    	
	    	else if(choice==0)
	    		System.exit(0); //No streams need to be closed here.... Only br is open
	    	
	    	else
	    		System.out.println("try again");
	    	
	    	
		}while(true); //Inf loop, exit at choice==0

    }
    
    
    
    
    //READ METHOD - Does not return anything
    //I made it private so that the reader scanner will delete (trash) after the method is done
    //If it stayed around as an object this might cause problems when trying to read again?
    public static int[] read(File file)
    {
    	//LinkedList<Integer> total = new LinkedList<Integer>();
    	int[] total = new int[50];
        int i=0;
    	try {

            //Define the scanner to read the file
            Scanner reader = new Scanner(file);
            
            //While there is text in the file, print the text

            for(i = 0; reader.hasNextLine(); i++) {
                int line = reader.nextInt();
                total[i]=line;
            }
            
            
            //System.out.println("\n\n---------END OF FILE---------\n\n\n");
            reader.close(); //closes the scanners when its done reading
            
            

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    	
    	int[] newTotal = new int[i];
    	for(int j = 0; total[j]>0; j++)
    		newTotal[j] = total[j];
    		
    	return newTotal;
    }
    
    
    
    
    
    //WRITE METHOD
    //Takes in the BufferedReader which allows reading of the console and the File to read
    private static void write(BufferedReader input, File file)
    {

        try {
	        
	        //Define the writer
	        //If it is not true, it will erase the entire file every time the method is run
	        BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
	        out.write("Write to text");
	        out.newLine();
	        
	        //Allowing the user to write a custom message to the file
	        System.out.print("\n\nWrite to the file: ");
	        String line = input.readLine();
	        out.write(line);
	        
	        //Close the writer
	        out.close();
	
        }
        
	    catch (Exception ex) {
	        ex.printStackTrace();
	    }
    	
    }
    
    //STATIC WRITE METHOD
    //Takes in the BufferedReader which allows reading of the console and the File to read
    public static void writeStat(String input, File file)
    {

        try {
	        
	        //Define the writer
	        //If it is not true, it will erase the entire file every time the method is run
	        BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
	        
	        //Just writing the pre-written string
	        System.out.print("\n\nWriting to the file..");
	        out.write(input);
	        out.newLine();
	        
	        //Close the writer
	        out.close();
	
        }
        
	    catch (Exception ex) {
	        ex.printStackTrace();
	    }
    	
    }
    
    
    
    
    

}
