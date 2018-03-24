package processing;

import model.ApplicationTime;
import model.Statistics;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogProcessing {

    private static Log log = LogFactory.getLog(LogProcessing.class);

    private static final String SEARCH_TEXT_START = "SearchedText";
    private static final String APP_LAUNCH_STRING = "AppDirector::launch";
    private static final String VEHICLE_STATE_TEXT = "Vehicle movement status has changed to";

    private String filename;
    private List<ApplicationTime> appTimes = new ArrayList<>();
    private Map<String, Integer> searchOccurrences = new HashMap<>();
    private Map<String, Integer> vehicleStateOccurrences = new HashMap<>();

    public LogProcessing(String filename) {
        this.filename = filename;
        this.doProcess();
    }

    public Statistics getStats() {
        log.info(searchOccurrences);
        log.info(appTimes);
        log.info(vehicleStateOccurrences);

        return new Statistics(appTimes, searchOccurrences, vehicleStateOccurrences);
    }

    private void doProcess() {
        try (BufferedReader br = new BufferedReader(new FileReader("logs"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.startsWith(SEARCH_TEXT_START)) {
                    String[] split = trimmed.split(" ");
                    if (split.length > 0) {
                        addSearchOccurrence(split[1]);
                    } else {
                        log.error("Incomplete search log [" + line + "]");
                    }
                } else if (trimmed.contains(APP_LAUNCH_STRING)) {
                    String cleaned = trimmed.substring(trimmed.indexOf(APP_LAUNCH_STRING));
                    String[] split = cleaned.split(" ");
                    if (split.length >= 4) {
                        addAppUsage(split);
                    } else {
                        log.error("Incomplete app usage log [" + line + "]");
                    }
                } else if (trimmed.contains(VEHICLE_STATE_TEXT)) {
                    addVehicleState(line);
                }


            }
        } catch (FileNotFoundException e) {
            log.error("File not found", e);
        } catch (IOException e) {
            log.error("IO error parsing the file", e);
        }
    }

    private void addSearchOccurrence(String search) {
        if (searchOccurrences.containsKey(search)) {
            log.info("Adding search occurrence [" + search + "], was: " + searchOccurrences.get(search));
            searchOccurrences.put(search, searchOccurrences.get(search) + 1);
        } else {
            log.info("Adding search occurrence [" + search + "], NEW");
            searchOccurrences.put(search, 1);
        }
    }

    private void addAppUsage(String[] usage) {
        String app = usage[1];
        LocalTime time = LocalTime.parse(usage[3].substring(0, 8));

        // TODO this is ugly
        ApplicationTime last = (appTimes.isEmpty()) ? null : appTimes.get(appTimes.size() - 1);
        LocalTime lastLoggedTime = (last != null) ? last.getTime() : null;

        Duration duration = (lastLoggedTime == null) ? Duration.ZERO : Duration.between(lastLoggedTime, time);

        appTimes.add(new ApplicationTime(app, time, duration));
    }

    private void addVehicleState(String line) {
        // TODO add input check
        String state = line.substring(line.indexOf("[ \"")).replace("[ \"", "").replace("\" ]", "");


        if (vehicleStateOccurrences.containsKey(state)) {
            log.info("Adding vehicle state [" + state + "], was: " + vehicleStateOccurrences.get(state));
            vehicleStateOccurrences.put(state, vehicleStateOccurrences.get(state) + 1);
        } else {
            log.info("Adding vehicle state [" + state + "], NEW");
            vehicleStateOccurrences.put(state, 1);
        }
    }
}