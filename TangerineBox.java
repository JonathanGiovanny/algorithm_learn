import java.util.*;

/**
You have a box of tangerines of x * y where one of the tangerines could be rotten.
One rotten tangerine will rotten it's neighbours, not on diagnoal tho.
Count the amount of days until the entire box gets rotten.
*/
public class TangerineBox {
    public static void main(String[] args) throws Exception {
        // Your code here!
        int[][] arr = { {0,0,0,0,0},
                        {0,0,0,0,0},
                        {0,0,0,1,0},
                        {0,0,0,0,0},
                        {0,0,0,0,0}};
        System.out.println(days(arr));
    }
    
    private static int days(int[][] arr) {
        int days = 1;
        boolean noneRotten = false;
        boolean allRotten = false;
        int[][] memo = new int[arr.length][arr[0].length];
        int[][] memo2 = new int[arr.length][arr[0].length];
        boolean first = true;
        boolean dual = true;

        while((!noneRotten && !allRotten) && days < 10) {
            allRotten = true;
            noneRotten = true;
            for(int x = 0; x < arr.length; x++) {
                for(int y = 0; y < arr[0].length; y++) {
                    if (first && arr[x][y] == 1) {
                        memo[x][y] = 1;
                    }
                    if (dual && memo[x][y] == 1) {
                        noneRotten = false;
                        memo2[x][y] = 1;
                        if (x > 0) {
                            memo2[x-1][y] = 1;
                        }
                        if (y > 0) {
                            memo2[x][y-1] = 1;
                        }
                        if (x+1 < arr.length) {
                            memo2[x+1][y] = 1;
                        }
                        if (y+1 < arr[0].length) {
                            memo2[x][y+1] = 1;
                        }
                    } else if (!dual && memo2[x][y] == 1) {
                        noneRotten = false;
                        memo[x][y] = 1;
                        if (x > 0) {
                            memo[x-1][y] = 1;
                        }
                        if (y > 0) {
                            memo[x][y-1] = 1;
                        }
                        if (x+1 < arr.length) {
                            memo[x+1][y] = 1;
                        }
                        if (y+1 < arr[0].length) {
                            memo[x][y+1] = 1;
                        }
                    } else {
                        allRotten = false;
                    }
                }
            }
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    System.out.print(dual ? memo2[i][j] : memo[i][j]);
                }
                System.out.println();
            }
            System.out.println("-----" + days + "--------");
            first = false;
            dual = !dual;
            days++;
        }

        return days - 2;
    }
}
