import java.util.Scanner;


public class PJShell{

	public static void ShellCommand(String args[]){
		int current_flag = 0;

		// for (int i=0;i<args.length;i++){
		// 	System.out.println(args[i]);
		// }


		if (args.length == 0){
			// do nothing, go to the bottom and print prompt
		} else if (args[current_flag] == "ls"){
			// do some listing stuff here
		} else if (args[current_flag] == "rm"){
			// do some remove shit here
		} else if (args[current_flag] == "cp"){
			// do some copy shit here
		} else if (args[current_flag] == "mkdir"){
			// make a directory
		} else if (args[current_flag] == "rmdir"){
			// remove a directory
		}


		System.out.print("pjshell> ");
	}

	public static void main(String args[]){
		Scanner keyboard = new Scanner(System.in);
		String shell_args[] = args;

		while(shell_args.length == 0 || !shell_args[0].equals("exit")){
			ShellCommand(shell_args);
			shell_args = keyboard.nextLine().split(" ");
		}
	}

}