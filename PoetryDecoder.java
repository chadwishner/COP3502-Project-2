import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Scanner;

/**
 * STUDENTS FILL IN PROPER DOCUMENTATION HERE
 * @author Chad Wishner
 * COP 3502 Section Number: 149A
 */
public class PoetryDecoder {
	/**
	 * This main method asks the user to enter input. Then, it calls
	 * the decode method on that input. 
	 * @param args This main method does not take command line args.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//This prompt to the user is intentionally vague to avoid
		//incrementing those who use this program. 
		System.out.println("Please enter your input: ");
		String hex = sc.nextLine();

		//STUDENTS: Your decode method must return a fully formatted String,
		//which will be output here. 
		System.out.print(decode(hex));
		
		sc.close();
	}

	/**
	 * The decode method
	 * To keep it clean and simple, it calls other methods.
	 * @param hex The string of hex digits that is to be decoded.
	 * @return A String representing an entire decoded poem of English words. 
	 * (The returned string includes line breaks and spacing.) 
	 */
	public static String decode(String hex) {
		// create string that calls the toEnglisLetters method in order to convert hex to english
		String english = toEnglishLetters(hex);
		//create and initialize string poem to print out
		String poem = "";
		
		//call findWordsOfLength method for all three word lengths
		String [] longest = findWordsOfLength(english, 6);
		String [] medium = findWordsOfLength(english, 5);
		String [] shortest = findWordsOfLength(english, 4);
		
		//call formatPoem method in order to format the poem correctly
		poem = formatPoem(shortest, medium, longest);
		
		//return string poem to be calling in main method
		return poem;
	}


	/**
	 * This method takes a String of hex characters and, chunking them two at a time,
	 * finds a corresponding English letter according to the ASCII table. If the String of 
	 * hex characters is of odd length, the final hex character is IGNORED. 
	 * Note that the pairs of hex characters may not precisely correspond to ASCII letter values--
	 * that would be too easy to track! Instead, you must translate the hex pair into an ASCII English
	 * letter (no punctuation or digits) using character arithmetic. More details on that: 
	 * 
	 * To translate any pair of hex digits to English letters, here is the procedure:
	 * Take every two hex digits and convert it to its decimal form. If this number is inside the ASCII range 
	 * for an English letter, either lower case or upper case, then you're done converting to a letter.
	 * If it is not inside the ASCII range for letters, mod the value by 26 and map it to an ASCII letter based on 
	 *  the result. mod 0='a'; mod 1 = 'b'; mod 2='c' and so on. You do not need
	 *  a big switch statement or if's. Just use character arithmetic. 
	 *  
	 * @param String hex: String of hex characters. If the String is of odd length, the final hex
	 * character is ignored and not translated.
	 * 
	 * @return String: the String of English letters. Each letter came from a pair of hex
	 * digits in the original input String. 
	 */
	public static String toEnglishLetters(String hex){
		//create integer decimal in order to use the parseInt method to convert hex to decimal 
		int decimal;
		//create and initialize string with all possible English letters
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		//create and initialize string to return the correct list of English letters according to the hex input
		String english = "";
		
		//if the hex input length is odd, then remove the final hex character
		if (hex.length() % 2 != 0){
			hex = hex.substring(0, hex.length() - 1);
		}
		//for loop to loop through every pair of hex to convert it to decimal and then to English through 2 different ways
		for (int i = 0; i < hex.length(); i += 2){
			//create the substring of a pair of 2 characters in the hex string that is passed into the method
			String convert = hex.substring(i, i + 2);
			//convert hex to decimal using the parseInt method
			decimal = Integer.parseInt(convert, 16);
			
			//create an if statement to convert the decimals which fall outside the ascii range into English through charAt method 
			if ((decimal < 65 || decimal > 90) && (decimal < 97 || decimal > 122)){
				decimal = decimal % 26;
				english = english + Character.toString(alphabet.charAt(decimal));
			}
			
			//create an else statement to directly change the decimal to an English character by casting the decimal as a char
			else {
				english = english + Character.toString((char)decimal);
			}
		}
		//return the string of English letters
		return english;
	}

	/** 
	 * This method searches an input String of English letters, and returns all words of 
	 * the specified length that occur in the String. A "sliding window" will be considered, so 
	 * a given character could occur in more than one word that is found. This is desirable.
	 * @param String letters: A string of English letters
	 * @param String wordSize: The size of words to be extracted from the input String
	 * @return String[]: Contains a word in each element. Elements can be null if no words are found.  
	 */
	public static String[] findWordsOfLength(String letters, int wordSize) {
		//create a string array called english to return when the method is called
		String [] english;
		//create and initialize an integer index, this integer will be used to place the newly found words in the next slot in the array
		int index = 0;
		
		//make the array hold the maximum amount of words that could be found in the letters string
		english = new String [letters.length() - (wordSize - 1)];
		//create a for loop in order to run the loop finding and saving a word if isWord = true
		for (int i = 0; i < letters.length() - (wordSize - 1); i++){
			String convert = letters.substring(i, i + wordSize);
			if(isWord(convert) == true){
				english[index] = convert;
				index++;
			}
		}	
		//return the array english when the method is called
		return english;
	}

	/**
	 * This method formats a poem according to Poetry Movement specifications. 
	 * @param Takes three arrays of Strings of shortest, medium, and longest lengths. 
	 * @return Returns a String with line breaks and tabs as needed for poetic formatting. 
	 */
	public static String formatPoem(String[] shortest, String[] medium, String[] longest){
		//create and initialize the String poem that will be returned when this method is called
		String poem = "";
		//create integers that will be used to make sure I print the proper amount of spacings and words per line
		int space = 0, stop = 0, longCount = 0, mediumCount = 0, shortCount = 0, longIndex = 0, mediumIndex = 0, shortIndex = 0;
		//create a variable maxLen in order to stop the case that the program tries to print more words then are available 
		int maxLen = longest.length + medium.length + shortest.length;
		
		//run loop in order to check how many words are in each array
		for(int j = 0; j < (longest.length); j++){
			if(longest[j] != null){
				longIndex++;
			}
		}
		for(int j = 0; j < (medium.length); j++){
			if(medium[j] != null){
				mediumIndex++;
			}
		}
		for(int j = 0; j < (shortest.length); j++){
			if(shortest[j] != null){
				shortIndex++;
			}
		}
		
		//create a for loop in order to run the loop only as many words that are available
		for(int i = 0; i<maxLen; i++){
			
			//create an if statement in order to run a for loop to tab at the start of a new line (where stop = 0 at the end of main for loop
			if(stop != 0){
				//for loop to print the right amount tabs for each line
				for(int j=0; j<i; j++){
					poem = poem + "\t";
				}
			}
			
			//create three if statements to handle the different length words
			//run the if statement if the ___Count is less then the length of the array, and the array slot is not empty
			if(longCount < longest.length && longest[longCount] != null){
				//add the word saved in the array spot of [longCount] to the string poem
				poem = poem + longest[longCount];
				//increment space to let the program know that when a word has been added to poem
				space++;
				longCount++;
				}
			//if space isn't zero, print a space after the word
			if(space != 0){
				poem = poem + " ";
				}
			
			//repeat the same thing as above for the medium and the short length words
			if(mediumCount < medium.length && medium[mediumCount] != null){
				poem = poem + medium[mediumCount];
				space++;
				mediumCount++;
			}
			if(space != 0){
				poem = poem + " ";
			}
			
			if(shortCount < shortest.length && shortest[shortCount] != null){
				poem = poem + shortest[shortCount];
				space++;
				shortCount++;
				//do not add space so there is no extra space at the end of the line
			
			}
			//set stop = space in order to keep track of how many times words are printed
			stop = space;
			
			//check to see if all the words in each array have been printed, if so return poem without without a new line or new tabs
			if(shortCount >= shortIndex && mediumCount >= mediumIndex && longCount >= longIndex){
				return poem;
			}
			//trim white spaces after each line
			poem = poem.trim();
			
			//if stop is not zero (it has printed a word), print to a new line
			if(stop != 0){
				poem = poem + "\n";
			}
			//set space = 0 to reset 
			space = 0;
		}
		//return string poem when the method is called
		return poem;	
	}

	/**
	 * This method checks whether the given String occurs in a dictionary of English.
	 * This method will terminate your program if it is unable to access the remote URL.
	 * You must be online for this code to work. 
	 * @param possWord The word to be checked.
	 * @return boolean Returns true if the word given is an English word, false otherwise.
	 */
	private static boolean isWord(String possWord) {
		boolean isWord = true;
		try {
			//connect to the URL. 
			String s = getUrl(possWord);
			Document d = Jsoup.connect(s).timeout(6000).get();
			Elements tdTags = d.select("h3");

			// Loop over all tdTags, in this case: the h3 tag 
			for( Element element : tdTags ){
				String check = element.toString();

				//Wordnet has a special h3 tag that appears only if the word is not in the dictionary
				//We search for this tag. If it is found, then the word searched is not in the dictionary
				if(check.equals("<h3>Your search did not return any results.</h3>") ){
					isWord = false;
				}
			}
		}
		catch (IOException e) {
			System.err.print("CHECK INTERNET CONNECTION. Could not connect to jsoup URL.");
			System.exit(0);
		}
		return isWord;
	}

	/**
	 * This is a helper method that the teaching staff code uses. 
	 * This method will terminate your program if it is unable to access the remote URL.
	 * You must be online for this code to work. 
	 * @param String: search 
	 * @return A string containing the URL for the wordnet search.
	 */
	private static String getUrl(String search) {
		//Standard URL for wordnet to search
		String url = "http://wordnetweb.princeton.edu/perl/webwn?s=";
		String newURL = null;
		try {
			//Get new page from word wordnet and get its location
			Document doc = Jsoup.connect(url + search).timeout(6000).get();
			newURL = doc.location().toString();
		}
		catch (IOException e) {
			System.err.print("CHECK INTERNET CONNECTION. Could not connect to jsoup URL.");
			System.exit(0);
		}
		//Return the string of the new URL. 
		return (newURL);
	}

}