import java.io.IOException; 
 
import org.apache.hadoop.conf.Configuration; 
import org.apache.hadoop.fs.Path; 
import org.apache.hadoop.io.IntWritable; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Job; 
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat; 
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat; 
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat; 
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat; 

 
public class OnidaSales  
{ 
@SuppressWarnings("deprecation") 
public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException  
{ 
//Check if input parameters provided appropriately 
if(args==null || args.length!=2) 
{ 
System.err.println("Incorrect jar or output directory provided"); 
System.exit(-1); 
} 

	 
//Instantiate configuration object 
Configuration conf = new Configuration(); 
	 
//Instantiate job object 
Job job  = new Job(conf,"OnidaSales"); 
 		 
	 
job.setJarByClass(OnidaSales.class); 
 	 
/* 
 * Set input pathput 
*/ 
FileInputFormat.setInputPaths(job, new Path(args[0])); 
 		 
/* 
* Set output path 
*/ 
Path outputPath = new Path(args[1]); 
FileOutputFormat.setOutputPath(job, outputPath); 
 		 
//Delete output directory if already existing will fail if present
outputPath.getFileSystem(conf).delete(outputPath, true); 
		 
//Set mapper class 
job.setMapperClass(OnidaSalesMapper.class); 
job.setReducerClass(OnidaSalesReducer.class); 
		 
//Set input and output format class 
job.setInputFormatClass(TextInputFormat.class); 
job.setOutputFormatClass(TextOutputFormat.class); 
//Set output key'value class types 
job.setOutputKeyClass(Text.class); 
job.setOutputValueClass(IntWritable.class); 
 				 
//Execute the job and wait until completion and then exit 
System.exit(job.waitForCompletion(true)?0:1); 
		 
} 

 
	 
} 
