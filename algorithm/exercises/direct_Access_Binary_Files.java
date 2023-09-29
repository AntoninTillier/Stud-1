import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;
public class direct_Access_Binary_Files {
	public static void writeAdherents(String fileName) throws IOException {
		RandomAccessFile input = null;
		Scanner sc = new Scanner(System.in);
		try {
			input = new RandomAccessFile(fileName,"rw");
			int end =0;
			int old;
			int contribution;
			String name="JB CENA";
			System.out.println("Enter the name of the member:");
			System.out.print("		Enter the name of the member:");
			name = sc.nextLine();
			while(!name.equals("")) {
				System.out.print("		Enter the old of:"+ name +" : ");
				old = sc.nextInt();
				System.out.print("		Enter the contribution of: "+ name +" : ");
				contribution = sc.nextInt();
				System.out.println();
				input.writeUTF(name);
				input.writeInt(old);
				input.writeInt(contribution);
				sc.nextLine();
				System.out.print("		Enter the member's name: ");
				name =sc.nextLine();
			}
			System.out.println("End of members input.");
		}
		catch(IOException e) {
			System.out.println("File Error");
		}
		endally {
			input.close();
		}
	}

	public static void readAdherents(String fileName) throws IOException {
		RandomAccessFile read = null;
		try {
			read = new RandomAccessFile(fileName,"r");
			long taille = read.length();
			long fp=read.getFilePointer();
			String name = "";

			System.out.println("List of members:");
			while(fp<taille) {
				name = read.readUTF();
				System.out.println("name : "+name);
				int old=read.readInt();
				System.out.print("		old : "+old);
				int contribution=read.readInt();
				System.out.print("	contribution : "+contribution);
				System.out.println();
				fp=read.getFilePointer();
			}
			System.out.println("end");
		}
		catch(IOException e) {
			System.out.println("File Error");
		}
		endally {
			read.close();
		}
	}

	public static void createDatabases(String nameFichier) {
		RandomAccessFile input = null;
		RandomAccessFile out_name = null;
		RandomAccessFile out_old = null;
		RandomAccessFile out_coti = null;
		boolean eof = false;
		String name = "";
		int age;
		int coti;
		System.out.println("Start of Adherents' Databases Creation!");
		try {
			input = new RandomAccessFile(nameFichier, "r");
			out_name = new RandomAccessFile("name.base", "rw");
			out_old = new RandomAccessFile("age.base", "rw");
			out_coti = new RandomAccessFile("cotis.base", "rw");
			while (!eof) {
				try {
					name = input.readUTF();
				} catch (EOFException e) {
					eof = true;
				}
				if (!eof) {
					age = input.readInt();
					coti = input.readInt();
					out_name.writeUTF(name);
					out_old.writeInt(age);
					out_coti.writeInt(coti);
				}
			}
			System.out.println("\tEnd of Adherents' Databases Creation!");
		} catch (IOException e) {
			System.out.println("File Manipulation Error");
		} finally {
			if (input != null) {
				try {
					input.close();
					out_name.close();
					out_old.close();
					out_coti.close();
				} catch (IOException e) {
					System.out.println("File Closure Error: "+ input.toString());
				}
			}
		}
	}

	public static int nameIndex() {
		RandomAccessFile input = null;
		boolean eof = false;
		String name = "";
		int indice = 0;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the name for which the index should be retrieved:");
		String nameFind = in.nextLine();
		try {
			input = new RandomAccessFile("name.base", "r");
			while (!eof) {
				try {
					name = input.readUTF();
					if (nameFind.equals(name))
						return indice;
					else
						indice++;
				} catch (EOFException e) {
					eof = true;
				}
			}
		} catch (IOException e) {
			System.out.println("File Manipulation Error");
		} finally {
			in.close();
			if (input != null)
				try {
					input.close();
				} catch (IOException e) {
					System.out.println("File Closure Error: " + input.toString());
				}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		String Fichier="members";
		// First, write a method void createMembers(String fileName) that creates a binary file named 'fileName'
		// passed as a parameter, which will contain the list of members.
		// The created file will consist of a series of records, each including a name (string), an age,
		// and a membership fee (both integers). The records will be prompted for by the method.
		// The end of input will be signaled by entering an empty name.
		// Create a file named 'members' containing some member records.
		writeAdherents(Fichier);
		// Write a method void readAdherents(String fileName) that receives as a parameter the name of a binary file
		// containing records as described in 1. and displays the name of each member along with their age and
		// membership fee.
		readAdherents(Fichier);
		// Write a method void createDatabases(String fileName) which, from the file received as parameter "fileName"
		// (containing records as described in 1.), creates 3 binary files.
		// The first, named "name.base", should contain only the names of the members, the second, named "age.base",
		// will contain only the ages, and the third, named "fee.base", will contain only the membership fees.
		//These 3 files must be filled so that for a given name, the associated age and fee have the same position in
		// each of the files as the name in the "name.base" file.
		createDatabases(Fichier);
		// Write a method int nameIndex() that asks the user for a name and returns the index corresponding to that
		// name in the "name.base" file (sequential access is preferable here).
		// The method should return -1 if the entered name is not present.
		nameIndex()
	}
}