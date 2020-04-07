package cs455.hadoop.combiner;

import java.io.IOException;
import cs455.hadoop.util.CountySiteNumWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MaxMonitoringSitesCombiner extends Reducer<Text, CountySiteNumWritable, Text, IntWritable> {
    private static final Logger log = LogManager.getLogger(MaxMonitoringSitesCombiner.class);

    @Override
    protected void reduce(Text key, Iterable<CountySiteNumWritable> values, Context context) throws IOException, InterruptedException {
        // processing one state
        log.info("Processing: " + key.toString());
        int total = 0;
        for (CountySiteNumWritable value : values) {
            total++;
        }

        context.write(key, new IntWritable(total));
    }
}
