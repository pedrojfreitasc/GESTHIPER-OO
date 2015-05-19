
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
public class Contabilidade implements IContabilidade, Serializable {

    private HashMap<String, NodoContabilidade> contabilidade;

    public Contabilidade() {
        this.contabilidade = new HashMap<>();
    }

    public Contabilidade(Contabilidade other) {
        this.contabilidade = other.getContabilidade();
    }

    public void setContabilidade(HashMap<String, NodoContabilidade> cont) {
        this.contabilidade = new HashMap<>();
        for (NodoContabilidade nc : cont.values()) {
            this.contabilidade.put(nc.getCodigo(), nc.clone());
        }

    }

    public HashMap<String, NodoContabilidade> getContabilidade() {
        HashMap<String, NodoContabilidade> res = new HashMap<>();
        for (NodoContabilidade nc : this.contabilidade.values()) {
            res.put(nc.getCodigo(), nc.clone());
        }
        return res;
    }

    @Override
    public void insertCode(String code) {
        this.contabilidade.put(code, new NodoContabilidade(code));
    }

    @Override
    public void registerSale(Compra c) {
        this.contabilidade.get(c.getCodigoProd()).incrementaFaturacao(c.getModo(), c.getMes(), c.getQuantidade(), c.getValorUni());
        this.contabilidade.get(c.getCodigoProd()).incrementaNVendas(c.getModo(), c.getMes());
        this.contabilidade.get(c.getCodigoProd()).incrementaQtdComprada(c.getModo(), c.getMes(), c.getQuantidade());
    }

    @Override
    public IContabilidade clone() {
        return new Contabilidade(this);
    }

}