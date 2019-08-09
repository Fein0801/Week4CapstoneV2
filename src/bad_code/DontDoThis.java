package bad_code;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DontDoThis {

    public static void main(String[] args) {
	runThis();
    }

    private static void runThis() {
	Scanner scan = new Scanner(System.in);

	ArrayList<Item> itemList = new ArrayList<>();

	Path p1 = Paths.get("script.js");
	File script = p1.toFile();

	Path p2 = Paths.get("input.txt");
	File inputFile = p2.toFile();

	Path p3 = Paths.get("script_functions.txt");
	File scriptTextFile = p3.toFile();

	openFile(inputFile);
	waitForInput(scan);
	itemList = readItemsFromFile(inputFile);
	StringBuffer buff = new StringBuffer();
	buff.append("var itemArray = [");
	for (int i = 0; i < itemList.size(); i++) {
	    buff.append(itemList.get(i));
	    if (i < itemList.size() - 1) {
		buff.append(",");
	    }
	}
	buff.append("\n];\n");

	flushFile(script);

	writeToScriptFile(script, buff.toString());
	writeToScriptFile(script, getFunctions(scriptTextFile));

	boolean test = test(scan);

	if (test) {
	    Path p4 = Paths.get("index.html");
	    File website = p4.toFile();
	    openFile(website);
	}

	scan.close();
    }

    private static boolean test(Scanner scan) {
	char userChar = 'a';
	while (userChar != 'y') {
	    System.out.println("Would you like to test the website? (y/n)");
	    System.out.print("> ");
	    userChar = scan.next().charAt(0);
	    scan.nextLine();

	    if (userChar == 'n') {
		System.out.println("Goodbye.");
		return false;
	    }
	}
	System.out.println("Goodbye, thanks for testing!");
	return true;
    }

    private static void flushFile(File script) {
	try {
	    PrintWriter output = new PrintWriter(new FileOutputStream(script));
	    output.flush();
	    output.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
    }

    private static void waitForInput(Scanner scan) {
	System.out.println("Enter \"1\" when you are finished editing the input file.");
	System.out.print("> ");
	String answer = scan.next();
	if (!answer.equals("1")) {
	    waitForInput(scan);
	}

    }

    private static ArrayList<Item> readItemsFromFile(File inputFile) {
	ArrayList<Item> itemList = new ArrayList<Item>();
	try {
	    BufferedReader buffer = new BufferedReader(new FileReader(inputFile));
	    String line = "";
	    int count = 0;
	    while (line != null) {
		line = buffer.readLine();
		count++;
		if (line == null || line.contains("***")) {
		    continue;
		}

		try {
		    Item item = Item.parse(line);
		    Double.parseDouble(item.getPrice());
		    itemList.add(item);
		} catch (NumberFormatException e) {
		    System.out.println("Skipped line " + count + " in input file. Invalid price number.");
		}
	    }

	    buffer.close();
	} catch (FileNotFoundException e) {
	    System.out.println("File not found.");
	} catch (IOException e) {
	    System.out.println("Error reading from file.");
	}
	return itemList;
    }

    private static void openFile(File file) {
	try {
	    Desktop.getDesktop().open(file);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private static void writeToScriptFile(File file, String str) {
	try {
	    if (!file.exists()) {
		file.createNewFile();
	    }

	    PrintWriter output = new PrintWriter(new FileOutputStream(file, true));
	    output.println(str);
	    output.close();
	} catch (IOException e) {
	    System.out.println("Error writing to output file.");
	}
    }

    private static String getFunctions(File inputFile) {
	StringBuffer buff = new StringBuffer();
	try {
	    inputFile.setReadOnly();

	    BufferedReader buffer = new BufferedReader(new FileReader(inputFile));
	    String line = "";
	    while (line != null) {
		line = buffer.readLine();
		buff.append(line + "\n");
	    }
	    buffer.close();
	} catch (IOException e) {
	    System.out.println("Error reading functions from text file.");
	}
	return buff.toString();
    }

}
