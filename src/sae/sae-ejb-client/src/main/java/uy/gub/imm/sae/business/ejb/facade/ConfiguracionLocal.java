package uy.gub.imm.sae.business.ejb.facade;

import java.util.List;
import javax.ejb.Local;

@Local
public interface ConfiguracionLocal {

    public String getString(String clave,Integer organismoId);

    public Boolean getBoolean(String clave,Integer organismoId);

    public Long getLong(String clave,Integer organismoId);

    public List<uy.gub.imm.sae.entity.global.Configuracion> getAll();
    
    public List<uy.gub.imm.sae.entity.global.Configuracion> obtenerConfiguracionesPorOrganismo(Integer organismoId);

    public void guardar(uy.gub.imm.sae.entity.global.Configuracion conf);
}
