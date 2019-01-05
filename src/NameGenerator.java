import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class NameGenerator {

    Random random;

    LinkedList<NameSection> nameTemplate;

    public NameGenerator(int seed) {

        nameTemplate = new LinkedList<>();

        random = new Random(seed);

    }

    public String getNextName() {

        StringBuilder sb = new StringBuilder();

        for (NameSection section : nameTemplate) {

            // Just in case max = 0 for some reason.
            boolean prev = (section.max > 0);

            // Counts the current number of this section added so far
            int i = 0;

            // Keep trying to add whenever the previous was and we haven't added more than the max
            while (prev && i < section.max) {

                if (getIsTrue(section.prob)) {

                    // Append a randomly selected substring from the available strings
                    sb.append(section.strings[random.nextInt(section.strings.length)]);

                    prev = true;

                } else {

                    prev = false;

                }

                i++;

            }

        }

        return sb.toString();

    }

    private boolean getIsTrue(double chance) {

        double randomDouble = random.nextDouble();

        // get just the double part
        double rand = randomDouble - (int) randomDouble;

        return rand < chance;

    }

    public static String[] stringsFromPath(String path) {

        List<String> lines = null;

        // Try relative
        try {
            lines = Files.readAllLines(Paths.get("./" + path));
        } catch (IOException e1) {

            // Otherwise try absolute
            try {
                lines = Files.readAllLines(Paths.get(path));
            } catch (IOException e) {

                System.out.println("Couldn't open: " + path);
                e.printStackTrace();

            }

        }

        // Convert lines list to array
        String[] strings = new String[lines.size()];
        strings = lines.toArray(strings);

        return strings;

    }

}

class NameSection {

    public String[] strings;

    public int max = 1;

    public double prob = 1.0;

    public NameSection(String[] strings, double prob, int max) {

        this.strings = strings;
        this.prob = prob;
        this.max = max;

    }

}
