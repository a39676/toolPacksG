package toolPack.dateTimeHandle;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

	@Override
	public JsonElement serialize(LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {
		LocalDateTimeHandler l = new LocalDateTimeHandler();
		return new JsonPrimitive(l.dateToStr(date));
	}

	@Override
	public LocalDateTime deserialize(JsonElement element, Type type, JsonDeserializationContext context)
			throws JsonParseException {
		LocalDateTimeHandler l = new LocalDateTimeHandler();
		return l.jsonStrToLocalDateTime(String.valueOf(element));
	}
}