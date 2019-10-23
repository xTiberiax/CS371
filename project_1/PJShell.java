
public class PJShell{
	public static void main(String args[]){
		for (int i=0;i<args.length;i++){
			System.out.println(args[i]);
		}

		int current_flag = 0;

		if (args.length == 0){
			// print bash prompt
		}

		if (args[current_flag++] == "ls"){
			// do some listing stuff here
		} else if (args[current_flag++] == "rm"){
			// do some remove shit here
		} else if (args[current_flag++] == "cp"){
			// do some copy shit here
		} else if (args[current_flag++] == "mkdir"){
			// make a directory
		} else if (args[current_flag++] == "rmdir"){
			// remove a directory
		}
	}
}