import java.io.IOException; 
 
import org.apache.hadoop.conf.Configuration; 
import org.apache.hadoop.fs.Path; 
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat; 
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat; 
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat; 
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat; 

 

@SuppressWarnings("unused")
public class TV { 
 
 
public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException  
{ 
//Check if input parameters provided appropriately 
if(args==null || args.length!=2) 
{ 
System.err.println("Wrong input parameters provided"); 
System.exit(-1); 
} 
	 
		 
//Instantiate configuration object 
Configuration conf = new Configuration();
 
//Instantiate job object 
@SuppressWarnings("deprecation")
Job job  = new Job(conf,"Remove records with NA");

job.setJarByClass(TV.class); 

/* 
 * Input path for data
 */

FileInputFormat.setInputPaths(job, new Path(args[0])); 

/* 
* Output path for results
*/ 

Path outputPath = new Path(args[1]); 
FileOutputFormat.setOutputPath(job, outputPath); 
	 
//Delete output directory if already existing will fail if already exists
outputPath.getFileSystem(conf).delete(outputPath, true); 
 
//Set mapper class to mapper TVMapper

job.setMapperClass(TVMapper.class);

//Set input and output format class 

job.setInputFormatClass(TextInputFormat.class); 
job.setOutputFormatClass(TextOutputFormat.class);

//Set output key'value class types 

job.setOutputKeyClass(Text.class); 
job.setOutputValueClass(Text.class); 
	 
//To set the job with no reducer 
job.setNumReduceTasks(0); 
		 
//Execute the job and wait until completion and then exit 
System.exit(job.waitForCompletion(true)?0:1); 
		 
} 

 
} 
