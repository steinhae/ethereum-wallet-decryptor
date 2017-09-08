import org.apache.commons.cli.*;
import com.google.gson.Gson;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static final String WALLET_OPTION = "wallet-file-path";
    private static final String OUTPUT_OPTION = "output-file-path";

    public static void main(String[] args) {

        Options options = new Options();

        Option input = new Option(
                "w",
                WALLET_OPTION,
                true,
                "The file containing the wallet in json format."
        );
        input.setRequired(true);
        Option output = new Option(
                "o",
                OUTPUT_OPTION,
                true,
                "The credentials will be written to this file."
        );

        options.addOption(input);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("ethereum-wallet-decrypter", options);
            System.exit(1);
            return;
        }

        String walletFilePath = cmd.getOptionValue(WALLET_OPTION);
        char[] pwd = System.console().readPassword("Enter wallet password: ");
        String password = new String(pwd);

        try {
            Credentials credentials = WalletUtils.loadCredentials(password, walletFilePath);
            Gson gson = new Gson();
            String credentialsJson = gson.toJson(credentials);
            String outputFile;
            if (cmd.hasOption(OUTPUT_OPTION)) {
                outputFile = cmd.getOptionValue(OUTPUT_OPTION);
                writeFile(outputFile, credentialsJson);
                System.out.println("Credentials written to " + outputFile);
            } else {
                System.out.println(credentialsJson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(String outputFile, String content) throws IOException {
        File file = new File(outputFile);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(content);
        fileWriter.flush();
        fileWriter.close();
    }
}
