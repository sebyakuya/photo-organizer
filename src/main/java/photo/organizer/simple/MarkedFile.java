package photo.organizer.simple;

import java.io.File;

public class MarkedFile {
	
	
	private File file;
	public MarkedFile(File src) {
		this.file = src;
	}
	@Override
	public String toString() {
		return "MarkedFile [file=" + file.getAbsolutePath() + "]";
	}
	
	public File getFile() {
		return file;
	}

	
	

}
