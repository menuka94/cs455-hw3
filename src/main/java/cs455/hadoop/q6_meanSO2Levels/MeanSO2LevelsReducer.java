package cs455.hadoop.q6_meanSO2Levels;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

public class MeanSO2LevelsReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
    private static final Logger log = LogManager.getLogger(MeanSO2LevelsReducer.class);

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
