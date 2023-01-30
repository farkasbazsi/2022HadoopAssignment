//Farkas Balazs
//EMK76R
//Big data architekturak es elemzo modszerek
//2022. 10. 29.

package basic_package;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class BeadDriver {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "JobName");
		job.setJarByClass(basic_package.BeadDriver.class);
		job.setMapperClass(basic_package.BeadMapper.class);
		job.setCombinerClass(BeadReducer.class);
		job.setReducerClass(basic_package.BeadReducer.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		FileInputFormat.setInputPaths(job, new Path("kmerInput.txt"));
		FileOutputFormat.setOutputPath(job, new Path("out"));
		
		FileSystem fs;
		try {
			fs = FileSystem.get(conf);
			if (fs.exists(new Path("out")))
				fs.delete(new Path("out"),true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (!job.waitForCompletion(true))
			return;
	}

}
