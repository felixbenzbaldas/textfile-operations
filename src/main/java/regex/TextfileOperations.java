package regex;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TextfileOperations {

	public static final String firstNumberGroup = "(?<n1>-?\\d+(,\\d+)?)";
	public static final String secondNumberGroup = "(?<n2>\\d+(,\\d+)?)";
	public static final String REGEX = "\\[" + firstNumberGroup + "/" + secondNumberGroup + "\\]";
	
	public static void main(String[] args) throws IOException {
		File file = new File(args[0]);
		String text = getText(file);
		renameFile(file);
		try (PrintWriter out = new PrintWriter(file)) {
			out.print(getNewText(text));
		}
	}
	
	private static String getNewText(String text) {
		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(text);
		StringBuffer sb = new StringBuffer();
		while(matcher.find()) {
			String firstNumberString = matcher.group("n1");
			String secondNumberString = matcher.group("n2");
			float firstNumber = Float.parseFloat(firstNumberString.replace(',', '.'));
			float secondNumber = Float.parseFloat(secondNumberString.replace(',', '.'));
			NumberFormat formatter = NumberFormat.getInstance();
			formatter.setMaximumFractionDigits(1);
			String newFirstNumberString = formatter.format(firstNumber + secondNumber).replace('.', ',');
			String replacement = "[" + newFirstNumberString + "/" + secondNumberString + "]";
			matcher.appendReplacement(sb, replacement);
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	private static String getText(File file) throws IOException {
		List<String> lines = Files.readAllLines(file.toPath());
		return String.join("\n", lines);
	}
	
	private static void renameFile(File file) {
		String fileName = file.getName();
		File parentFile = file.getParentFile();
		for (int i = 0; i < 20; i++) {
			String newFileName = getPrefix(i) + fileName;
			File renamedFile = new File(parentFile, newFileName);
			if (!renamedFile.exists()) {
				file.renameTo(renamedFile);
				return;
			}
		}
	}
	
	private static String getPrefix(int length) {
		String prefix = "";
		for (int j = 0; j < length; j++) {
			prefix += "-";
		}
		return prefix;
	}
}