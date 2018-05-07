/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seedhost.fodupa.controller;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import com.seedhost.fodupa.model.Carrera;

/**
 *
 * @author fergch97
 */
@FacesConverter(value = "convertidorCarrera")
public class ConvertidorCarrera implements Converter {
    
    @Override
    public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String carreraId) {
        ValueExpression vex =
                ctx.getApplication().getExpressionFactory()
                        .createValueExpression(ctx.getELContext(),
                                "#{registraController}", RegistraController.class);

        RegistraController registra = (RegistraController)vex.getValue(ctx.getELContext());
        return registra.getCarrera(Integer.valueOf(carreraId));
    }
    
    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object carrera) {
        return ((Carrera)carrera).getId().toString();
    }

}
