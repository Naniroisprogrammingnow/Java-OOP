package EqualityLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        TreeSet<Person> treePeople = new TreeSet<>();
        HashSet<Person> hashPeople = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String[] tokens = bufferedReader.readLine().split("\\s+");
            Person person = new Person(tokens[0], Integer.parseInt(tokens[1]));
            treePeople.add(person);
            hashPeople.add(person);
        }
        System.out.println(treePeople.size());
        System.out.println(hashPeople.size());
    }
}
