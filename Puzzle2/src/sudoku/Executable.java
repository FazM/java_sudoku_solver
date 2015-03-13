package sudoku;

import java.io.*;

import javax.xml.parsers.*;
import org.w3c.dom.Document; // use DOM  
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Executable {
	
	private static int [][] a = new int [9][9];

	public static void main(String[] args) {
		DocumentBuilderFactory builder = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuilder = builder.newDocumentBuilder();
			Document document = dBuilder.parse(Executable.class.getResourceAsStream("14769_fiendish.xml"));
			document.normalize();
			a = generateIntArrayfromXMLfile(document);
			Sudoku easy = new Sudoku(a);
			easy.Run();
			int [][] i = easy.getSudokuGrid();
			writeToTextfile(i);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * This static method returns a two dimensional int array from an XML document.
     * Method iterates through all the node elements in the document to retrieve the 
     * textual content within each <cell> node. The resulting content is converted to 
     * an int value and stored within the 9x9 two dimensional int array. Value = 0 if no
     * textual content exists within a <cell> node.
     * @param-a the document xml file
     * @return-result the two dimensional int array.
     */
	private static int [][] generateIntArrayfromXMLfile (Document a) {
		int [] [] result = new int [9] [9];
		String [] [] result2 = new String [9][9];
		Node sudokugridNode = a.getElementsByTagName("sudokugrid").item(0);
		Element sudokugridElement = (Element) sudokugridNode;
		for (int i=0; i < 9; i++) {
			Node row = sudokugridElement.getElementsByTagName("row").item(i);
			Element rowElement = (Element) row;
			for (int j=0; j < 9; j++) {
				Node cell = rowElement.getElementsByTagName("cell").item(j);
				Element cellElement = (Element) cell;
				if (cellElement.getTextContent() == ""){
					result2[i][j] =  "0";
				} else result2[i][j] = cellElement.getTextContent();
			}
		}
		result = convertStringtoIntArray(result2);
		return result;
	}
	
    /**
     * This static method returns a two dimensional int array from a two dimensional 
     * String array.
     * @param-i the two dimensional String array.
     * @return-a the two dimensional int array.
     */
	private static int [][] convertStringtoIntArray (String [][] i) {
		int a [] [] = new int [i.length][i[0].length];
		for (int row = 0; row < i.length ; row++ ) { 
			for (int col = 0; col < i[row].length; col++ ) { 
				  a[row][col] = Integer.parseInt(i[row][col]);
			}    
		}
		return a;
	}
	
	/**
     * This static method prints out a two dimensional int array into a text file.
     * @param-i the two dimensional int array to be printed.
     */
	private static void writeToTextfile (int [][] i) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("result_fiendish.txt")); 
		out.println("run() method Iterates up to the 4th row using the brute force method, it does not 'solve' effectively beyond the 4th row.");
		for (int row = 0; row < i.length ; row++ ) { 
			for (int col = 0; col < i[row].length; col++ ) { 
				out.print(i[row][col]);    
			}    
			out.println();
		}	
		out.close();
	}
	
	
}
