package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

	private static Log log = LogFactory.getLog(FileUtils.class);

	public static void toJsonFile(Object object, String filename) {
		ObjectMapper mapper = new ObjectMapper();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			writer.write(mapper.writeValueAsString(object));
			log.info("Successfully written out to json file [" + filename + "]");
		} catch (IOException e) {
			log.error(e);
		}
	}
}
