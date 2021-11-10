import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Introduce tu nombre y apellidos:");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String name = bufferedReader.readLine();

        System.out.println(nameParts(name));
    }

    static String nameParts(String name) {
        String[] nameParts = name.split(" ");
        if(!name.contains(" ")) {
            return "Nombre:" + name;
        } else if(nameParts.length == 3) {
            String tempFirstName = nameParts[0] + " " + nameParts[1];
            if(isFirstName(tempFirstName))
                return "Nombre: " + nameParts[0] + " " + nameParts[1] + ", Apellido 1: " + nameParts[2];
            else
                return "Nombre: " + nameParts[0] + ", Apellido 1: " + nameParts[1] + ", Apellido 2: " + nameParts[2];
        } else if(nameParts.length == 4) {
            String tempFirstName = nameParts[0] + " " + nameParts[1];
            if(isFirstName(tempFirstName))
                return "Nombre: " + nameParts[0] + " " + nameParts[1] + ", Apellido 1: " + nameParts[2] + ", Apellido 2: " + nameParts[3];
            else
                return "Nombre: " + nameParts[0] + ", Apellido 1: " + nameParts[1] + ", Apellido 2: " + nameParts[2] + " " + nameParts[3];
        }
        return name;
    }

    static Boolean isFirstName(String inputName) {
        try {
            List<String> firstnames = Files.readAllLines(Paths.get("src\\firstnames.txt"));
            for(String name : firstnames) {
                if(cleanName(inputName.toLowerCase()).equals(cleanName(name)))
                    return true;
            }
        } catch(IOException ex) {
            System.out.println("Error while reading 'firstnames.txt': " + ex.toString());
        }
        
        return false;
    }

    static String cleanName(String inputName) {
        String cleanName = inputName;
        cleanName = cleanName.replace("á", "a");
        cleanName = cleanName.replace("é", "e");
        cleanName = cleanName.replace("í", "i");
        cleanName = cleanName.replace("ó", "o");
        cleanName = cleanName.replace("ú", "u");
        return cleanName.toLowerCase();
    }
}
