package stage1;

public class File {
	public String base; //for example, "log" in "log.txt"
	public String extension; //for example, "txt" in "log.txt"
	public int size; //in bytes
	public int permissions; //explanation in toString

	//DO NOT MODIFY
	public String getBase() {
		return base;
	}

	/**
	 * 
	 * @param b
	 * if b is null or if empty (""), base should become "default",
	 * for all other values of b, base should become b
	 */
	public void setBase(String b) {
		if(b == null || b == "" ) {
			base = "default";
		}
		else base = b;
	}

	//DO NOT MODIFY
	public String getExtension() {
		return extension;
	}

	/**
	 * 
	 * @param e
	 * if e is null or if empty (""), extension should become "txt",
	 * for all other values of e, extension should become e
	 */
	public void setExtension(String e) {
		if(e == null || e == "" ) {
			extension = "txt";
		}
		else extension = e;
	}

	//DO NOT MODIFY
	public int getSize() {
		return size;
	}

	/**
	 * 
	 * @param s
	 * if s is less than 0, size should become 0
	 * if s is more than or equal to 0, size should become s
	 */
	public void setSize(int s) {
		if(s < 0) {
			size = 0;
		}
		else size = s;
	}

	//DO NOT MODIFY
	public int getPermissions() {
		return permissions;
	}

	/**
	 * 
	 * @param p
	 * if p is less than 0, permissions should become 0
	 * if p is more than 7, permissions should become 7
	 * if p is between 0 and 7 (inclusive on both sides), permissions should become p
	 */
	public void setPermissions(int p) {
		if(p < 0) {
			permissions = 0;
		}
		else if(p > 7) {
			permissions = 7;
		}
		
		else if(p >= 0 && p <= 7) {
			permissions = p;
		}
		
	}

	//DO NOT MODIFY
	public File() {
		setBase("default");
		setExtension(".txt");
		setSize(0);
		setPermissions(0);
	}
	
	/**
	 * 
	 * @param b: value for base
	 * @param e: value for extension
	 * @param s: value for size
	 * @param p: value for permissions
	 */
	public File(String b, String e, int s, int p) {
		setBase(b);
		setExtension(e);
		setSize(s);
		setPermissions(p);
	}

	//DO NOT MODIFY
	public String getName() {
		return base + "." + extension;
	}

	/**
	 * 
	 * @param other
	 * @return 
	 * 1 if calling object is bigger in size than the parameter object
	 * -1 if calling object is smaller in size than the parameter object
	 * 0 if calling object has the same size as the parameter object
	 */
	public int compareTo(File other) {
		if(this.getSize() > other.getSize()) {
			return 1;
		}
		if(this.getSize() < other.getSize()) {
			return -1;
		}
		else {
			return 0;
		}

	}

	/**
	 * 
	 * @param other
	 * @return true if calling object and parameter object are identical
	 * in every aspect (base, extension, size, permissions) 
	 * 
	 * NOTE: file name should be checked in case INsensitive manner
	 * 
	 * HINT: Strings are NOT compared using == (s1 == s2 is WRONG)
	 * Google "String comparison java" and "String case insensitive comparison java" to 
	 * see the right way!
	 */
	public boolean equals(File other) {
		return((this.getBase()).equalsIgnoreCase(other.getBase()) 
				&& (this.getExtension()).equalsIgnoreCase(other.getExtension())
				&& this.getSize() == other.getSize() 
				&& this.getPermissions() == other.getPermissions());
	}

	/**
	 * HD component of stage 1
	 * return String representation of the calling object.
	 * 
	 * Size:
	 * 
	 * 1024 bytes = 1 kilobyte (KB)
	 * 1024 kilobytes = 1 megabyte (MB)
	 * 1024 megabytes = 1 gigabyte  (GB
	 * 
	 * for files below 1024 bytes, you should represent size in bytes (B)
	 * for files 1024 bytes or more but less than 1024 kilobytes, you should represent size in kilobytes (KB)
	 * for files 1024 kilobytes or more but less than 1024 megabytes, you should represent size in megabytes (MB)
	 * for files 1024 megabytes or more, you should represent size in gigabytes (GB)
	 * 
	 * only the integer part of size should be displayed. 
	 * 
	 * Permissions:
	 * 
	 * Permissions is a number between 0 and 7. 
	 * 1st bit represents read permission,
	 * 2nd bit represents write permission,
	 * 3rd bit represents execute permissiono
	 * 
	 * 0: 000 (---)
	 * 1: 001 (--x)
	 * 2: 010 (-w-)
	 * 3: 011 (-wx)
	 * 4: 100 (r--)
	 * 5: 101 (r-x)
	 * 6: 110 (rw-)
	 * 7: 111 (rwx)
	 * 
	 * Syntax of toString:
	 * <permissions in (r/-)(w/-)(x/-) format> <file name> <integer size in correct magnitude>
	 * 
	 * Some examples:
	 * if base = "log", extension = "txt", size = 1056, permissions = 4,
	 * the String returned should return 
	 * 
	 * 						"r-- log.txt 1KB"
	 * 
	 * if base = "data", extension = "csv", size = 4500000, permissions = 7,
	 * the String returned should return: 
	 * 
	 * 						"rwx data.csv 4MB"
	 */
	public String toString() {
		String permissionStr = "";
		int byteNum = 1024;
		int kiloNum = byteNum * byteNum;
		int megaNum = byteNum * byteNum * byteNum;
		int shortSize = 0;
		String properName = "";
		
		if(getPermissions() == 0) {
			permissionStr = "---";
		}
		if(getPermissions() == 1) {
			permissionStr = "--x";
		}
		if(getPermissions() == 2) {
			permissionStr = "-w-";
		}
		if(getPermissions() == 3) {
			permissionStr = "-wx";
		}
		if(getPermissions() == 4) {
			permissionStr = "r--";
		}
		if(getPermissions() == 5) {
			permissionStr = "r-x";
		}
		if(getPermissions() == 6) {
			permissionStr = "rw-";
		}
		if(getPermissions() == 7) {
			permissionStr = "rwx";
		}
		
		
		if(byteNum > getSize()) {
			properName = size + " B";
		}
		if(byteNum <= getSize() && getSize() < kiloNum) {
			shortSize = getSize()/byteNum;
			properName = shortSize + " KB";
		}
		if(kiloNum <= getSize() && getSize() < megaNum) {
			shortSize = getSize()/kiloNum;
			properName = shortSize + " MB";
		}
		if(megaNum <= getSize() ) {
			shortSize = getSize()/megaNum;
			properName = shortSize + " GB";
		}
		
		return permissionStr + " " + getName() + " " + properName;
	}
}
