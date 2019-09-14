
package com.snippets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.nio.file.Files.newBufferedReader;

public class Snippets {
    public enum SimpleSingleton {
        INSTANCE;
        public void doSomething() {
        }
    }

    public static void main(String[] args) {
        // Converting int to String and Strings to int
        String a = String.valueOf(2);   //integer to numeric string
        int i = Integer.parseInt(a); //numeric string to an int
        System.out.println("Converting int to String and Strings to int ==> String: " + a + " Integer: " + i);
        System.out.println("----------------------------------------------------");

        // Converting String to date
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date date = null;
            date = format.parse("01.02.2019");
            System.out.println("Converting String to date in Java ==> Date parsed: " + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("----------------------------------------------------");

        // Converting Java util.Date to sql.Date
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println("Converting Java util.Date to sql.Date ==> " + sqlDate);
        System.out.println("----------------------------------------------------");

        // Datetime
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current DateTime = " + now);
        System.out.println(now.format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss")));
        System.out.println("----------------------------------------------------");

        // Sending the HTTP request and fetching data
        try {
            URL url = new URL("https://www.google.com/");
            BufferedReader bufferedReader = newBufferedReader((Path) new InputStreamReader(url.openStream()));
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                System.out.println(line);
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
        System.out.println("----------------------------------------------------");

        // Reading file
        try {
            InputStream in = Snippets.class.getClassLoader().getResourceAsStream("example.txt");
            if (in != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            // e.printStackTrace();
        }
        System.out.println("----------------------------------------------------");

        // Reading a file using Streams
        try {
            URL url = Snippets.class.getClassLoader().getResource("example.txt");
            if (url != null) {
                Path path = Paths.get(url.getPath());
                Files.lines(path).forEach(System.out::println);
            }
        } catch (IOException | InvalidPathException e) {
            // e.printStackTrace();
        }
        System.out.println("----------------------------------------------------");

        // Counting words, lines and characters using Streams
        try {
            URL url = Snippets.class.getClassLoader().getResource("example.txt");
            if (url != null) {
                Path path = Paths.get(url.getPath());
                Long words = Files.lines(path)
                        .flatMap(str -> Stream.of(str.split("[ ,.]"))) // spit by space, comma, dot
                        .filter(s -> s.length() > 0).count();

                Long lines = Files.lines(path).count();

                final Map<Character, Integer> countCharacters = Files.lines(path).
                        flatMap(line -> IntStream.range(0, line.length()).mapToObj(line::charAt)).
                        filter(Character::isLetter).
                        map(Character::toLowerCase).
                        collect(TreeMap::new, (m, c) -> m.merge(c, 1, Integer::sum), Map::putAll);

                Long characters = countCharacters.values().stream().mapToLong(t -> t).sum();
            }
        } catch (IOException | InvalidPathException e) {
            //e.printStackTrace();
        }
        System.out.println("----------------------------------------------------");

        // JSON builder
        Gson gson = new GsonBuilder().create();
        gson.toJson("Hello", System.out);
        gson.toJson(123, System.out);
        System.out.println("----------------------------------------------------");

        // Collections sorting
        // Initial data
        List<String> list = new ArrayList<String>();
        list.add("AAAA");
        list.add("CCCC");
        list.add("ABTGT");
        list.add("ACBGT");

        System.out.println("Initial List :");
        list.forEach(System.out::println);

        Collections.sort(list, Comparator.naturalOrder());
        System.out.println("\nStandard Sorted by Name :");
        list.forEach(System.out::println);
        System.out.println("----------------------------------------------------");

        // Streams
        String[] arr = new String[]{"P", "A", "V"};
        Stream<String> stream = Arrays.stream(arr);
        stream.forEach(System.out::println);

        stream = Stream.of("V", "A", "P");
        stream.forEach(System.out::println);

        List<String> newList = Arrays.asList("Pavan", "Opencodez");
        stream = newList.stream();

        newList.parallelStream().forEach(System.out::println);
        // Iterating
        newList.stream().filter(element -> element.contains("P"));
        // Matching
        boolean isValid = newList.stream().anyMatch(element -> element.contains("P"));
        // Collecting
        List<String> results = newList.stream().map(element -> element.toUpperCase()).collect(Collectors.toList());
        System.out.println("----------------------------------------------------");

        // Iterating over a list
        List<String> list2 = new ArrayList<String>();
        list2.add("AAAA");
        list2.add("CCCC");
        list2.add("ABTGT");
        list2.add("ACBGT");

        System.out.println("Printing List with forEach");
        list2.forEach(System.out::println);

        System.out.println("\nPrinting List after Filtering");
        list2.stream()
                .filter(str -> str.contains("AA"))
                .forEach(System.out::println);
        System.out.println("----------------------------------------------------");

        // Enum Singleton
        SimpleSingleton.INSTANCE.doSomething();
        System.out.println("----------------------------------------------------");

        // Read from console
        Scanner sc = new Scanner(System.in);
        System.out.println("Type something in the console:");
        System.out.println(sc.nextLine());
        System.out.println("----------------------------------------------------");

        // Is prime number
        int number = 3;
        System.out.println(number+ " is prime: " +(number > 1 && IntStream.range(2, number).noneMatch(j -> number % j == 0)));
        System.out.println("----------------------------------------------------");

        //  Find first element greater than 3 and double it
        List<Integer> values = Arrays.asList(1, 2, 3, 5, 4, 6, 7, 8, 9, 10);
        values.stream().filter(ii -> ii > 3).filter(ii -> ii % 2 == 0).map(ii -> ii * 2).findFirst().get();
        System.out.println("----------------------------------------------------");

        // Converts the given string to an array of words.
       String[] arrWords = Arrays.stream("ACBGT".split("[^a-zA-Z-]+"))
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
        System.out.println(Arrays.toString(arrWords));

    }

}

