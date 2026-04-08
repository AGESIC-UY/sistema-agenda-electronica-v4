package uy.gub.imm.sae.web.component;

import org.primefaces.component.inputtext.InputText;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;

@FacesComponent(value = "uy.gub.imm.sae.web.component.InputDate", createTag = true,
        namespace =  "http://uy.gub.imm.sae.web/", tagName = "inputDate")
@ResourceDependencies({
        @ResourceDependency(library="primefaces", name="primefaces.css"),
        @ResourceDependency(library="primefaces", name="jquery/jquery.js"),
        @ResourceDependency(library="primefaces", name="primefaces.js")}
)
public class InputDate extends InputText {

    public InputDate(){
        this.setType("date");
    }

}
