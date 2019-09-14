###### Converting int to String and Strings to int
```java
String a = String.valueOf(2);   //integer to numeric string
int i = Integer.parseInt(a); //numeric string to an int
System.out.println("Converting int to String and Strings to int ==> String: " + a + " Integer: " + i);
```

###### Converting String to date
```java
try {
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    Date date = null;
    date = format.parse("01.02.2019");
    System.out.println("Converting String to date in Java ==> Date parsed: " + date);
} catch (ParseException e) {
    e.printStackTrace();
}
```

###### Converting Java util.Date to sql.Date
```java
java.util.Date utilDate = new java.util.Date();
java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
```

###### Datetime
```java
LocalDateTime now = LocalDateTime.now();
System.out.println("Current DateTime = " + now);
System.out.println(now.format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm:ss")));
```

######  Sending the HTTP request and fetching data
```java
 try {
    URL url = new URL("https://www.google.com/");
    BufferedReader bufferedReader = newBufferedReader((Path) new InputStreamReader(url.openStream()));
    for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
        System.out.println(line);
    }
} catch (IOException e) {
    //e.printStackTrace();
}
```

###### Reading file
```java
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
```

###### Reading a file using Streams
```java
 try {
    URL url = Snippets.class.getClassLoader().getResource("example.txt");
    if (url != null) {
        Path path = Paths.get(url.getPath());
        Files.lines(path).forEach(System.out::println);
    }
} catch (IOException | InvalidPathException e) {
    // e.printStackTrace();
}
```

###### Counting words, lines and characters using Streams
```java
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
```

###### JSON builder
```java
Gson gson = new GsonBuilder().create();
gson.toJson("Hello", System.out);
gson.toJson(123, System.out);
```

###### Collections sorting
###### Initial data
```java
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
```

###### Streams
```java
String[] arr = new String[]{"P", "A", "V"};
Stream<String> stream = Arrays.stream(arr);
stream.forEach(System.out::println);

stream = Stream.of("V", "A", "P");
stream.forEach(System.out::println);

List<String> newList = Arrays.asList("Pavan", "Opencodez");
stream = newList.stream();

newList.parallelStream().forEach(System.out::println);
```

###### Iterating Streams
```java
newList.stream().filter(element -> element.contains("P"));
```

###### Matching Streams
```java
boolean isValid = newList.stream().anyMatch(element -> element.contains("P"));
```

###### Collecting Streams
```java
List<String> results = newList.stream().map(element -> element.toUpperCase()).collect(Collectors.toList());
```

###### Iterating over a list
```java
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
```

###### Number is prime
```java
int number = 3;
System.out.println(number+ " is prime: " +(number > 1 && IntStream.range(2, number).noneMatch(j -> number % j == 0)));
```

###### Singleton
```java
SimpleSingleton.INSTANCE.doSomething();
```

###### Read from console (System.in)
```java
Scanner sc = new Scanner(System.in);
System.out.println("Type something in the console:");
System.out.println(sc.nextLine());
```

###### Find first element greater than 3 and double it
```java
List<Integer> values = Arrays.asList(1, 2, 3, 5, 4, 6, 7, 8, 9, 10);
values.stream().filter(ii -> ii > 3).filter(ii -> ii % 2 == 0).map(ii -> ii * 2).findFirst().get();
```

###### Converts the given string to an array of words.
```java
String[] arrWords = Arrays.stream("ACBGT".split("[^a-zA-Z-]+"))
.filter(s -> !s.isEmpty())
.toArray(String[]::new);
```