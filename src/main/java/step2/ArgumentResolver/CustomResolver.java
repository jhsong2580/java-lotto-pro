package step2.ArgumentResolver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomResolver implements Resolver {

    private final String REGEX_DELIMITER = "//(.)\n(.*)";
    private final String REGEX_SPLIT_SOURCE = "//(.)\n";

    @Override
    public boolean canResolve(String source) {
        source = "//;\n1;2;3";
        return extractMatcher(source, REGEX_DELIMITER).find();
    }

    private Matcher extractMatcher(String source, String regex) {
        return Pattern.compile(regex).matcher(source);
    }

    @Override
    public String[] resolve(String source) {
        String delimiter = "";
        Matcher delimiterMatcher = extractMatcher(source, REGEX_DELIMITER);

        if (delimiterMatcher.find()) {
            delimiter = delimiterMatcher.group(1);
        }
        String splitSource = extractMatcher(source, REGEX_SPLIT_SOURCE).replaceAll("");
        return splitSource.split(delimiter);
    }
}
