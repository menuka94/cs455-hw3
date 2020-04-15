package cs455.hadoop.q6_meanSO2Levels;

import cs455.hadoop.util.Constants;
import cs455.hadoop.util.DataFields;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MeanSO2LevelsMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    private static final Logger log = LogManager.getLogger(MeanSO2LevelsMapper.class);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        final List<String> top10hottestStates =
                Arrays.asList(
                        "Arizona",
                        "Puerto Rico",
                        "Texas",
                        "Nevada",
                        "Virgin Islands",
                        "Mississippi",
                        "Florida",
                        "Louisiana",
                        "Arkansas",
                        "Oklahoma"
                );

        if (key.get() == 0 && value.toString().contains("State")) {
            log.info("Skipping header row");
        } else {
            String[] splits = value.toString().split(Constants.DELIMETER);
            String stateName = splits[DataFields.STATE_NAME - 1].replaceAll("\"", "").trim();
            if (top10hottestStates.contains(stateName)) {
                String sampleMeasurementString = splits[DataFields.SAMPLE_MEASUREMENT - 1];

                try {
                    double sampleMeasurement = Double.parseDouble(sampleMeasurementString);
                    context.write(new Text(stateName), new DoubleWritable(sampleMeasurement));
                } catch (NumberFormatException e) {
                    log.error("Error parsing value");
                    e.printStackTrace();
                }
            }
        }
    }
}
