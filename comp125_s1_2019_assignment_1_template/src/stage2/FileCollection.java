package stage2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import stage1.File;

public class FileCollection {
	private File[] files;

	/**
	 * DO NOT MODIFY
	 * Loads collection from input file
	 * @param input: name of input file
	 * @throws IOException
	 */
	public FileCollection(String input) throws IOException {
		FileInputStream inputStream1 = new FileInputStream(input);
		BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream1));

		int count = 0;
		while (bufferedReader1.readLine() != null) {
			count++;
		}
		bufferedReader1.close();

		FileInputStream inputStream2 = new FileInputStream(input);
		BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2));
		files = new File[count];
		String line = null;
		for (int i = 0; i < count; i++) {
			line = bufferedReader2.readLine();
			String tokens[] = line.split(",");
			String base = tokens[0];
			String ext = tokens[1];
			int size = Integer.parseInt(tokens[2]);
			int perm = Integer.parseInt(tokens[3]);
			files[i] = new File(base, ext, size, perm);
		}
		bufferedReader2.close();
	}

	/**
	 * DO NOT MODIFY
	 * @param files
	 */
	public FileCollection(File[] files) {
		this.files = files;
	}

	/**
	 * DO NOT MODIFY
	 * @return String representation of the collection
	 */
	public String toString() {
		String result = "";
		for(int i=0; i < files.length; i++) {
			result = result + files[i].toString()+"\n";
		}
		return result+"\n";
	}

	/**
	 * DO NOT MODIFY
	 * @return the total size of the collection.
	 * FOR EXAMPLE, if there are 3 files in the collection,
	 * with sizes 20, 8000 and 700 bytes, the size 
	 * of the collection is 8720 bytes 
	 */
	public int collectionSize() {
		int result = 0;
		for(File file: files)
			result+=file.getSize();
		return result;
	}

	/**
	 * 
	 * @param extension
	 * @return number of files with the given extension
	 * (case insensitive)
	 * NOTE: don't compare Strings using ==
	 * Google "String case insensitive comparison java"
	 */
	public int getCountByExtension(String extension) {
		int count = 0;
		for (File file: files) {
			if( file.getExtension().equalsIgnoreCase(extension)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 
	 * @param min
	 * @param max
	 * @return number of files in the given size range 
	 * (inclusive on both sides)
	 */
	public int countFilesInSizeRange(int min, int max) {
		int count = 0;
		for (File file: files) {
			if( file.getSize() >=min && file.getSize() <=max) {
				count++;
			}
		}
		return count;
	}

	/**
	 * 
	 * @param target
	 * @return first index at which the file passed
	 * as parameter exists in the collection, -1 if file doesn't exist
	 * NOTE: use the equals method defined in File class for comparison, not ==
	 */
	public int indexOf(File target) {
		
		for(int i = 0; i < files.length; i++) {
			if(files[i].equals(target)) {
				return i;
			}
		}
		return -1;
	}


	/**
	 * 
	 * @return the name of the biggest file
	 * (the file that has the biggest size, not the name)
	 */
	public String getBiggestFilesName() {
		int big = 0;
		String bigName = "";
		for(File file: files) {
		if (file.getSize() > big) {
			big = file.getSize();
			bigName = file.getName();
		}
		}
		return bigName;
	}

	/**
	 * 
	 * @param file
	 * @return the number of times the given file exists 
	 * in the collection.
	 * NOTE: use the equals method defined in File class for comparison, not ==
	 */
	public int count(File file) {
		int num = 0;
		for(int i = 0; i < files.length; i++) {
			if(files[i].equals(file)) {
				num++;
			}
		}
		return num;
		
		
	}

	/**
	 * HD component 1
	 * @param ascending: true if sorting required in ascending order 
	 * and false if sorted required in descending order
	 * 
	 *  the array files must be sorted when the method 
	 *  finishes its execution
	 */
	public void sortByFileSize(boolean ascending) {
		File temp;
		if(ascending == true) {
		for(int i = 0; i < files.length; i++)
        {
			for(int j = 0; j < files.length-1; j++) {
        	if(files[j].getSize() > files[j+1].getSize()) {
        		temp = files[j];
        		files[j] = files[j+1];
        		files[j+1] = temp;
        	}
			}
        }
       	}
		
		if(ascending == false) {
			for(int i = 0; i < files.length; i++)
	        {
				for(int j = 0; j < files.length-1; j++) {
	        	if(files[j].getSize() < files[j+1].getSize()) {
	        		temp = files[j];
	        		files[j] = files[j+1];
	        		files[j+1] = temp;
	        	}
				}
	        }
		}
			System.out.println(files);

		
	}

	/**
	 * HD component 2
	 * @param other
	 * @return a FileCollection object containing all files in the 
	 * calling object followed by all files in the parameter object
	 */
	public FileCollection merge(FileCollection other) {
		File[] bothFiles = new File[this.files.length + other.files.length];
		for(int i = 0; i < this.files.length; i++) {
			 bothFiles[i] = this.files[i];
		}
		for(int i = 0; i < other.files.length; i++) {
			bothFiles[this.files.length + i] = other.files[i];
		}
		other.files = bothFiles;
		return other;
	}


	/**
	 * HD component 3
	 * @param other
	 * @return true if the calling object contains
	 * all files in the parameter object, false otherwise
	 */
	public boolean contains(FileCollection other) {
        int[] doubleFile = new int[this.files.length];

        if(other.files.length > this.files.length) {
        	return false;
        }
        for(int i = 0; i < doubleFile.length; i++) {
        	doubleFile[i] = 0;
        }
        for(int i = 0; i < other.files.length; i++) {
    		boolean containsFile = false;
            for(int j=0; j< this.files.length; j++){
                if(other.files[i].equals(this.files[j]) && doubleFile[j] != 1 ){
                    doubleFile[j] = 1;
                	containsFile = true;
                	break;
                }
            }
            if(!containsFile){
                return false;
            }
        }

        return true;
	}

	/**
	 * HD component 4
	 * @param other
	 * @return true if the calling object and parameter object
	 * represent the same collection (it's ok if they are NOT in the same order)
	 */
	public boolean identical(FileCollection other) {
		if(other.files.length != this.files.length) {
			return false;
		}
		boolean identicalFiles = false;
		for(int i = 0; i < other.files.length; i++) {
			for(int j = 0; j < this.files.length; j++) {
				if(other.files[i].equals(this.files[j])) {
					identicalFiles = true;
				}
			}
			if(!identicalFiles) {
				return false;
			}
		}
		return true;
	}
}
