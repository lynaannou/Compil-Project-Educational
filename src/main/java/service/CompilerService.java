package service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CompilerService {

    public static String analyze(String expression) throws IOException {

        // Resolve absolute path to calc.exe
        Path exePath = Paths.get("native", "calc.exe").toAbsolutePath();

        ProcessBuilder pb = new ProcessBuilder(exePath.toString());
        pb.redirectErrorStream(true);

        
        Process process = pb.start();

        // Send input to calc.exe
        try (BufferedWriter writer =
                     new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {

            writer.write(expression);
            writer.newLine();
            writer.flush();
        }

        // Read output from calc.exe
        StringBuilder response = new StringBuilder();
        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(process.getInputStream()))) {

            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
            }
        }

        return response.toString();
    }
}