import translator.*;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        Translator translator = new Translator();
        try {
            System.out.println(translator.translate("en", "Привет, мир"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



