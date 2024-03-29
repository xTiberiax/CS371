import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class PJShell{
	public static String cwd = "";
	public static String homedir = "";

	public static int ls_method(String args[], int current_word){
		String path = ".";
		if (current_word < args.length){
			path = args[current_word++];
		}

		File tempfile = new File(cd_helper(cwd, path));
		ArrayList<String> contents = new ArrayList<String>(
			Arrays.asList(tempfile.list())
			);

		for (int i=0;i<contents.size();i++){
			System.out.println(contents.get(i));
		}
		System.out.println();
		return current_word;
	}

	public static int rm_method(String args[], int current_word){
		String rm_path = "";
		while (current_word < args.length){
			if (args[current_word].equals(";")){
				break;
			} else {
				rm_path = args[current_word++];
				File tempfile = new File(cd_helper(cwd, rm_path));
				if (tempfile.exists() && !tempfile.isDirectory()){
					tempfile.delete();
				} else {
					System.out.println(" Error: this file does not exist");
				}
			}
		}
		if (rm_path.equals("")){
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
			System.out.println(" Error: You must provide a path to copy from");
		}

		// if from_path is empty, to_path will always be ""
		if (to_path.equals("")){
			System.out.println(" Error: You must provide a path to copy to");
		} else {
			// do copy here
			InputStream inStream = null;
			OutputStream outStream = null;

			try{
				
				File from_file =new File(cd_helper(cwd, from_path));
			    File to_file =new File(cd_helper(cwd, to_path));

			    if (!from_file.exists() || from_file.isDirectory()){
			    	System.out.println(" Error: the file copying from does not exist");
			    }
				
			    inStream = new FileInputStream(from_file);
			    outStream = new FileOutputStream(to_file);
		    	
			    byte[] buffer = new byte[1024];
				
			    int length;
			    //copy the file content in bytes 
			    while ((length = inStream.read(buffer)) > 0){
			    	outStream.write(buffer, 0, length);
			    }
			 
			    inStream.close();
			    outStream.close();
		    }catch(IOException e){
	    		e.printStackTrace();
	    	}
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
			File tempfile = new File(cd_helper(cwd, new_path));
			if (tempfile.exists()){
				System.out.println(" Error: This directory already exists");
			} else {
				tempfile.mkdir();
			}
		}
		return current_word;
	}

	public static void rmdir_helper(String ext_cwd){
		File current_root = new File(ext_cwd);
		ArrayList<String> contents = new ArrayList<String>(Arrays.asList(current_root.list()));
		File content;

		for (int i=0;i<contents.size();i++){
			content = new File(cd_helper(current_root.getAbsolutePath(), contents.get(i)));
			if (content.isDirectory()){				// recursive case
				rmdir_helper(content.getAbsolutePath());
			}
			content.delete();						// base case
		}
	}

	public static int rmdir_method(String args[], int current_word){
		String rm_path = "";

		while (current_word < args.length){
			if (args[current_word].equals(";")){
				break;
			}
			rm_path = args[current_word++];
			File dir_to_del = new File(cd_helper(cwd, rm_path));
			if (dir_to_del.exists() && dir_to_del.isDirectory()){
				rmdir_helper(dir_to_del.getAbsolutePath());
				dir_to_del.delete();
			} else {
				System.out.println(" Error: This directory does not exist");
			}
		}
		return current_word;
	}

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
				base.clear();
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
		int current_word = 0;

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
				// dont recognize a command
				System.out.println(" Error: Command '" + args[current_word] + "' is invalid");
				System.out.println(" use 'exit' to quit pjshell");
				break;
			}
		} 

		System.out.print(cwd + " ");
		System.out.print("pjshell> ");
	}

	public static void main(String args[]){
		Scanner keyboard = new Scanner(System.in);
		String shell_args[] = args;
		cwd = cwd + System.getProperty("user.dir");

		while(shell_args.length == 0 || !shell_args[0].equals("exit")){
			ShellCommand(shell_args);
			shell_args = keyboard.nextLine().split(" ");
		}
		System.out.println("  *   *   *   *   *   *   *  ");
		System.out.println("*   *   *   *   *   *   *   *");
		System.out.println(" Thank you for using PJShell!");
		System.out.println("*   *   *   *   *   *   *   *");
		System.out.println("  *   *   *   *   *   *   *  ");
	}
}