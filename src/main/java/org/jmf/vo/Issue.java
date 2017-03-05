package org.jmf.vo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Represents a SonarQube issue.
 * 
 * @author jose
 *
 */
public final class Issue {

    private String key;

    private String component;

    private String rule;

    private String status;

    private String resolution;

    private String severity;

    private String line;

    private String actionPlan;
    
    private List<Comment> comments;
    
    public String getActionPlan() {
        return actionPlan;
    }

    
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    } 
    
    
    public void setActionPlan(String actionPlan) {
        this.actionPlan = actionPlan;
    }


    public String getKey() {
        return key;
    }

    public String getComponent() {
        return component;
    }

    public String getRule() {
        return rule;
    }

    public String getStatus() {
        return status;
    }

    public String getSeverity() {
        return severity;
    }

    public String getLine() {
        return line;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getParsedComponent() {
        return this.component == null ? "" : this.component.replaceAll(".*:", "");
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public boolean compare(Issue issue) {
        if (issue == null || 
                !StringUtils.isNotEmpty(issue.getLine()) || 
                    !StringUtils.isNotEmpty(issue.getRule())) {
            return false;
        } else {
            return issue.getLine().equals(this.line) && issue.getParsedComponent().equals(getParsedComponent())
                    && issue.getRule().equals(this.rule);
        }

    }

}
