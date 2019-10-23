import java.util.*;


public class PJShell{

	public static int ls_method(String args[], int current_word){
		String path = ".";
		if (current_word < args.length){
			path = args[current_word++];
		}
		// do list printing and exceptions here
		System.out.println("listing current children in directory: " + path);
		return current_word;
	}

	public static int rm_method(String args[], int current_word){
		String rm_path = "";
		while (current_word < args.length){
			if (args[current_word].equals(";")){
				break;
			} else {
				rm_path = args[current_word++];
				// do removal
				System.out.println("removing file: " + rm_path);
			}
		}
		if (rm_path.equals("")){
			// throw no path exception
			System.out.println(" Error: You must provide a file path to remove");
		}
		return current_word;
	}

	public static int cp_method(String args[], int current_word){
		return 0;
	}

	public static int mkdir_method(String args[], int current_word){
		return 0;
	}

	public static int rmdir_method(String args[], int current_word){
		return 0;
	}



	public static void ShellCommand(String args[]){
		int current_word = 0;

		// for (int i=0;i<args.length;i++){
		// 	System.out.println(args[i]);
		// }

		while(current_word < args.length){

			if (args[current_word].equals("ls")){
				current_word = ls_method(args, ++current_word);
				
			} else if (args[current_word].equals("rm")){
				current_word = rm_method(args, ++current_word);

			} else if (args[current_word].equals("cp")){
				current_word = cp_method(args, ++current_word);

			} else if (args[current_word].equals("mkdir")){
				current_word = mkdir_method(args, ++current_word);
				
			} else if (args[current_word].equals("rmdir")){
				current_word = rmdir_method(args, ++current_word);
				
			} else if (args[current_word].equals(";")){
				// go through another execution cycle
			} else {
				// dont recognize a command. For now, just ignore it
				current_word++;
			}
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

		System.out.println(
			"~~~~~~~~~~~~~~~~~\nThank you for using pjshell\n~~~~~~~~~~~~~~~~~"
			);
	}

}