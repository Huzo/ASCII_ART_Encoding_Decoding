package assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author huzeyfekiran
 */
public class RunLengthDecoder {
    String inputFileName;
    String outputFileName;
    File inputFile;
    
    public RunLengthDecoder(String inp, String out){
        inputFileName= inp;
        outputFileName = out;
        
        inputFile = new File(inputFileName);
    }
    
    public void decode() throws FileNotFoundException{
        Scanner scanner = new Scanner(inputFile);
        PrintStream printStream = new PrintStream(outputFileName);
        
        while(scanner.hasNextLine()){
            String aLine = scanner.nextLine();
            
            int length = aLine.length();
            char c;
            String charList = "";
            String negativeNumber = "";
            String positiveNumber = "";
            boolean isNegativeNumber = false;
            
            for(int i = 0; i < length; i++){
                if(aLine.charAt(i) == ' '){ // ignore and skip if there is space
                    continue;
                }
                if(aLine.charAt(i) == '-' && aLine.charAt(i + 1) >= '0' && aLine.charAt(i + 1) <= '9'){
                    //check if it indeed is a negative number
                    while(i + 1 < length && aLine.charAt(i + 1) >= '0' && aLine.charAt(i + 1) <= '9'){
                        isNegativeNumber = true;
                        i++;
                        negativeNumber = negativeNumber + aLine.charAt(i);
                    }
                    
                    //if it is a negative number, print spaces
                    if(isNegativeNumber){
                        int n = Integer.parseInt(negativeNumber);
                        for(int j = 0; j < n; j++){
                            printStream.print(' ');
                        }
                        negativeNumber = "";
                        
                        i++;
                    }
                }
                if(i < length && aLine.charAt(i) >= '0' && aLine.charAt(i) <= '9'){
                    positiveNumber = "" + aLine.charAt(i);
                    isNegativeNumber = false;
                    
                    //combine the positive number if it has more than one digit.
                    while(i + 1 < length && aLine.charAt(i + 1) >= '0' && aLine.charAt(i + 1) <= '9'){
                        i++;
                        positiveNumber = positiveNumber + aLine.charAt(i);
                    }
                    i++;
                    
                    //get the character(s) after the positive number
                    c = aLine.charAt(i);
                    charList = charList + c;
                    while(i + 1 < length && aLine.charAt(i + 1) != ' '){
                        i++;
                        charList = charList + aLine.charAt(i);
                    }
                    
                    //omit space if necessary
                    charList = charList.replace(" ", "");
                    
                    //print the characters
                    int n = Integer.parseInt(positiveNumber);
                    for(int j = 0; j < n; j++){
                        printStream.print(charList);
                    }
                    i++;
                    charList = "";
                    positiveNumber = "";
                }
                
            }
            printStream.println(""); // new line
            
        }
    }
}
