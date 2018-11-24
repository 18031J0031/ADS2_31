import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class Edge {
int source;
int destination;
double weight;
Edge(int source,int destination,double weight)
{
	this.source=source;
	this.destination=destination;
	this.weight=weight;
}
int V()
{
	return source;
}
int E()
{
	return destination;
}
double W()
{
	return weight;
}
}

class Graph
{
int v;
ArrayList<Edge>[] adj;
Graph(int v)
{
	this.v=v;
	adj=(ArrayList<Edge>[]) new ArrayList[v];
     for(int i=0;i<v;i++)
     {
			adj[i]=new ArrayList<Edge>();

     }
}
void addEdge(int s,int d,double w)
{
	Edge e=new Edge(s,d,w);
	Edge e1=new Edge(d,s,w);
	int v=e.V();
	adj[v].add(e);
	adj[d].add(e1);
}
Iterable<Edge> adj(int v)
{
	return adj[v];
}
int V()
{
	return v;
}
}
class Dijkistra
{
	Hashtable<Integer,Double> ht = new Hashtable<Integer,Double>();	
	Edge[] edgeto;
	double distto[];
	Dijkistra(Graph g,int s)
	{
		distto=new double[g.V()];
		edgeto=new Edge[g.V()];
		for(int i=0;i<g.V();i++)
		{
			distto[i]=Double.POSITIVE_INFINITY;
		}
		distto[s]=0.0;
		ht.put(s, distto[s]);
		while(!ht.isEmpty())
		{
		double max=1000;
		int keyo=0;
		Set<Integer> st=ht.keySet();
	   Iterator i=st.iterator();
	   while(i.hasNext())
	   {
             int key=(int) i.next();
		    Double value=ht.get(key);
		   if(max>value)
		   {
			   max=value;
			   keyo=key;
		   }
	   }
	  ht.remove(keyo);
	  for(Edge e:g.adj(keyo))
	  {
		  relax(e);
	  }
		}
	}
	void relax(Edge e)
	{
	int v=e.V();
	//System.out.println(v+"-");
	int w=e.E();
	//System.out.println(w+"  ");
	if(distto[w]>distto[v]+e.W())
	{
		distto[w]=distto[v]+e.W();
		//System.out.println(distto[w]);
		edgeto[w]=e;
		 if(!ht.containsKey(w))
         {
         ht.put(w, distto[w]);
         }
         else
         {
         	ht.replace(w, distto[w]);
         }
		 }
	
	}
	void display(int i)
	{
		//System.out.println(distto[i]);
		if(distto[i]==Double.POSITIVE_INFINITY)
		System.out.println("No Path Found.");
		else
			System.out.println(distto[i]);
		
	}
}