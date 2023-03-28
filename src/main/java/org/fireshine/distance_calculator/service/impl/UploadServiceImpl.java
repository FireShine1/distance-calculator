package org.fireshine.distance_calculator.service.impl;

import org.fireshine.distance_calculator.model.City;
import org.fireshine.distance_calculator.model.Distance;
import org.fireshine.distance_calculator.service.CityService;
import org.fireshine.distance_calculator.service.DistanceService;
import org.fireshine.distance_calculator.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {

    private final DistanceService distanceService;
    private final CityService cityService;

    @Autowired
    public UploadServiceImpl(DistanceService distanceService, CityService cityService) {
        this.distanceService = distanceService;
        this.cityService = cityService;
    }

    @Override
    public void uploadToDatabase(InputStream inputStream) throws XMLStreamException, JAXBException {
        JAXBContext cityContext;
        JAXBContext distanceContext;
        Unmarshaller cityUnmarshaller;
        Unmarshaller distanceUnmarshaller;
        XMLStreamReader streamReader;
        List<City> cities = new ArrayList<>();
        List<Distance> distances = new ArrayList<>();

        XMLInputFactory inputFactory = XMLInputFactory.newFactory();
        streamReader = inputFactory.createXMLStreamReader(inputStream);

        cityContext = JAXBContext.newInstance(City.class);
        cityUnmarshaller = cityContext.createUnmarshaller();
        distanceContext = JAXBContext.newInstance(Distance.class);
        distanceUnmarshaller = distanceContext.createUnmarshaller();

        while (streamReader.hasNext()) {
            int event = streamReader.getEventType();
            if (event == XMLEvent.START_ELEMENT) {
                String elementName = streamReader.getLocalName();
                if ("City".equals(elementName)) {
                    JAXBElement<City> cityElement = cityUnmarshaller.unmarshal(streamReader, City.class);
                    City city = cityElement.getValue();
                    cities.add(city);
                }
                if ("Distance".equals(elementName)) {
                    JAXBElement<Distance> distanceElement = distanceUnmarshaller.unmarshal(streamReader, Distance.class);
                    Distance distance = distanceElement.getValue();
                    distances.add(distance);
                }
            }
            streamReader.next();
        }
        streamReader.close();
        cityService.saveAll(cities);
        distanceService.saveAll(distances);
    }
}
