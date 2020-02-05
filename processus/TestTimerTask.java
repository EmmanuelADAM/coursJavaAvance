import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;
import java.time.format.DateTimeFormatter;

/** petite tache qui affiche la date et son nom
@author E.ADAM
*/
class MaTachePlanifiee extends TimerTask
{
	String name;
	MaTachePlanifiee(String name){this.name = name;}
	public void run() {
	    System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"))  + " -> Execution de tache " + name);   
	}
	
}

/** classe qui lance au bout de 1 seconde deux instances de tâche MaTachePlanifiee,<br>
l'une relancee toutes les 2 sec., l'autre toutes les 3 sec.<br>
stoppe tout au bout de 15 secondes
@author E.ADAM
*/
public class TestTimerTask {

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new MaTachePlanifiee("t1"), 1000, 2000);
		timer.schedule(new MaTachePlanifiee("t2"), 1000, 3000);
		try { Thread.sleep(15000); } catch (InterruptedException e) {}
		timer.cancel();
	    System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")) + " -> FIN !!");   
	}

}
