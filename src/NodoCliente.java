
import java.io.Serializable;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.Map;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pedro Cunha
 */
public class NodoCliente implements Serializable {

    private String codigoC;
    private int compraMes[];
    private int nCompras;
    private Map<String, NodoProdutoComprado> prodComprados;

    public NodoCliente(String codigoC, int[] compraMes, int nCompras) {
        this.codigoC = codigoC;
        this.compraMes = compraMes;
        this.nCompras = nCompras;
        this.prodComprados = new TreeMap<>();
    }

    public NodoCliente(String codigoC) {
        this.codigoC = codigoC;
        this.compraMes = new int[12];
        this.nCompras = 0;
        this.prodComprados = new TreeMap<>();
    }

    public NodoCliente(NodoCliente other) {
        this.codigoC = other.getCodigoC();
        this.compraMes = other.getCompraMes();
        this.nCompras = other.getnCompras();
        this.prodComprados = other.getProdComprados();
    }

    public String getCodigoC() {
        return this.codigoC;
    }

    public Map<String, NodoProdutoComprado> getProdComprados() {
        TreeMap<String, NodoProdutoComprado> res = new TreeMap<>();
        for (NodoProdutoComprado npc : this.prodComprados.values()) {
            res.put(npc.getCodigoP(), npc.clone());
        }
        return res;
    }

    public int[] getCompraMes() {
        return this.compraMes.clone();
    }

    public int getnCompras() {
        return this.nCompras;
    }

    public void setCodigoC(String codigoC) {
        this.codigoC = codigoC;
    }

    public void setCompraMes(int[] compraMes) {
        this.compraMes = compraMes.clone();
    }

    public void setnCompras(int nCompras) {
        this.nCompras = nCompras;
    }

    public void setProdComprados(Map<String, NodoProdutoComprado> produtosC) {
        this.prodComprados = new TreeMap<>();
        for (NodoProdutoComprado npc : produtosC.values()) {
            this.prodComprados.put(npc.getCodigoP(), npc.clone());
        }
    }

    public void registaCompra(Compra c) {
        this.compraMes[c.getMes() - 1]++;
        this.nCompras++;
        this.atualizaProdutos(c.clone());

    }
    
    public int getProdutosDistintosComprados(){
        return this.prodComprados.keySet().size();
    }

    private void atualizaProdutos(Compra c) {
        if (!(this.prodComprados.containsKey(c.getCodigoProd()))) {
            this.prodComprados.put(c.getCodigoProd(), new NodoProdutoComprado());
        }
        this.prodComprados.get(c.getCodigoProd()).incrementaQuantidade(c.getModo(), c.getMes(), c.getQuantidade());
        this.prodComprados.get(c.getCodigoProd()).incrementaValor(c.getModo(), c.getMes(), c.getValorUni(), c.getQuantidade());
    }

    public NodoCliente clone() {
        return new NodoCliente(this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        } else {
            NodoCliente e = (NodoCliente) obj;

            boolean flag = true;
            for (int i = 0; i < 12 && flag; i++) {
                if (this.compraMes[i] != e.getCompraMes(i + 1)) {
                    flag = false;
                }
            }
            return flag && (this.codigoC.equals(e.getCodigoC())) && this.prodComprados.equals(e.getProdComprados());

        }
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.codigoC,this.compraMes,this.nCompras,this.prodComprados});
    }

    public int getNProdutosComprados(int mes) {
        int counter = 0;
        for (NodoProdutoComprado ncc : this.prodComprados.values()) {
            if (ncc.getQtdCompMes('N', mes) != 0 || ncc.getQtdCompMes('P', mes) != 0) {
                counter++;
            }
        }
        return counter;
    }

    public int getCompraMes(int mes) {
        return this.compraMes[mes - 1];
    }

    public float getFaturacao(int mes) {
        float total = 0;
        for (NodoProdutoComprado ncc : this.prodComprados.values()) {
            total += ncc.getValorMes('N', mes);
            total += ncc.getValorMes('P', mes);
        }
        return total;

    }
}
