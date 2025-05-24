package org.example.model;

import java.util.List;

public class StockData {

    // Identity
    private String ticker;
    private String companyName;
    private String companyDomain;
    private String logoUrl;

    // Market data
    private double currentPrice;
    private double previousClose;
    private double priceChange;
    private double priceChangePercent;
    private long volume;

    // Presentation helpers
    private boolean isPriceUp;
    private String formattedChange;
    private String formattedVolume;

    // Optional: sparkline data
    private List<Double> recentPrices;

    // Timestamp
    private long lastUpdated;

    // --- Constructors ---

    public StockData() {
    }

    public StockData(String ticker, String companyName, String companyDomain, String logoUrl,
            double currentPrice, double previousClose, double priceChange, double priceChangePercent,
            long volume, boolean isPriceUp, String formattedChange, String formattedVolume,
            List<Double> recentPrices, long lastUpdated) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.companyDomain = companyDomain;
        this.logoUrl = logoUrl;
        this.currentPrice = currentPrice;
        this.previousClose = previousClose;
        this.priceChange = priceChange;
        this.priceChangePercent = priceChangePercent;
        this.volume = volume;
        this.isPriceUp = isPriceUp;
        this.formattedChange = formattedChange;
        this.formattedVolume = formattedVolume;
        this.recentPrices = recentPrices;
        this.lastUpdated = lastUpdated;
    }

    // --- Getters and Setters ---

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDomain() {
        return companyDomain;
    }

    public void setCompanyDomain(String companyDomain) {
        this.companyDomain = companyDomain;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public double getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(double previousClose) {
        this.previousClose = previousClose;
    }

    public double getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(double priceChange) {
        this.priceChange = priceChange;
    }

    public double getPriceChangePercent() {
        return priceChangePercent;
    }

    public void setPriceChangePercent(double priceChangePercent) {
        this.priceChangePercent = priceChangePercent;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public boolean isPriceUp() {
        return isPriceUp;
    }

    public void setPriceUp(boolean priceUp) {
        isPriceUp = priceUp;
    }

    public String getFormattedChange() {
        return formattedChange;
    }

    public void setFormattedChange(String formattedChange) {
        this.formattedChange = formattedChange;
    }

    public String getFormattedVolume() {
        return formattedVolume;
    }

    public void setFormattedVolume(String formattedVolume) {
        this.formattedVolume = formattedVolume;
    }

    public List<Double> getRecentPrices() {
        return recentPrices;
    }

    public void setRecentPrices(List<Double> recentPrices) {
        this.recentPrices = recentPrices;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    // public void setLastUpdated(long lastUpdated) {
    // this.lastUpdated = lastUpdated;
    // }

    // public double getCurrentPrice() {
    // return currentPrice;
    // }

    // public void setCurrentPrice(double currentPrice) {
    // this.currentPrice = currentPrice;
    // }

    public synchronized void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public synchronized void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public synchronized double getCurrentPrice() {
        return currentPrice;
    }
}
