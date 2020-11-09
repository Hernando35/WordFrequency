import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import javafx.util.*;
/*
 * TO-DO --> Implements the frequency analyzer methods with Jakarta
 * */
abstract class Frequency {
    abstract int calculateHighestFrequency(String text) throws IOException;
    abstract int calculateFrequencyForWord (String text, String word)throws IOException;
    Set<Map.Entry<String, Integer>> calculateMostFrequentNWords (String text, int number) throws IOException{
        return null;
    }
}
