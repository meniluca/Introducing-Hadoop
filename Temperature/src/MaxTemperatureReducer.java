import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class MaxTemperatureReducer 
	extends MapReduceBase implements Reducer<Text, IntWritable, Text, DoubleWritable> {
	
	public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, DoubleWritable> output, Reporter reporter)
			throws IOException {
		
		int maxValue = Integer.MIN_VALUE;
		
		while (values.hasNext()) {
			maxValue = Math.max(maxValue, values.next().get());
		}
		
		output.collect(key, new DoubleWritable(((double)maxValue)/10));
	}
	
}