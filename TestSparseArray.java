import java.io.*;
import java.util.*;
public class TestSparseArray{

	public static void main(String[] args) {
		int[][] data = new int[11][11];
		int len1 = data.length;

		data[1][2] = 1;
		data[2][3] = 2;
		printArray(data);
		
		/*
			二维数组转稀疏数组的思路：
			1. 遍历原始的二维数组， 得到有效数据的个数sum
			2. 根据sum的值，就可以创建稀疏数组 sparseArr int[sum + 1][3] 
			3. 将二维数组的有效数据存入到稀疏数组中

			稀疏数组转二维数组的思路：
		 */
		int[][] result = arrayToSparseArray(data);
		printArray(result);
		saveData(result, "./data.txt");

		data = sparseArrayToArray(result);
		printArray(data);
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

		 	input.close();
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

	public static int[][] sparseArrayToArray(int [][] data){
		int len1 = data.length;
		int [][] result = new int[data[0][0]][data[0][1]];
		for(int i = 1; i < len1; i++) {
			result[data[i][0]][data[i][1]] = data[i][2];
		}
		return result;
	}


	public static int[][] arrayToSparseArray(int [][] data){
		int sum = 0;
		int len1 = data.length;
		int[][] result = null;

		int validIndex = 1;
		for(int i = 0; i < len1; i++){
			int len2 = data[i].length;
			for(int j = 0; j < len2; j++){
				if (data[i][j] != 0) {
					sum++;
				}
			}
		}

		result = new int [(sum + 1)][3]; 
		//初始化第一行数据： 原始数组的大小和有效值数据的总个数
		result[0][0] = len1;
		result[0][1] = data[0].length;
		result[0][2] = sum;

		for(int i = 0; i < len1; i++){
			int len2 = data[i].length;
			for(int j = 0; j < len2; j++){
				if (data[i][j] != 0) {
					result[validIndex][0] = i;
					result[validIndex][1] = j;
					result[validIndex][2] = data[i][j]; 
					validIndex++;
				}
			}
		}

		return result;
	}

}