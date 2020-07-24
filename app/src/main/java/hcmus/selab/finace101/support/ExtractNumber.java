package hcmus.selab.finace101.support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// This class extract numbers from a string (the return type is int)
// Use ExtractNumber.main("your string here") to start extract

public class ExtractNumber {
    public static int main(String data) {
        String result_string = "";
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(data);
        int result = 0;

        while(m.find()) {
            result_string = m.group();
        }

        result = Integer.parseInt(result_string);

        return result;
    }
}
