package cs455.hadoop.hourlySO2Levels;

import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class HourlySO2LevelsReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
    private static final Logger log = LogManager.getLogger(HourlySO2LevelsReducer.class);

    @Override
    protected void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        double total = 0;
        int count = 0;
        for (DoubleWritable value : values) {
            total += value.get();
            count++;
        }

        double mean = total / count;

        context.write(new Text(key), new DoubleWritable(mean));
    }
}
