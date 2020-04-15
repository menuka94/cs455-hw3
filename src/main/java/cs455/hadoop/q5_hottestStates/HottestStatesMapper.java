package cs455.hadoop.q5_hottestStates;

import java.io.IOException;
import cs455.hadoop.util.Constants;
import cs455.hadoop.util.DataFields;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class HottestStatesMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    private static final Logger log = LogManager.getLogger(HottestStatesMapper.class);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (key.get() == 0 && value.toString().contains("State")) {
            log.info("Skipping header row");
        } else {
            String[] splits = value.toString().split(Constants.DELIMETER);
            String date = splits[DataFields.DATE_GMT - 1].replaceAll("\"", "");
            try {
                int month = Integer.parseInt(date.substring(5, 7));
                if (month >= 6 && month <= 8) {
                    String temperatureString = splits[DataFields.SAMPLE_MEASUREMENT - 1].replaceAll(
                            "\"", "");
                    double temperature = Double.parseDouble(temperatureString);
                    String state = splits[DataFields.STATE_NAME - 1].replaceAll("\"", "");
                    context.write(new Text(state), new DoubleWritable(temperature));
                }
            } catch (NumberFormatException e) {
                log.error("Error in parsing month");
                e.printStackTrace();
            }
        }
    }
}