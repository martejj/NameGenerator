public class NameGeneratorTest {

    public static void main(String[] args) {

        NameGenerator generator = new NameGenerator(1);
        
        generator.nameTemplate.addLast(new NameSection(NameGenerator.stringsFromPath("townPrependages.txt"), 0.5, 1));
        generator.nameTemplate.addLast(new NameSection(NameGenerator.stringsFromPath("townBeginnings.txt"), 1, 1));
        generator.nameTemplate.addLast(new NameSection(NameGenerator.stringsFromPath("townExtras.txt"), 0.1, 2));
        // For some spice add in a dash
        String[] dash = {"-"};
        generator.nameTemplate.addLast(new NameSection(dash, 0.05, 1));
        generator.nameTemplate.addLast(new NameSection(NameGenerator.stringsFromPath("townEndings.txt"), 0.8, 1));

        // Print 20 names
        for (int i = 0; i < 20; i++) System.out.println(generator.getNextName());

    }
    
}
