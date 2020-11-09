
public class Word implements WordFrequency{
    private String word;
    private int frequency;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;

    }

    public int getFrequency() {
        return frequency;
    }
    /*
     * This method contains an simple algorithm which returns repeated
     * words from a specific text
     * */
    public void setFrequency(int frequency) {
        if (frequency < 0) {
            System.out.println("The words are not in the list");
            System.exit(1);
        } else {
            this.frequency = frequency;
            String[] arr = new String[10];
            boolean[] seen = new boolean[arr.length];
            for (int i = 0; i < arr.length; i++) {
                if(seen[i]) continue;
                boolean duplicate = false;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[i] == arr[j]) {
                        duplicate = seen[j] = true;
                    }
                }
                if(duplicate)
                    System.out.print(arr[i] + " ");
            }
        }
    }
    @Override
    public String toString() {
        return word;
    }
}