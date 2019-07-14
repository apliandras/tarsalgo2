package tarsalgo;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;




public class Tarsalgo {


	public static void main(String[] args) {
	ArrayList<String> door = new ArrayList<String>(); //ki-be 	
	ArrayList<Integer> timeh=new ArrayList<Integer>();//ora
	ArrayList<Integer> timem=new ArrayList<Integer>();//perc
	ArrayList<Integer> person=new ArrayList<Integer>();//azonosito
	

	try {
	BufferedReader br= new BufferedReader(new FileReader("ajto.txt"));
	String sor;
	
	
	while((sor=br.readLine())!=null) {
		String darabok[]=sor.split(" ");
		for(int i=0; i<=darabok.length;i++ ) {
		if(i==0)
			timeh.add(Integer.parseInt(darabok[i]));
		else if(i==1)
			timem.add(Integer.parseInt(darabok[i]));
		else if(i==2)
			person.add(Integer.parseInt(darabok[i]));
		else if(i==3)
			door.add(darabok[i]);
		
		}
	}
	br.close();}
	catch(IOException ioe){
		
	}

aufGabe(2);
	
	for(int i=0;i<=door.size();i++) {
		String be="be";
		if(be.equals(door.get(i)))
				System.out.println("Az elsö belepö: "+person.get(i));
		break;
	};
	
	
	int x=door.size();
	int z=0;
	int y=0;
	while(z!=x) {
		String ki="ki";
		ki.equals(door.get(z));
		if(ki.equals(door.get(z))) {
			y=z;
			
			z++;}
		else
			z++;
	}
	System.out.println("Az utolso kilepö: "+person.get(y));
aufGabe(3);
	

	Map<Integer, Integer> m =new TreeMap();
	
	for(int i:person) {
		if(m.containsKey(i)) {
			int val=m.get(i);
			m.put(i, val+1);
		}
		else {
			m.put(i, 1);
		}
	}
	
	TreeSet<Integer> setPerson=new TreeSet<Integer>(person);
	for(int i:setPerson) {
		System.out.println(i+" "+m.get(i));
	}
	
	aufGabe(4);
	for(int i:setPerson) {
		if(m.get(i)%2==0)
			m.remove(i);
	}
	
	System.out.println("A vegen a tarsalgoban voltak: "+m.keySet()); // nem tudom a megfelelö kiirast produkalni
	aufGabe(5);
	ArrayList<Integer> sameTime= new ArrayList<Integer>(); 
	int counter=0;
	for(String i:door) {
		String be="be";
		if(i.equals(be)) {
			counter++;
			sameTime.add(counter);
			}
		else {
			counter--;
			sameTime.add(counter);
		}
	}
	
	sameTime.sort(null);
	
	int counter1=0;//putto modon visszaallitottam a sameTime elemeit
	for(String i:door) {
		String be="be";
		if(i.equals(be)) {
			counter1++;
			sameTime.add(counter1);
			}
		else {
			counter1--;
			sameTime.add(counter1);
		}
	}
	
	int maxInsd=sameTime.get(sameTime.size()-1);
	
	for(int i=0;i<=sameTime.size();i++) {
		
		if(sameTime.get(i).equals(maxInsd)) {
			System.out.println("Például "+timeh.get(i)+":"+timem.get(i)+" -kor voltak a legtöbben a társalgóban. ");
			break;}
	}
	
	aufGabe(6);
	Scanner sc=new Scanner(System.in);
	System.out.print("Adja meg a személy azonosítóját! ");
	int id=sc.nextInt();
	
	aufGabe(7);
	// problema a kiirassal
	for(int i=0;i<=person.size();i++) {
		if(person.get(i).equals(id) && door.get(i).equals("be")) {
			System.out.print(timeh.get(i)+":");
			System.out.print(timem.get(i)+"-");}
		else if(person.get(i).equals(id)&& door.get(i).equals("ki")) {
			System.out.print(timeh.get(i)+":");
			System.out.println(timem.get(i));
			}
			
		
	}
		
	}
	public static void aufGabe(int x) {
		System.out.println(" ");
		System.out.println( x+". feladat");
		System.out.println(" ");
	}
	}
	
	
	
		
	
	
	
	


