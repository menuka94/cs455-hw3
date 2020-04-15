package cs455.hadoop.q1_maxMonitoringSites;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MaxMonitoringSitesCombiner extends Reducer<Text, CountySiteNumWritable, Text, IntWritable> {
    private static final Logger log = LogManager.getLogger(MaxMonitoringSitesCombiner.class);

    @Override
    protected void reduce(Text key, Iterable<CountySiteNumWritable> values, Context context) throws IOException, InterruptedException {
        HashMap<Integer, HashSet<Integer>> countySiteMap = new HashMap<>();

        // processing one state
        log.info("Processing: " + key.toString());
        for (CountySiteNumWritable value : values) {
            CountySiteNumWritable composite = new CountySiteNumWritable(value.getCountyCode(),
                    value.getSiteNum());

            if (countySiteMap.containsKey(composite.getCountyCode())) {
                countySiteMap.get(composite.getCountyCode()).add(composite.getSiteNum());
            } else {
                HashSet<Integer> siteSet = new HashSet<>();
                siteSet.add(composite.getSiteNum());
                countySiteMap.put(composite.getCountyCode(), siteSet);
            }
        }

        int total = 0;
        for (HashSet<Integer> set : countySiteMap.values()) {
            total += set.size();
        }

        context.write(key, new IntWritable(total));
    }
}
