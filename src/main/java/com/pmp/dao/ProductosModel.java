/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmp.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.util.ArrayList;


/**
 *
 * @author FernandaGonzalez
 */
public class ProductosModel {
   private Connection _conexion =null;  
   
   public ProductosModel(){
       _conexion = Conexion.getConexion();
      String sqlCreateTable= "CREATE TABLE IF NOT EXISTS productos"
               + " (cod INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
               + " nombre TEXT NOT NULL,"
               + " precio DECIMAL(10,2),"
               + " cantidad NUMERIC,"
               + " observacion TEXT);";
      
      try{
          Statement comando = _conexion.createStatement();
          int resultado = comando.executeUpdate(sqlCreateTable);
      }
      catch(Exception ex)
      {
          System.err.println(ex.getMessage());
      }
   }
   
   public ArrayList<Producto_s> obtenerProductos()
   {
       try{
       ArrayList productos = new ArrayList<Producto_s>();
       Statement comandoSQL = _conexion.createStatement();
       ResultSet registroEnTabla = comandoSQL.executeQuery("SELECT * FROM productos;");
       
      while (registroEnTabla.next())
        {
            Producto_s productoActual = new Producto_s();
            productoActual.setCod(registroEnTabla.getInt("cod"));
            productoActual.setNombre(registroEnTabla.getString("nombre"));
            productoActual.setObservacion(registroEnTabla.getString("observacion"));
            productoActual.setCantidad(registroEnTabla.getInt("cantidad"));
            productoActual.setPrecio(registroEnTabla.getDouble("precio"));
              
              productos.add(productoActual);
        }
        return productos;
       }
       catch(Exception ex){
           System.err.println(ex.getMessage());
           return new ArrayList <Producto_s>();
       }
   }
   
    public Producto_s obtenerProducto(int cod)
    {
        try{
         
        PreparedStatement comandoSQL = _conexion.prepareStatement("SELECT * FROM productos where cod=?;");
        comandoSQL.setInt(1,cod);
        ResultSet registroEnTabla = comandoSQL.executeQuery();
        Producto_s productoActual = new Producto_s();
        while (registroEnTabla.next())
        {
           
            productoActual.setCod(registroEnTabla.getInt("cod"));
            productoActual.setNombre(registroEnTabla.getString("nombre"));
            productoActual.setObservacion(registroEnTabla.getString("observacion"));
            productoActual.setCantidad(registroEnTabla.getInt("cantidad"));
            productoActual.setPrecio(registroEnTabla.getDouble("precio"));
             break; 
             
        }
         return productoActual;
            
        }
        catch (Exception ex)
        {
           System.err.println(ex.getMessage());
           return null;            
        }
        
    }
   
   
    public int agregarProducto(Producto_s nuevoProducto){
        
        try
        {
            String insertSql = "INSERT INTO productos(nombre, observacion, cantidad, precio) values (?, ?, ?, ?);";
            PreparedStatement comandoSQL = _conexion.prepareStatement(insertSql);
            comandoSQL.setString(1, nuevoProducto.getNombre());
            comandoSQL.setString(2, nuevoProducto.getObservacion());
            comandoSQL.setInt (3, nuevoProducto.getCantidad());
            comandoSQL.setDouble (4, nuevoProducto.getPrecio());
            
            int RegistroAfectados = comandoSQL.executeUpdate(); 
            comandoSQL.close(); 
            return RegistroAfectados;
        }   
        catch (Exception ex)
        {
           System.err.println(ex.getMessage());
           return 0;         
        }
    }
    
    public int actualizarProducto(Producto_s updateProducto)
    {
        try
        {
            String updateSql = "UPDATE productos SET nombre=?,observacion=?,cantidad=?,precio=? where cod=?"; 
            PreparedStatement comandoSQL = _conexion.prepareStatement(updateSql);
            comandoSQL.setString(1, updateProducto.getNombre()); 
            comandoSQL.setString(2, updateProducto.getObservacion());
            comandoSQL.setInt (3, updateProducto.getCantidad());
            comandoSQL.setDouble (4, updateProducto.getPrecio());
            comandoSQL.setInt(5, updateProducto.getCod());
            
            int RegistroAfectado = comandoSQL.executeUpdate(); 
            comandoSQL.close(); 
            return RegistroAfectado;
        }   
        catch (Exception ex)
        {
           System.err.println(ex.getMessage());
           return 0;         
        }
    }
    
    public int EliminarProducto(Producto_s deleteProducto)
    {
        try
        {
            String deleteSql = "DELETE FROM productos where cod=?"; 
            PreparedStatement comandoSQL = _conexion.prepareStatement(deleteSql);
            comandoSQL.setInt(1, deleteProducto.getCod());
            
            int RegistroAfectado = comandoSQL.executeUpdate(); 
            comandoSQL.close(); 
            return RegistroAfectado; 
        }   
        catch (Exception ex)
        {
           System.err.println(ex.getMessage());
           return 0;         
        }
    }
   
}
