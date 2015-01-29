package kafka.custom.serializer;

import kafka.utils.VerifiableProperties;
import scala.ScalaObject;

public class CustomEncoder implements kafka.serializer.Encoder<Item>, ScalaObject {

	@Override
	public byte[] toBytes(Item arg0) {
		return arg0.toString().getBytes();
	}

	public CustomEncoder(VerifiableProperties verifiableproperties) {
	}

}
