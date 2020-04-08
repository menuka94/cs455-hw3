package cs455.hadoop.maxMonitoringSites;

import java.io.IOException;
import cs455.hadoop.util.Constants;
import cs455.hadoop.util.DataFields;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MaxMonitoringSitesMapper extends Mapper<LongWritable, Text, Text, CountySiteNumWritable> {
    private static final Logger log = LogManager.getLogger(MaxMonitoringSitesMapper.class);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (key.get() == 0 && value.toString().contains("State")) {
            log.info("Skipping header row");
        } else {
            String[] splits = value.toString().split(Constants.DELIMETER);

            String stateCode = splits[DataFields.STATE_CODE - 1].replaceAll("\"", "");
            String countyCode = splits[DataFields.COUNTY_CODE - 1].replaceAll("\"", "");
            String siteNum = splits[DataFields.SITE_NUM - 1].replaceAll("\"", "");

            try {
                int countyCodeInt = Integer.parseInt(countyCode.trim());
                int siteNumInt = Integer.parseInt(siteNum.trim());
                CountySiteNumWritable writable = new CountySiteNumWritable(countyCodeInt, siteNumInt);

                String state = splits[DataFields.STATE_NAME - 1].replaceAll("\"", "");

                context.write(new Text(state), writable);

            } catch (NumberFormatException e) {
                log.error("Invalid data");
                e.printStackTrace();
            }
        }
    }
}
