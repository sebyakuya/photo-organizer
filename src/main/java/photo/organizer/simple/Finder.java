package photo.organizer.simple;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Finder {

	private ArrayList<File> output;

	public Finder() {
		output = new ArrayList<File>();
	}

	public List<File> getOutput() {
		return output;
	}

	public void getFilesInDirectory(File src) {
		findFilesInPaths(src);
	}

	public void findFilesInPaths(File srcPath) {
		for (File src : srcPath.listFiles()) {
			if (src.isDirectory()) {
				findFilesInPaths(src);
			} else {
				output.add(src);
			}

		}
	}

}
