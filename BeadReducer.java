//Farkas Balazs
//EMK76R
//Big data architekturak es elemzo modszerek
//2022. 10. 29.

package basic_package;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class BeadReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	private int sumAll = 0;
	
	public void reduce(Text _key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int sum = 0;
		
		for (IntWritable val : values) {
			sum += val.get();
		}
		
		if(sum>100) {
			sumAll+=sum;
			context.write(_key, new IntWritable(sum));
		}

	}
	
	public void cleanup(Context context) throws IOException, InterruptedException {
		context.write(new Text("Sum: "), new IntWritable(sumAll));
	}

}
