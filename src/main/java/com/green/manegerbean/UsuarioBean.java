/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.manegerbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.green.modelo.Contato;
import com.green.modelo.Funcionario;
import com.green.modelo.Grupoacesso;
import com.green.modelo.Usuario;
import com.green.rn.ContatoRN;
import com.green.rn.FuncionarioRN;
import com.green.rn.GrupoAcessoRN;
import com.green.rn.UsuarioRn;
import com.green.util.ContextoBean;
import com.green.util.ContextoUtil;

/**
 * 
 * @author leandro.silva
 */
@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManagedProperty("#{grupoAcessoRN}")
	private GrupoAcessoRN grupoAcessoRN;
	@ManagedProperty("#{funcionarioRN}")
	private FuncionarioRN funcionarioRN;
	private List<Grupoacesso> grupoacessos;
	@ManagedProperty("#{usuarioRn}")
	private UsuarioRn usuarioRn;
	@ManagedProperty("#{contatoRN}")
	private ContatoRN contatoRN;
	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> funcionarios;
	private List<Usuario> usuarios;
	private List<Contato> contatos;
	private Boolean opcaoCadastrar = Boolean.FALSE;
	private Boolean menuOpcoes = Boolean.TRUE;
	private Boolean opcaoAlterar = Boolean.FALSE;
	private Boolean opcaoExcluir = Boolean.FALSE;
	private Boolean opcaoAlterarSenha = Boolean.FALSE;
	private Boolean opcaoAlterarDados = Boolean.FALSE;
	private int opcao;
	private Usuario usuario = new Usuario();
	private String senhaAtual;

	@PostConstruct
	private void init() {
		setGrupoacessos(grupoAcessoRN.listar());
		setFuncionarios(getFuncionarioRN().listar());
		setUsuarios(getUsuarioRn().listar());
		this.usuario = new Usuario();
	}

	public void exluir() {
		setOpcaoExcluir(Boolean.TRUE);
		setOpcaoAlterar(Boolean.FALSE);
		setOpcaoCadastrar(Boolean.FALSE);
		setMenuOpcoes(Boolean.FALSE);
	}

	public void cadastrar() {
		this.usuario = new Usuario();
		setOpcaoExcluir(Boolean.FALSE);
		setOpcaoAlterar(Boolean.FALSE);
		setOpcaoCadastrar(Boolean.TRUE);
		setMenuOpcoes(Boolean.FALSE);
	}

	public void alterar() {
		this.usuario = new Usuario();
		setOpcaoExcluir(Boolean.FALSE);
		setOpcaoAlterar(Boolean.TRUE);
		setOpcaoCadastrar(Boolean.FALSE);
		setMenuOpcoes(Boolean.FALSE);

	}

	public void alterarSenha() {
		this.usuario = new Usuario();
		setOpcaoAlterarDados(Boolean.FALSE);
		setOpcaoAlterarSenha(Boolean.TRUE);
	}

	public void alterarDados() {
		this.usuario = new Usuario();
		editarUsuario();
		setOpcaoAlterarDados(Boolean.TRUE);
		setOpcaoAlterarSenha(Boolean.FALSE);
	}

	public List<Contato> getContatos() {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		setContatos(getContatoRN().contatoPessoa(
				contextoBean.getUsuarioLogado().getIDFuncionario()
						.getIDPessoa()));
		return contatos;
	}

	public void editarUsuario() {
		ContextoBean contextoBean = ContextoUtil.getContextoBean();
		setUsuario(contextoBean.getUsuarioLogado());
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public ContatoRN getContatoRN() {
		return contatoRN;
	}

	public void setContatoRN(ContatoRN contatoRN) {
		this.contatoRN = contatoRN;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public FuncionarioRN getFuncionarioRN() {
		return funcionarioRN;
	}

	public void setFuncionarioRN(FuncionarioRN funcionarioRN) {
		this.funcionarioRN = funcionarioRN;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public GrupoAcessoRN getGrupoAcessoRN() {
		return grupoAcessoRN;
	}

	public void setGrupoAcessoRN(GrupoAcessoRN grupoAcessoRN) {
		this.grupoAcessoRN = grupoAcessoRN;
	}

	public List<Grupoacesso> getGrupoacessos() {
		return grupoacessos;
	}

	public void setGrupoacessos(List<Grupoacesso> grupoacessos) {
		this.grupoacessos = grupoacessos;
	}

	public Boolean getMenuOpcoes() {
		return menuOpcoes;
	}

	public void setMenuOpcoes(Boolean menuOpcoes) {
		this.menuOpcoes = menuOpcoes;
	}

	public Boolean getOpcaoAlterar() {
		return opcaoAlterar;
	}

	public void setOpcaoAlterar(Boolean opcaoAlterar) {
		this.opcaoAlterar = opcaoAlterar;
	}

	public Boolean getOpcaoCadastrar() {
		return opcaoCadastrar;
	}

	public void setOpcaoCadastrar(Boolean opcaoCadastrar) {
		this.opcaoCadastrar = opcaoCadastrar;
	}

	public Boolean getOpcaoExcluir() {
		return opcaoExcluir;
	}

	public void setOpcaoExcluir(Boolean opcaoExcluir) {
		this.opcaoExcluir = opcaoExcluir;
	}

	public Boolean getOpcaoAlterarDados() {
		return opcaoAlterarDados;
	}

	public void setOpcaoAlterarDados(Boolean opcaoAlterarDados) {
		this.opcaoAlterarDados = opcaoAlterarDados;
	}

	public Boolean getOpcaoAlterarSenha() {
		return opcaoAlterarSenha;
	}

	public void setOpcaoAlterarSenha(Boolean opcaoAlterarSenha) {
		this.opcaoAlterarSenha = opcaoAlterarSenha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public int getOpcao() {
		return opcao;
	}

	public void setOpcao(int opcao) {
		this.opcao = opcao;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void salvar() {
		getUsuario().setIDFuncionario(getFuncionario());
		this.getUsuarioRn().salvar(getUsuario());

	}

	public void atualizar() {
		if (getOpcaoAlterarDados() == Boolean.TRUE) {
			getUsuarioRn().atualizarDados(getUsuario());
		} else {
			getUsuarioRn().atualizar(getUsuario());
		}

	}

	public void atualizarSenha() {
		getUsuarioRn().atualizarSenhas(getUsuario(), getSenhaAtual());
	}

	public void atualizarSenhaExterno() {
		if (getUsuarioRn()
				.atualizarSenhasExterno(getUsuario(), getSenhaAtual())) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Confirmado!",
							"Atualizado com sucesso!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro!",
							"Senha atual incorreta!"));
		}
		setUsuario(new Usuario());
		setSenhaAtual("");
	}

	/**
	 * @return the usuarioRn
	 */
	public UsuarioRn getUsuarioRn() {
		return usuarioRn;
	}

	/**
	 * @param usuarioRn
	 *            the usuarioRn to set
	 */
	public void setUsuarioRn(UsuarioRn usuarioRn) {
		this.usuarioRn = usuarioRn;
	}
}
