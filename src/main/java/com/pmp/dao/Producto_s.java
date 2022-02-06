/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmp.dao;

/**
 *
 * @author FernandaGonzalez
 */
public class Producto_s {

    /**
     * @return the _cod
     */
    public int getCod() {
        return _cod;
    }

    /**
     * @param _cod the _cod to set
     */
    public void setCod(int _cod) {
        this._cod = _cod;
    }

    /**
     * @return the _nombre
     */
    public String getNombre() {
        return _nombre;
    }

    /**
     * @param _nombre the _nombre to set
     */
    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    /**
     * @return the _precio
     */
    public double getPrecio() {
        return _precio;
    }

    /**
     * @param _precio the _precio to set
     */
    public void setPrecio(double _precio) {
        this._precio = _precio;
    }

    /**
     * @return the _cantidad
     */
    public int getCantidad() {
        return _cantidad;
    }

    /**
     * @param _cantidad the _cantidad to set
     */
    public void setCantidad(int _cantidad) {
        this._cantidad = _cantidad;
    }

    /**
     * @return the _observacion
     */
    public String getObservacion() {
        return _observacion;
    }

    /**
     * @param _observacion the _observacion to set
     */
    public void setObservacion(String _observacion) {
        this._observacion = _observacion;
    }
    
    public String getRow()
    {
        return String.format("%d\t%s\t%s\t%s", _cod, _nombre, _precio, _observacion);
    }
    private int _cod;
    private String _nombre;
    private double _precio;
    private int _cantidad;
    private String _observacion;
   
   
}
