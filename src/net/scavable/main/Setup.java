package net.scavable.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Setup {
    Setup() {

        File startService = new File("StartService.bat");
        File stopService = new File("StopService.bat");

        try {
            if (!startService.exists()) {
                boolean status = startService.createNewFile();
                FileWriter fw1 = new FileWriter(startService);
                BufferedWriter bw1 = new BufferedWriter(fw1);
                bw1.write("@ECHO OFF\n" +
                        "setlocal EnableDelayedExpansion\n" +
                        "\n" +
                        "::net file to test privileges, 1>NUL redirects output, 2>NUL redirects errors\n" +
                        "NET FILE 1>NUL 2>NUL\n" +
                        "if '%errorlevel%' == '0' ( goto START ) else ( goto getPrivileges ) \n" +
                        "\n" +
                        ":getPrivileges\n" +
                        "if '%1'=='ELEV' ( goto START )\n" +
                        "\n" +
                        "set \"batchPath=%~f0\"\n" +
                        "set \"batchArgs=ELEV\"\n" +
                        "\n" +
                        "::Add quotes to the batch path, if needed\n" +
                        "set \"script=%0\"\n" +
                        "set script=%script:\"=%\n" +
                        "IF '%0'=='!script!' ( GOTO PathQuotesDone )\n" +
                        "    set \"batchPath=\"\"%batchPath%\"\"\"\n" +
                        ":PathQuotesDone\n" +
                        "\n" +
                        "::Add quotes to the arguments, if needed.\n" +
                        ":ArgLoop\n" +
                        "IF '%1'=='' ( GOTO EndArgLoop ) else ( GOTO AddArg )\n" +
                        "    :AddArg\n" +
                        "    set \"arg=%1\"\n" +
                        "    set arg=%arg:\"=%\n" +
                        "    IF '%1'=='!arg!' ( GOTO NoQuotes )\n" +
                        "        set \"batchArgs=%batchArgs% \"%1\"\"\n" +
                        "        GOTO QuotesDone\n" +
                        "        :NoQuotes\n" +
                        "        set \"batchArgs=%batchArgs% %1\"\n" +
                        "    :QuotesDone\n" +
                        "    shift\n" +
                        "    GOTO ArgLoop\n" +
                        ":EndArgLoop\n" +
                        "\n" +
                        "::Create and run the vb script to elevate the batch file\n" +
                        "ECHO Set UAC = CreateObject^(\"Shell.Application\"^) > \"%temp%\\OEgetPrivileges.vbs\"\n" +
                        "ECHO UAC.ShellExecute \"cmd\", \"/c \"\"!batchPath! !batchArgs!\"\"\", \"\", \"runas\", 1 >> \"%temp%\\OEgetPrivileges.vbs\"\n" +
                        "\"%temp%\\OEgetPrivileges.vbs\" \n" +
                        "exit /B\n" +
                        "\n" +
                        ":START\n" +
                        "::Remove the elevation tag and set the correct working directory\n" +
                        "IF '%1'=='ELEV' ( shift /1 )\n" +
                        "cd /d %~dp0\n" +
                        "\n" +
                        "sc start MySQL80");
                bw1.close();
                fw1.close();
            }
            if (!stopService.exists()) {
                boolean status = stopService.createNewFile();
                FileWriter fw2 = new FileWriter(stopService);
                BufferedWriter bw2 = new BufferedWriter(fw2);
                bw2.write("@ECHO OFF\n" +
                        "setlocal EnableDelayedExpansion\n" +
                        "\n" +
                        "::net file to test privileges, 1>NUL redirects output, 2>NUL redirects errors\n" +
                        "NET FILE 1>NUL 2>NUL\n" +
                        "if '%errorlevel%' == '0' ( goto START ) else ( goto getPrivileges ) \n" +
                        "\n" +
                        ":getPrivileges\n" +
                        "if '%1'=='ELEV' ( goto START )\n" +
                        "\n" +
                        "set \"batchPath=%~f0\"\n" +
                        "set \"batchArgs=ELEV\"\n" +
                        "\n" +
                        "::Add quotes to the batch path, if needed\n" +
                        "set \"script=%0\"\n" +
                        "set script=%script:\"=%\n" +
                        "IF '%0'=='!script!' ( GOTO PathQuotesDone )\n" +
                        "    set \"batchPath=\"\"%batchPath%\"\"\"\n" +
                        ":PathQuotesDone\n" +
                        "\n" +
                        "::Add quotes to the arguments, if needed.\n" +
                        ":ArgLoop\n" +
                        "IF '%1'=='' ( GOTO EndArgLoop ) else ( GOTO AddArg )\n" +
                        "    :AddArg\n" +
                        "    set \"arg=%1\"\n" +
                        "    set arg=%arg:\"=%\n" +
                        "    IF '%1'=='!arg!' ( GOTO NoQuotes )\n" +
                        "        set \"batchArgs=%batchArgs% \"%1\"\"\n" +
                        "        GOTO QuotesDone\n" +
                        "        :NoQuotes\n" +
                        "        set \"batchArgs=%batchArgs% %1\"\n" +
                        "    :QuotesDone\n" +
                        "    shift\n" +
                        "    GOTO ArgLoop\n" +
                        ":EndArgLoop\n" +
                        "\n" +
                        "::Create and run the vb script to elevate the batch file\n" +
                        "ECHO Set UAC = CreateObject^(\"Shell.Application\"^) > \"%temp%\\OEgetPrivileges.vbs\"\n" +
                        "ECHO UAC.ShellExecute \"cmd\", \"/c \"\"!batchPath! !batchArgs!\"\"\", \"\", \"runas\", 1 >> \"%temp%\\OEgetPrivileges.vbs\"\n" +
                        "\"%temp%\\OEgetPrivileges.vbs\" \n" +
                        "exit /B\n" +
                        "\n" +
                        ":START\n" +
                        "::Remove the elevation tag and set the correct working directory\n" +
                        "IF '%1'=='ELEV' ( shift /1 )\n" +
                        "cd /d %~dp0\n" +
                        "\n" +
                        "sc stop MySQL80");
                bw2.close();
                fw2.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
