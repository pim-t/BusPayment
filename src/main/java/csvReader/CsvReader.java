package csvReader;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import dataModels.TripDetail;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;


public class CsvReader {

    // test driver code
//    public static void main(String[] args) throws Exception {
//        File input = new File("src/main/java/input.csv");
//        File output = new File("output.json");
//
//        List<Map<?, ?>> data = readObjectsFromCsv(input);
//        writeAsJson(data, output);
//    }


    public static List<Map<?, ?>> readObjectsFromCsv(File file) throws IOException {
        CsvSchema bootstrap = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader(Map.class).with(bootstrap).readValues(file);

        return mappingIterator.readAll();
    }

    public static void writeCSV(TripDetail trip, File file) throws IOException {
        CsvMapper mapper= new CsvMapper();
        CsvSchema schema = mapper.schemaFor(TripDetail.class);
        schema = schema.withColumnSeparator(',');
        schema = schema.withUseHeader(true);

        // output writer
        ObjectWriter myObjectWriter = mapper.writer(schema);

        FileOutputStream tempFileOutputStream = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(tempFileOutputStream, 1024);
        OutputStreamWriter writerOutputStream = new OutputStreamWriter(bufferedOutputStream, "UTF-8");
        myObjectWriter.writeValue(writerOutputStream, trip);



    }



}
