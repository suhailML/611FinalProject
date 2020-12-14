import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ParseFile {

    public static ArrayList<List<String>> parseRows(String fileLocation)
    {
        ArrayList<List<String>> parsedObjects = new ArrayList<List<String>>();
        File file = new File(fileLocation);
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
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

    public static void addLine(String fileLocation, String line)
    {
        File file = new File(fileLocation);
        try 
        {
            if (file.createNewFile())
            {
                Files.write(Paths.get(fileLocation),"".getBytes());
                Files.write(Paths.get(fileLocation), line.getBytes(), StandardOpenOption.APPEND);
            }
            else
            {
                Files.write(Paths.get(fileLocation), line.getBytes(), StandardOpenOption.APPEND);
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    public static void deleteLine(String fileLocation, String keyString)
    {
        ArrayList<List<String>> parsedRows = parseRows(fileLocation);
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
            System.out.println("Couldn't fine line.");
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
            System.out.println(lineToRemove);
            String currentLine;

            try {
                while ((currentLine = reader.readLine()) != null) {
                    // trim newline when comparing with lineToRemove
                    String trimmedLine = currentLine.trim();
                    if (trimmedLine.equals(lineToRemove))
                        continue;
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            boolean successful = tempFile.renameTo(file);
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
        }
    }

    private static String convertListofStringsToString(List<String> arr)
    {  
        StringBuilder sb = new StringBuilder();
        for (String s: arr)
        {
            sb.append(s);
        }
        return sb.toString();
    }

    // public static void removeLine(BufferedReader br , File f,  String Line) throws IOException{
    //     File temp = new File("temp.txt");
    //     BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
    //     String removeID = Line;
    //     String currentLine;
    //     while((currentLine = br.readLine()) != null){
    //         String trimmedLine = currentLine.trim();
    //         if(trimmedLine.equals(removeID)){
    //             currentLine = "";
    //         }
    //         bw.write(currentLine + System.getProperty("line.separator"));
    
    //     }
    //     bw.close();
    //     br.close();
    //     boolean delete = f.delete();
    //     boolean b = temp.renameTo(f);
    // }

    public static void main(String[] args) {
        // System.out.println(parseRows("./credentials/credentials.txt"));
        // for (int i = 0; i<5; i++)
        // {
        //     UUID uniqueKey = UUID.randomUUID();
        //     System.out.println(uniqueKey);
        // }

        // addLine("./credentials/customercredentials2.txt", "\n" + "boi2");

        deleteLine("../src/credentials/customercredentials.txt", "test");
        // File file = new File("../src/credentials/customercredentials.txt");
        // BufferedReader reader = new BufferedReader(new FileReader("../src/credentials/customercredentials.txt"));



        
    }
}
