package service;
import java.io.*;
public class CompilerService{
    public static String analyze(String expression) throws IOException {
        ProcessBuilder pb = new ProcessBuilder("native\\calc.exe");
        pb.redirectErrorStream(true);

        // Ssending input to calc.exe
        BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(process.getOutputStream())
        );
        writer.write(expression);
        writer.newLine();
        writer.flush();
        writer.close();


        // reading output from calc.exe the output, we have a couple of messages : expression error, expression correct + results etc
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(process.getInputStream())
        );


        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line).append("\n");


        }
        return response.toString();
    }
}