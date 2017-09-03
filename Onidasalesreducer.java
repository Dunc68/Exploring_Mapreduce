import java.io.IOException; 
 
 
import org.apache.hadoop.io.IntWritable; 
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Reducer; 

 
/** 
* Input key is the the name of the state 
* Input value is 1 
* Output key is the name of the state 
* OUtput value is the number of televisions in the state of Onida 
* 
*/ 
public class OnidaSalesReducer extends Reducer<Text,IntWritable,Text,LongWritable> 
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
