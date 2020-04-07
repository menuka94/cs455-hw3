package cs455.hadoop.higherSO2Levels;

import java.io.IOException;
import cs455.hadoop.util.Constants;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HigherSO2LevelsReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
    @Override
    protected void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        int noOfEntries = 0;
        double total = 0;
        for (DoubleWritable value : values) {
            total += value.get();
            noOfEntries++;
        }

        double mean = total / noOfEntries;

        if (Constants.EAST_COAST.equals(key.toString())) {
            context.write(new Text(Constants.EAST_COAST), new DoubleWritable(mean));
        } else if (Constants.WEST_COAST.equals(key.toString())) {
            context.write(new Text(Constants.WEST_COAST), new DoubleWritable(mean));
        }
    }
}
