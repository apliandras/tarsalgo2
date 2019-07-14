package tarsalgo;


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Tarsalgo {
    private static ArrayList<String> door = new ArrayList<String>(); //ki-be
    private static ArrayList<Integer> timeH = new ArrayList<Integer>();//ora
    private static ArrayList<Integer> timeM = new ArrayList<Integer>();//perc
    private static ArrayList<Integer> person = new ArrayList<Integer>();//azonosito

    public static void main(String[] args) throws IOException, ParseException {
        aufGabe(1);
        readSourceFile();
        aufGabe(2);
        findFirstAndLastPersonToEnter();
        aufGabe(3);
        countOfEntriesPerPerson();
        aufGabe(5);
        task5();
        aufGabe(6);
        int id = readId();
        aufGabe(7);
        task7(id);
        aufGabe(8);
        task8(id);
    }

    private static String complementTime(Integer time) {
        int length = String.valueOf(time).length();
        if (length == 1) {
            return "0" + time;
        }
        return time.toString();
    }

    private static int readId() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Adja meg a személy azonosítóját! ");
        return sc.nextInt();
    }

    public static void aufGabe(int x) {
        System.out.println(" ");
        System.out.println(x + ". feladat");
        System.out.println(" ");
    }

    private static void readSourceFile() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("ajto.txt"));
        String sor;


        while ((sor = br.readLine()) != null) {
            String darabok[] = sor.split(" ");
            for (int i = 0; i <= darabok.length; i++) {
                if (i == 0)
                    timeH.add(Integer.parseInt(darabok[i]));
                else if (i == 1)
                    timeM.add(Integer.parseInt(darabok[i]));
                else if (i == 2)
                    person.add(Integer.parseInt(darabok[i]));
                else if (i == 3)
                    door.add(darabok[i]);

            }
        }
        br.close();
    }


    private static void findFirstAndLastPersonToEnter() {
        for (int i = 0; i < door.size(); i++) {
            String be = "be";
            if (be.equals(door.get(i)))
                System.out.println("Az elsö belepö: " + person.get(i));
            break;
        }


/*        int x = door.size();
        int z = 0;
        int y = 0;
        while (z != x) {
            String ki = "ki";
            ki.equals(door.get(z));
            if (ki.equals(door.get(z))) {
                y = z;

                z++;
            } else
                z++;
        }
        System.out.println("Az utolso kilepö: " + person.get(y));*/

        System.out.println("Reverse loop");
        String ki = "ki";
        for (int i = door.size() - 1; i >= 0; i--) {
            if (door.get(i).equals(ki)) {
                System.out.println("Az utolso kilepö: " + person.get(i));
                break;
            }
        }


        //other solution
        int doorIndex = 0;
        for (int i = 0; i < door.size(); i++) {
            if (ki.equals(door.get(i)))
                doorIndex = i;
        }
        System.out.println("plain loop");
        System.out.println("Az utolso kilepö: " + person.get(doorIndex));
    }

    private static void countOfEntriesPerPerson() throws IOException {
        Map<Integer, Integer> m = new TreeMap();

        for (int i : person) {
            if (m.containsKey(i)) {
                int val = m.get(i);
                m.put(i, val + 1);
            } else {
                m.put(i, 1);
            }
        }

        TreeSet<Integer> setPerson = new TreeSet<Integer>(person);

        //Write to file
        BufferedWriter writer = new BufferedWriter(new FileWriter("athaladas.txt"));
        for (int i : setPerson) {
            writer.write(i + " " + m.get(i));
            writer.write("\n");
        }
        writer.close();
        System.out.println("Write to file athaladas.txt finished.");

        for (int i : setPerson) {
            if (m.get(i) % 2 == 0)
                m.remove(i);
        }

        aufGabe(4);
        System.out.println("A vegen a tarsalgoban voltak: " + m.keySet()); // nem tudom a megfelelö kiirast produkalni


        System.out.print("A vegen a tarsalgoban voltak: ");
        m.keySet().forEach(person -> System.out.print(person + " "));
    }

    private static void task5() {
       /* ArrayList<Integer> sameTime = new ArrayList<Integer>();
        int counter = 0;
        for (String i : door) {
            String be = "be";
            if (i.equals(be)) {
                counter++;
                sameTime.add(counter);
            } else {
                counter--;
                sameTime.add(counter);
            }
        }

        sameTime.sort(null);

        int counter1 = 0;
        for (String i : door) {
            String be = "be";
            if (i.equals(be)) {
                counter1++;
                sameTime.add(counter1);
            } else {
                counter1--;
                sameTime.add(counter1);
            }
        }

        int maxInsd = sameTime.get(sameTime.size() - 1);

        for (int i = 0; i <= sameTime.size(); i++) {

            if (sameTime.get(i).equals(maxInsd)) {
                System.out.println("Például " + timeH.get(i) + ":" + timeM.get(i) + " -kor voltak a legtöbben a társalgóban. ");
                break;
            }
        }*/


        int indexOfMaxPersonInside = 0;
        int maxPersonInside = 0;
        int counter2 = 0;
        String be = "be";
        for (int i = 0; i < door.size(); i++) {
            if (door.get(i).equals(be)) {
                counter2++;
            } else {
                counter2--;
            }
            if (maxPersonInside < counter2) {
                maxPersonInside = counter2;
                indexOfMaxPersonInside = i;
            }
        }
        System.out.println("Legtöbben voltak bent " + timeH.get(indexOfMaxPersonInside) + ":" + timeM.get(indexOfMaxPersonInside) + " perckor. Összesen " + maxPersonInside + " személy");
    }

    private static void task7(int id) {
        for (int i = 0; i < person.size(); i++) {
            if (person.get(i).equals(id) && door.get(i).equals("be")) {
                System.out.print(complementTime(timeH.get(i)) + ":");
                System.out.print(complementTime(timeM.get(i)) + "-");
            } else if (person.get(i).equals(id) && door.get(i).equals("ki")) {
                System.out.print(complementTime(timeH.get(i)) + ":");
                System.out.println(complementTime(timeM.get(i)));
            }
        }

    }

    private static void task8(int id) throws ParseException {

        long durationInMills = 0;
        boolean kilepett = false;
        Date startDate = null;

        for (int i = 0; i < person.size(); i++) {

            if (person.get(i).equals(id) && door.get(i).equals("be")) {
                String start = timeH.get(i) + ":" + complementTime(timeM.get(i));
                startDate = parseDate(start);
                kilepett = false;
            } else if (person.get(i).equals(id) && door.get(i).equals("ki")) {
                String end = timeH.get(i) + ":" + complementTime(timeM.get(i));
                Date endDate = parseDate(end);
                durationInMills += endDate.getTime() - startDate.getTime();
                kilepett = true;
            }
        }

        String allapot;
        if (kilepett) {
            allapot = "nem volt a társalgóban.";
        } else {
            allapot = "a társalgóban volt.";
        }

        System.out.println(id + " személy " + durationInMills / 1000 / 60 + " percet töltött a társalgóban. A megfigyelés végén " + allapot);
    }

    private static Date parseDate(String raw) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        return df.parse(raw);
    }


}

	
		
	
	
	
	


