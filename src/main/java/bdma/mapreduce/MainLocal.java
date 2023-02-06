package bdma.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MainLocal extends Configured implements Tool {
	
    static String HADOOP_COMMON_PATH = "C:\\ ... \\src\\main\\resources\\winutils";

    public int run(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "LocalMapReduce");

        if (args[0].equals("-projection")) {
            Projection.configureJob(job, args[1], args[2]);
        }
        else if (args[0].equals("-aggregationSum")) {
            AggregationSum.configureJob(job, args[1],args[2]);
        }
        else if (args[0].equals("-cartesian")) {
            CartesianProduct.configureJob(job, args[1], args[2]);
        }
        else if (args[0].equals("-selection")) {
            Selection.configureJob(job, args[1], args[2]);
        }
        else if (args[0].equals("-aggregationAvg")) {
            AggregationAvg.configureJob(job, args[1], args[2]);
        }

        boolean success = job.waitForCompletion(true);
        return success ? 0 : 1;
    }

	public static void main(String[] args) throws Exception {
        //System.setProperty("hadoop.home.dir", HADOOP_COMMON_PATH);
		
        MainLocal driver = new MainLocal();
        int exitCode = ToolRunner.run(driver,args);
        System.exit(exitCode);
	}

}
