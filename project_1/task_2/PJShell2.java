import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.*;
import java.lang.*;


class PJShell2{
	public static final String HOME = new ProcessBuilder("").environment().get("HOME");
	public static String cwd = HOME;

	public static String cd_helper(String current_path, String to_add){
		Stack<String> base = new Stack<>();
		Vector<String> target = new Vector<>();
		String base_arr[] = current_path.split("/");
		String target_arr[] = to_add.split("/");

		for (int i=0;i<base_arr.length;i++){
			base.push(base_arr[i]);
		}
		for (int i=0;i<target_arr.length;i++){
			target.add(target_arr[i]);
		}

		// now we have a vector for the target
		// and a stack for the base

		// this scheme only takes relative paths
		for (int i=0;i<target.size();i++){
			if (target.get(i).equals("..")){
				if (!base.empty()){
					base.pop();
				}
			} else if (target.get(i).equals("~")){
				String home_arr[] = HOME.split("/");
				base.clear();
				for (int j=0;j<home_arr.length;j++){
					base.push(home_arr[j]);
				}
			} else {
				base.push(target.get(i));
			}
		}

		// now build the string and return it
		String new_path = "";
		for (int i=0;i<base.size();i++){
			if (!(base.get(i).equals("") || base.get(i).equals("."))){
				// if (i < base.size()){
				// 	new_path = new_path ;
				// }
				new_path = new_path + "/" + base.get(i);
				
			}
			// System.out.println(" building: " + new_path);
		}
		// System.out.println(" testing path: " + new_path);
		if (new_path.equals("")){
			new_path = new_path + "/";
		}
		return new_path;
	}

	public static int cd_method(String args[], int current_word){
		String path = "";

		if (current_word < args.length){
			path = args[current_word++];
			// validate path here
			File move_to = new File(cd_helper(cwd, path));
			if (move_to.exists() && move_to.isDirectory()){
				cwd = move_to.getAbsolutePath();	
			} else {
				System.out.println(" Error: That path does not exist");
			}
		} else {
			// throw no-path exception
			System.out.println(" Error: You must provide a path to move to");
		}

		return current_word;
	}

	public static void ShellCommand(String args[]){
		

		if (args[0].equals("cd")){
			cd_method(args, 1);
		}

		try {
			ProcessBuilder pb = new ProcessBuilder(args);
			pb.directory(new File(cwd));
	        final Process p = pb.start();

	        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));


	        // output section
            String line;
            while((line=br.readLine())!=null){
	            System.out.println(line);
            }

        } catch (Exception ex) {
	        System.out.println(ex);
	    }
	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String shell_args[] = args;
		String commands[];
		Boolean exit = false;

		while(!exit){
            System.out.print("pjshell> ");
			commands = keyboard.nextLine().split(";");
			for (int i=0;i<commands.length;i++){
				shell_args = commands[i].trim().replaceAll("\\s+", " ").split(" ");

				if (shell_args.length > 0 && shell_args[0].equals("exit")){
					exit = true;
				} else if (shell_args.length > 0 && !shell_args[0].equals("")){
					ShellCommand(shell_args);
				}
			}
		}
		System.out.println("  *   *   *   *   *   *   *  ");
		System.out.println("*   *   *   *   *   *   *   *");
		System.out.println(" Thank you for using PJShell!");
		System.out.println("*   *   *   *   *   *   *   *");
		System.out.println("  *   *   *   *   *   *   *  ");
	}
}