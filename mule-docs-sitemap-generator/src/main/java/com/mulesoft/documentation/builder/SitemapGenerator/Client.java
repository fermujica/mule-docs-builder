package com.mulesoft.documentation.builder.SitemapGenerator;

import org.apache.commons.cli.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by fernandomujica on 9/23/16.
 */
public class Client {
    private static String clientName = "MuleSoft Sitemap Generator: ";

    public static void main(String[] args) { parseInput(args); }

    public static void parseInput(String[] args) {
        CommandLineParser parser = new DefaultParser();
        Options options = getCliOptions();
        try {
            CommandLine line = parser.parse(options, args);
            validateHelp(line);
            validateInput(line);
            generateMap(line);
        } catch (ParseException exp) {
            System.out.println(clientName + exp.getMessage());
        } catch (ClientException e) {
            System.out.println(e.getMessage());
        }
    }


    public static Options getCliOptions() {
        Options options = new Options();
        options.addOption( "url", "base URL", true, "The base URL to show up in the sitemap.xml file" );
        options.addOption( "s", "source", true, "The source directory to map" );
        options.addOption( "h", "help", false, "Help/usage information." );

        return options;
    }


    private static void validateHelp(CommandLine line) {
        if(line.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            String usage = "-url https://docs.mulesoft.com -s /path/to/html/dir ";
            formatter.printHelp(usage, getCliOptions());
            System.exit(0);
        }
    }

    private static void validateInput(CommandLine line) {
        if (line.hasOption("url")) {
            File sourceFile = new File(line.getOptionValue("url"));
            if (!line.hasOption("url")) {
                throw new ClientException(clientName + "No URL path specified");
            }
            if (!line.hasOption("s")) {
                System.out.println("Didn't specify destination directory. Mapping to /tmp directory instead.");
            }
        }
    }


        private static void generateMap(CommandLine line) {
            String sourceDir = line.getOptionValue("s");
            String sourceURL = line.getOptionValue("url");

            Generator sitemapper = new Generator(sourceURL,sourceDir);
            sitemapper.generate();
        }



}
