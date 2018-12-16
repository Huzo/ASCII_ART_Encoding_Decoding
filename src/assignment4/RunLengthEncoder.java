package assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author huzeyfekiran
 */
public class RunLengthEncoder {
    String inputFileName;
    String outputFileName;
    File inputFile;
    
    public RunLengthEncoder(String inp, String out){
        inputFileName = inp;
        outputFileName = out;
        
        inputFile = new File(inputFileName);
    }
    
    public void encode() throws FileNotFoundException{
        Scanner scanner = new Scanner(inputFile);
        PrintStream printStream = new PrintStream(outputFileName);
        
        while(scanner.hasNext()){
            String aLine = scanner.nextLine();
            
            int length = aLine.length();
            int count = 0;
            char c;
            boolean isFirst = false; // for preventing extra white space at the end.
            
            
            for(int i = 0; i < length; i++){
                if(i == 0){
                    isFirst = true;
                } else{
                    isFirst = false;
                }
                c = aLine.charAt(i); //first new char
                count = 1;
                while( i + 1 <  length && aLine.charAt(i + 1) == c){ 
                    //loop while new char is encountered
                    count++; 
                    i++;
                }
                if(!isFirst){
                    if(c == ' '){ //space should be negative integer
                        printStream.print(" " + "-"  + count);
                    } else{ 
                        printStream.print(" " + count + " " + c);
                    }
                }
                else{
                    if(c == ' '){ //space should be negative integer
                        printStream.print("-"  + count);
                    } else{ 
                        printStream.print("" + count + " " + c);
                    }
                }
                
            }
            printStream.println(""); //new line
        }
    }
    
    
}
