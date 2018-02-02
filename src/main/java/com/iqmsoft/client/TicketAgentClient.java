package com.iqmsoft.client;

import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.JAXBElement;

import com.iqmsoft.ticketagent.ObjectFactory;
import com.iqmsoft.ticketagent.TFlightsResponse;
import com.iqmsoft.ticketagent.TListFlights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

@Component
public class TicketAgentClient {

  @Autowired
  private WebServiceTemplate webServiceTemplate;

  @SuppressWarnings("unchecked")
  public List<BigInteger> listFlights() {
    ObjectFactory factory = new ObjectFactory();
    TListFlights tListFlights = factory.createTListFlights();

    JAXBElement<TListFlights> request = factory.createListFlightsRequest(tListFlights);

    // use SoapActionCallback to add the SOAPAction
    JAXBElement<TFlightsResponse> response =
        (JAXBElement<TFlightsResponse>) webServiceTemplate.marshalSendAndReceive(request,
            new SoapActionCallback("http://iqmsoft.com/TicketAgent/listFlights"));

    return response.getValue().getFlightNumber();
  }
}
