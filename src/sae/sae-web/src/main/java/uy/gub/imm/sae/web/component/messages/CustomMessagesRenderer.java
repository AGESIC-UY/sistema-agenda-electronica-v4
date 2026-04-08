package uy.gub.imm.sae.web.component.messages;

import org.primefaces.component.messages.MessagesRenderer;
import org.primefaces.context.PrimeFacesContext;
import org.primefaces.context.PrimeRequestContext;
import org.primefaces.expression.SearchExpressionFacade;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class CustomMessagesRenderer extends MessagesRenderer {
    private static final Logger logger = Logger.getLogger(CustomMessagesRenderer.class.getName());

    public CustomMessagesRenderer() {
    }

    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        CustomMessages uiMessages = (CustomMessages)component;
        ResponseWriter writer = context.getResponseWriter();
        String clientId = uiMessages.getClientId(context);
        Map<String, List<FacesMessage>> messagesMap = new HashMap<>();
        boolean globalOnly = uiMessages.isGlobalOnly();
        String containerClass = uiMessages.isShowIcon() ? "ui-messages ui-widget" : "ui-messages ui-messages-noicon ui-widget";
        String style = uiMessages.getStyle();
        String styleClass = uiMessages.getStyleClass();
        styleClass = styleClass == null ? containerClass : containerClass + " " + styleClass;
        String form = uiMessages.getFor();
        List<FacesMessage> messages = new ArrayList<>();
        Iterator<FacesMessage> messagesIterator;
        if (!this.isValueBlank(form)) {
            String forType = uiMessages.getForType();
            Iterator<FacesMessage> messagesIteratorForContext = PrimeFacesContext.getCurrentInstance().getMessages(form);
            if (forType == null || forType.equals("key")) {
                while(messagesIteratorForContext.hasNext()) {
                    messages.add(messagesIteratorForContext.next());
                }
            }

            if (forType == null || forType.equals("expression")) {
                UIComponent forComponent = SearchExpressionFacade.resolveComponent(context, uiMessages, form);
                if (forComponent != null) {
                    String forComponentClientId = forComponent.getClientId(context);
                    if (!form.equals(forComponentClientId)) {
                        messagesIterator = context.getMessages(forComponentClientId);

                        while(messagesIterator.hasNext()) {
                            FacesMessage next = messagesIterator.next();
                            if (!messages.contains(next)) {
                                messages.add(next);
                            }
                        }
                    }
                }
            }
        } else {
            messagesIterator = uiMessages.isGlobalOnly() ? PrimeFacesContext.getCurrentInstance().getMessages((String)null) : PrimeFacesContext.getCurrentInstance().getMessages();

            while(messagesIterator.hasNext()) {
                messages.add(messagesIterator.next());
            }
        }

        messagesIterator = messages.iterator();

        while(messagesIterator.hasNext()) {
            FacesMessage message = messagesIterator.next();
            FacesMessage.Severity severity = message.getSeverity();
            if (severity.equals(FacesMessage.SEVERITY_INFO)) {
                this.addMessage(uiMessages, message, messagesMap, "info");
            } else if (severity.equals(FacesMessage.SEVERITY_WARN)) {
                this.addMessage(uiMessages, message, messagesMap, "warn");
            } else if (severity.equals(FacesMessage.SEVERITY_ERROR) || severity.toString().contains(FacesMessage.SEVERITY_ERROR.toString())) {
                this.addMessage(uiMessages, message, messagesMap, "error");
            } else if (severity.equals(FacesMessage.SEVERITY_FATAL)) {
                this.addMessage(uiMessages, message, messagesMap, "fatal");
            }
        }

        writer.startElement("div", uiMessages);
        writer.writeAttribute("id", clientId, "id");
        writer.writeAttribute("class", styleClass, (String)null);
        if (style != null) {
            writer.writeAttribute("style", style, (String)null);
        }

        writer.writeAttribute("aria-live", "polite", (String)null);
        if (PrimeRequestContext.getCurrentInstance().getApplicationContext().getConfig().isClientSideValidationEnabled()) {
            writer.writeAttribute("data-global", String.valueOf(globalOnly), (String)null);
            writer.writeAttribute("data-summary", uiMessages.isShowSummary(), (String)null);
            writer.writeAttribute("data-detail", uiMessages.isShowDetail(), (String)null);
            writer.writeAttribute("data-severity", this.getClientSideSeverity(uiMessages.getSeverity()), (String)null);
            writer.writeAttribute("data-redisplay", String.valueOf(uiMessages.isRedisplay()), (String)null);
        }

       for(Map.Entry<String,List<FacesMessage>> entry: messagesMap.entrySet()){
            List<FacesMessage> severityMessages = entry.getValue();
            if (!severityMessages.isEmpty()) {
                this.encodeSeverityMessages(context, uiMessages, entry.getKey(), severityMessages);
            }
        }

        writer.endElement("div");
        if (uiMessages.isAutoUpdate()) {
            logger.info("autoUpdate attribute is deprecated and will be removed in a future version, use p:autoUpdate component instead.");
        }

    }

    protected void addMessage(CustomMessages uiMessages, FacesMessage message, Map<String, List<FacesMessage>> messagesMap, String severity) {
        if (this.shouldRender(uiMessages, message, severity)) {
            List<FacesMessage> severityMessages = messagesMap.get(severity);
            if(severityMessages == null) {
                severityMessages = new ArrayList<>();
            }
            severityMessages.add(message);
            messagesMap.put(severity, severityMessages);
        }
    }

    protected void encodeSeverityMessages(FacesContext context, CustomMessages uiMessages, String severity, List<FacesMessage> messages) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String styleClassPrefix = "ui-messages-" + severity;
        boolean escape = uiMessages.isEscape();
        writer.startElement("div", (UIComponent)null);
        writer.writeAttribute("class", styleClassPrefix + " ui-corner-all", (String)null);
        if (uiMessages.isClosable()) {
            this.encodeCloseIcon(context, uiMessages);
        }

        if (uiMessages.isShowIcon()) {
            writer.startElement("span", (UIComponent)null);
            writer.writeAttribute("class", styleClassPrefix + "-icon", (String)null);
            writer.endElement("span");
        }

        String title = null;
        if (messages.size() == 1) {
            if ("info".equals(severity)) {
                title = uiMessages.getSingleInfoTitle();
            } else if ("warn".equals(severity)) {
                title = uiMessages.getSingleWarnTitle();
            } else if ("error".equals(severity)) {
                title = uiMessages.getSingleErrorTitle();
            }
        } else if ("info".equals(severity)) {
            title = uiMessages.getMultiInfoTitle();
        } else if ("warn".equals(severity)) {
            title = uiMessages.getMultiWarnTitle();
        } else if ("error".equals(severity)) {
            title = uiMessages.getMultiErrorTitle();
        }

        if (title != null) {
            title = title.replace("{count}", "" + messages.size());
            writer.startElement("h4", null);
            writer.writeAttribute("class", styleClassPrefix + "-title", (String)null);
            writer.write(title);
            writer.endElement("h4");
        }

        writer.startElement("ul", null);

        for (FacesMessage msg : messages) {
            writer.startElement("li", null);
            writer.writeAttribute("role", "alert",  null);
            writer.writeAttribute("aria-atomic", "true",  null);
            String summary = msg.getSummary() != null ? msg.getSummary() : "";
            String detail = msg.getDetail() != null ? msg.getDetail() : summary;
            if (uiMessages.isShowSummary()) {
                writer.startElement("span", (UIComponent) null);
                writer.writeAttribute("class", styleClassPrefix + "-summary", (String) null);
                if (escape) {
                    writer.writeText(summary, (String) null);
                } else {
                    writer.write(summary);
                }

                writer.endElement("span");
            }

            if (uiMessages.isShowDetail()) {
                writer.startElement("span", (UIComponent) null);
                writer.writeAttribute("class", styleClassPrefix + "-detail", (String) null);
                if (escape) {
                    writer.writeText(detail, (String) null);
                } else {
                    writer.write(detail);
                }

                writer.endElement("span");
            }

            writer.endElement("li");
            msg.rendered();
        }

        writer.endElement("ul");
        writer.endElement("div");
        writer.startElement("br", null);
        writer.endElement("br");
    }

    protected void encodeCloseIcon(FacesContext context, CustomMessages uiMessages) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("a", null);
        writer.writeAttribute("href", "#", null);
        writer.writeAttribute("class", "ui-messages-close",null);
        writer.writeAttribute("onclick", "$(this).parent().slideUp();return false;", (String)null);
        writer.startElement("span",null);
        writer.writeAttribute("class", "ui-icon ui-icon-close", null);
        writer.endElement("span");
        writer.endElement("a");
    }
}
