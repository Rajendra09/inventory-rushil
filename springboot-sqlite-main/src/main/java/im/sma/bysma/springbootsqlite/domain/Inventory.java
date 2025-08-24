package im.sma.bysma.springbootsqlite.domain;

import jakarta.persistence.*;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int quantity;
    private String unit;
    private double averageDailyConsumption;
    private int lowThresholdLimit;
    private String notes;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public double getAverageDailyConsumption() { return averageDailyConsumption; }
    public void setAverageDailyConsumption(double averageDailyConsumption) { this.averageDailyConsumption = averageDailyConsumption; }

    public int getLowThresholdLimit() { return lowThresholdLimit; }
    public void setLowThresholdLimit(int lowThresholdLimit) { this.lowThresholdLimit = lowThresholdLimit; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}