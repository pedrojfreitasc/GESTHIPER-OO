
import java.io.Serializable;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pedro Cunha
 */
public class NodoProduto implements Serializable{
    private String codigoP;
    private int nVezesComprado;
    private int qtdComprada;
    private int compradoMes[];
    private HashMap<String,NodoClienteComprador> clientesComp;
    
    public NodoProduto(){
        this.codigoP="";
        this.compradoMes=new int[12];
        this.nVezesComprado=0;
        this.qtdComprada=0;
        this.clientesComp=new HashMap<>();
    }
    
    public NodoProduto(String codigoP){
        this.codigoP=codigoP;
        this.clientesComp=new HashMap<>();
        this.compradoMes=new int[12];
        this.qtdComprada=0;
        this.nVezesComprado=0;
        
    }
    
    public NodoProduto(NodoProduto other){
        this.clientesComp=other.getClientesComp();
        this.codigoP=other.getCodigoP();
        this.compradoMes=other.getCompradoMes();
        this.nVezesComprado=other.getnVezesComprado();
        this.qtdComprada=other.getQtdComprada();
    }

    public HashMap<String, NodoClienteComprador> getClientesComp() {
        HashMap<String, NodoClienteComprador> res=new HashMap<>();
        for(NodoClienteComprador ncc:this.clientesComp.values())
            res.put(ncc.getCodigoC(), ncc.clone());
        return res;
    }

    public String getCodigoP() {
        return this.codigoP;
    }

    public int[] getCompradoMes() {
        return this.compradoMes;
    }

    public int getQtdComprada() {
        return this.qtdComprada;
    }

    public int getnVezesComprado() {
        return this.nVezesComprado;
    }

    public void setClientesComp(HashMap<String, NodoClienteComprador> clientesComp) {
        this.clientesComp = new HashMap<>();
        for(NodoClienteComprador ncc: clientesComp.values())
            this.clientesComp.put(ncc.getCodigoC(), ncc.clone());
    }

    public void setCodigoP(String codigoP) {
        this.codigoP = codigoP;
    }

    public void setCompradoMes(int[] compradoMes) {
        this.compradoMes = compradoMes;
    }

    public void setQtdComprada(int qtdComprada) {
        this.qtdComprada = qtdComprada;
    }

    public void setnVezesComprado(int nVezesComprado) {
        this.nVezesComprado = nVezesComprado;
    }
    
}