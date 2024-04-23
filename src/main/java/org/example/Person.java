package org.example;

import com.opencsv.bean.CsvBindByPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    @CsvBindByPosition(position = 0)    private String name;
    @CsvBindByPosition(position = 1)    private String surname;
    @CsvBindByPosition(position = 2)    private String serificatNum;
    @CsvBindByPosition(position = 3)    private String sertificateIssueDateStr;
    @CsvBindByPosition(position = 4)    private String status;
    @CsvBindByPosition(position = 5)    private String areaOfActivityNum;
    @CsvBindByPosition(position = 6)    private String areaOfActivityName;
    @CsvBindByPosition(position = 7)    private String responsibleInstitutionName;
    @CsvBindByPosition(position = 8)    private String responsibleInstitutionsNum;
    @CsvBindByPosition(position = 9)    private String areaOfActivityIssueDateStr;
    @CsvBindByPosition(position = 10)   private String areaOfActivityStatus;

    private Date areaOfActivityIssueDate;
    private Date sertificateIssueDate;


    public Person() {}
    public void convertDates() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            if (this.sertificateIssueDateStr != null) {
                this.sertificateIssueDate = formatter.parse(this.sertificateIssueDateStr);
            }
            if (this.areaOfActivityIssueDateStr != null) {
                this.areaOfActivityIssueDate = formatter.parse(this.areaOfActivityIssueDateStr);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getSerificatNum() {
        return serificatNum;
    }
    public String getStatus() {
        return status;
    }
    public String getAreaOfActivityNum() {
        return areaOfActivityNum;
    }
    public String getAreaOfActivityName() {
        return areaOfActivityName;
    }
    public String getResponsibleInstitutionName() {
        return responsibleInstitutionName;
    }
    public String getResponsibleInstitutionsNum() {
        return responsibleInstitutionsNum;
    }
    public String getAreaOfActivityStatus() {
        return areaOfActivityStatus;
    }
    public String getSertificateIssueDateStr() {
        return sertificateIssueDateStr;
    }
    public String getAreaOfActivityIssueDateStr() {
        return areaOfActivityIssueDateStr;
    }
}