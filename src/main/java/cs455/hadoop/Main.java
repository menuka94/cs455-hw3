package cs455.hadoop;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        Configuration conf = new Configuration();

        try {
            Job job = Job.getInstance(conf, "Sample Job");
            job.setJarByClass(Main.class);
            job.setMapperClass(TestMapper.class);
            job.setReducerClass(TestReducer.class);

            // path io input HDFS
            FileInputFormat.addInputPath(job, new Path(args[0]));

            FileOutputFormat.setOutputPath(job, new Path(args[1]));

            // block until the job is completed
            System.exit(job.waitForCompletion(true) ? 0 : 1);
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            log.error("Error in MapReduce job");
            e.printStackTrace();
        }
    }
}
