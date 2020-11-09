import javax.ws.rs.core.MultivaluedMap;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrequencyAnalyzer extends Frequency implements WordFrequencyAnalyzer {
    /*Global variables*/
    public static final String text = "/Users/hernandogarciamorales/Documents/yyy/test.txt";
    public static final String WORD = "";
    public static final int number = 0;
    public static Word word = new Word();


    public int calculateHighestFrequency(String text) throws FileNotFoundException {
        //Creating wordCountMap which holds words as keys and their occurrences as values
        int count = word.getFrequency();
        HashMap<String, Integer> wordCountMap = new HashMap<String, Integer>();
        BufferedReader reader = null;
        try {
            //Creating BufferedReader object
            reader = new BufferedReader(new FileReader(text));
            //Reading the first line into currentLine
            String currentLine =  word.getWord();
            currentLine = reader.readLine();
            while (currentLine != null) {
                //splitting the currentLine into words
                String[] words = currentLine.toLowerCase().split(" ");
                //Iterating each word
                for (String word : words) {
                    //if word is already present in wordCountMap, updating its count
                    if (wordCountMap.containsKey(word)) {
                        wordCountMap.put(word, wordCountMap.get(word) + 1);
                    }
                    //otherwise inserting the word as key and 1 as its value
                    else {
                        wordCountMap.put(word, 1);
                    }
                }
                //Reading next line into currentLine
                currentLine = reader.readLine();
            }
            //Getting the most repeated word and its occurrence
            String mostRepeatedWord = null;
            Set<Map.Entry<String, Integer>> entrySet = wordCountMap.entrySet();
            for (Map.Entry<String, Integer> entry : entrySet) {
                if (entry.getValue() > count) {
                    mostRepeatedWord = entry.getKey();
                    count = entry.getValue();
                }
            }
            System.out.println("The most repeated word in input file is : " + mostRepeatedWord);
            System.out.print("Number Of Occurrences : ");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();//Closing the reader
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return count;
    }
    /*This method calculates the frequency of the words*/
    public int calculateFrequencyForWord(String text, String word) throws FileNotFoundException {
        Word words = new Word();
        int counter = words.getFrequency();
        word = null;
        LinkedList<String> list = new LinkedList<String>();
        File file = new File(text);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                word = sc.next();
                word = word.replaceAll("[^a-zA-Z0-9]", "");
                word = word.toLowerCase();
                word = word.trim();
                list.add(word);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, Integer> frequency = new TreeMap<String, Integer>();
        for (String count : list) {
            if (frequency.containsKey(count)) {
                frequency.put(count, frequency.get(count) + 1);
            } else {
                frequency.put(count, 1);
            }
            counter++;
        }
        System.out.println("This is the frequency of all the words from the text: ");
        System.out.print(frequency);
        System.out.print("\n" + "The amount of words were in total: ");
        return counter;
    }

    public Set<Map.Entry<String, Integer>> calculateMostFrequentNWords(String text, int number) throws IOException {
        String wording = word.getWord();
        String[] words = null;
        Set<Map.Entry<String, Integer>> elements = null;
        Map<String, Integer> freq = new HashMap<>();
        Pattern pattern = Pattern.compile("\\s+");
        try {
            Path path = Paths.get("/Users/hernandogarciamorales/Documents/yyy/test.txt");
            byte[] bytes = Files.readAllBytes(path);
            wording = new String(bytes);
            wording = wording.toLowerCase();
            Pattern r = Pattern.compile("\\p{javaLowerCase}+");
            Matcher matcher = r.matcher(wording);
            while (matcher.find()) {
                String word = matcher.group();
                words = pattern.split(wording);
                Integer current = freq.getOrDefault(word, 0);
                freq.put(word, current + 1);
            }
            //LinkedHashMap preserve the ordering of elements in which they are inserted
            LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
            freq.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
            //System.out.println("Sorted Map   : " + sortedMap);
            elements = sortedMap.entrySet();
            elements.removeIf(element -> element.getValue() == 1);//removes words ocurred only once
        }   catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.print("The duplicated words are:" );
        return  elements;
    }

    public static void main(String[] args) throws IOException {
        FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        System.out.println(frequencyAnalyzer.calculateHighestFrequency(text));
        System.out.println("************");
        System.out.println(frequencyAnalyzer.calculateFrequencyForWord(text, WORD));
        System.out.println("************");
        System.out.println(frequencyAnalyzer.calculateMostFrequentNWords(text, number));
        System.out.println("************");

    }
}
