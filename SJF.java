import java.util.Scanner;

public class SJF {
    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        System.out.println ("enter no of process:");
        int numOfProcesses = input.nextInt();
        int completionTime[] = new int[numOfProcesses];
        int turnAroundTime[] = new int[numOfProcesses];
        int waitingTime[] = new int[numOfProcesses];
        int complete[] = new int[numOfProcesses];
        int processID[] = new int[numOfProcesses];
        int arrivalTime[] = new int[numOfProcesses];
        int burstTime[] = new int[numOfProcesses];
        int priority[] = new int[numOfProcesses];
        

        int startTime=0, total=0;
        float AvgWaitingTime=0, AvgTurnAroundTime=0;

        //To get the arrival time
        for(int i=0;i<numOfProcesses;i++)    
        {
            System.out.println ("Process " + (i+1) + " arrival time:");
            arrivalTime[i] = input.nextInt();
            System.out.println ("Process " + (i+1) + " brust time:");
            burstTime[i] = input.nextInt();
            complete[i] = 0;
            processID[i] = i+1;
            
        }

        for (int i=0; i<numOfProcesses; i++)
        {
        	priority[i]=burstTime[i];
        }
        
        while(true)
        {
            int counter=numOfProcesses, minimum = 1000000;

            if (total == numOfProcesses)
                break;
            
            //To check which process has arrived and has least burst time and is not completed
            for (int i=0; i<numOfProcesses; i++)
            {
                if ( (complete[i] == 0) && (arrivalTime[i] <= startTime)  && (priority[i]<minimum))
                {   
                	minimum=burstTime[i];
                	counter=i;
                	
                }
                if ( (complete[i] == 0) && (arrivalTime[i] <= startTime))
                {
                	priority[i]=priority[i]-1;
                }
                
            }
            //If we don't have any process at starting time 0 we increase start time
            if (counter==numOfProcesses)
            	startTime++;
            else
            {
            	//Completion time of current process is startingTime+burstTime
            	completionTime[counter]=startTime+burstTime[counter];
            	
            	//Then increase start time to be equal burstTime
            	startTime+=burstTime[counter];
            	
            	//TunAround time is completionTime - arrivalTime
                turnAroundTime[counter]=completionTime[counter]-arrivalTime[counter];
                
                //Waiting time is turnAround time  - burst time
                waitingTime[counter]=turnAroundTime[counter]-burstTime[counter];
                
                //Then mark the process to be done
                complete[counter]=1;
                
                //To know the id of the process
                processID[total] = counter + 1;
                
                total++;
            }
        }

        System.out.println("ProgramID   Arrival   Burst   Completion  Turn   Waiting");
        for(int i=0;i<numOfProcesses;i++)
        {
        	//Add all waiting times to calculate average
        	AvgWaitingTime+= waitingTime[i];
        		
        	//Add all turn around time to calculate average
        	AvgTurnAroundTime+= turnAroundTime[i];
        	
            //System.out.println(processID[i]+"           "
        	System.out.println(i+1+"           "
            +arrivalTime[i]+"         "
            +burstTime[i]+"       "
            +completionTime[i]
            +"           "+turnAroundTime[i]
            +"      "+waitingTime[i]);
        }
        System.out.println ("Average waiting time is "+ (float)(AvgWaitingTime/numOfProcesses));
        System.out.println ("Average turn around time is "+ (float)(AvgTurnAroundTime/numOfProcesses));
        
        for(int i=0;i<numOfProcesses;i++)
        {
            System.out.print(processID[i] + " ");
        }
        System.out.println(" ");
        for(int i=0;i<numOfProcesses;i++)
        {
            System.out.print(priority[i]+ " ");
        }
    }
} 
