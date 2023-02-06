package bdma.mapreduce;

public class MainCluster {

	private static JobMapReduce job;

	public static void main(String[] args) throws Exception {
	    if (args[0].equals("-projection")) {
	        job = new Projection();
        }
	    else if (args[0].equals("-aggregationSum")) {
	        job = new AggregationSum();
	    }
	    else if (args[0].equals("-cartesian")) {
	        job = new CartesianProduct();
	    }
        else if (args[0].equals("-selection")) {
            job = new Selection();
        }
        else if (args[0].equals("-aggregationAvg")) {
            job = new AggregationAvg();
        }
        job.setInput(args[1]);
	    job.setOutput(args[2]);
	    job.run();
	}

}
