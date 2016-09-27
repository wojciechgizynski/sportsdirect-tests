package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class OsUtils {

    private static final Logger log = LoggerFactory.getLogger(OsUtils.class);

    private static boolean runCommand(String... cmds) throws IOException, InterruptedException {
        String cmdLine = Arrays.stream(cmds).collect(Collectors.joining(" "));
        log.debug("Running command: '%s'", cmdLine);
        Process process = Runtime.getRuntime().exec(cmds);
        return process.waitFor() == 0;
    }

    public static void killProcess(String name) {
        try {
            runCommand("taskkill", "/F", "/T", "/IM", name);
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }
}
