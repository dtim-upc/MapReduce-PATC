package bdma.mapreduce;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CartesianProduct extends JobMapReduce {

    public CartesianProduct() {
        this.input = null;
        this.output = null;
    }

    public static class CartesianMapper extends Mapper<Text, Text, IntWritable, Text> {
		
		private static int N = 100;
		
		public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
			// Obtain the parameters sent during the configuration of the job
			String cartesian = context.getConfiguration().getStrings("cartesian")[0];
			String external = context.getConfiguration().getStrings("external")[0];
			String internal = context.getConfiguration().getStrings("internal")[0];
			// Since the value is a CSV, just get the lines split by commas
			String[] arrayValues = value.toString().split(",");
			String cartesianValue = Utils.getAttribute(arrayValues, cartesian);
			// Do the cartesian product and emit it
			if (cartesianValue.equals(external)) {
				int newKey = (int)(Math.random()*N);
				context.write(new IntWritable(newKey), value);
			}
			else if (cartesianValue.equals(internal)) {
				for (int newKey = 0; newKey < N; newKey++) {
					context.write(new IntWritable(newKey), value);
				}
			}
		}
		
	}
	
	public static class CartesianReducer extends Reducer<IntWritable, Text, NullWritable, Text> {
		
		public void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            String cartesian = context.getConfiguration().getStrings("cartesian")[0];
			String external = context.getConfiguration().getStrings("external")[0];
			// Let's separate between the external and internal sets
			ArrayList<String> externals = new ArrayList<String>();
			ArrayList<String> internals = new ArrayList<String>();
			for (Text value : values) {
				String[] arrayValues = value.toString().split(",");
				String cartesianValue = Utils.getAttribute(arrayValues, cartesian);
				// If it is external, let's add it as it
				if (cartesianValue.equals(external)) {
					externals.add(value.toString());
				}
				// If it is internal, let's add it as it
				else {
					internals.add(value.toString());
				}
			}
			// Finally, let's iterate over both external and internal sets
			// and making pairs of them
			for (int i = 0; i < externals.size(); i++) {
				for (int j = 0; j < internals.size(); j++) {
					context.write(NullWritable.get(), new Text(externals.get(i)+"<->"+internals.get(j)));
				}
			}
		}
		
	}
	
	public boolean run() throws IOException, ClassNotFoundException, InterruptedException {
		Configuration configuration = new Configuration();
		// Define the new job and the name it will be given
		Job job = Job.getInstance(configuration, "CartesianProduct");
		configureJob(job,this.input,this.output);
	    // Let's run it!
	    return job.waitForCompletion(true);
	}

    public static void configureJob(Job job, String pathIn, String pathOut) throws IOException, ClassNotFoundException, InterruptedException {
        job.setJarByClass(CartesianProduct.class);
        // Set the mapper class it must use
        job.setMapperClass(CartesianMapper.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);
        // Set the reducer class it must use
        job.setReducerClass(CartesianReducer.class);
        // The output will be Text
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);
        // The files the job will read from/write to
        job.setInputFormatClass(SequenceFileInputFormat.class);
        FileInputFormat.addInputPath(job, new Path(pathIn));
        FileOutputFormat.setOutputPath(job, new Path(pathOut));
        // These are the parameters that we are sending to the job
        job.getConfiguration().setStrings("cartesian", "type");
        job.getConfiguration().setStrings("external", "type_1");
        job.getConfiguration().setStrings("internal", "type_2");

    }
	
}
