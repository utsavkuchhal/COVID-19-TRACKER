package com.utsav.tracker;

public class Data {
    private String active;
    private String confirm;
    private String state;
    private String death;
    private String recovered;
    private String deltaconfirmed;
    private String deltadeaths;
    private String deltarecovered;

    public Data(String active, String confirm, String state, String death, String recovered, String deltaconfirmed, String deltadeaths, String deltarecovered) {
        this.active = active;
        this.confirm = confirm;
        this.state = state;
        this.death = death;
        this.recovered = recovered;
        this.deltaconfirmed = deltaconfirmed;
        this.deltadeaths = deltadeaths;
        this.deltarecovered = deltarecovered;
    }

    public String getDeltaconfirmed() {
        return deltaconfirmed;
    }

    public String getDeltadeaths() {
        return deltadeaths;
    }

    public String getDeltarecovered() {
        return deltarecovered;
    }

    public String getActive() {
        return active;
    }

    public String getConfirm() {
        return confirm;
    }

    public String getDeath() {
        return death;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getState() {
        return state;
    }
}
