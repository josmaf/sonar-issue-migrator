package org.jmf.view;

import java.util.List;
import java.util.Map;

import org.jmf.services.SonarClientService;
import org.jmf.util.FileUtils;
import org.jmf.vo.Issue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author jose
 *
 */
public final class CommandLineClient {

    // Logger
    private static final Logger CONSOLE_LOGGER = LoggerFactory.getLogger("console");

    private CommandLineClient() {
    }

    public static void main(final String... args) {       
       
        // Get map with properties
        Map<String, String> props = FileUtils.getProperties("configuration.properties");
        
        List<Issue> flaggedIssues;

        // Create Sonar Client for flagged issues server
        SonarClientService sclientFlagged = new SonarClientService(props.get("flagged_issues_url"));

        // Authenticate server
        CONSOLE_LOGGER.info("Authenticating...\n");
        if (sclientFlagged.authenticate(props.get("flagged_http_user"), 
                                        props.get("flagged_http_passw"),
                                        props.get("flagged_sonar_user"), 
                                        props.get("flagged_sonar_passw"))) {
            // If authenticated, get list of flagged issues
            CONSOLE_LOGGER.info("Getting list of flagged issues...\n");
            flaggedIssues = sclientFlagged.getIssuesFromUrl(props.get("flagged_issues_url"));
        } else {
            CONSOLE_LOGGER.info("Authentication failed\n");
            return;
        }

        // If we have obtained a list of flagged issues...
        if (flaggedIssues != null && !flaggedIssues.isEmpty()) {
            CONSOLE_LOGGER.info("Flagged issues list size: {}\n ",flaggedIssues.size());
            // Create Sonar Client for open issues
            SonarClientService sClientOpen = new SonarClientService(props.get("open_issues_host"));
            if (sClientOpen.authenticate(null, null, props.get("open_sonar_user"), props.get("open_sonar_passw"))) {
                // Copy issues to project
                sClientOpen.copyIssues(flaggedIssues, props.get("open_issues_project"));
            }
        }
        
    }

}
