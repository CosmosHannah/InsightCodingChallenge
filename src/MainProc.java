import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * Since using hashmap, the time complexity is O(N), N is the number of word.
 * Space complexity is also O(N), can be smaller if the file has many repeat words.
 * If the file is very big,  tire tree may get better performance,
 * but since it may includes all ASCII Code, hashmap should be better. 
 */
public class MainProc {

	public static void main(String[] args) {
		String INPUT_FILE_PATH = args[0];
		String OUTPUT_FILE_1_PATH = args[1];
		String OUTPUT_FILE_2_PATH = args[2];

		Map<String, Integer> allTweetsCountMap = new HashMap<>(); // use to count words for all tweet 
		long totalUniqueWordCount = 0; // unique word count for all current read tweets.
		long numOfTweet = 0; // number of tweets current read.
		DecimalFormat df1  = new DecimalFormat(".0");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE_PATH));
			BufferedWriter writer1 = new BufferedWriter(new FileWriter(OUTPUT_FILE_1_PATH));
			BufferedWriter writer2 = new BufferedWriter(new FileWriter(OUTPUT_FILE_2_PATH));

			String tweet;
			boolean firstLine = true; // don't need to change to newline if it's first line.
			while((tweet = reader.readLine()) != null) {
				int uniqueWordCount = getUniqueWordCount(tweet, allTweetsCountMap);
				totalUniqueWordCount += uniqueWordCount;
				numOfTweet++;
				double medianNumUniqueWord = (double) totalUniqueWordCount / numOfTweet;
				writer2.write((firstLine ? "" : "\n") + df1.format(medianNumUniqueWord));
				firstLine = false;
			}

			//sort the count map in alphabetical order.
			Map<String, Integer> sortedAllTweetsCountMap = new TreeMap<>(allTweetsCountMap);

			firstLine = true;
			for (Entry<String, Integer> entry : sortedAllTweetsCountMap.entrySet()) {
				writer1.write((firstLine ? "" : "\n") +
						String.format("%-29s", entry.getKey()) + " " + entry.getValue());
				firstLine = false;
			}
			writer1.close();
			writer2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the count of unique word for the giving tweet.
	 * And add the word count of this tweet to allTweetsCountMap.
	 */
	private static int getUniqueWordCount(String tweet, Map<String, Integer> allTweetsCountMap) {
		String[] words = tweet.split(" +");
		// get the words count for a single tweet
		Map<String, Integer> singleTweetCountMap = new HashMap<>();
		for (String word : words) {
			if(singleTweetCountMap.containsKey(word)) {
				singleTweetCountMap.put(word, singleTweetCountMap.get(word) + 1);
			} else {
				singleTweetCountMap.put(word, 1);
			}
		}

		//count the unique word
		int uniqueWordCount = singleTweetCountMap.size();
		for (Entry<String, Integer> entry : singleTweetCountMap.entrySet()) {
			// add the word count to all tweets count map.
			if(allTweetsCountMap.containsKey(word)) {
				allTweetsCountMap.put(word, allTweetsCountMap.get(word) + count);
			} else {
				allTweetsCountMap.put(word, count);
			}
		}

		return uniqueWordCount;
	}
}
