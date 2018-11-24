import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1 {
public static void main(String[] args) throws NumberFormatException, IOException
{
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
   int v=Integer.parseInt(br.readLine());
   Graph g=new Graph(v);
   
   int e=Integer.parseInt(br.readLine());
   for(int i=0;i<e;i++)
   {
	   String x=br.readLine();
	   String[] z=x.split(" ");
	   g.addEdge(Integer.parseInt(z[0]), Integer.parseInt(z[1]), Double.parseDouble(z[2]));
   }
   switch(br.readLine())
   {
   case "Graph":
   {
	   for(int i=0;i<v;i++)
	   {
		   System.out.println(i+":");
	   for(Edge e1:g.adj(i) )
	   {
		   System.out.print(e1.source+"-"+e1.destination+" "+e1.weight+" ");
	     
	   }
	   System.out.println();
	   }
	 
   }
   case "DirectedPaths":
   {
	   System.out.println();
	   String k=br.readLine();
	  // System.out.println(k);
	   String[] h=k.split(" ");
	 //  System.out.println(h[0]+","+h[1]);
	   Dijkistra dj=new Dijkistra(g,Integer.parseInt(h[0]));
	  dj.display(Integer.parseInt(h[1]));
   }
   case "ViaPaths":
   {
	   String w=br.readLine();
	   String[] r=w.split(" ");
	   Dijkistra dj=new Dijkistra(g,Integer.parseInt(r[0]));
	   dj.display(Integer.parseInt(r[1]));
	   Dijkistra dj1=new Dijkistra(g,Integer.parseInt(r[1]));
	   dj1.display(Integer.parseInt(r[2]));
   }
   }
}
}
