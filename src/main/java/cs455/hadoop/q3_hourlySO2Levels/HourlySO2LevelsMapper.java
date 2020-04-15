package cs455.hadoop.q3_hourlySO2Levels;

import java.io.IOException;
import cs455.hadoop.util.Constants;
import cs455.hadoop.util.DataFields;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class HourlySO2LevelsMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    private static final Logger log = LogManager.getLogger(HourlySO2LevelsMapper.class);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (key.get() == 0 && value.toString().contains("State")) {
            log.info("Skipping header row");
        } else {
            String[] splits = value.toString().split(Constants.DELIMETER);
            String dateGmt = splits[DataFields.DATE_GMT - 1].replaceAll("\"", "");
            try {
                int year = Integer.parseInt(dateGmt.substring(0, 4));
                if (year >= 2000 && year <= 2019) {
                    String timeGmt = splits[DataFields.TIME_GMT - 1].replaceAll("\"", "");
                    String sampleMeasurementString =
                            splits[DataFields.SAMPLE_MEASUREMENT - 1].replaceAll("\"", "");
                    double sampleMeasurement = Double.parseDouble(sampleMeasurementString);
                    context.write(new Text(timeGmt), new DoubleWritable(sampleMeasurement));
                } else {
                    log.info("Year out of considered range");
                }
            } catch (NumberFormatException e) {
                log.error("Error parsing date");
                e.printStackTrace();
            }
        }
    }
}
