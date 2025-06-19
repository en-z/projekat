package com.projekat.nastavnik_service.wrapper;


import com.projekat.nastavnik_service.entity.IshodIspita;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

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
