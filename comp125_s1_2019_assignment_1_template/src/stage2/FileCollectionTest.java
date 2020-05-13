package stage2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import stage1.File;

class FileCollectionTest {
	private FileCollection c1, c2;
	private static int score = 0;

	@BeforeEach
	void initialize() throws IOException {
		c1 = new FileCollection("input1.txt");
		c2 = new FileCollection("input2.txt");
	}
	
	@Test
	void testGetCountByExtension() throws IOException {
		assertEquals(4, c1.getCountByExtension("py"));
		assertEquals(4, c1.getCountByExtension("PY")); //case insensitive
		assertEquals(0, c1.getCountByExtension("dll"));
		assertEquals(1, c1.getCountByExtension("docx"));
		score+=10;
	}

	@Test
	void testCountFilesInSizeRange() {
		assertEquals(4, c1.countFilesInSizeRange(1000,200000));
		assertEquals(0, c1.countFilesInSizeRange(0,19));
		assertEquals(1, c1.countFilesInSizeRange(20,20));
		score+=10;
	}

	@Test
	void testIndexOf() {
		assertEquals(2, c1.indexOf(new File("hello","doc",600000,3)));
		assertEquals(0, c1.indexOf(new File("BOOT","PY",32030,7))); //case is not important
		assertEquals(-1, c1.indexOf(new File("BOOT","PY",32031,7))); //diff size
		assertEquals(-1, c1.indexOf(new File("BOOT","PY",32030,6))); //diff permissions
		assertEquals(15, c1.indexOf(new File("champions league","jpg",4756455,1)));
		score+=10;
	}

	@Test
	void testGetBiggestFilesName() {
		assertNotNull(c1.getBiggestFilesName());
		assertEquals("messi.mp4", c1.getBiggestFilesName());
		assertNotNull(c2.getBiggestFilesName());
		assertEquals("test.py", c2.getBiggestFilesName());
		score+=10;
	}

	@Test
	void testCount() {
		assertEquals(7, c1.count(new File("hello","doc",600000,3)));
		assertEquals(1, c1.count(new File("second","md",545,6)));
		assertEquals(3, c2.count(new File("bar","htm",130424,0)));
		assertEquals(0, c2.count(new File("bar","htm",130423,0))); //diff size
		assertEquals(0, c2.count(new File("bar","htm",130424,1))); //diff permissions
		assertEquals(0, c2.count(new File("bar","html",130424,0))); //diff extension
		score+=10;
	}

	@Test
	void testSortByFileSize() {
		assertEquals("--- bar.HTM 127 KB\n" + 
				"r-x messi.mp4 38 MB\n" + 
				"rw- init.bat 2 MB\n" + 
				"rwx run.exe 22 KB\n" + 
				"--x bash.sh 246 KB\n" + 
				"rw- special.py 413 KB\n" + 
				"r-- fun.md 41 MB\n" + 
				"r-x foo.html 600 B\n" + 
				"--- bar.htm 127 KB\n" + 
				"r-x log.java 4 KB\n" + 
				"-wx soccer.doc 530 KB\n" + 
				"-w- club.xls 2 KB\n" + 
				"r-x flowchart.png 123 B\n" + 
				"rwx nab.gif 310 MB\n" + 
				"-wx laliga.mp4 13 KB\n" + 
				"rwx test.py 1 GB\n" + 
				"--- bar.htm 127 KB", c2.toString().trim());
		
		c2.sortByFileSize(false);

		assertEquals("rwx test.py 1 GB\n" + 
				"rwx nab.gif 310 MB\n" + 
				"r-- fun.md 41 MB\n" + 
				"r-x messi.mp4 38 MB\n" + 
				"rw- init.bat 2 MB\n" + 
				"-wx soccer.doc 530 KB\n" + 
				"rw- special.py 413 KB\n" + 
				"--x bash.sh 246 KB\n" + 
				"--- bar.HTM 127 KB\n" + 
				"--- bar.htm 127 KB\n" + 
				"--- bar.htm 127 KB\n" + 
				"rwx run.exe 22 KB\n" + 
				"-wx laliga.mp4 13 KB\n" + 
				"r-x log.java 4 KB\n" + 
				"-w- club.xls 2 KB\n" + 
				"r-x foo.html 600 B\n" + 
				"r-x flowchart.png 123 B", c2.toString().trim());
		
		c2.sortByFileSize(true);
		
		assertEquals("r-x flowchart.png 123 B\n" + 
				"r-x foo.html 600 B\n" + 
				"-w- club.xls 2 KB\n" + 
				"r-x log.java 4 KB\n" + 
				"-wx laliga.mp4 13 KB\n" + 
				"rwx run.exe 22 KB\n" + 
				"--- bar.HTM 127 KB\n" + 
				"--- bar.htm 127 KB\n" + 
				"--- bar.htm 127 KB\n" + 
				"--x bash.sh 246 KB\n" + 
				"rw- special.py 413 KB\n" + 
				"-wx soccer.doc 530 KB\n" + 
				"rw- init.bat 2 MB\n" + 
				"r-x messi.mp4 38 MB\n" + 
				"r-- fun.md 41 MB\n" + 
				"rwx nab.gif 310 MB\n" + 
				"rwx test.py 1 GB", c2.toString().trim());	
		score+=3;
	}

	@Test
	void testMerge() {
		FileCollection c = c1.merge(c2);
		assertNotNull(c);
		assertEquals("rwx boot.py 31 KB\n" + 
				"r-x log.java 4 KB\n" + 
				"-wx hello.doc 585 KB\n" + 
				"-wx init.py 800 B\n" + 
				"rwx first.txt 105 B\n" + 
				"r-- instructions.pdf 976 KB\n" + 
				"--- audit.doc 20 B\n" + 
				"rwx test.py 200 B\n" + 
				"rw- second.md 545 B\n" + 
				"-w- manual.docx 5 KB\n" + 
				"rwx init.php 638 KB\n" + 
				"r-- australia.pdf 493 KB\n" + 
				"r-x messi.mp4 38 MB\n" + 
				"r-x analysis.py 9 KB\n" + 
				"-wx hello.doc 585 KB\n" + 
				"--x champions league.jpg 4 MB\n" + 
				"-wx hello.doc 585 KB\n" + 
				"-wx hello.doc 585 KB\n" + 
				"-wx HELLO.DOC 585 KB\n" + 
				"-wx Hello.Doc 585 KB\n" + 
				"-wx hello.doc 585 KB\n" + 
				"--- bar.HTM 127 KB\n" + 
				"r-x messi.mp4 38 MB\n" + 
				"rw- init.bat 2 MB\n" + 
				"rwx run.exe 22 KB\n" + 
				"--x bash.sh 246 KB\n" + 
				"rw- special.py 413 KB\n" + 
				"r-- fun.md 41 MB\n" + 
				"r-x foo.html 600 B\n" + 
				"--- bar.htm 127 KB\n" + 
				"r-x log.java 4 KB\n" + 
				"-wx soccer.doc 530 KB\n" + 
				"-w- club.xls 2 KB\n" + 
				"r-x flowchart.png 123 B\n" + 
				"rwx nab.gif 310 MB\n" + 
				"-wx laliga.mp4 13 KB\n" + 
				"rwx test.py 1 GB\n" + 
				"--- bar.htm 127 KB", c.toString().trim());
		score+=3;
	}

	@Test
	void testContains() throws IOException {
		FileCollection c3 = new FileCollection("input3.txt");
		FileCollection c4 = new FileCollection("input4.txt");
		assertTrue(c1.contains(c3));
		assertTrue(c4.contains(c4));
		assertFalse(c2.contains(c4));
		score+=2;
	}

	@Test
	void testIdentical() throws IOException {
		FileCollection c5 = new FileCollection("input5.txt");
		FileCollection c6 = new FileCollection("input6.txt");
		assertTrue(c1.identical(c5));
		assertFalse(c1.identical(c6));
		//only one file - "audit","doc",20,0 missing in c6 
		score+=2;
	}

	@AfterAll
	public static void wrapUp() throws IOException {
		System.out.println("Score = "+score+" (out of 60)");
	}
}
