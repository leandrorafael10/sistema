/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.rn;

import com.green.dao.PessoaDao;
import com.green.dao.UsuarioDao;
import com.green.modelo.Usuario;
import com.green.util.ContextoBean;
import com.green.util.ContextoUtil;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/**
 *
 * @author leandro.silva
 */
@Service("usuarioRn")
public class UsuarioRn {

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private GrupoAcessoRN acessoRN;
    @Autowired
    private PessoaDao pessoaDao;

    public Usuario carregarPeloLogin(String usuario) {
        Usuario u = getUsuarioDao().carregarPeloLogin(usuario);
        return u;
    }

    @Transactional("tmGreen")
    public Boolean salvar(Usuario usuario) {

        ContextoBean contextoBean = ContextoUtil.getContextoBean();
       
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")) {
            Usuario u = this.usuarioDao.carregarPeloLogin(usuario.getLogin());
            Integer num = this.usuarioDao.carregarPeloFuncionario(usuario.getIDFuncionario());
            if (u != null) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Falha!", "Login já cadastrado "));
                return Boolean.FALSE;
            } else if (num == 1) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Falha!", "Funcionario já possui acesso "));
                return Boolean.FALSE;
            } else {
                usuario.setIDUsuarioInc(contextoBean.getUsuarioLogado().getIDUsuario());
                usuario.setDTInc(new Date(System.currentTimeMillis()));
                usuario.setAtivo(Boolean.TRUE);
                String senha = usuario.getSenha();
                usuario.setSenha(senhaCripto(senha));
                usuarioDao.salvar(usuario);
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ok, sucesso!", "Salvo com sucesso "));
                return Boolean.TRUE;
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Falha!Usuario não autorizado.", "Usuario não autorizado."));
            return Boolean.FALSE;
        }
        
    }
    @Transactional("tmGreen")
    public void atualizarSenhas(Usuario usuario,String senhaAtual){
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        FacesContext context = FacesContext.getCurrentInstance();
        senhaAtual = senhaCripto(senhaAtual);
       if (senhaAtual.equals(contextoBean.getUsuarioLogado().getSenha())){
            contextoBean.getUsuarioLogado().setSenha(senhaCripto(usuario.getSenha()));
            getUsuarioDao().atualizar(contextoBean.getUsuarioLogado());
            
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK! Atualizado com sucesso.", "Atualizado com sucesso!"));
       }else{
           context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Falha! Senha atual não confere.","Senha atual não confere."));
       }
    }
    @Transactional("tmGreen")
    public boolean atualizarSenhasExterno(Usuario usuario,String senhaAtual){
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        senhaAtual = senhaCripto(senhaAtual);
       if (senhaAtual.equals(contextoBean.getUsuarioLogado().getSenha())){
            contextoBean.getUsuarioLogado().setSenha(senhaCripto(usuario.getSenha()));
            getUsuarioDao().atualizar(contextoBean.getUsuarioLogado());
            
            return true;
       }else{
           return false;
       }
    }

    @Transactional("tmGreen")
    public void atualizar(Usuario usuario) {
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        if(!usuario.getLogin().equals("master")){
        if (contextoBean.getUsuarioLogado().getIDGrupoAcesso().getDescricao().equals("ROLE_ADMINISTRACAO")) {

            FacesContext context = FacesContext.getCurrentInstance();
            usuario.setDTAlt(new Date(System.currentTimeMillis()));
            usuario.setIDUsuarioAlt(contextoBean.getUsuarioLogado().getIDUsuario());
            getUsuarioDao().atualizar(usuario);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK!", "Atualizado com sucesso!"));
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Falha!", "Usuario sem Autorização a essa ação!"));
        }
        }else{
            
            usuario.setIDGrupoAcesso(acessoRN.carregar(1));
            usuario.setAtivo(true);
            FacesContext contextErro = FacesContext.getCurrentInstance();
            contextErro.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Falha,Usuario não pode ser alterado!", "Usuario não pode ser alterado!"));
        }
            
    }
     @Transactional("tmGreen")
    public void atualizarDados(Usuario usuario){
         ContextoBean contextoBean = ContextoUtil.getContextoBean();
         FacesContext context = FacesContext.getCurrentInstance();
            usuario.setDTAlt(new Date(System.currentTimeMillis()));
            usuario.setIDUsuarioAlt(contextoBean.getUsuarioLogado().getIDUsuario());
            getPessoaDao().atualizar(usuario.getIDFuncionario().getIDPessoa());
            getUsuarioDao().atualizar(usuario);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK!", "Atualizado com sucesso!"));
     }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public List<Usuario> listar() {
        return getUsuarioDao().listar();
    }

    //metodo de descriptografiar senha
    public String senhaCripto(String senha) {
        return DigestUtils.md5DigestAsHex(senha.getBytes());
    }

    public PessoaDao getPessoaDao() {
        return pessoaDao;
    }

    public void setPessoaDao(PessoaDao pessoaDao) {
        this.pessoaDao = pessoaDao;
    }
    
}
