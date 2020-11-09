import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface WordFrequencyAnalyzer {
    int calculateHighestFrequency(String text) throws IOException;
    int calculateFrequencyForWord (String text, String word)throws IOException;
    Set<Map.Entry<String, Integer>> calculateMostFrequentNWords (String text, int number) throws IOException;
}
