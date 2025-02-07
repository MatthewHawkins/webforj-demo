package org.example.data;

public class SalesEntry{
public enum Ranking {
  HIGH, MEDIAN, LOW
}

private Ranking ranking;
private String company;
private String product;
private String serialNumber;
private String dayOfWeek;
private String dateTime;
private double totalRevenue;

public SalesEntry(Ranking ranking, String company, String product, String serialNumber, 
                  String dayOfWeek, String dateTime, double totalRevenue) {
    this.ranking = ranking;
    this.company = company;
    this.product = product;
    this.serialNumber = serialNumber;
    this.dayOfWeek = dayOfWeek;
    this.dateTime = dateTime;
    this.totalRevenue = totalRevenue;
}

public Ranking getRanking() { return ranking; }
public void setRanking(Ranking ranking) { this.ranking = ranking; }

public String getCompany() { return company; }
public void setCompany(String company) { this.company = company; }

public String getProduct() { return product; }
public void setProduct(String product) { this.product = product; }

public String getSerialNumber() { return serialNumber; }
public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

public String getDayOfWeek() { return dayOfWeek; }
public void setDayOfWeek(String dayOfWeek) { this.dayOfWeek = dayOfWeek; }

public String getDateTime() { return dateTime; }
public void setDateTime(String dateTime) { this.dateTime = dateTime; }

public double getTotalRevenue() { return totalRevenue; }
public void setTotalRevenue(double totalRevenue) { this.totalRevenue = totalRevenue; }

}
