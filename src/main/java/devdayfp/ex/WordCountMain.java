package devdayfp.ex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WordCountMain {
	
	public static void main(String[] args) {
		Valid<String> wordCount =
				Valid.value("files/txt.txt")
				.flatMap (fileName -> loadFileContent(fileName))
				.map     (str      -> str.split("[^a-zA-Z]+"))
				.validate(parts    -> parts.length, l -> l != 0,  "No word")
				.map     (parts    -> parts.length)
				.map     (count    -> "There are " + count + " words.");
		System.out.println(wordCount);
	}

	private static Valid<String> loadFileContent(String fileName) {
		try {
			return Valid.value(new String(Files.readAllBytes(Paths.get(fileName))));
		} catch (IOException e) {
			return Valid.error("File Not Found");
		}
	}
	
}
