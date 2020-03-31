package cs455.hadoop;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestReducer extends Reducer {
    private static final Logger log = LogManager.getLogger(Reducer.class);

    @Override
    protected void reduce(Object key, Iterable values, Context context) {
        log.info("reduce()");
    }
}
