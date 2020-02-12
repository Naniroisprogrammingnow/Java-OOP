package Froggy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        String line = scanner.nextLine();
        Lake lake = new Lake();
        while (!line.equals("END")){
            List<Integer> list = Arrays.stream(line.split(",\\s+"))
                    .map(Integer::parseInt).collect(Collectors.toList());
             lake.add(list);
            line = scanner.nextLine();
        }

        List<String> res = new ArrayList<>();

        for (int hop : lake) {
            res.add(String.valueOf(hop));
        }
        if(res.size()!=0){
            System.out.println(String.join(", ", res));
        }

    }
}
