package org.fireshine.distance_calculator.service;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.InputStream;

public interface UploadService {

    public void uploadToDatabase(InputStream inputStream) throws XMLStreamException, JAXBException, FileNotFoundException;

}
