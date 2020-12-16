/*
File: ParseFile.java
Developer: Suhail Singh
Email: suhails@bu.edu
Last Edited: Wednesday, December 16, 2020

Description: Our program uses .txt files to store data, similar to the .txt files used to store data in the Legends Project. ParseFile is used to parse and edit these text files in order to load the data into the Bank on startup, as well as keep the Bank's data intact after closing and reopening the program. 
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ParseFile {

    /**
     *  Parses input file by splitting every row in the file into a list of strings.
     * 
     * @param fileLocation: Path to file
     */
    public static List<List<String>> parseRows(String fileLocation)
    {
        ArrayList<List<String>> parsedObjects = new ArrayList<List<String>>();
        File file = new File(fileLocation);
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found " + fileLocation);
            return new ArrayList<List<String>>();
        }
        int count = 0;
        while (fileScanner.hasNextLine())
        {
            if (count == 0)
            {
                fileScanner.nextLine();
                count++;
                continue;
            }
            String str[] = fileScanner.nextLine().split("\\s+");
            List<String> objectInfo = new ArrayList<String>();
            objectInfo = Arrays.asList(str);
            parsedObjects.add(objectInfo);
        }
        fileScanner.close();
        return parsedObjects;
    }

    /**
     * Adds new line with data into the input file, as well as simultaneously creates a new file if the file does not exist.
     * 
     * @param fileLocation: Path to file
     * @param line: Line to add to the file
     */
    public static void addLine(String fileLocation, String line)
    {
        File file = new File(fileLocation);
        try 
        {
            if (file.createNewFile())
            {
                Files.write(Paths.get(fileLocation),(""+System.lineSeparator()).getBytes());
                Files.write(Paths.get(fileLocation), (line+System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            }
            else
            {
                Files.write(Paths.get(fileLocation), (line+System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    /**
     * Deletes a row in the file based on a unique substring in that row. 
     * 
     * @param fileLocation: Path to file
     * @param keyString: Substring that identifies which row should be deleted. 
     */
    public static void deleteLine(String fileLocation, String keyString)
    {
        List<List<String>> parsedRows = parseRows(fileLocation);
        int count = 0;
        int rowToDelete = -1;
        for (List<String> row: parsedRows)
        {
            for (String s: row)
            {
                if (s.contains(keyString))
                {
                    rowToDelete = count;
                }
            }
            count++; 
        }
        if (rowToDelete == -1)
        {
            System.out.println("Couldn't fine line." + keyString);
        }
        else
        {
            File file = new File(fileLocation);
            File tempFile = new File("myTempFile.txt");
            BufferedReader reader;
            BufferedWriter writer;
            try {
                reader = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }
            try {
                writer = new BufferedWriter(new FileWriter(tempFile));
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            String lineToRemove = convertListofStringsToString(parsedRows.get(rowToDelete));
            String currentLine;

            try {
                while ((currentLine = reader.readLine()) != null) {
                    // trim newline when comparing with lineToRemove
                    String trimmedLine = currentLine.trim().replaceAll("\\s", "");
                    if (trimmedLine.equals(lineToRemove))
                        continue;
                    writer.write(currentLine + System.getProperty("line.separator")); 
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Path source = tempFile.toPath();
            try {
                Files.delete(source.resolveSibling(file.toPath()));
                Files.move(source, source.resolveSibling(file.toPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("UPDATE: " + "\t" + file);
        }
    }

    // Helper to convert a List of Strings to a String
    private static String convertListofStringsToString(List<String> arr)
    {  
        StringBuilder sb = new StringBuilder();
        for (String s: arr)
        {
            sb.append(s);
        }
        return sb.toString();
    }
}
