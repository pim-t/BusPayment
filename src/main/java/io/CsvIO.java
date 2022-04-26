package io;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import dataModels.Tap;
import dataModels.Trip;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CsvIO {
    public static List<Tap> readTapsFromCsv(File inputFile) throws IOException {
        var csvMapper = new CsvMapper();

        var tapSchema = csvMapper
                .schemaFor(Tap.class)
                .withHeader();

        var objectReader = csvMapper
                .readerFor(Tap.class)
                .with(tapSchema);

        try (MappingIterator<Tap> mappingIterator = objectReader.readValues(inputFile)) {
            return mappingIterator.readAll();
        }
    }

    public static void writeTripsToCsv(File outputFile, List<Trip> trips) throws IOException {
        var csvMapper = new CsvMapper();

        CsvSchema tripSchema = csvMapper
                .schemaFor(Trip.class)
                .withoutQuoteChar()
                .withHeader();

        var objectWriter = csvMapper
                .writerFor(Trip.class)
                .with(tripSchema);

        try (SequenceWriter sequenceWriter = objectWriter.writeValues(outputFile)) {
            sequenceWriter.writeAll(trips);
        }
    }
}
