import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;


public class WordCount {

	public static void main(String[] args) throws Exception {
		
		JobConf conf = new JobConf(WordCount.class);
		conf.setJobName("Word count");

		conf.setMapperClass(WordCountMapper.class);
		conf.setReducerClass(WordCountReducer.class);
		
		FileInputFormat.addInputPath(conf, new Path("words.txt"));
		FileOutputFormat.setOutputPath(conf, new Path("output"));
		
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);

		JobClient.runJob(conf);
	}

}