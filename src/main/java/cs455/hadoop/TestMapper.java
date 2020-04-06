package cs455.hadoop;

import java.io.IOException;
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
        log.info("map()");
        String textValue = value.toString();
        String[] splits = textValue.split(" ");
        for (String split : splits) {
            if (split.chars().allMatch(Character::isLetter)) {
                context.write(new Text(split), new IntWritable(1));
            }
        }
    }
}
