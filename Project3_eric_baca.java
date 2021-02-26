//for file I/O class
import java.io.*;
//scanner class
import java.util.Scanner;

public class Project3_eric_baca
{
   public static void main (String[] args) throws IOException
   {
      File file = new File("Computers.txt");//open file
      Scanner inputFile = new Scanner(file);//read file
      
      //declarations
      boolean firstcomputer = true;
      double highscore = 0.0;
      double lowscore = 0.0;
      double gpu = 0.0, cpu = 0.0;
      double performancescore = 0.0;
      int resolution = 0, cores = 0;
      double multiplier = 0.0;
      
      //strings
      String chgqrt = "Computer Hardware Graphics Quality Recommendation Tool";
      String monitorRESO = "";
      String graphicsQ = "";
      
      while(inputFile.hasNext())
      {
         gpu = inputFile.nextDouble();//collect gpu megahertz
         
         cpu = inputFile.nextDouble();//collect cpu megahertz
         
         cores = inputFile.nextInt();//collect number of cores
         
         resolution = inputFile.nextInt();//collect resolution selection
         
         if(inputFile.hasNext())
         {
            inputFile.nextLine();
         }
         
         //display title once
         if (firstcomputer)
         {
            displayTitle (chgqrt);
         }
         
         monitorRESO = getResolutionString (resolution);
         
         multiplier = getMultiplierValue (resolution);
         
         performancescore = calculatePerformanceScore (gpu, cpu, cores, multiplier);
            
         graphicsQ = getRecommendedQuality (performancescore);
         
         displayInformation( gpu, cpu, cores, monitorRESO, performancescore, graphicsQ);
         
         //check if first computer
         if (firstcomputer)
         {
            lowscore = performancescore;
            highscore = performancescore;
            firstcomputer = false; //set false so this step is skipped from now on
         }
         
        else if(performancescore > highscore) //check for new highscore
         {
            highscore = performancescore;
         }
         else //check for new lowscore
         {
            if (performancescore < lowscore)
               lowscore = performancescore;
         }

      }
      
         //output high and low scores
         System.out.printf("\nThe highest performance score was: %.3f", + highscore);
         System.out.printf("\nThe lowest performance score was: %.3f", + lowscore);
         
    //close file
    inputFile.close();
    }
      //method to display title
      public static void displayTitle (String chgqrt)
      {
         System.out.print(chgqrt + "\n");
      }
      //method to get string resolution
      public static String getResolutionString(int resolution)
      {
         String monitorRESO = "";
         
         switch(resolution)
         {
            case 1:
               monitorRESO = "1280 x 720";
               break;
            case 2:
               monitorRESO = "1920 x 1080";
               break;
            case 3:
               monitorRESO = "2560 x 1440";
               break;
            case 4:
               monitorRESO = "3840 x 2160";
               break;
         }
      return monitorRESO;
      }
      //method to get multiplier value
      public static double getMultiplierValue(int resolution)
      {
         final double MULTIPLIER1 = 1, MULTIPLIER2 = 0.75, MULTIPLIER3 = 0.55, MULTIPLIER4 = 0.35;
         double multiplier = 0.0;
         
         switch(resolution)
         {
            case 1:
               multiplier = MULTIPLIER1;
               break;
            case 2:
               multiplier = MULTIPLIER2;
               break;
            case 3:
               multiplier = MULTIPLIER3;
               break;
            case 4:
               multiplier = MULTIPLIER4;
               break;
         }
      return multiplier;
      }
      //method to calculate performance score
      public static double calculatePerformanceScore(double gpu, double cpu, int cores, double multiplier)
      {
         double performancescore;
         
         //calculate performance score
         performancescore = ((5 * gpu) + (cores * cpu)) * multiplier;
         
      return performancescore;
      }
      //method to get recommended graphics quality
      public static String getRecommendedQuality(double performancescore)
      {
      
         String graphicsQ = "";
         
         //to display proper graphics quality recommendation
         if (performancescore > 17000.0)
         {
            graphicsQ = "Ultra";
         }
         else if (performancescore <= 17000.0 && performancescore > 15000.0)
         {
            graphicsQ = "High";
         }
         else if (performancescore <= 15000.0 && performancescore > 13000.0)
         {
            graphicsQ = "Medium";
         }
         else if (performancescore <= 13000.0 && performancescore > 11000.0)
         {
            graphicsQ = "Low";
         }
         else
         {
            graphicsQ = "Unable to Play";
         }
         
      return graphicsQ;
      }
      //method to display each computer recommendation
      public static void displayInformation(double gpu, double cpu, int cores, String monitorRESO, double performancescore, String graphicsQ)
      {
         System.out.println("\nGPU Clock Speed: " + gpu + " MHz");
         System.out.println("CPU Clock Speed: " + cpu + " MHz");
         System.out.println("Number of Cores: " + cores);
         System.out.println("Monitor Resolution: " + monitorRESO);
         System.out.printf("Performance Score: %.3f", + performancescore);
         System.out.println("\nRecommended Graphics Quality: " + graphicsQ);
      }
} 