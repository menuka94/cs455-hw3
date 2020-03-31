package cs455.hadoop;

import java.io.IOException;
import cs455.hadoop.util.DataFields;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TestMapper extends Mapper<LongWritable, Text, Text, Text> {
    private static final Logger log = LogManager.getLogger(TestMapper.class);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        log.info("key: " + key.get());
        String[] strings = value.toString().split(",");
        log.info("State: " + strings[DataFields.STATE_NAME]);
    }
}
