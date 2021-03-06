/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CaptureEvent;

import com.green.modelo.Contato;
import com.green.modelo.Funcao;
import com.green.modelo.Funcionario;
import com.green.modelo.HistoricoDemicao;
import com.green.modelo.Pessoa;
import com.green.rn.ContatoRN;
import com.green.rn.FuncaoRN;
import com.green.rn.FuncionarioRN;
import com.green.rn.PessoaRN;
import com.green.util.CepWebService;

/**
 *
 * @author leandro.silva
 */
@ManagedBean(name = "funcionarioBean")
@ViewScoped
public class FuncionarioBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{funcionarioRN}")
    private FuncionarioRN funcionarioRN;
    @ManagedProperty("#{contatoRN}")
    private ContatoRN contatoRN;
    @ManagedProperty("#{pessoaRN}")
    private PessoaRN pessoaRN;
    private Contato contato = new Contato();
    private Funcionario funcionario;
    private Funcionario funcionarioEditado;
    private HistoricoDemicao historicoDemicao;
    private List<Funcionario> funcionarios;
    private List<Funcionario> funcionarioFiltro;
    private List<Funcionario> gerentes;
    private List<Funcionario> vendedores;
    private Contato contatoEditado;
    private String telefoneF ;
    private String telefoneCel;
    private String foto;
    private byte[] data;
    private String newFileName = new String();
    private Date novaAdimicao;
    

    @PostConstruct
    private void init() {
        this.contatoEditado = new Contato();
        this.funcionario = new Funcionario();
        this.funcionarioEditado = new Funcionario();
        this.historicoDemicao = new HistoricoDemicao();
        setFuncionarios(getFuncionarioRN().listar());
        this.funcionario.setIDFuncao(new Funcao());
        this.funcionario.setIDPessoa(new Pessoa());
        this.telefoneF = new String();
        this.telefoneCel = new String();
        this.foto = new String();
        
    }

    

   

    public void listarPorFuncao() {
        setFuncionarios(getFuncionarioRN().listarPorFuncao(new FuncaoRN().carregar(1)));
    }
    
    public void buscaPorCep(){
        CepWebService cepWebService = new CepWebService(getFuncionario().getIDPessoa().getCep());
        getFuncionario().getIDPessoa().setLogradouro(cepWebService.getTipo_logradouro() +" "+cepWebService.getLogradouro());
        getFuncionario().getIDPessoa().setCidade(cepWebService.getCidade());
        getFuncionario().getIDPessoa().setEstado(cepWebService.getEstado());
        getFuncionario().getIDPessoa().setBairro(cepWebService.getBairro());
    }

    public void oncapture(CaptureEvent captureEvent) {
        this.data = captureEvent.getData();
        Date nome = new Date();
        setFoto(String.valueOf(nome.getTime()));

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        this.newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "imagens" + File.separator + getFoto() + ".png";

        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(this.data, 0, this.data.length);
            imageOutput.close();
        } catch (Exception e) {
            throw new FacesException("Erro na captura da imagem!");
        }
    }

    public void salvaFoto(ActionEvent event) {
        getFuncionario().setImagem(getData());
        File destino = new File(newFileName);
        try {
            destino.delete();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar imagem", e);
        }
    }

    public void cancelarFoto(ActionEvent event) {
        getFuncionario().setImagem(null);
        if (!newFileName.equals("")) {
            File destino = new File(newFileName);
            try {
                destino.delete();
                setFoto("");
            } catch (Exception e) {
                throw new RuntimeException("Erro ao deletar imagem", e);
            }
        }

    }
    public void cancelarFotoPesquisa(ActionEvent event) {
        if (!newFileName.equals("")) {
            File destino = new File(newFileName);
            try {
                destino.delete();
                setFoto("");
            } catch (Exception e) {
                throw new RuntimeException("Erro ao deletar imagem", e);
            }
        }

    }

    public void pesquisaFuncionario(Funcionario f) {
        setFuncionario(f);
        if (f.getImagem() != null) {
            this.data = f.getImagem();
            Date nome = new Date();
            setFoto(String.valueOf(nome.getTime()));

            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            this.newFileName = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "imagens" + File.separator + getFoto() + ".png";

            FileImageOutputStream imageOutput;
            try {
                imageOutput = new FileImageOutputStream(new File(newFileName));
                imageOutput.write(this.data, 0, this.data.length);
                imageOutput.close();


                
            } catch (Exception e) {
                throw new FacesException("Erro na captura da imagem!");
            }
        } else {
            
        }

    }

    public void salvarFuncionario(ActionEvent actionEvent) {
        RequestContext rc = RequestContext.getCurrentInstance();

        if (!buscaCpfCnpj(getFuncionario().getIDPessoa().getCnpjCpf())) {
            rc.execute("PF('dialogError').show()");
        } else {
            formatatel(getTelefoneF(), getTelefoneCel());
            getFuncionarioRN().Salvar(getFuncionario(), getContato());
            this.funcionario = new Funcionario();
            addMessage("Salvo com sucesso!");
            init();
        }

    }

    public void atualizar() {
        formatatel(getTelefoneF(), getTelefoneCel());
        getFuncionarioRN().atualizar(getFuncionarioEditado(), getContato());
    }

    public void demitirFuncionario(Funcionario f) {
        getFuncionarioRN().demitirFuncionario(f);
    }
    public void readimitirFuncionario(){
        getFuncionarioRN().readimitirFuncionario(getHistoricoDemicao(),getNovaAdimicao());
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, "Ok");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public boolean buscaCpfCnpj(String cpf) {
        Pessoa p;
        p = getPessoaRN().buscaCpfCnpj(cpf);
        if (p != null) {
            return false;

        } else {
            return true;
        }
    }

    public void formatatel(String fixo, String celular) {
        if (fixo != null && !fixo.equals("")) {
            getContato().setDddf(fixo.substring(1, 3));
            getContato().setTelefoneF(fixo.substring(4, 8) + fixo.substring(9));
        }
        if (celular != null && !celular.equals("")) {
            getContato().setDDDCel(celular.substring(1, 3));
            getContato().setTelefoneCel(celular.substring(4, 8) + celular.substring(9));
        }

    }

    public PessoaRN getPessoaRN() {
        return pessoaRN;
    }

    public void setPessoaRN(PessoaRN pessoaRN) {
        this.pessoaRN = pessoaRN;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {

        this.funcionario = funcionario;
    }

    public FuncionarioRN getFuncionarioRN() {
        return funcionarioRN;
    }

    public void setFuncionarioRN(FuncionarioRN funcionarioRN) {
        this.funcionarioRN = funcionarioRN;
    }


    public Funcionario getFuncionarioEditado() {
        return funcionarioEditado;
    }

    public void setFuncionarioEditado(Funcionario funcionarioEditado) {
        Contato c;

        setTelefoneCel("");
        setTelefoneF("");

        c = getContatoRN().buscaPorContato(funcionarioEditado.getIDPessoa());
        if (c != null) {
            setContato(c);
            setTelefoneCel(c.getDDDCel() + c.getTelefoneCel());
            setTelefoneF(c.getDddf() + c.getTelefoneF());
        }
        this.funcionarioEditado = funcionarioEditado;
    }


    public Contato getContatoEditado() {
        return contatoEditado;
    }

    public void setContatoEditado(Contato contatoEditado) {
        this.contatoEditado = contatoEditado;
    }

    public String getTelefoneCel() {
        return telefoneCel;
    }

    public void setTelefoneCel(String telefoneCel) {
        this.telefoneCel = telefoneCel;
    }

    public String getTelefoneF() {
        return telefoneF;
    }

    public void setTelefoneF(String telefoneF) {
        this.telefoneF = telefoneF;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Funcionario> getVendedores() {
        return vendedores;
    }

    public List<Funcionario> getFuncionarioFiltro() {
        return funcionarioFiltro;
    }

    public void setFuncionarioFiltro(List<Funcionario> funcionarioFiltro) {
        this.funcionarioFiltro = funcionarioFiltro;
    }

    public void setVendedores(List<Funcionario> vendedores) {
        this.vendedores = vendedores;
    }

    public List<Funcionario> getGerentes() {
        return gerentes;
    }

    public void setGerentes(List<Funcionario> gerentes) {
        this.gerentes = gerentes;
    }

    public ContatoRN getContatoRN() {
        return contatoRN;
    }

    public void setContatoRN(ContatoRN contatoRN) {
        this.contatoRN = contatoRN;
    }

    public HistoricoDemicao getHistoricoDemicao() {
        return historicoDemicao;
    }

    public void setHistoricoDemicao(HistoricoDemicao historicoDemicao) {
        this.historicoDemicao = historicoDemicao;
    }

    public Date getNovaAdimicao() {
        return novaAdimicao;
    }

    public void setNovaAdimicao(Date novaAdimicao) {
        this.novaAdimicao = novaAdimicao;
    }
    
}
