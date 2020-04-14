import java.io.*;
import java.util.*;

public class TestFile{

	public static void main(String[] args) {
		// int [][] data = new int[5][3];
		// saveData(data, "./data.txt");
		int[][] result = readData("./data.txt");
		printArray(result);
	}

	public static void printArray(int[][] data){
		int len1 = data.length;
		for(int i = 0; i<len1; i++){
			int len2 = data[i].length;
			for(int j = 0; j<len2; j++){
				System.out.print("data[" + i + "][" + j +"]=" + data[i][j] +"\t");
			}
			System.out.println();
		}
	}

	public static int[][] readData(String pathname) {
		ArrayList<int[]> dataList = new ArrayList<int[]>();
		int i = 0;
		try{
			File file = new File(pathname);
			BufferedReader input = new BufferedReader(new FileReader(file));			
			String str = null;
			int[] lineData = new int [3];
			int count = 0;
			while((i = input.read()) != -1){
		  	if ((char)i == '\n') {
		  		lineData = new int [3];
		  		count = 0;
		  		dataList.add(lineData);
		  		for(int k = 0; k<3; k++){
		  			System.out.print(lineData[k]);		  			
		  		}
		  	}else if( (char)i != ' '){
		  		lineData[count++] = i - 48;
		  	}
		 	}

		}catch(Exception e){
			e.printStackTrace();
		}
		int[][] result = new int[dataList.size()][3];
		for(i = 0; i < dataList.size(); i++){
			result[i] = dataList.get(i);
		}
		return result;
	}

	public static void saveData(int[][] data, String path) {
		File file = new File(path);
		if(false || file.exists()){
			file.delete();
		}else{
			try{
				StringBuffer buffer = convertArrayToStr(data);
				byte[] bytes = buffer.toString().getBytes();
				
				System.out.println(bytes);
				boolean isSuccess = file.createNewFile();
				OutputStream out = new FileOutputStream(file);
				out.write(bytes);
				out.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public static StringBuffer convertArrayToStr(int[][] data) {
		int len1 = data.length;
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < len1; i++) {
			buffer.append(data[i][0] + " " + data[i][1] + " " + data[i][2] + "\n");
		}
		System.out.println(buffer.toString());
		return buffer;
	}



}