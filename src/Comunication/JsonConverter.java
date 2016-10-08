package Comunication;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Static class used to construct a json from an object.
 * @author gsegura96
 *
 */
public class JsonConverter {

	/**Constructs a {@link String} json from the specified object.
	 * @param value Object to be converted.
	 * @return String containing the json structure.
	 */
	public static String objectToJson(Object value) {
		ObjectMapper objectMapper = new ObjectMapper();
		StringWriter stringJson = new StringWriter();
		try {
			objectMapper.writeValue(stringJson, value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
		return stringJson.toString();

	}
	

}
