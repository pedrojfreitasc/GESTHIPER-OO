
import java.io.Serializable;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pedro Cunha
 */
public class Hipermercado implements Serializable {

    private ICatalog clientes;
    private ICatalog produtos;
    private IContabilidade contabilidade;
    private IComprasDB compras;

    public Hipermercado() {
        this.clientes = new Catalog();
        this.produtos = new Catalog();
        this.contabilidade = new Contabilidade();
        this.compras = new ComprasDB();
    }

    public ICatalog getClientes() {
        return this.clientes.clone();
    }

    public ICatalog getProdutos() {
        return this.produtos.clone();
    }

    public IContabilidade getContabilidade() {
        return this.contabilidade.clone();
    }

    public IComprasDB getCompras() {
        return this.compras.clone();
    }

    public void setClientes(ICatalog icata) {
        this.clientes = icata.clone();
    }

    public void setProdutos(ICatalog icata) {
        this.produtos = icata.clone();
    }

    public void setContabilidade(IContabilidade icont) {
        this.contabilidade = icont.clone();
    }

    public void setCompras(IComprasDB icomp) {
        this.compras = icomp.clone();
    }

    public void insertCliente(String codigoC) {
        this.clientes.insertCode(codigoC);
        this.compras.insertCodigoCliente(codigoC);
    }

    public void insertProduto(String codigoP) {
        this.produtos.insertCode(codigoP);
        this.contabilidade.insertCode(codigoP);
        this.compras.insertCodigoProduto(codigoP);
    }

    public void registerSale(Compra c) {
        this.contabilidade.registerSale(c);
        this.compras.registerSale(c);
    }

    public ArrayList<String> getProdutosNuncaComprados() {
        return this.contabilidade.getNuncaComprados();
    }

    public ArrayList<String> getClientesNaoCompradores() {
        return this.compras.clientesNaoCompradores();
    }

    public ParNComprasNClientes getTotNComprasNClientes(int mes) throws InvalidMonthException {
        return this.compras.getTotCompTotCli(mes);
    }

    public Table getTableCliente(String codigoC) throws UnexistentCodeException {
        return this.compras.getTableCliente(codigoC);
    }

    public Table getTableProduto(String codigoC) throws UnexistentCodeException {
        return this.compras.getTableProduto(codigoC);
    }

    public VendasProdutoMensais getVendasMensais(String codigoP) throws UnexistentCodeException {
        return this.contabilidade.getVendasMensais(codigoP);
    }

    public ArrayList<ParCodigoQuantidade> getTopCompras(String codigoC) throws UnexistentCodeException {
        return this.compras.getTopCompras(codigoC);
    }
    
    public ArrayList<TrioCodQuantNClientes> getTopComprados(int nElementos){
        return this.compras.getTopComprados(nElementos);
    }
    
    public ArrayList<ParCodigoQuantidade> getClientesMaisProdutosDistintos(int nElementos){
        return this.compras.getClientesMaisProdutosDistintos(nElementos);
    }
    
    public ArrayList<TrioCodQuantFat> getTopCompradores(String codigoP) throws UnexistentCodeException{
        return this.compras.getTopCompradores(codigoP);
    }

    public boolean compraValida(Compra c) {
        if (this.clientes.codeExists(c.getCodigoCli()) && this.produtos.codeExists(c.getCodigoProd())) {
            return true;
        } else {
            return false;
        }
    }
}
