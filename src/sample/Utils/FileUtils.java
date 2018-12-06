package sample.Utils;

import java.io.*;
import java.util.ArrayList;

public class FileUtils {
    public static ArrayList<String> readLines(File file){
        FileReader fr;
        BufferedReader br;

        ArrayList<String> lines = new ArrayList<>();

        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String current;

            while ((current = br.readLine()) != null) {
                lines.add(current);
            }
        } catch (FileNotFoundException e) {
            System.out.println(
                    "Error - could not find log file. Aborting program."
            );
            System.exit(1);
        } catch (IOException i) {
            System.out.println(
                    "Error reading log file. Aborting program."
            );
            System.exit(1);
        }

        return lines;
    }

    public static void writeLines(String fileName, ArrayList<String> lines) {
        File f;
        FileWriter fr;
        BufferedWriter br;

        try {
            f = new File(fileName);
            fr = new FileWriter(f);
            br = new BufferedWriter(fr);

            for(String line: lines){
                br.write(line);
                br.write("\r\n");
            }
        } catch (IOException i) {
            System.out.println(
                    "Error reading log file. Aborting program."
            );
            System.exit(1);
        }
    }
}
