package cs455.hadoop;

import java.util.StringTokenizer;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TestMapper extends Mapper {
    private static final Logger log = LogManager.getLogger(TestMapper.class);

    @Override
    protected void map(Object key, Object value, Context context) {
        log.info("map()");
        StringTokenizer itr = new StringTokenizer(value.toString());
        while (itr.hasMoreTokens()) {
            log.info(itr.nextToken());
        }
    }
}
