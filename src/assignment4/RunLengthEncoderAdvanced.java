package assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author huzeyfekiran
 */
public class RunLengthEncoderAdvanced {
    String inputFileName;
    String outputFileName;
    File inputFile;
    
    public RunLengthEncoderAdvanced(String inp, String out){
        inputFileName = inp;
        outputFileName = out;
        
        inputFile = new File(inputFileName);
    }
    
    public void encode() throws FileNotFoundException{
        // this advanced encryption combines all the characters that are printed only once
        // ex. 1 * 1 = 1 + would be 1 *=+ instead
        Scanner scanner = new Scanner(inputFile);
        PrintStream printStream = new PrintStream(outputFileName);
        
        while(scanner.hasNext()){
            String aLine = scanner.nextLine();
            String dummyLine = ""; //first do a standard encrpytion and copy it to a dummy string
            
            int length = aLine.length();
            int count = 0;
            char c;
            
            //same algorithm as RunLengthEncoder, but printed on a dummy string
            for(int i = 0; i < length; i++){
                c = aLine.charAt(i); //first new char
                count = 1;
                while( i + 1 <  length && aLine.charAt(i + 1) == c){ 
                    //loop while new char is encountered
                    count++; 
                    i++;
                }
                if(c == ' '){ //space should be negative integer
                    dummyLine = dummyLine + "-"  + count + " ";
                } else{ 
                    dummyLine = dummyLine + count + " " + c + " ";
                }
                
            }
            dummyLine = dummyLine.trim(); // trim extra space
           
            // now we apply advanced encryption to the dummy string and copy the result to the file
            for(int j = 0; j < dummyLine.length(); j++){
                String charList = ""; // concatination of characters which occur only once
                boolean found = false; //a boolean switch to control the flow of the algorithm
                                
                if(dummyLine.charAt(j) == '1' && (j == 0 || dummyLine.charAt(j-1) == ' ') && (j + 1 < dummyLine.length() && dummyLine.charAt(j+1) == ' ')){
                // if we encounter a '1' which acts as a coefficient
                    
                    while(j+5 < dummyLine.length() && dummyLine.charAt(j+4) == dummyLine.charAt(j) && dummyLine.charAt(j+5) == ' ' && dummyLine.charAt(j+3) == ' '){
                        // if the coefficient of the next (number character) couple is also 1
                        found = true;
                        charList = charList + dummyLine.charAt(j+2); // concatinate the character with others
                        j = j + 4;
                    }
                    if(found){
                        if(j + 2 < dummyLine.length()){
                            j = j + 2;
                        }     
                        charList = charList + dummyLine.charAt(j);
                        charList = charList.replace(" ", ""); // prevent errors
                        printStream.print("1 " + charList);
                        j++;
                        found = false;
                    }
                }
                
                // else, just print the encyption as it is. 
                if(j < dummyLine.length()){
                    printStream.print(dummyLine.charAt(j));
                }
                
                
            }    
            String newDummyLine = "";
            printStream.println(""); //new line
        }
    }
}
