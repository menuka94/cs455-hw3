package cs455.hadoop.q4_yearlySO2Levels;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class YearlySO2LevelsJob {
    private static final Logger log = LogManager.getLogger(YearlySO2LevelsJob.class);

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: " + YearlySO2LevelsJob.class + " inputPath outputPath");
            System.exit(1);
        }

        String inputPath = args[0];
        String outputPath = args[1];

        log.info("inputPath: " + inputPath);
        log.info("outputPath: " + outputPath);

        Configuration conf = new Configuration();

        try {
            Job job = Job.getInstance(conf, "Yearly SO2 Levels Job");
            job.setJarByClass(YearlySO2LevelsJob.class);
            job.setMapperClass(YearlySO2LevelsMapper.class);
            job.setReducerClass(YearlySO2LevelsReducer.class);

            // path io input HDFS
            FileInputFormat.addInputPath(job, new Path(inputPath));
            FileInputFormat.setInputDirRecursive(job, true);

            // Delete output if exists
            FileSystem hdfs = FileSystem.get(conf);
            if (hdfs.exists(new Path(outputPath))) {
                hdfs.delete(new Path(outputPath), true);
            }

            FileOutputFormat.setOutputPath(job, new Path(outputPath));

            // Outputs from the Mapper.
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(DoubleWritable.class);

            // block until the job is completed
            System.exit(job.waitForCompletion(true) ? 0 : 1);
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            log.error("Error in MapReduce job");
            e.printStackTrace();
        }
    }
}
