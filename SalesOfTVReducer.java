import java.io.IOException; 

 
import org.apache.hadoop.io.IntWritable; 
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Reducer; 

 
public class SalesOfTVReducer extends Reducer<Text,IntWritable,Text,LongWritable> 
{ 
@Override 
public void reduce(Text key,Iterable<IntWritable> values,Context context) throws IOException, InterruptedException 
{ 
long lCount = 0; 
for(IntWritable value:values) 
{
	
lCount=lCount + value.get(); 
} 
context.write(key, new LongWritable(lCount)); 
} 
} 

