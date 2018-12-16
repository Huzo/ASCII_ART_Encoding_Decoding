/**
 * CSCI1130 Assignment 1 Shaky Display
 *
 * I declare that the assignment here submitted is original
 * except for source material explicitly acknowledged,
 * and that the same or closely related material has not been
 * previously submitted for another course.
 * I also acknowledge that I am aware of University policy and
 * regulations on honesty in academic work, and of the disciplinary
 * guidelines and procedures applicable to breaches of such
 * policy and regulations, as contained in the website.
 *
 * University Guideline on Academic Honesty:
 *  http://www.cuhk.edu.hk/policy/academichonesty
 *Faculty of Engineering Guidelines to Academic Honesty:
 *  http://www.erg.cuhk.edu.hk/erg/AcademicHonesty
 *
 *
 * Student Name: Huzeyfe Kiran
 * Student ID: 1155104019
 * Date: 03/11/2018
 *
 */


package assignment4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author huzeyfekiran
 */
public class Assignment4 {

    
    public static void main(String[] args) throws Exception {
        String fileToCreate = askUserInput();
        File file_e = new File(fileToCreate + "_EFile.txt");
        file_e.createNewFile();
        File file_d = new File(fileToCreate + "_DFile.txt");
        file_d.createNewFile();
        File file_ae = new File(fileToCreate + "_AEFile.txt");
        file_ae.createNewFile();
        File file_ad = new File(fileToCreate + "_ADFile.txt");
        file_ad.createNewFile();
        
        RunLengthEncoder rle = new RunLengthEncoder(fileToCreate + ".txt", file_e.getName());
        rle.encode();
        
        RunLengthDecoder rld = new RunLengthDecoder(file_e.getName(), file_d.getName());
        rld.decode();
        
        RunLengthEncoderAdvanced rlea = new RunLengthEncoderAdvanced(fileToCreate + ".txt", file_ae.getName());
        rlea.encode();
        
        RunLengthDecoder rld2 = new RunLengthDecoder(file_ae.getName(), file_ad.getName());
        rld2.decode();
        
    }
    
    public static String askUserInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("The original ASCII art picture file: ");
        String userInput = scanner.next();
        return userInput;
    }
}
