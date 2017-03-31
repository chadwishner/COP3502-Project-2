import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;

//This is a junit file

public class PoetryDecoderTest{
	
	//This tests a hex string that has both Hex code inside and outside the ASCII English ranges
	//Also includes an extra bit at the end
	@Test
	public void toEnglishLetterTest() {
		String ans = "abcdmnopstuv";
		String students = PoetryDecoder.toEnglishLetters("000102036D6E6f70fcFDFEFF0");
		assertTrue("toEnglishLetter failed", ans.equals(students));		
	}
	
	
	//This tests a English string that contains words of size 3
	//If you don't want to wait/test this test case,comment out this test
//	@Test
//	public void fWOL_3(){
//		String toCheck = "actbadcardadfadgethatjetmomnotpopratseatopvetwinyetzoo";
//		int size = (toCheck.length() - 2);
//			
//		String[] ans = new String[size];
//		String[] temp = {"act", "bad", "car", "dad", "fad", "get", "hat", "jet", "mom", "not", "pop", "rat", "ats", "sea", "eat", "top", "opv", "vet", "win", "yet", "zoo"};
//		for(int i = 0; i < temp.length; i++){
//			ans[i] = temp[i];
//		}
//		String[] students = PoetryDecoder.findWordsOfLength(toCheck, 3);
//		assertArrayEquals(ans, students);
//	}
	
	//This test formatPoem with the medium size array having more Strings
	//than the other 2 arrays
	@Test
	public void formatPoemTest(){
		//Sizes of words: 2, 5, 8
		String[] shortArr = {"xx", "XX", "Xx"};
		String[] medArr   = {"yyyyy", "YYYYY", "YyYyY", "yYyYy", "YyyyY"};
		String[] longArr  = {"xxxxxxxx", "XXXXXXXX", "XxXxXxXx"};
				
		String ans = "xxxxxxxx yyyyy xx\n\tXXXXXXXX YYYYY XX\n\t\tXxXxXxXx YyYyY Xx\n\t\t\tyYyYy\n\t\t\t\tYyyyY";
		String students = PoetryDecoder.formatPoem(shortArr, medArr, longArr);
		assertTrue("Your answer \n\n" + students + "\n\n Desired output \n\n" + ans,ans.equals(students.trim()));
	}
	
	//decode Test
		//Depending on your internet connection, this test might timeout
		//If you don't want to wait/test this test case,comment out this test	
//		@Test
//		public void decodeTest(){
//			String ans = "hearts heart hear\n\tstereo beats arts\n\t\tlisten close beat\n\t\t\thearth heart eats\n\t\t\t\tthough earth list\n\t\t\t\t\tmelody never lose\n\t\t\t\t\t\tstereo every hear\n\t\t\t\t\t\t\tradio thou\n\t\t\t\t\t\t\t\tmeant neve\n\t\t\t\t\t\t\t\t\talong ever\n\t\t\t\t\t\t\t\t\t\tlongs very\n\t\t\t\t\t\t\t\t\t\t\tmake\n\t\t\t\t\t\t\t\t\t\t\t\tturn\n\t\t\t\t\t\t\t\t\t\t\t\t\tfeel\n\t\t\t\t\t\t\t\t\t\t\t\t\t\tmean\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tjust\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tsing\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tinga\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tlong";
//			String students = PoetryDecoder.decode("68656172747373746572656f62656174736c697374656e636c6f73656865617274686f75676874736e65766572796d616b65796f7572726164696f7475726e7768656e6665656c746869736d656c6f64796d65616e746a75737473696e67616c6f6e6773746572656f	");
//			assertTrue(ans.equals(students.trim()));
//		}
}
