package org.projekat.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.projekat.model.IshodIspita;

import java.util.List;

@XmlRootElement(name = "ishodi")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class IshodIspitaList {
    private List<IshodIspita> ishodi;
    @XmlElement(name="ishod")
    public List<IshodIspita> getIshodi(){
        return ishodi;
    }
    public void setIshodi(List<IshodIspita> ishodi){
        this.ishodi = ishodi;
    }
}
