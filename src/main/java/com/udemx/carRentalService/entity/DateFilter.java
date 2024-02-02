package com.udemx.carRentalService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class DateFilter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String startDate;

    private String endDate;

    public DateFilter() {
    }

    public DateFilter(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "DateFilter{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    public int getDayDifferences(String startDateStr, String endDateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int differenceInDays=0;

        try {
            Date startDate = sdf.parse(startDateStr);
            Date endDate = sdf.parse(endDateStr);

            long differenceInMillis = endDate.getTime() - startDate.getTime();
            differenceInDays = (int) (differenceInMillis / (24 * 60 * 60 * 1000));
            differenceInDays++;

        } catch (ParseException e) {
            return differenceInDays;
        }

        return differenceInDays;
    }

}
