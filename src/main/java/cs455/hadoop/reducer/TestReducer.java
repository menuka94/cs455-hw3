package cs455.hadoop.reducer;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private static final Logger log = LogManager.getLogger(Reducer.class);

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        log.info("reduce()");
        int total = 0;
        for (IntWritable value : values) {
            total += value.get();
        }
        context.write(key, new IntWritable(total));
    }
}
