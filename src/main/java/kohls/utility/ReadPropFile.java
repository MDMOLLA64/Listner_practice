package kohls.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropFile {

	public static Properties ReadFile(String path) throws IOException {
		FileInputStream f = new FileInputStream(new File(path));
		Properties prop = new Properties();
		prop.load(f);
		return prop;
	}

}
