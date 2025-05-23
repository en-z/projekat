package org.projekat.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.projekat.model.IshodIspita;

import java.util.List;

@XmlRootElement(name = "ishodi")
public class IshodIspitaList {
    private List<IshodIspita> ishodi;
    @XmlElement(name="ishodi")
    public List<IshodIspita> getIshodi(){
        return ishodi;
    }
    public void setIshodi(List<IshodIspita> ishodi){
        this.ishodi = ishodi;
    }
}
