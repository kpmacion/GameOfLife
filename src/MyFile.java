import javax.swing.*;
import java.io.*;
import java.util.Arrays;

public class MyFile
{
    public static void saveToFile(int[][] arr)
    {
        String filePath = "";

        JFileChooser fileChooser = new JFileChooser();
        if(fileChooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
        {
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
        }

        try(FileWriter fileWriter = new FileWriter(filePath))
        {
            for (int[] i: arr)
            {
                fileWriter.write(Arrays.toString(i));
                fileWriter.write("\n");
            }
        }
        catch (IOException ignored) { }
    }

    public static int[][] loadFromFile(int[][] oldArr)
    {
        String filePath = "";
        int [][] newArr = new int[oldArr.length][oldArr.length];

        JFileChooser fileChooser = new JFileChooser();
        if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
        {
            filePath = fileChooser.getSelectedFile().getAbsolutePath();
        }

       try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath)))
       {
           String line;
           int i = 0;

           while ((line = bufferedReader.readLine()) != null)
           {
               int j = 0;
               line = line.replace("[", "");
               line = line.replace("]", "");
               for (String s : line.split(", "))
               {
                   newArr[i][j] = Integer.parseInt(s);
                   j++;
               }
               i++;
           }
       }
       catch (IOException ignored) { }

       return newArr;
    }
}
