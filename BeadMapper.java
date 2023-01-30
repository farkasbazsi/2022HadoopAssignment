//Farkas Balazs
//EMK76R
//Big data architekturak es elemzo modszerek
//2022. 10. 29.

package basic_package;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class BeadMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String s = ivalue.toString();
		for(int i = 0; i < s.length()-2; i++) {
			if(s.substring(i,i+3).indexOf('T')>=0) { 
				context.write(new Text(s.substring(i,i+3)), new IntWritable(1));
			}
			
		}
		
	}

}
