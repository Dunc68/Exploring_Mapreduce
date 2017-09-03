import java.io.IOException; 
import java.util.StringTokenizer; 

 
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.Text; 
import org.apache.hadoop.mapreduce.Mapper; 


// Set data types

public class TVMapper extends Mapper<LongWritable,Text,Text,Text> 
{ 
private final static String DELIMITER = "|"; 
private final static String NA = "NA"; 
	 
@Override 
public void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException 
{ 
//Input format : Company|Product|Size|State|ZIP|Price 

 
String strValue  = value.toString(); 
System.out.println("Current value is "+strValue); 
StringTokenizer tokenizer  = new StringTokenizer(strValue, DELIMITER); 
String strCompanyName = tokenizer.nextToken(); 
String strProductName  = tokenizer.nextToken(); 
	 
//Check if CompanyName or Product Name is 'NA' 
if(!strCompanyName.equalsIgnoreCase(NA)  && !strProductName.equalsIgnoreCase(NA)) 
{ 
context.write(value,null); 
System.out.println("Put value "+strValue+" in context"); 
} 
 		 
		 
} 
} 

