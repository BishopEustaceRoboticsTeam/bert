import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;


public class LineParser {

	public static void main(String[] args) {
		Vector readText = new Vector();
		//Vector<Line>  vectors = new Vector();
		File textfile = new File("text.txt");
		String line;
		Line nextLine;
		int counter = 0;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(textfile));
			while ((line = br.readLine()) != null) {
				  readText.addElement(new Line(line));
				  ((Line) readText.elementAt(counter)).printLine();
				  System.out.println( ((Line) readText.elementAt(counter)).getNumberofWords());
				  System.out.println(((Line) readText.elementAt(counter)).getWord(0));
				  counter++;
				}
				br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
