package org.jmf.vo;

public class Comment {

    private String key;
    
    private String login;
    
    private String userName;
    
    private String htmlText;
    
    private String markdown;
    
    private String updatable;
    
    private String createdAt;

    public final String getKey() {
        return key;
    }

    public final String getLogin() {
        return login;
    }

    public final String getUserName() {
        return userName;
    }

    public final String getHtmlText() {
        return htmlText;
    }

    public final String getMarkdown() {
        return markdown;
    }

    public final String getUpdatable() {
        return updatable;
    }

    public final String getCreatedAt() {
        return createdAt;
    }

    public final void setKey(String key) {
        this.key = key;
    }

    public final void setLogin(String login) {
        this.login = login;
    }

    public final void setUserName(String userName) {
        this.userName = userName;
    }

    public final void setHtmlText(String htmlText) {
        this.htmlText = htmlText;
    }

    public final void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    public final void setUpdatable(String updatable) {
        this.updatable = updatable;
    }

    public final void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
       
}
