import java.util.ArrayList;

/**
 * WordGram objects represent a k-gram of strings/words.
 * 
 * @author Mylie Walker
 *
 */

public class WordGram {
	
	private String[] myWords;   
	private String myToString;  // cached string
	private int myHash;         // cached hash value

	/**
	 * Create a WordGram based on an array of Strings, source.
	 The WordGram includes the elements in source beginning at 
	 index start. Its size is equal to the size parameter.
	 * @param source is the array that WordGram is based on
	 * @param start is the starting index for the array
	 * @param size is the size of WordGram
	 */
	public WordGram(String[] source, int start, int size) {
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = start; i < size + start; i++)
		{
			temp.add(source[i]);
		}
		myWords = temp.toArray(new String[size]);
		myToString = null;
		myHash = 0;
	}

	/**
	 * Return string at specific index in this WordGram
	 * @param index in range [0..length() ) for string 
	 * @return string at index
	 */
	public String wordAt(int index) {
		if (index < 0 || index >= myWords.length) {
			throw new IndexOutOfBoundsException("bad index in wordAt "+index);
		}
		return myWords[index];
	}

	/**
	 * Return the length of myWords
	 * @return
	 */
	public int length(){
		return myWords.length;
	}


	/**
	 * Check if two WordGrams are the same, meaning they
	 * are the same size and are made up of the same words
	 */
	 @Override
	public boolean equals(Object o) {
		if (! (o instanceof WordGram) || o == null){
			return false;
		}
		WordGram wg = (WordGram) o;
		if (this.length() != wg.length())
		{
			return false;
		}
		for (int i = 0; i < this.length(); i++)
		{
			if (! (this.wordAt(i).equals(wg.wordAt(i))))
			{
				return false;
			}
		}
		return true;
	}

	 /**
	 * Initialize myHash with the word's hashCode
	 */
	@Override
	public int hashCode(){
		if (myHash == 0 && myWords.length > 0)
		{
			myHash = this.toString().hashCode();
		}		
		return myHash;
	}
	

	/**
	 * Remove the first word in myWords, and adds a new word at the end
	 * @param last is last String of returned WordGram
	 * @return
	 */
	public WordGram shiftAdd(String last) {
		WordGram wg = new WordGram(myWords, 0, myWords.length);
		String[] shifted = new String[myWords.length];
		for (int i = 0; i < shifted.length-1; i++)
		{
			shifted[i] = myWords[i+1];
		}
		shifted[shifted.length-1] = last;
		return new WordGram(shifted, 0, shifted.length);
	}

	 /**
	 * Combine all the elements of myWords into one String, with
	 * each word separated by a space.
	 */
	@Override
	public String toString(){
		if (myToString == null)
		{
			myToString = String.join(" ", myWords);
		}	
		return myToString;
	}
}