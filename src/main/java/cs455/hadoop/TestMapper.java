package cs455.hadoop;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TestMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private static final Logger log = LogManager.getLogger(TestMapper.class);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        StringTokenizer itr = new StringTokenizer(value.toString());
        int i = 0;
        while (itr.hasMoreTokens()) {
            String nextToken = itr.nextToken();
            log.info(nextToken);
            context.write(new Text(nextToken), new IntWritable(1));
            if (i++ > 3) {
                break;
            }
        }
    }
}
