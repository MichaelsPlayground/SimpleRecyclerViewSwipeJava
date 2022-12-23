package de.androidcrypto.simplerecyclerviewjava;

public class EntryModel {

    private String title;
    private String url;
    private long timestamp;

    // generate Constructor
    public EntryModel(String title, String url, long timestamp) {
        this.title = title;
        this.url = url;
        this.timestamp = timestamp;
    }

    // generate setter and getter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
