import java.util.*;


public class PJShell{
	public String cwd = "";

	public static int ls_method(String args[], int current_word){
		String path = ".";
		if (current_word < args.length){
			path = args[current_word++];
		}
		// do list printing and exceptions here



		File listing_dir = new File(path);
		File 



		System.out.println(" listing current children in directory: " + path);
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
				System.out.println(" removing file: " + rm_path);
			}
		}
		if (rm_path.equals("")){
			// throw no-path exception
			System.out.println(" Error: You must provide a file path to remove");
		}
		return current_word;
	}

	public static int cp_method(String args[], int current_word){
		String from_path = "";
		String to_path = "";

		if (current_word < args.length){
			from_path = args[current_word++];
		}
		if (current_word < args.length){
			to_path = args[current_word++];
		}

		if (from_path.equals("")){
			// throw no-path exception
			System.out.println(" Error: You must provide a path to copy from");
		}

		// if from_path is empty, to_path will always be ""
		if (to_path.equals("")){
			// throw no-path exception
			System.out.println(" Error: You must provide a path to copy to");
		} else {
			// do copy here
			System.out.println(" copying from: " + from_path 
				+ " to: " + to_path);
		}
		
		return current_word;
	}

	public static int mkdir_method(String args[], int current_word){
		String new_path = "";

		while (current_word < args.length){
			if (args[current_word].equals(";")){
				break;
			}

			new_path = args[current_word++];
			// verify path here
			// make the directory here
			System.out.println(" making directory: " + new_path);
		}
		return current_word;
	}

	public static int rmdir_method(String args[], int current_word){
		String rm_path = "";

		while (current_word < args.length){
			if (args[current_word].equals(";")){
				break;
			}
			rm_path = args[current_word++];
			// verify path here
			// remove the directory here
			// this may require a recursive removal function
			System.out.println(" removing directory: " + rm_path);
		}
		return current_word;
	}

	public static int cd_method(String args[], int current_word){
		String path = "";

		if (current_word < args.length){
			path = args[current_word++];
			// verify path here
			
		} else {
			// throw no-path exception
			System.out.println(" Error: You must provide a path to move to");
		}

		return current_word;
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
				
			} else if (args[current_word].equals("cd")){
				current_word = cd_method(args, ++current_word);

			} else if (args[current_word].equals(";")){
				// go through another execution cycle
				// right now its just an ignore
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
		cwd = "~";

		while(shell_args.length == 0 || !shell_args[0].equals("exit")){
			ShellCommand(shell_args);
			shell_args = keyboard.nextLine().split(" ");
		}

		System.out.println("* * * * * * * * * * * * * * *\n");
		System.out.println("Thank you for using PJShell!\n");
		System.out.println("* * * * * * * * * * * * * * *");
	}

}