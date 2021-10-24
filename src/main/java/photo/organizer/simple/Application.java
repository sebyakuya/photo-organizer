package photo.organizer.simple;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class Application {

	public static void main(String[] args) {
		System.out.println("Starting Photo Organizer");

		// Checking argument
		if (args.length == 0) {
			System.out.println("No argument found. Use: java -jar photo-organizer-vx.jar folderWithPhotos");
			System.exit(-1);
		}

		// Checking that the directory exists
		File src = new File(args[0]);
		if (!src.exists()) {
			System.out.println("Folder not found: " + args[0]);
			System.exit(-1);
		} else if (src.isFile()) {
			System.out.println("This is a file, not a folder: " + args[0]);
			System.exit(-1);
		}

		// Creating the folder that will contain the organized photos
		File results = new File("photosFromPhotoOrganizer");
		results.mkdir();

		// Here we iterate through the folder to copy the images to the new folder

		Finder d = new Finder();
		d.getFilesInDirectory(src);
		System.out.println(d.getOutput().size());

		Pattern p = Pattern.compile("(20)[0-9]{2}[- /.]?[0-1][0-9][- /.]?[0-3][0-9]");

		for (File i : d.getOutput()) {
			String year = "unknown";
			String month = "unknown";

			Matcher m = p.matcher(i.getName());

			if (m.find()) {
				String date = m.group();

				if (date.contains("-")) {
					year = date.substring(0, 4);
					month = date.substring(5, 7);
				} else {
					year = date.substring(0, 4);
					month = date.substring(4, 6);
				}
			}

			if ("unknown".equals(year)) {
				try {
					FileTime time = Files.getLastModifiedTime(i.toPath());
					year = time.toString().substring(0, 4);
					month = time.toString().substring(5, 7);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			try {

				File check = new File("photosFromPhotoOrganizer/" + year + "/" + month + "/" + i.getName());
				if (!check.exists())
					FileUtils.copyFile(i, check);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		System.out.println("Finished Photo Organizer");

	}

}
