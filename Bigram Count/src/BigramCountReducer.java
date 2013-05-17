import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class BigramCountReducer extends Reducer<BigramWritable, IntWritable, Text, IntWritable> {
	
	private final static IntWritable SUM = new IntWritable();
	
	@Override
	public void reduce(BigramWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		int sum = 0;
		Iterator<IntWritable> iter = values.iterator();
		while (iter.hasNext()) {
			sum += iter.next().get();
		}
		SUM.set(sum);
		context.write(new Text(key.toString()), SUM);
	}
	
}
