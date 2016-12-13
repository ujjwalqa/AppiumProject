package com.app.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class OpenCommandPrompt 
{
	/**
	 * Execute command from array of commands
	 * @param commands
	 */
	public static void executeCommand(List<String> commands)
	{
		String allCommands = "";
		
		if(commands.size() > 0)
		{
			for (String command : commands) 
			{
				allCommands = allCommands + command +" && start ";
			}
		}
		
		allCommands = removeEnd(allCommands, " && start ");
			
		try {
			Runtime.getRuntime().exec("cmd.exe /c start "+ allCommands);
		} catch (IOException e) {
			System.out.println("From Execute command " + e.toString());
		}
	}
	
	public static void CloseExecuteCommand(List<String> commands)
	{
		String allCommands = "";
		
		if(commands.size() > 0)
		{
			for (String command : commands) 
			{
				allCommands = allCommands + command +" && start ";
			}
		}
		
		allCommands = removeEnd(allCommands, " && start ");
			
		try {
			Process proc = Runtime.getRuntime().exec("cmd.exe /c start "+ allCommands);
			
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			String s = null;
			while ((s = stdInput.readLine()) != null) 
			{
			    System.out.println(s);
			}

			// read any errors from the attempted command
			System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) 
			{
			    System.out.println(s);
			}
		} catch (IOException e) {
			System.out.println("From Execute command " + e.toString());
		}
	}
	
	/**
	 * Remove some specific chars from end of string 
	 * @param str
	 * @param remove
	 * @return
	 */
	public static String removeEnd(String str, String remove) {
	      if (str.endsWith(remove)) {
	          return str.substring(0, str.length() - remove.length());
	      }
	      return str;
	  }

}
