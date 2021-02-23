package com.chrish.paymentsystem.queuecomms.serialisation;

import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class MessageSerialiser<T> {

    private final Class<T> clazz;
    private final JAXBContext jaxbContext;

    public MessageSerialiser(Class<T> clazz) throws JAXBException {
        this.clazz = clazz;
        jaxbContext = JAXBContext.newInstance(clazz);
    }

    public String serialise(T message) throws JAXBException {
        Marshaller marshaller = jaxbContext.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(message, stringWriter);

        return stringWriter.toString();
    }

    public T deserialise(String message) throws JAXBException {
        StringReader stringReader = new StringReader(message);

        return deserialise(new StreamSource(stringReader));
    }

    public T deserialise(byte[] message) throws JAXBException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(message);

        return deserialise(new StreamSource(byteArrayInputStream));
    }

    public T deserialise(StreamSource streamSource) throws JAXBException {
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller.unmarshal(streamSource, clazz).getValue();
    }
}
