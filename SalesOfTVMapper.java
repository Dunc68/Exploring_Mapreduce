import java.io.IOException; 
import java.util.StringTokenizer; 

 
import org.apache.hadoop.io.IntWritable; 
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Mapper; 

 
/* 
* Input key is the offset of line in file 
* Input value format : CompanyName|ProductName|SizeInInches|State|PinCOde|Prize 
* Output key is the the name of the company 
* Output value is 1 
*/ 

public class SalesOfTVMapper extends Mapper<LongWritable,Text,Text,IntWritable> 
{ 
private final static String DELIMITER = "|"; 
private final static String NA = "NA"; 
private final static IntWritable ONE = new IntWritable(1); 
Text companyName  = new Text(); 
	 
@Override 
public void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException 
{ 
String strValue  = value.toString(); 
System.out.println("Current value is "+strValue); 
		 
StringTokenizer tokenizer  = new StringTokenizer(strValue, DELIMITER); 
String strCompanyName = tokenizer.nextToken(); 
String strProductName  = tokenizer.nextToken(); 
 
//Check if CompanyName or Product Name is 'NA' 

if(!strCompanyName.equalsIgnoreCase(NA)  && !strProductName.equalsIgnoreCase(NA)) 
{ 
companyName.set(strCompanyName.trim()); 
context.write(companyName,ONE); 
System.out.println("Putting key : "+strCompanyName+" and value : 1 in context"); 
} 
} 
} 

