package com.iqmsoft.endpoint;

import java.math.BigInteger;

import javax.xml.bind.JAXBElement;

import com.iqmsoft.ticketagent.ObjectFactory;
import com.iqmsoft.ticketagent.TFlightsResponse;
import com.iqmsoft.ticketagent.TListFlights;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

@Endpoint
public class TicketAgentEndpoint {

  private static final Logger LOGGER = LoggerFactory.getLogger(TicketAgentEndpoint.class);

  // map a message to this endpoint based on the SOAPAction
  @SoapAction(value = "http://iqmsoft.com/TicketAgent/listFlights")
  @ResponsePayload
  public JAXBElement<TFlightsResponse> listFlights(
      @RequestPayload JAXBElement<TListFlights> request, MessageContext messageContext) {
    // access the SOAPAction value
    WebServiceMessage webServiceMessage = messageContext.getRequest();
    SoapMessage soapMessage = (SoapMessage) webServiceMessage;
    LOGGER.info("SOAPAction header: {}", soapMessage.getSoapAction());

    ObjectFactory factory = new ObjectFactory();
    TFlightsResponse tFlightsResponse = factory.createTFlightsResponse();
    tFlightsResponse.getFlightNumber().add(BigInteger.valueOf(99));

    return factory.createListFlightsResponse(tFlightsResponse);
  }
}
