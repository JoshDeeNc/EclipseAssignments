package stage1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class FileTest {
	private static int score = 0;

	@Test
	void testSetBase() {
		File f = new File();

		f.setBase(null);
		assertEquals("default", f.getBase(), "base should be \"default\" if null String passed");

		f.setBase("log");
		assertEquals("log", f.getBase());

		f.setBase("");
		assertEquals("default", f.getBase(), "base should be \"default\" if empty String passed");
		score+=5;
	}

	@Test
	void testSetExtension() {
		File f = new File();

		f.setExtension(null);
		assertEquals("txt", f.getExtension(), "extension should be \"txt\" if null String passed");

		f.setExtension("java");
		assertEquals("java", f.getExtension());

		f.setExtension("");
		assertEquals("txt", f.getExtension(), "extension should be \"txt\" if empty String passed");
		score+=5;
	}

	@Test
	void testSetSize() {
		File f = new File();

		f.setSize(-1);		
		assertEquals(0, f.getSize(), "size cannot be negative");

		f.setSize(1);		
		assertEquals(1, f.getSize());

		f.setSize(0);		
		assertEquals(0, f.getSize(), "size cannot be negative");

		f.setSize(2147483647);		
		assertEquals(2147483647, f.getSize());
		score+=5;
	}

	@Test
	void testSetPermissions() {
		File f = new File();

		f.setPermissions(-1);		
		assertEquals(0, f.getPermissions(), "permissions cannot be negative");

		f.setPermissions(8);		
		assertEquals(7, f.getPermissions(), "permissions cannot be more than 7");

		for(int p=0; p<8; p++) {
			f.setPermissions(p);
			assertEquals(p, f.getPermissions());
		}
		score+=5;
	}

	@Test
	void testFileStringStringIntInt() {
		File f = new File("log", "java", 4500, 5);
		assertEquals("log", f.getBase());
		assertEquals("java", f.getExtension());
		assertEquals(4500, f.getSize());
		assertEquals(5, f.getPermissions());

		f = new File(null, null, -10, 20);
		assertEquals("default", f.getBase(), "constructor should call setters");
		assertEquals("txt", f.getExtension(), "constructor should call setters");
		assertEquals(0, f.getSize(), "constructor should call setters");
		assertEquals(7, f.getPermissions(), "constructor should call setters");
		score+=5;
	}

	@Test
	void testCompareTo() {
		File a = new File();
		a.setSize(2000);

		File b = new File();
		b.setSize(2001);

		File c = new File();
		c.setSize(2000);

		assertEquals(-1, a.compareTo(b));
		assertEquals(1, b.compareTo(c));
		assertEquals(0, a.compareTo(c));
		score+=5;
	}

	@Test
	void testEquals() {
		File a = new File("log","txt",10, 5);
		File b = new File("log","txt",10, 5);
		assertTrue(a.equals(b));
		File c = new File("LOG","Txt",10, 5);
		assertTrue(a.equals(c)); //case doesn't matter
		File d = new File("LOG","Txt",11, 5);
		assertFalse(a.equals(d)); //different size
		File e = new File("LOG","Txt",10, 4);
		assertFalse(a.equals(e)); //different permissions
		File f = new File("data","txt",10, 5);
		assertFalse(a.equals(f)); //different base name
		File g = new File("log","java",10, 5);
		assertFalse(a.equals(g)); //different extension
		score+=5;
	}

	@Test
	void testToString() {
		File f = new File("log", "java", 4500, 5);
		assertEquals("r-x log.java 4 KB", f.toString());

		f = new File("DATA", "TXT", 10000000, 2);
		assertEquals("-w- DATA.TXT 9 MB", f.toString());

		f = new File("test", "md", 2147483647, 0);
		assertEquals("--- test.md 1 GB", f.toString());

		f = new File("hello", "java", 1023, 7);
		assertEquals("rwx hello.java 1023 B", f.toString());
		score+=5;
	}

	@AfterAll
	public static void wrapUp() throws IOException {
		System.out.println("Score = "+score+" (out of 40)");
	}
}
