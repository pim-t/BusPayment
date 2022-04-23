package dataModels;

import java.math.BigDecimal;
import java.util.Date;


public class TripDetail {

    private String startTime;
    private String endTime;
    private long durationSecs;
    private String fromStopId;
    private String toStopId;
    private BigDecimal chargeAmount;
    private String companyId;
    private String busId;
    private String PAN;
    private Journey.Status status;


    public TripDetail(String startTime,
                      String endTime,
                      long durationSecs,
                      String fromStopId,
                      String toStopId,
                      BigDecimal chargeAmount,
                      String companyId,
                      String busId,
                      String PAN,
                      Journey.Status status) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationSecs = durationSecs;
        this.fromStopId = fromStopId;
        this.toStopId = toStopId;
        this.chargeAmount = chargeAmount;
        this.companyId = companyId;
        this.busId = busId;
        this.PAN = PAN;
        this.status = status;
    }


    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public long getDurationSecs() {
        return durationSecs;
    }

    public String getFromStopId() {
        return fromStopId;
    }

    public String getToStopId() {
        return toStopId;
    }

    public BigDecimal getChargeAmount() {
        return chargeAmount;
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

    public Journey.Status getStatus() {
        return status;
    }
}
