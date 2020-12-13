import java.io.File;
import java.io.FileNotFoundException;
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

    public static void main(String[] args) {
        // System.out.println(parseRows("./credentials/credentials.txt"));
        // for (int i = 0; i<5; i++)
        // {
        //     UUID uniqueKey = UUID.randomUUID();
        //     System.out.println(uniqueKey);
        // }

        addLine("./credentials/customercredentials2.txt", "\n" + "boi2");
    }
}
