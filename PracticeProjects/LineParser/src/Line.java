
public class Line {
	   public String line;
       public Line(String _line){
    	   line = _line;
       }
       
       public void printLine(){
    	   System.out.println(line);
       }
       public int getNumberofWords(){
    	   
    	   if (line == null){
    		   return 0;    		   
    	   }
    	   String[] words = line.trim().split("\\s+");
    	   return words.length;
       }
       
       public String getWord(int pos){
    	   if (line == null){
    		   return null;    		   
    	   }
    	   String[] words = line.trim().split("\\s+");
    	   return words[pos];
       }
}
