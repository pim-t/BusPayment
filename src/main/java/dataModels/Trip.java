package dataModels;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

@JsonPropertyOrder({"startDateTime", "endDateTime", "durationSecs", "fromStopId", "toStopId", "chargeAmount", "companyId", "busId", "pan", "status"})
public record Trip(
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime startDateTime,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime endDateTime,
        long durationSecs,
        String fromStopId,
        String toStopId,
        double chargeAmount,
        String companyId,
        String busId,
        String pan,
        Status status
) {
}
