import java.util.Arrays;

public class GameOfLife
{
    static int borderSize = 10;
    int speed = 500;
    boolean isRunning = false;
    int[][] arr;

    public GameOfLife(int size)
    {
        arr = new int[size + borderSize * 2][size + borderSize * 2];
    }

    public void clear()
    {
        for(int[] i : arr)
        {
            Arrays.fill(i, 0);
        }
    }

    public void rebuild()
    {
        int [][] old_arr = new int[arr.length][arr.length];

        for(int i=0; i<arr.length; i++)
        {
            System.arraycopy(arr[i], 0, old_arr[i], 0, arr[i].length);
        }

        for(int i=0; i<arr.length; i++)
        {
            for(int j=0; j<arr[i].length; j++)
            {
                int life_counter = 0;

                if(i + 1 < arr.length && j + 1 < arr.length && old_arr[i + 1][j + 1] == 1) life_counter++;
                if(i - 1 > 0 && j - 1 > 0 && old_arr[i - 1][j - 1] == 1) life_counter++;

                if(i + 1 < arr.length && j - 1 > 0 && old_arr[i + 1][j - 1] == 1) life_counter++;
                if(i - 1 > 0 && j + 1 < arr.length && old_arr[i - 1][j + 1] == 1) life_counter++;

                if(i + 1 < arr.length  && old_arr[i + 1][j] == 1) life_counter++;
                if(i - 1 > 0  && old_arr[i - 1][j] == 1) life_counter++;

                if(j + 1 < arr.length && old_arr[i][j + 1] == 1) life_counter++;
                if(j - 1 > 0 && old_arr[i][j - 1] == 1) life_counter++;

                if(life_counter == 3)
                {
                    arr[i][j] = 1;
                }
                else if(life_counter != 2)
                {
                    arr[i][j] = 0;
                }
            }
        }
    }
}
