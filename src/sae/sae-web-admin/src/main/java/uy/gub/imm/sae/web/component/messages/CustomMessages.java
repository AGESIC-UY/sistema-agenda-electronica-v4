package uy.gub.imm.sae.web.component.messages;

import org.primefaces.component.messages.Messages;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

@FacesComponent(value = "uy.gub.imm.sae.web.component.messages", createTag = true,
        namespace =  "http://uy.gub.imm.sae.web/", tagName = "messages")
@ResourceDependencies({
        @ResourceDependency(library="primefaces", name="primefaces.css"),
        @ResourceDependency(library="primefaces", name="jquery/jquery.js"),
        @ResourceDependency(library="primefaces", name="primefaces.js")}
)
public class CustomMessages extends Messages {
    public CustomMessages(){
        setRendererType("uy.gub.imm.sae.web.component.messages.CustomMessagesRenderer");
    }

    public boolean isAutoUpdate() {
        return (Boolean)this.getStateHelper().eval(CustomMessages.PropertyKeys.autoUpdate, false);
    }

    public void setAutoUpdate(boolean autoUpdate) {
        this.getStateHelper().put(CustomMessages.PropertyKeys.autoUpdate, autoUpdate);
    }

    public boolean isEscape() {
        return (Boolean)this.getStateHelper().eval(Messages.PropertyKeys.escape, true);
    }

    public void setEscape(boolean escape) {
        this.getStateHelper().put(Messages.PropertyKeys.escape, escape);
    }

    public String getSeverity() {
        return (String)this.getStateHelper().eval(Messages.PropertyKeys.severity, (Object)null);
    }

    public void setSeverity(String severity) {
        this.getStateHelper().put(Messages.PropertyKeys.severity, severity);
    }

    public boolean isClosable() {
        return (Boolean)this.getStateHelper().eval(Messages.PropertyKeys.closable, false);
    }

    public void setClosable(boolean closable) {
        this.getStateHelper().put(Messages.PropertyKeys.closable, closable);
    }

    public String getStyle() {
        return (String)this.getStateHelper().eval(Messages.PropertyKeys.style, (Object)null);
    }

    public void setStyle(String style) {
        this.getStateHelper().put(Messages.PropertyKeys.style, style);
    }

    public String getStyleClass() {
        return (String)this.getStateHelper().eval(Messages.PropertyKeys.styleClass, (Object)null);
    }

    public void setStyleClass(String styleClass) {
        this.getStateHelper().put(Messages.PropertyKeys.styleClass, styleClass);
    }

    public boolean isShowIcon() {
        return (Boolean)this.getStateHelper().eval(Messages.PropertyKeys.showIcon, true);
    }

    public void setShowIcon(boolean showIcon) {
        this.getStateHelper().put(Messages.PropertyKeys.showIcon, showIcon);
    }

    public String getForType() {
        return (String)this.getStateHelper().eval(Messages.PropertyKeys.forType, (Object)null);
    }

    public void setForType(String forType) {
        this.getStateHelper().put(Messages.PropertyKeys.forType, forType);
    }

    public String getSingleInfoTitle() {
        return (String)this.getStateHelper().eval(CustomMessages.PropertyKeys.singleInfoTitle, (Object)null);
    }

    public void setSingleInfoTitle(String singleInfoTitle) {
        this.getStateHelper().put(CustomMessages.PropertyKeys.singleInfoTitle, singleInfoTitle);
    }

    public String getSingleWarnTitle() {
        return (String)this.getStateHelper().eval(CustomMessages.PropertyKeys.singleWarnTitle, (Object)null);
    }

    public void setSingleWarnTitle(String singleWarnTitle) {
        this.getStateHelper().put(CustomMessages.PropertyKeys.singleWarnTitle, singleWarnTitle);
    }

    public String getSingleErrorTitle() {
        return (String)this.getStateHelper().eval(CustomMessages.PropertyKeys.singleErrorTitle, (Object)null);
    }

    public void setSingleErrorTitle(String singleErrorTitle) {
        this.getStateHelper().put(CustomMessages.PropertyKeys.singleErrorTitle, singleErrorTitle);
    }

    public String getMultiInfoTitle() {
        return (String)this.getStateHelper().eval(CustomMessages.PropertyKeys.multiInfoTitle, (Object)null);
    }

    public void setMultiInfoTitle(String multiInfoTitle) {
        this.getStateHelper().put(CustomMessages.PropertyKeys.multiInfoTitle, multiInfoTitle);
    }

    public String getMultiWarnTitle() {
        return (String)this.getStateHelper().eval(CustomMessages.PropertyKeys.multiWarnTitle, (Object)null);
    }

    public void setMultiWarnTitle(String _multiWarnTitle) {
        this.getStateHelper().put(CustomMessages.PropertyKeys.multiWarnTitle, _multiWarnTitle);
    }

    public String getMultiErrorTitle() {
        return (String)this.getStateHelper().eval(CustomMessages.PropertyKeys.multiErrorTitle, (Object)null);
    }

    public void setMultiErrorTitle(String multiErrorTitle) {
        this.getStateHelper().put(CustomMessages.PropertyKeys.multiErrorTitle, multiErrorTitle);
    }

    public enum PropertyKeys {
        autoUpdate,
        escape,
        severity,
        closable,
        style,
        styleClass,
        showIcon,
        forType,
        singleInfoTitle,
        singleWarnTitle,
        singleErrorTitle,
        multiInfoTitle,
        multiWarnTitle,
        multiErrorTitle;

        String toString;

        private PropertyKeys(String toString) {
            this.toString = toString;
        }

        private PropertyKeys() {
        }

        public String toString() {
            return this.toString != null ? this.toString : super.toString();
        }
    }

    public String getFamily(){
        return "uy.gub.imm.sae.web.component";
    }


}
