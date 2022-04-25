package dataModels;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;

@JsonPropertyOrder({"id", "dateTime", "tapType", "stopId", "companyId", "busId", "pan"})
public record Tap(
        String id,
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        LocalDateTime dateTime,
        String tapType,
        String stopId,
        String companyId,
        String busId,
        String pan
) {
}
