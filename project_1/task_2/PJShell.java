import java.util.*;


public class PJShell{

	public static void ls_method(String args[]){
		String path = ".";
		if (args.length > 0){
			path = args[0];
		}
		// do list printing here
		System.out.println("listing current children");
	}

	public static void ShellCommand(String args[]){
		int current_word = 0;

		// for (int i=0;i<args.length;i++){
		// 	System.out.println(args[i]);
		// }


		if (args.length == 0){
			// do nothing, go to the bottom and print prompt
		} else if (args[current_word].equals("ls")){
			current_word++;
			ls_method(Arrays.copyOfRange(args, current_word, args.length));
			
		} else if (args[current_word].equals("rm")){
			// do some remove shit here
		} else if (args[current_word].equals("cp")){
			// do some copy shit here
		} else if (args[current_word].equals("mkdir")){
			// make a directory
		} else if (args[current_word].equals("rmdir")){
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