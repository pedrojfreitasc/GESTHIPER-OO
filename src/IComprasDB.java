/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pedro Cunha
 */
public interface IComprasDB {
    public void insertCodigoCliente(String codigoC);
    public void insertCodigoProduto(String codigoP);
    public void registerSale(Compra c);
}