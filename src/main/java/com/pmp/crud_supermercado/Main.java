/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pmp.crud_supermercado;

import java.util.Scanner;
import java.util.ArrayList;

import com.pmp.dao.Producto_s;
import com.pmp.dao.ProductosModel;
/**
 *
 * @author FernandaGonzalez
 */
public class Main {
  private static Scanner entradaTeclado = new Scanner(System.in);
  private static ProductosModel model =new ProductosModel();
  public static void main (String[] args)
  {
      UIUtilidades_s.encabezado("Supermercado");
      UIUtilidades_s.separador();
      UIUtilidades_s.print("Gestion de Productos");
      String menuOption = "L";
      while (!menuOption.contentEquals("S"))
      {
          switch (menuOption)
          {
                case "L":
                  mostrarListado();
                  break;
                case "I":
                  insertarUnProducto();
                  break;  
                case "A":
                  updateUnProducto();
                  break;
                case "E":
                  EliminarProducto();
                  break;
                  
                default:
                  UIUtilidades_s.print("Opcion no encontrada!!");
          }
          UIUtilidades_s.menu();
          menuOption = entradaTeclado.nextLine().toUpperCase();
      }
  }
  private static void mostrarListado(){
      UIUtilidades_s.printEncabezadoTabla();
      ArrayList<Producto_s> productos = model.obtenerProductos();
      for (int i=0; i<productos.size(); i++){
          UIUtilidades_s.print(productos.get(i).getRow());
          UIUtilidades_s.separador();
      }
  }
  private static void insertarUnProducto()
  {
      Producto_s nuevoProducto = new Producto_s();
      UIUtilidades_s.encabezado("Nuevo Producto");
      nuevoProducto.setNombre(UIUtilidades_s.capturarCampo(entradaTeclado,"Nombre", "Producto XYZ"));
      nuevoProducto.setPrecio(Double.parseDouble( UIUtilidades_s.capturarCampo(entradaTeclado,"Precio", "20.00")));
      nuevoProducto.setCantidad(Integer.parseInt( UIUtilidades_s.capturarCampo(entradaTeclado,"Cantidad", "5")));
      nuevoProducto.setObservacion(UIUtilidades_s.capturarCampo(entradaTeclado,"Observacion", ""));
      UIUtilidades_s.separador();
      
      int insertado = model.agregarProducto(nuevoProducto);
      
      if (insertado > 0)
      {
          UIUtilidades_s.print("Se agrego el producto satisfactoriamente");
      }
      UIUtilidades_s.separador();
  }
  
   private static void updateUnProducto()  //entrada de texto
    {
        Producto_s updateProducto = new Producto_s();
        int setCod;
        UIUtilidades_s.encabezado("Actualizar Producto");
        updateProducto.setCod(Integer.parseInt(UIUtilidades_s.capturarCampo(entradaTeclado,"Cod", "")));
        updateProducto = model.obtenerProducto(updateProducto.getCod());
        updateProducto.setNombre(UIUtilidades_s.capturarCampo(entradaTeclado,"Nombre", updateProducto.getNombre() ));
        updateProducto.setPrecio(Double.parseDouble(UIUtilidades_s.capturarCampo(entradaTeclado,"Precio", Double.toString(updateProducto.getPrecio()))));
        updateProducto.setCantidad(Integer.parseInt(UIUtilidades_s.capturarCampo(entradaTeclado,"Cantidad", Integer.toString(updateProducto.getCantidad()))));
        updateProducto.setObservacion(UIUtilidades_s.capturarCampo(entradaTeclado,"Observacion", updateProducto.getObservacion()));

        int actualizado = model.actualizarProducto(updateProducto);
        if(actualizado > 0)
        {
            UIUtilidades_s.print("Producto Agregado Satisfactoriamente");   
        }
        UIUtilidades_s.separador();
    }
     
     private static void EliminarProducto(){
        Producto_s deleteProducto=new Producto_s();
        UIUtilidades_s.encabezado("Eliminar Producto");
        deleteProducto.setCod(Integer.parseInt(UIUtilidades_s.capturarCampo(entradaTeclado,"cod","")));

        UIUtilidades_s.separador();
        int eliminado=model.EliminarProducto(deleteProducto);
        if (eliminado>0){
            UIUtilidades_s.print("Producto Eliminado Satisfactoriamente");
        }
        UIUtilidades_s.separador();
    }
    

}
