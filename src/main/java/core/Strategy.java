package core;

import io.FileManager;
import utils.Pattern;

import java.util.ArrayList;

public class Strategy {

    private ArrayList<Pattern> workerPatterns = new ArrayList<Pattern>();

    public Strategy() {
        this(new String[] { "achimsp4", "achimsp11", "achimsp144" } );
    }

    public Strategy(String[] patternNames) {
        if (patternNames.length < 3)
            throw new IllegalArgumentException("the number of patterns in Strategy cannot be less than 3.");

        for (String name : patternNames) {
            workerPatterns.add(FileManager.FileReader.readPatternFromFile(name));
        }
    }

    public Pattern generatePattern() {
        int ind = (int) (Math.random() * this.workerPatterns.size());
        return this.workerPatterns.get(ind);
    }

    public void addWorkerPattern(Pattern pattern) {
        workerPatterns.add(pattern);
    }

}
