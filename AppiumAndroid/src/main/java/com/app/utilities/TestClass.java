package com.app.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestClass {

	public static void main(String[] args) throws Exception
	{
		//	AndroidTest test = new AndroidTest();
		//	test.stopAppiumAndEmulator("", "");
		//System.out.println(getProcessOutput());
		//String output = getProcessOutput();
		//System.out.println(output.contains("4723"));
		//System.out.println(output.indexOf("4723"));
		getProcessOutput();
	}

	public static void getProcessOutput() throws IOException, InterruptedException
    {
//        ProcessBuilder processBuilder = new ProcessBuilder("netstat", "-a", "-n", "-o");
//
//        processBuilder.redirectErrorStream(true);
//
//        Process process = processBuilder.start();
//        StringBuilder processOutput = new StringBuilder();
//
//        try (BufferedReader processOutputReader = new BufferedReader(
//                new InputStreamReader(process.getInputStream()));)
//        {
//            String readLine;
//
//            while ((readLine = processOutputReader.readLine()) != null)
//            {
//                processOutput.append(readLine + System.lineSeparator());
//            }
//
//            process.waitFor();
//        }
//
//        return processOutput.toString().trim();
		
		Runtime rt = Runtime.getRuntime();
		String[] commands = {"cmd.exe", "netstat", "-a", "-n", "-o"};
		Process proc = rt.exec(commands);

		BufferedReader stdInput = new BufferedReader(new 
		     InputStreamReader(proc.getInputStream()));

		BufferedReader stdError = new BufferedReader(new 
		     InputStreamReader(proc.getErrorStream()));

		// read the output from the command
		System.out.println("Here is the standard output of the command:\n");
		String s = null;
		while ((s = stdInput.readLine()) != null) {
		    System.out.println(s);
		}

		// read any errors from the attempted command
		System.out.println("Here is the standard error of the command (if any):\n");
		while ((s = stdError.readLine()) != null) {
		    System.out.println(s);
		}
    }
	
}
