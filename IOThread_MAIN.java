import java.util.LinkedList;
import java.util.Iterator;
import java.util.Random;

public class IOThread_MAIN
{
	public static void main(String[] args) throws InterruptedException
	{
		int nodes_T	= 20 ;
		Random r1 = new Random();
		PCB	pcbRunning = null ;
		
		LinkedList<PCB> QReady	= new LinkedList<PCB>();
		
		for (int ii=0; ii<nodes_T; ii++)
		{
			if (((r1.nextInt(100)+1) % 5)==0)
			{
				pcbRunning	= new PCB() ;
				QReady.add(pcbRunning) ;
				
				Thread iop	= new Thread(new IOProcess(Integer.toString(ii)
						,pcbRunning
						,QReady
						));
				
				iop.start();
				
				System.out.printf("main: thread started %d %s %d %s\n"
						,ii
						,iop.getName()
						,iop.getId()
						,iop.getState()
						);
			}
		}
		
		System.out.printf("main: threads still running: %d\n"
				,Thread.activeCount()-1
				);
		
		for (PCB loopI : QReady)
			System.out.printf("main: %s\n"	,loopI.showPCB()) ;

		System.out.printf("\t***** done *****\n");
	}
}
