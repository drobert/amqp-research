package name.danielrobert.amqp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FileSplitter {

    public static List<String> split(File in) {
        List<String> retval = new LinkedList<>();
        String line;
        try (BufferedReader r = new BufferedReader(new FileReader(in))) {
            while ((line = r.readLine()) != null)
                retval.add(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return retval;
    }
}
