light-bson
==========

Light-weight, good performing java bson writer with facade for the jackson JsonGenerator.

``` 
BsonGeneratorFactory lightBson = new BsonGeneratorFactory();
ByteArrayOutputStream out = new ByteArrayOutputStream();
com.fasterxml.jackson.core.JsonGenerator generator = lightBson.createGenerator(out);

generator.writeStartObject();
generator.writeFieldName("BSON");
generator.writeStartArray();
generator.writeString("awesome");
generator.writeNumber(5.05);
generator.writeNumber(1986);
generator.writeEndArray();
generator.writeEndObject();
generator.close();
``` 
