import model.Statistics;
// import org.apache.commons.logging.Log;
// import org.apache.commons.logging.LogFactory;
import processing.LogProcessing;
import util.FileUtils;

public class LogReader {

	// private static Log log = LogFactory.getLog(LogReader.class);

	public static void main(String[] args) {
		Statistics stats = new LogProcessing("logs").getStats();
		FileUtils.toJsonFile(stats, "analysis.json");
	}
}