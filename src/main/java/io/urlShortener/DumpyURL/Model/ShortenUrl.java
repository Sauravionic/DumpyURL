package io.urlShortener.DumpyURL.Model;

public class ShortenUrl {

    private String full_url;
    private String short_url;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getFull_url() {
        return full_url;
    }

    public void setFull_url(String full_url) {
        this.full_url = full_url;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

}
