package contactlistservice.entity.converter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class DateSerializer extends StdSerializer<LocalDateTime>{

	private static final long serialVersionUID = -6234258830013248578L;
	private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public DateSerializer() {
		this(null);
	}
	
	public DateSerializer(Class<LocalDateTime> dateTime) {
		super(dateTime);
	}

	@Override
	public void serialize(LocalDateTime dateTime, JsonGenerator generator, SerializerProvider provider) 
			throws IOException {
		
		generator.writeString(dateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
	}
}
