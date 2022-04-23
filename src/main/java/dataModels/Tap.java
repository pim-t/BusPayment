package dataModels;

import java.util.Date;


public class Tap {

    private String id;
    private String date;
    private String stopId;
    private String companyId;
    private String busId;
    private String PAN;

    private String tapType;

    public Tap( String id, String date, String stopId, String companyId, String busId, String PAN, String tapType){
        this.id = id;
        this.date = date;
        this.stopId = stopId;
        this.companyId = companyId;
        this.busId = busId;
        this.PAN = PAN;
        this.tapType = tapType;

    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getStopId() {
        return stopId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getBusId() {
        return busId;
    }

    public String getPAN() {
        return PAN;
    }

    public String getTapType() {
        return tapType;
    }
}
