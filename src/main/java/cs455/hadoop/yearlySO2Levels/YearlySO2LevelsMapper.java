package cs455.hadoop.yearlySO2Levels;

import java.io.IOException;
import cs455.hadoop.util.Constants;
import cs455.hadoop.util.DataFields;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class YearlySO2LevelsMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    private static final Logger log = LogManager.getLogger(YearlySO2LevelsMapper.class);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (key.get() == 0 && value.toString().contains("State")) {
            log.info("Skipping header row");
        } else {
            String[] splits = value.toString().split(Constants.DELIMETER);
            String sampleMeasurementString = splits[DataFields.SAMPLE_MEASUREMENT - 1].replaceAll("\"", "");

            try {
                double sampleMeasurement = Double.parseDouble(sampleMeasurementString);
                String year = splits[DataFields.DATE_GMT - 1].replaceAll("\"", "").substring(0, 4);
                context.write(new Text(year), new DoubleWritable(sampleMeasurement));
            } catch (NumberFormatException e) {
                log.error("Invalid Sample Measurement");
                e.printStackTrace();
            }
        }
    }
}
