package part2;
/*
 * Written by: Jun Ma
 * Core Java Test
 * Core Java Problem#2
 * 02/17/2016
 * Provide by Mercury Systems, Inc.
 * Class Name: SolutionPrblm2 
 */

public class SolutionPrblm2 
{
    public static void main(String args[])
    {    	
    	// Input money change value and the array of coins value
    	double value = 0.67;
    	double[] coins = {0.01, 0.10, 0.25};
    	
    	SolutionPrblm2.getDetails(coins, value);
    }

    // Method to get the minimum number of change ant the details of number for quarter, dime and cent
    public static void getDetails(double[] coinsD, double n)
    {
    	String[] money = new String[3];
    	money[0] = "Cent:   ";
    	money[1] = "Dime:   ";
    	money[2] = "Quarter:";
    	int total = 0;

    	// Change the input value from double to integer
    	// plus a very small number to avoid unexpected events when type casting from double to integer
    	int m = (int)(n * 100 + 0.001);	
    	
		int[] table = new int[m + 1];
		int[] result = new int[coinsD.length];
		int[] coinsI = new int[coinsD.length];
		
		// Array of minimum coins number for each value before the input change value
		int[][] counts = new int[coinsI.length][m + 1];
		table[0] = 0;	
		
		for(int k = 0; k < coinsD.length; k++)
		{
			coinsI[k] = (int) (coinsD[k] * 100);
		}
		for(int i = 0; i < counts.length; i++)
		{
			counts[i][0] = 0;
		}
		// min(table[i]) = min(table[i-coins[j]]+1)
		for(int i = 1; i < table.length; i++)
		{
			int temp = Integer.MAX_VALUE;
			int temp1 = 0;
			int temp2 = 0;
			for(int j = 0; j<coinsI.length;j++)
			{
				if(i - coinsI[j] >= 0 && table[i - coinsI[j]] + 1 <= temp)
				{
					temp = table[i-coinsI[j]] + 1;
					temp1 = j;
					temp2 = coinsI[j];
				}
				table[i] = temp;
			}
			
			for(int l = 0; l < counts.length; l++)
			{
				if(l == temp1)
					counts[l][i] = counts[l][i - temp2] + 1;
				else
					counts[l][i] = counts[l][i - temp2];
			}
		}
		// Save values for number of quarters, dimes and cents
		for(int i = 0; i < result.length; i ++)
		{
			result[i] = counts[i][m];
			total += result[i];
		}
		// Print the results
		System.out.println("Change: " + n);
		for(int i=0; i < counts.length; i++)
    	{
    		System.out.println(money[i] + " " + result[i]);
    	}
		System.out.println("Minimum total coins: " + total);
	}
}