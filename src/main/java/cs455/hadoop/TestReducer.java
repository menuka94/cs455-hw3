package cs455.hadoop;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestReducer extends Reducer<Text, Text, Text, Text> {
    private static final Logger log = LogManager.getLogger(Reducer.class);

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException,
            InterruptedException {
        log.info("reduce()");
        log.info(key.toString());

        context.write(key, new Text());
    }
}
