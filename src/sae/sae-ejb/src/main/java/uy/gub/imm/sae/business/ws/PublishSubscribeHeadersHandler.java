package uy.gub.imm.sae.business.ws;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class PublishSubscribeHeadersHandler
        implements SOAPHandler<SOAPMessageContext> {

    private final String producerId;
    private final String topicId;
    private String notificationId;

    public PublishSubscribeHeadersHandler(String producerId, String topicId) {
        this.producerId = producerId;
        this.topicId = topicId;
    }

    @Override
    public Set<QName> getHeaders() {
        return new TreeSet();
    }

    @Override
    public void close(MessageContext mc) {
    }

    @Override
    public boolean handleFault(SOAPMessageContext mc) {
        return true;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext mc) {
        Boolean outbound = (Boolean) mc.get("javax.xml.ws.handler.message.outbound");
        if (outbound) {
            SOAPMessage message = mc.getMessage();
            try {
                SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
                SOAPFactory factory = SOAPFactory.newInstance();
                String prefix = "ps";
                String uri = "http://ps.agesic.gub.uy";
                SOAPElement producerElem = factory.createElement("producer", prefix, uri);
                SOAPElement topicElem = factory.createElement("topic", prefix, uri);
                producerElem.addTextNode(this.producerId);
                topicElem.addTextNode(this.topicId);
                SOAPHeader header = envelope.getHeader();
                header.addChildElement(producerElem);
                header.addChildElement(topicElem);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            SOAPMessage message = mc.getMessage();
            try {
                Iterator it = message.getSOAPHeader().getChildElements(new QName("http://ps.agesic.gub.uy", "notificationId"));
                if (it.hasNext()) {
                    SOAPHeaderElement elem = (SOAPHeaderElement) it.next();
                    this.notificationId = elem.getValue();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

    public String getNotificationId() {
        return this.notificationId;
    }
}
